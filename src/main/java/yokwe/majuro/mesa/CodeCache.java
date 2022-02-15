package yokwe.majuro.mesa;

public class CodeCache {
	private static final int PAGE_SIZE_IN_BYTE = Constants.PAGE_SIZE * 2;
	private static final int PAGE_MASK_IN_BYTE = PAGE_SIZE_IN_BYTE - 1;
	
	private static int     cb;
	private static int     pc;
	private static int     wordPC;
	private static boolean even;
	
	private static int page; // real address of page
	
	private static int lastWordPC;
	private static int lastWord;

	private static int     wordOffset;
	private static int     startPC;
	private static int     endPC;
	
	
	public static void CB(int newValue) {
		cb = newValue;
		invalidate();
	}
	public static int getCB() {
		return cb;
	}
	
	private static void invalidate() {
		startPC    = 0x10000;
		endPC      = 0;
		lastWordPC = -1;
	}

	public static void PC(int newValue) {
		// size of pc is 16 bit
		pc     = newValue & 0xFFFF;
		wordPC = pc / 2;
		even   = (pc & 1) == 0;
		
		lastWordPC = -1;
	}
	public static int PC() {
		return pc;
	}

	public static int getCodeByte() {		
		if (Perf.ENABLED) Perf.codeCacheCodeByte++;
		if (lastWordPC != wordPC) {
			// unit of pc is byte
			if (startPC <= pc && pc <= endPC) {
				if (Perf.ENABLED) Perf.codeCacheHit++;
			} else {
				if (Perf.ENABLED) Perf.codeCacheMiss++;
				setup();
			}
			lastWord = Memory.instance.readReal16(page + wordOffset + wordPC);
		}
		
		// RETURN[IF even THEN word.left ELSE word.right];
		int ret = (even ? (lastWord >>> 8) : lastWord) & 0xFF;
		
		// increment pc
		// size of pc is 16 bit
		pc     = (pc + 1) & 0xFFFF;
		wordPC = pc / 2;
		even   = !even;
		
		return ret;
	}
	public static int getCodeWord() {
		int left  = getCodeByte();
		int right = getCodeByte();
		
		return (left << 8) | right;
	}


	private static void setup() {
		// To prevent bogus PageFault, PC need to have real value
		int va = cb + wordPC;
		page = Memory.instance.fetch(va & ~Constants.PAGE_MASK);
		// unit of offsetCB is byte
		int offsetCB = (cb * 2) & PAGE_MASK_IN_BYTE;
		
		if ((pc + offsetCB) < PAGE_SIZE_IN_BYTE) {
			// Valid PC range is [startPC..endPC]
			startPC    = 0;
			endPC      = PAGE_SIZE_IN_BYTE - offsetCB - 1;
			wordOffset = offsetCB / 2;
		} else {
			// Valid PC range is [startPC..endPC]
			startPC    = ((pc + offsetCB) & ~PAGE_MASK_IN_BYTE) - offsetCB;
			endPC      = startPC + PAGE_SIZE_IN_BYTE - 1; // to end of page
			wordOffset = (-startPC) / 2;
		}
	}

	
}

package yokwe.majuro.mesa;

public final class CodeCache {
	private static int cb;
	private static int pc;

	public static int CB() {
		return cb;
	}
	public static void CB(int newValue) {
		cb = newValue;
		invalidate();
	}
	
	public static char PC() {
		return (char)pc;
	}
	public static void PC(int newValue) {
		pc = newValue & 0xFFFF;
	}
	
	private static int vaPage;
	private static int raPage;
	private static int va;
	private static int ra;
	
	private static int vaLast;
	private static int word;
	
	private static void invalidate() {
		va     = 0;
		vaLast = ~0; // make (va == vaLast) false
		vaPage = ~0; // make Memory.isSamePage(vaPage, va) false
	}
	
	public static int getCodeByte() {
		if (Perf.ENABLED) Perf.codeCacheCodeByte++;
		va = cb + (pc / 2);
		if (va == vaLast) {
			// point to same address again
			if (Perf.ENABLED) Perf.codeCacheSameLast++;
		} else {
			if (Memory.isSamePage(vaPage, va)) {
				if (Perf.ENABLED) Perf.codeCacheSamePage++;
				ra = raPage + (va & Constants.PAGE_MASK);
			} else {
				if (Perf.ENABLED) Perf.codeCacheNotSamePage++;
				ra = Memory.instance.fetch(va);
				vaPage = va & ~Constants.PAGE_MASK;
				raPage = ra & ~Constants.PAGE_MASK;
				word = Memory.instance.readReal16(ra);
			}
		}
		vaLast = va;
		
		// increment pc
		pc = (pc + 1) & 0xFFFF;
		
		//       even             left           right        
		return (((pc & 1) == 0) ? (word >>> 8) : word) & 0xFF;
	}
	
	public static int getCodeWord() {
		if (Perf.ENABLED) Perf.codeCacheCodeWord++;
		int left  = getCodeByte();
		int right = getCodeByte();
		
		return (left << 8) | right;
	}
	
}


/*
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
	public static int CB() {
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
*/

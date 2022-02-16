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
				ra = Memory.fetch(va);
				vaPage = va & ~Constants.PAGE_MASK;
				raPage = ra & ~Constants.PAGE_MASK;
				word = Memory.readReal16(ra);
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

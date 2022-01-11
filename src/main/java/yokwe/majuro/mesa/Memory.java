package yokwe.majuro.mesa;

public final class Memory {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Memory.class);
	
	// use memory based array of int
	public static final int VM_MAX = 24;
	public static final int RM_MAX = 20;
	
	public static final int PAGE_BITS = 8;
	public static final int PAGE_SIZE = 1 << PAGE_BITS;
	public static final int PAGE_MASK = PAGE_SIZE - 1;
	
	public static final int WORD_BITS = 16;
	public static final int WORD_SIZE = 1 << WORD_BITS;
	public static final int WORD_MASK = WORD_SIZE - 1;
	
	private static final int MASK_REALPAGE   = 0x0000_FFFF;

	private static final int MASK_MAPFLAG    = 0xFFFF_0000;
	private static final int SHIFT_MAPFLAG   = 16;
	
	private static final class FlagPage {
		private static final int MASK_REFERENCED = 0x8000_0000;
		private static final int MASK_DIRTY      = 0x4000_0000;
		private static final int MASK_PROTECT    = 0x2000_0000;
		
		private static final int MASK_REFERENCED_DIRTY = MASK_REFERENCED | MASK_DIRTY;
		
		private static final int MASK_VACANT = MASK_REFERENCED | MASK_DIRTY | MASK_PROTECT;
		private static final int FLAG_VACANT = MASK_DIRTY | MASK_PROTECT; // not referenced and dirty and protect
		
		private static int getMapFlag(int flagPage) {
			return (flagPage & MASK_MAPFLAG) >>> SHIFT_MAPFLAG;
		}
		private static int setMapFlag(int flagPage, int mapFlag) {
			return (flagPage & ~MASK_MAPFLAG) | ((mapFlag << SHIFT_MAPFLAG) & MASK_MAPFLAG);
		}
		private static int getRealPage(int flagPage) {
			return (flagPage & MASK_REALPAGE);
		}
		private static int setRealPage(int flagPage, int rp) {
			return (flagPage & ~MASK_REALPAGE) | (rp & MASK_REALPAGE);
		}
		
		private static boolean isVacant(int flagPage) {
			return (flagPage & MASK_VACANT) == FLAG_VACANT;
		}
		private static boolean isProtect(int flagPage) {
			return (flagPage & MASK_PROTECT) != 0;
		}
		
		private static boolean isNotReferenced(int flagPage) {
			return (flagPage & MASK_REFERENCED) == 0;
		}
		private static boolean isNotReferencedDirty(int flagPage) {
			return (flagPage & (MASK_REFERENCED | MASK_DIRTY)) != (MASK_REFERENCED | MASK_DIRTY);
		}
		
		private static int setReferenced(int flagPage) {
			return (flagPage & ~MASK_REFERENCED) | MASK_REFERENCED;
		}
		private static int setReferencedDirty(int flagPage) {
			return (flagPage & ~MASK_REFERENCED_DIRTY) | MASK_REFERENCED_DIRTY;
		}
	}
	
	private final int rpSize;
	private final int vpSize;
	
	// index of entry is virtual page
	// value of entry is MAPFLAG and REALPAGE
	// MAPFLAG [0..16), REALPAGE [16..32)
	private final int[] flagPages;

	// index of realMemory is real address
	private final short[] realMemory;
	
	public Memory(int vmbits, int rmbits, int ioRegionPage) {
		logger.info("vmbits {}", vmbits);
		logger.info("rmbits {}", rmbits);
		
		rpSize = 1 << rmbits;
		vpSize = 1 << (vmbits - PAGE_BITS);
		
		realMemory = new short[rpSize];
		flagPages  = new int[vpSize];
		
		// clear realMemory and flagPage
		for(int i = 0; i < realMemory.length; i++) {
			realMemory[i] = 0;
		}
		for(int i = 0; i < flagPages.length; i++) {
			flagPages[i] = 0;
		}
		
		//const int VP_START = pageGerm + countGermVM;
		int rp = 0;
		// vp:[ioRegionPage .. 256) <=> rp:[0..256-ioRegionPage)
		for(int i = ioRegionPage; i < 256; i++) {
			flagPages[i] = FlagPage.setRealPage(0, rp++);
		}
		// vp:[0..ioRegionPage) <=> rp: [256-ioRegionPage .. 256)
		for(int i = 0; i < ioRegionPage; i++) {
			flagPages[i] = FlagPage.setRealPage(0, rp++);
		}
		// vp: [256 .. rpSize)
		for(int i = 256; i < rpSize; i++) {
			flagPages[i] = FlagPage.setRealPage(0, rp++);
		}
		if (rp != rpSize) {
			logger.error("rp != rpSize");
			throw new Error();
		}
		// vp: [rpSize .. vpSize)
		for(int i = rpSize; i < vpSize; i++) {
			flagPages[i] = FlagPage.setRealPage(FlagPage.FLAG_VACANT, 0);
		}
	}
	
	public int getFlagPage(int vp) {
		return FlagPage.getMapFlag(flagPages[vp]);
	}
	public void setFlagPage(int vp, int newValue) {
		flagPages[vp] = FlagPage.setMapFlag(flagPages[vp], newValue);
	}
	public int getRealPage(int vp) {
		return FlagPage.getRealPage(flagPages[vp]);
	}
	public void setRealPage(int vp, int newValue) {
		flagPages[vp] = FlagPage.setRealPage(flagPages[vp], newValue);
	}
	
	public void setReferenced(int vp) {
		flagPages[vp] = FlagPage.setReferenced(flagPages[vp]);
	}
	public void setReferencedDirty(int vp) {
		flagPages[vp] = FlagPage.setReferencedDirty(flagPages[vp]);
	}
	
	
	// fetch returns real address == offset of realMemory
	int fetch(int va) {
		if (Perf.ENABLED) Perf.fetchMemory++;
		
		int vp = va >>> PAGE_BITS;
		int of = va & PAGE_MASK;
		
		int flagPage = flagPages[vp];
		if (FlagPage.isVacant(flagPage)) {
			// PageFault(virtualAddress)
		}
		if (FlagPage.isNotReferenced(flagPage)) {
			flagPages[vp] = FlagPage.setReferenced(flagPage);
		}
		int realPage = FlagPage.getRealPage(flagPage);
		if (realPage == 0) {
			throw new Error();
		}
		return (realPage << PAGE_BITS) + of;
	}
	// store returns real address == offset of realMemory
	int store(int va) {
		if (Perf.ENABLED) Perf.storeMemory++;

		int vp = va >>> PAGE_BITS;
		int of = va & PAGE_MASK;
		
		int flagPage = flagPages[vp];
		if (FlagPage.isVacant(flagPage)) {
			// PageFault(virtualAddress)
		}
		if (FlagPage.isProtect(flagPage)) {
			// WriteProtectFault(virtualAddress)
		}
		if (FlagPage.isNotReferencedDirty(flagPage)) {
			flagPages[vp] = FlagPage.setReferencedDirty(flagPage);
		}

		int realPage = FlagPage.getRealPage(flagPage);
		if (realPage == 0) {
			throw new Error();
		}
		return (realPage << PAGE_BITS) + of;
	}
	
	int readMemory(int va) {
		return realMemory[va] & WORD_MASK;
	}
	void writeMemory(int va, int newValue) {
		realMemory[va] = (short)newValue;
	}
}

package yokwe.majuro.mesa;

public final class Memory {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Memory.class);
	
	// use memory based array of int
	public static final int VM_MAX = 24;
	public static final int RM_MAX = 20;
	
	private final int rpSize;
	private final int vpSize;
	
	// index of entry is virtual page
	// value of entry is MAPFLAG and REALPAGE
	// MAPFLAG [0..16), REALPAGE [16..32)
	
	// index of mapFlags and realPages is virtual page
	// value of realPages contains realPage number of virtual page
	private final char[] mapFlags;
	private final char[] realPages;

	// index of realMemory is real address
	private final char[] realMemory;
	
	public Memory(int vmbits, int rmbits, int ioRegionPage) {
		logger.info("vmbits {}", vmbits);
		logger.info("rmbits {}", rmbits);
		
		vpSize = 1 << (vmbits - Mesa.PAGE_BITS);
		rpSize = Integer.max(Mesa.MAX_REALMEMORY_PAGE_SIZE, 1 << (rmbits - Mesa.PAGE_BITS));
		logger.info("vpSize {}", String.format("%X", vpSize));
		logger.info("rpSize {}", String.format("%X", rpSize));
		
		realMemory = new char[rpSize];
		mapFlags   = new char[vpSize];
		realPages  = new char[vpSize];
		
		// clear realMemory, mapFlags and realPages
		for(int i = 0; i < realMemory.length; i++) {
			realMemory[i] = 0;
		}
		for(int i = 0; i < mapFlags.length; i++) {
			mapFlags[i] = 0;
		}
		for(int i = 0; i < realPages.length; i++) {
			realPages[i] = 0;
		}
		
		//const int VP_START = pageGerm + countGermVM;
		char rp = 0;
		// vp:[ioRegionPage .. 256) <=> rp:[0..256-ioRegionPage)
		for(int i = ioRegionPage; i < 256; i++) {
			mapFlags[i]  = MapFlag.getClear();
			realPages[i] = rp++;
		}
		// vp:[0..ioRegionPage) <=> rp: [256-ioRegionPage .. 256)
		for(int i = 0; i < ioRegionPage; i++) {
			mapFlags[i]  = MapFlag.getClear();
			realPages[i] = rp++;
		}
		// vp: [256 .. rpSize)
		for(int i = 256; i < rpSize; i++) {
			mapFlags[i]  = MapFlag.getClear();
			realPages[i] = rp++;
		}
		if (rp != rpSize) {
			logger.error("rp != rpSize");
			throw new Error();
		}
		// vp: [rpSize .. vpSize)
		for(int i = rpSize; i < vpSize; i++) {
			mapFlags[i]  = MapFlag.getVacant();
			realPages[i] = 0;
		}
	}

	public void setReferenced(int vp) {
		mapFlags[vp] = MapFlag.setReferenced(mapFlags[vp]);
	}
	public void setReferencedDirty(int vp) {
		mapFlags[vp] = MapFlag.setReferencedDirty(mapFlags[vp]);
	}
	// fetch returns real address == offset of realMemory
	int fetch(int va) {
		if (Perf.ENABLED) Perf.fetchMemory++;
		
		int vp = va >>> Mesa.PAGE_BITS;
		
		char mapFlag = mapFlags[vp];
		if (MapFlag.isVacant(mapFlag)) {
			// PageFault(virtualAddress)
		}
		if (MapFlag.isNotReferenced(mapFlag)) {
			mapFlags[vp] = MapFlag.setReferenced(mapFlag);
		}
		int ra = realPages[vp] << Mesa.PAGE_BITS;
		if (ra == 0) {
			throw new Error();
		}
		return ra + (va & Mesa.PAGE_MASK);
	}
	// store returns real address == offset of realMemory
	int store(int va) {
		if (Perf.ENABLED) Perf.storeMemory++;

		int vp = va >>> Mesa.PAGE_BITS;
		
		char mapFlag = mapFlags[vp];
		if (MapFlag.isVacant(mapFlag)) {
			// PageFault(virtualAddress)
		}
		if (MapFlag.isProtect(mapFlag)) {
			// WriteProtectFault(virtualAddress)
		}
		if (MapFlag.isNotReferencedDirty(mapFlag)) {
			mapFlags[vp] = MapFlag.setReferencedDirty(mapFlag);
		}

		int ra = realPages[vp] << Mesa.PAGE_BITS;
		if (ra == 0) {
			throw new Error();
		}
		return ra + (va & Mesa.PAGE_MASK);
	}
	
	int readMemory(int va) {
		return realMemory[va];
	}
	void writeMemory(int va, int newValue) {
		realMemory[va] = (char)newValue;
	}
}

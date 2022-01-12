package yokwe.majuro.mesa;

public final class Memory {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Memory.class);
	
	public static final class Cache {
		private static final int N_BIT = 14;
		private static final int SIZE  = 1 << N_BIT;
		private static final int MASK  = SIZE - 1;
				
		private static Cache[] array = new Cache[SIZE];

		public  int     vp;
		public  int     ra;
		private boolean dirty;
		
		public Cache() {
			clear();
		}
		
		public void clear() {
			vp    = 0;
			ra    = 0;
			dirty = false;
		}
		
		public static Cache get(int vp) {
			return array[((vp >> N_BIT) ^ vp) & MASK];
		}
	}
	
	// use memory based array of int
	public static final int VM_MAX = 24;
	public static final int RM_MAX = 20;
	
	private final int rpSize;
	private final int vpSize;
	
	// index of mapFlags and realPages is virtual page
	// value of realPages contains realPage number of virtual page
	private final char[] mapFlags;
	private final char[] realPages;

	// index of realMemory is real address up to rpSize * Mesa.PAGE_SIZE
	private final char[] realMemory;
	
	public Memory(int vmbits, int rmbits, int ioRegionPage) {
		logger.info("vmbits {}", vmbits);
		logger.info("rmbits {}", rmbits);
		
		vpSize = 1 << (vmbits - Mesa.PAGE_BITS);
		rpSize = Integer.max(Mesa.MAX_REALMEMORY_PAGE_SIZE, 1 << (rmbits - Mesa.PAGE_BITS));
		logger.info("vpSize {}", String.format("%X", vpSize));
		logger.info("rpSize {}", String.format("%X", rpSize));
		
		realMemory = new char[rpSize * Mesa.PAGE_SIZE];
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

	// provide access to mapFlags
	public char getMapFlag(int vp) {
		return mapFlags[vp];
	}
	public void setMapFlag(int vp, char newValue) {
		mapFlags[vp] = newValue;
	}
	// provide access to realPages
	public char getRealPage(int vp) {
		return realPages[vp];
	}
	public void setRealPage(int vp, char newValue) {
		realPages[vp] = newValue;
	}
	// provide access to realMemory
	public char readRealMemory(int ra) {
		return realMemory[ra];
	}
	public void writeRealMemory(int ra, char newValue) {
		realMemory[ra] = newValue;
	}

	public void setReferenced(int vp) {
		mapFlags[vp] = MapFlag.setReferenced(mapFlags[vp]);
	}
	public void setReferencedDirty(int vp) {
		mapFlags[vp] = MapFlag.setReferencedDirty(mapFlags[vp]);
	}
	// fetch returns real address == offset of realMemory
	public int fetch(int va) {
		if (Perf.ENABLED) Perf.memoryFetch++;
		
		final int vp = va >>> Mesa.PAGE_BITS;
		final int ra;
		
		if (Debug.DISABLE_MEMORY_CACHE) {
			// FIXME DUPLICATE CODE START
			char mapFlag = mapFlags[vp];
			if (MapFlag.isVacant(mapFlag)) {
				Mesa.pageFault(va);
			}
			
			// NO FAULT FROM HERE
			if (MapFlag.isNotReferenced(mapFlag)) {
				mapFlags[vp] = MapFlag.setReferenced(mapFlag);
			}
			ra = realPages[vp] << Mesa.PAGE_BITS;
			if (ra == 0) throw new Error();
			// FIXME DUPLICATE CODE STOP
		} else {
			// check cache
			Cache cache = Cache.get(vp);
			if (cache.vp == vp) {
				if (Perf.ENABLED) {
					Perf.cacheHit++;
				}
				ra = cache.ra;
			} else {
				if (Perf.ENABLED) {
					if (cache.vp == 0) Perf.cacheMissEmpty++;
					else Perf.cacheMissConflict++;
				}

				// FIXME DUPLICATE CODE START
				char mapFlag = mapFlags[vp];
				if (MapFlag.isVacant(mapFlag)) {
					Mesa.pageFault(va);
				}
				
				// NO FAULT FROM HERE
				if (MapFlag.isNotReferenced(mapFlag)) {
					mapFlags[vp] = MapFlag.setReferenced(mapFlag);
				}
				ra = realPages[vp] << Mesa.PAGE_BITS;
				if (ra == 0) throw new Error();
				// FIXME DUPLICATE CODE STOP
				
				cache.vp    = vp;
				cache.ra    = ra;
				cache.dirty = MapFlag.isDirty(mapFlag);
			}
		}
		return ra | (va & Mesa.PAGE_MASK);
	}
	
	// store returns real address == offset of realMemory
	public int store(int va) {
		if (Perf.ENABLED) Perf.memoryStore++;

		final int vp = va >>> Mesa.PAGE_BITS;
		final int ra;
		
		if (Debug.DISABLE_MEMORY_CACHE) {
			// FIXME DUPLICATE CODE START
			char mapFlag = mapFlags[vp];
			if (MapFlag.isVacant(mapFlag)) {
				Mesa.pageFault(va);
			}
			if (MapFlag.isProtect(mapFlag)) {
				Mesa.writeProtectFault(va);
			}
			
			// NO FAULT FROM HERE
			if (MapFlag.isNotReferencedDirty(mapFlag)) {
				mapFlags[vp] = MapFlag.setReferencedDirty(mapFlag);
			}
			ra = realPages[vp] << Mesa.PAGE_BITS;
			if (ra == 0) throw new Error();
			// FIXME DUPLICATE CODE STOP
		} else {
			Cache cache = Cache.get(vp);
			if (cache.vp == vp) {
				if (Perf.ENABLED) {
					Perf.cacheHit++;
				}
				
				ra = cache.ra;
				if (!cache.dirty) {
					mapFlags[vp] = MapFlag.setReferencedDirty(mapFlags[vp]);
					cache.dirty  = true;
				}
			} else {
				if (Perf.ENABLED) {
					if (cache.vp == 0) Perf.cacheMissEmpty++;
					else Perf.cacheMissConflict++;
				}

				// FIXME DUPLICATE CODE START
				char mapFlag = mapFlags[vp];
				if (MapFlag.isVacant(mapFlag)) {
					Mesa.pageFault(va);
				}
				if (MapFlag.isProtect(mapFlag)) {
					Mesa.writeProtectFault(va);
				}
				
				// NO FAULT FROM HERE
				if (MapFlag.isNotReferencedDirty(mapFlag)) {
					mapFlags[vp] = MapFlag.setReferencedDirty(mapFlag);
				}
				ra = realPages[vp] << Mesa.PAGE_BITS;
				if (ra == 0) throw new Error();
				// FIXME DUPLICATE CODE STOP
				
				cache.vp    = vp;
				cache.ra    = ra;
				cache.dirty = true;
			}
		}
				
		return ra | (va & Mesa.PAGE_MASK);
	}
	
}

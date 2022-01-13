package yokwe.majuro.mesa;

public final class Memory {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Memory.class);
	
	private static final class Cache {
		private static final int N_BIT = 14;
		private static final int SIZE  = 1 << N_BIT;
		private static final int MASK  = SIZE - 1;
		
		private static Cache[] getCacheArray() {
			return new Cache[SIZE];
		}
		private static Cache getCache(Cache[] cacheArray, int vp) {
			return cacheArray[((vp >> Cache.N_BIT) ^ vp) & Cache.MASK];
		}

		private int     vp;
		private int     ra;
		private boolean dirty;
		
		private Cache() {
			clear();
		}
		private void clear() {
			vp    = 0;
			ra    = 0;
			dirty = false;
		}
	}
	
	// use memory based array of int
	public static final int VM_MAX = 24;
	public static final int RM_MAX = 20;
	
	private final int rpSize;
	private final int vpSize;
	
	// index of mapFlags and realPages is virtual page
	// value of realPages contains realPage number of virtual page
	private final MapFlag[] mapFlags;
	private final char[]    realPages;

	// index of realMemory is real address up to rpSize * Mesa.PAGE_SIZE
	private final char[]    realMemory;
	
	// array of cache
	private final Cache[]   cacheArray;
	
	public Memory(int vmbits, int rmbits, int ioRegionPage) {
		logger.info("vmbits {}", vmbits);
		logger.info("rmbits {}", rmbits);
		
		vpSize = 1 << (vmbits - Mesa.PAGE_BITS);
		rpSize = Integer.max(Mesa.MAX_REALMEMORY_PAGE_SIZE, 1 << (rmbits - Mesa.PAGE_BITS));
		logger.info("vpSize {}", String.format("%X", vpSize));
		logger.info("rpSize {}", String.format("%X", rpSize));
		
		realMemory = new char[rpSize * Mesa.PAGE_SIZE];
		mapFlags   = new MapFlag[vpSize];
		realPages  = new char[vpSize];
		cacheArray = Cache.getCacheArray();
		
		// clear realMemory, mapFlags and realPages
		for(int i = 0; i < realMemory.length; i++) {
			realMemory[i] = 0;
		}
		for(int i = 0; i < mapFlags.length; i++) {
			mapFlags[i].clear();
		}
		for(int i = 0; i < realPages.length; i++) {
			realPages[i] = 0;
		}
		
		//const int VP_START = pageGerm + countGermVM;
		char rp = 0;
		// vp:[ioRegionPage .. 256) <=> rp:[0..256-ioRegionPage)
		for(int i = ioRegionPage; i < 256; i++) {
			mapFlags[i].clear();
			realPages[i] = rp++;
		}
		// vp:[0..ioRegionPage) <=> rp: [256-ioRegionPage .. 256)
		for(int i = 0; i < ioRegionPage; i++) {
			mapFlags[i].clear();
			realPages[i] = rp++;
		}
		// vp: [256 .. rpSize)
		for(int i = 256; i < rpSize; i++) {
			mapFlags[i].clear();
			realPages[i] = rp++;
		}
		if (rp != rpSize) {
			logger.error("rp != rpSize");
			throw new Error();
		}
		// vp: [rpSize .. vpSize)
		for(int i = rpSize; i < vpSize; i++) {
			mapFlags[i].setVacant();
			realPages[i] = 0;
		}		
	}
	
	private Cache getCache(int vp) {
		return Cache.getCache(cacheArray, vp);
	}


	// provide access to mapFlags
	public MapFlag getMapFlag(int vp) {
		return mapFlags[vp];
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
	public void setRealMemory(int ra, char newValue) {
		realMemory[ra] = newValue;
	}

	public void invalidate(int vp) {
		getCache(vp).clear();
	}
	// fetch returns real address == offset of realMemory
	public int fetch(int va) {
		if (Perf.ENABLED) Perf.memoryFetch++;
		
		final int vp = va >>> Mesa.PAGE_BITS;
		final int ra;
		
		if (Debug.DISABLE_MEMORY_CACHE) {
			// FIXME DUPLICATE CODE START
			MapFlag mapFlag = mapFlags[vp];
			if (mapFlag.isVacant()) {
				Mesa.pageFault(va);
			}
			
			// NO FAULT FROM HERE
			if (mapFlag.isNotReferenced()) {
				mapFlag.setReferenced();
			}
			ra = realPages[vp] << Mesa.PAGE_BITS;
			if (ra == 0) throw new Error();
			// FIXME DUPLICATE CODE STOP
		} else {
			// check cache
			var cache = getCache(vp);
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
				MapFlag mapFlag = mapFlags[vp];
				if (mapFlag.isVacant()) {
					Mesa.pageFault(va);
				}
				
				// NO FAULT FROM HERE
				if (mapFlag.isNotReferenced()) {
					mapFlags[vp].setReferenced();
				}
				ra = realPages[vp] << Mesa.PAGE_BITS;
				if (ra == 0) throw new Error();
				// FIXME DUPLICATE CODE STOP
				
				cache.vp    = vp;
				cache.ra    = ra;
				cache.dirty = mapFlag.isDirty();
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
			MapFlag mapFlag = mapFlags[vp];
			if (mapFlag.isVacant()) {
				Mesa.pageFault(va);
			}
			if (mapFlag.isProtect()) {
				Mesa.writeProtectFault(va);
			}
			
			// NO FAULT FROM HERE
			if (mapFlag.isNotReferencedDirty()) {
				mapFlags[vp].setReferencedDirty();
			}
			ra = realPages[vp] << Mesa.PAGE_BITS;
			if (ra == 0) throw new Error();
			// FIXME DUPLICATE CODE STOP
		} else {
			var cache = getCache(vp);
			if (cache.vp == vp) {
				if (Perf.ENABLED) {
					Perf.cacheHit++;
				}
				
				ra = cache.ra;
				if (!cache.dirty) {
					mapFlags[vp].setReferencedDirty();
					cache.dirty  = true;
				}
			} else {
				if (Perf.ENABLED) {
					if (cache.vp == 0) Perf.cacheMissEmpty++;
					else Perf.cacheMissConflict++;
				}

				// FIXME DUPLICATE CODE START
				MapFlag mapFlag = mapFlags[vp];
				if (mapFlag.isVacant()) {
					Mesa.pageFault(va);
				}
				if (mapFlag.isProtect()) {
					Mesa.writeProtectFault(va);
				}
				
				// NO FAULT FROM HERE
				if (mapFlag.isNotReferencedDirty()) {
					mapFlags[vp].setReferencedDirty();
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

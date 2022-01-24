package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constant.MAX_REALMEMORY_PAGE_SIZE;
import static yokwe.majuro.mesa.Constant.PAGE_BITS;
import static yokwe.majuro.mesa.Constant.PAGE_MASK;
import static yokwe.majuro.mesa.Constant.PAGE_SIZE;
import static yokwe.majuro.mesa.Constant.WORD_SIZE;

import yokwe.majuro.UnexpectedException;


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
		
		vpSize = 1 << (vmbits - PAGE_BITS);
		rpSize = Integer.max(MAX_REALMEMORY_PAGE_SIZE, 1 << (rmbits - PAGE_BITS));
		logger.info("vpSize {}", String.format("%X", vpSize));
		logger.info("rpSize {}", String.format("%X", rpSize));
		
		realMemory = new char[rpSize * PAGE_SIZE];
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
			throw new UnexpectedException();
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


	public void clearCache(int vp) {
		getCache(vp).clear();
	}
	
	//
	// low level memory access
	//   fetch store mapFlag readReal writeReal
	//
	// fetch returns real address == offset of realMemory
	public int fetch(int va) {
		if (Perf.ENABLED) Perf.memoryFetch++;
		
		final int vp = va >>> PAGE_BITS;
		final int ra;
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

			MapFlag mapFlag = mapFlags[vp];
			if (mapFlag.isVacant()) {
				Mesa.pageFault(va);
			}
			
			// NO FAULT FROM HERE
			if (mapFlag.isNotReferenced()) {
				mapFlags[vp].setReferenced();
			}
			ra = realPages[vp] << PAGE_BITS;
			if (ra == 0) throw new UnexpectedException();
			
			cache.vp    = vp;
			cache.ra    = ra;
			cache.dirty = mapFlag.isDirty();
		}
		return ra | (va & PAGE_MASK);
	}
	// store returns real address == offset of realMemory
	public int store(int va) {
		if (Perf.ENABLED) Perf.memoryStore++;

		final int vp = va >>> PAGE_BITS;
		final int ra;
		// check cache
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
			ra = realPages[vp] << PAGE_BITS;
			if (ra == 0) throw new UnexpectedException();
			// FIXME DUPLICATE CODE STOP
			
			cache.vp    = vp;
			cache.ra    = ra;
			cache.dirty = true;
		}
				
		return ra | (va & PAGE_MASK);
	}
	public MapFlag mapFlag(int vp) {
		return mapFlags[vp];
	}
	public char readReal16(int ra) {
		return realMemory[ra];
	}
	public void writeReal16(int ra, char newValue) {
		realMemory[ra] = newValue;
	}
	public int readReal32(int ra0, int ra1) {
		return (realMemory[ra1] << WORD_SIZE) | realMemory[ra0];
	}
	public void writeReal32(int ra0, int ra1, int newValue) {
		realMemory[ra0] = (char)newValue;
		realMemory[ra1] = (char)(newValue >>> 16);
	}
	
	
	//
	// memory read and write
	//
	public char read16(int va) {
		return realMemory[fetch(va)];
	}
	public void write16(int va, char newValue) {
		realMemory[store(va)] = newValue;
	}
	

	// 2.3.1 Long Types
	// When these types are stored in memory, the low-order (least significant) sixteen bits
	// occupy the first memory word (at the lower numbered address), and the high-order (most
	// significant) sixteen bits occupy the second memory word(at the higher memory address).
	//         |15    31|0   15|
	// address  n        n+1    n+2
	public static boolean isSamePage(int a, int b) {
		return (a & ~PAGE_MASK) == (b & ~PAGE_MASK);
	}
	public int read32(int va) {
		int ra0 = fetch(va);
		int ra1 = isSamePage(va, va + 1) ? ra0 + 1 : fetch(va + 1);
		
		return readReal32(ra0, ra1);
	}
	public void write32(int va, int newValue) {
		int ra0 = store(va);
		int ra1 = isSamePage(va, va + 1) ? ra0 + 1 : store(va + 1);
		
		writeReal32(ra0, ra1, newValue);
	}
	
	
	//
	// MDS
	//
	public int mds = 0;
	
	public int fetchMDS(char va) {
		return fetch(mds + va);
	}
	public int storeMDS(char va) {
		return store(mds + va);
	}
	public char read16MDS(char va) {
		return read16(mds + va);
	}
	public void write16MDS(char va, char newValue) {
		write16(mds + va, newValue);
	}
	public int read32MDS(char va) {
		return read32(mds + va);
	}
	public void write32MDS(char va, int newValue) {
		write32(mds + va, newValue);
	}
	//
	// prohibit int promotion of above methods
	// capture wrong parameter invocation of (fetch|store|read16|write16|read32|write32)MDS
	//
	public int fetchMDS(int va) {
		throw new UnexpectedException("Unexpected");
	}
	public int storeMDS(int va) {
		throw new UnexpectedException("Unexpected");
	}
	public char read16MDS(int va) {
		throw new UnexpectedException("Unexpected");
	}
	public void write16MDS(int va, char newValue) {
		throw new UnexpectedException("Unexpected");
	}
	public int read32MDS(int va) {
		throw new UnexpectedException("Unexpected");
	}
	public void write32MDS(int va, int newValue) {
		throw new UnexpectedException("Unexpected");
	}

	
	
}

package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.MAX_REALMEMORY_PAGE_SIZE;
import static yokwe.majuro.mesa.Constants.PAGE_BITS;
import static yokwe.majuro.mesa.Constants.PAGE_MASK;
import static yokwe.majuro.mesa.Constants.PAGE_SIZE;
import static yokwe.majuro.mesa.Constants.WORD_BITS;

import yokwe.majuro.UnexpectedException;


public final class Memory {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Memory.class);
	
	private static final class Cache {
		private static final int N_BIT = 14;
		private static final int SIZE  = 1 << N_BIT;
		private static final int MASK  = SIZE - 1;
		
		private static Cache[] getCacheArray() {
			Cache[] cacheArray = new Cache[SIZE];
			for(int i = 0; i < cacheArray.length; i++) {
				cacheArray[i] = new Cache();
			}
			return cacheArray;
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
	
	public static final int DEFAULT_VM_SIZE        = 24;
	public static final int DEFAULT_RM_SIZE        = 20;
	public static final int DEFAULT_IO_REGION_PAGE = 0x80;
	
	public static final int VM_MAX = 25;
	public static final int RM_MAX = 20;
	
	private final int rpSize;
	private final int vpSize;
	private final int ioRegionPage;
	
	// index of mapFlags and realPages is virtual page
	// value of realPages contains realPage number of virtual page
	private final Map[] maps;
	
	// index of realMemory is real address up to rpSize * Mesa.PAGE_SIZE
	private final char[]    realMemory;
	
	// array of cache
	private final Cache[]   cacheArray;
	
	
	public static Memory instance = null;
	public static void init(int vmbits, int rmbits) {
		instance = new Memory(vmbits, rmbits);
	}
	
	
	private Memory(int vmbits, int rmbits) {
		this(vmbits, rmbits, DEFAULT_IO_REGION_PAGE);
	}
	private Memory(int vmbits, int rmbits, int ioRegionPage) {
		logger.info("vmbits       {}", vmbits);
		logger.info("rmbits       {}", rmbits);
		logger.info("ioRegionPage {}", String.format("0x%X", ioRegionPage));
		this.ioRegionPage = ioRegionPage;
		
		vpSize = 1 << (vmbits - PAGE_BITS);
		rpSize = Integer.max(MAX_REALMEMORY_PAGE_SIZE, 1 << (rmbits - PAGE_BITS));
		logger.info("vpSize {}", String.format("%X", vpSize));
		logger.info("rpSize {}", String.format("%X", rpSize));
		
		realMemory = new char[rpSize * PAGE_SIZE];
		maps       = new Map[vpSize];
		for(int i = 0; i < maps.length; i++) {
			maps[i] = new Map();
		}
		cacheArray = Cache.getCacheArray();

		clear();
	}
	
	public void clear() {
		// clear mds realMemory, mapFlags, realPages and cacheArray
		for(int i = 0; i < realMemory.length; i++) {
			realMemory[i] = 0;
		}
		for(int i = 0; i < maps.length; i++) {
			maps[i].clear();
		}
		for(int i = 0; i < cacheArray.length; i++) {
			cacheArray[i].clear();
		}
		
		//
		// initialize mapFlags and realPages
		//
		//const int VP_START = pageGerm + countGermVM;
		char rp = 0;
		// vp:[ioRegionPage .. 256) <=> rp:[0..256-ioRegionPage)
		for(int i = ioRegionPage; i < 256; i++) {
			maps[i].clear();
			maps[i].setRealPage(rp++);
		}
		// vp:[0..ioRegionPage) <=> rp: [256-ioRegionPage .. 256)
		for(int i = 0; i < ioRegionPage; i++) {
			maps[i].clear();
			maps[i].setRealPage(rp++);
		}
		// vp: [256 .. rpSize)
		for(int i = 256; i < rpSize; i++) {
			maps[i].clear();
			maps[i].setRealPage(rp++);
		}
		if (rp != rpSize) {
			logger.error("rp != rpSize");
			throw new UnexpectedException();
		}
		// vp: [rpSize .. vpSize)
		for(int i = rpSize; i < vpSize; i++) {
			maps[i].clear();
			maps[i].setVacant();
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
	public Map map(int va) {
		if (Perf.ENABLED) Perf.map++;
		return maps[va >>> PAGE_BITS];
	}
	// fetch returns real address == offset of realMemory
	public int fetch(int va) {
		if (Perf.ENABLED) Perf.fetch++;
		
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

			Map map = maps[vp];
			if (map.isVacant()) {
				Processes.pageFault(va);
			}
			
			// NO FAULT FROM HERE
			if (map.isNotReferenced()) {
				map.setReferenced();
			}
			ra = map.getRealAddress();
			if (ra == 0) throw new UnexpectedException();
			
			cache.vp    = vp;
			cache.ra    = ra;
			cache.dirty = map.isDirty();
		}
		return ra | (va & PAGE_MASK);
	}
	// store returns real address == offset of realMemory
	public int store(int va) {
		if (Perf.ENABLED) Perf.store++;

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
				maps[vp].setReferencedDirty();
				cache.dirty  = true;
			}
		} else {
			if (Perf.ENABLED) {
				if (cache.vp == 0) Perf.cacheMissEmpty++;
				else Perf.cacheMissConflict++;
			}

			Map map = maps[vp];
			if (map.isVacant()) {
				Processes.pageFault(va);
			}
			if (map.isProtect()) {
				Processes.writeProtectFault(va);
			}
			
			// NO FAULT FROM HERE
			if (map.isNotReferencedDirty()) {
				map.setReferencedDirty();
			}
			ra = map.getRealAddress();
			if (ra == 0) throw new UnexpectedException();
			
			cache.vp    = vp;
			cache.ra    = ra;
			cache.dirty = true;
		}
				
		return ra | (va & PAGE_MASK);
	}
	public char readReal16(int ra) {
		if (Perf.ENABLED) Perf.readReal16++;
		return realMemory[ra];
	}
	public void writeReal16(int ra, char newValue) {
		if (Perf.ENABLED) Perf.writeReal16++;
		realMemory[ra] = newValue;
	}
	public int readReal32(int ra0, int ra1) {
		// ra0 -- low order  16 bit
		// ra1 -- high order 16 bit
		if (Perf.ENABLED) Perf.readReal32++;
		return (realMemory[ra1] << WORD_BITS) | realMemory[ra0];
	}
	public void writeReal32(int ra0, int ra1, int newValue) {
		// ra0 -- low order  16 bit
		// ra1 -- high order 16 bit
		if (Perf.ENABLED) Perf.writeReal32++;
		realMemory[ra0] = (char)newValue;
		realMemory[ra1] = (char)(newValue >>> WORD_BITS);
	}
	
	
	//
	// Code Segments
	//
	public char readCode(int offset) {
		int longPointer = CodeCache.CB() + (offset & 0xFFFF);
		return realMemory[fetch(longPointer)];
	}

	
	//
	// memory read and write
	//
	public char read16(int va) {
		if (Perf.ENABLED) Perf.read16++;
		return realMemory[fetch(va)];
	}
	public void write16(int va, char newValue) {
		if (Perf.ENABLED) Perf.write16++;
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
		if (Perf.ENABLED) Perf.read32++;
		int ra0 = fetch(va);
		int ra1 = isSamePage(va, va + 1) ? ra0 + 1 : fetch(va + 1);
		
		return readReal32(ra0, ra1);
	}
	public void write32(int va, int newValue) {
		if (Perf.ENABLED) Perf.write32++;
		int ra0 = store(va);
		int ra1 = isSamePage(va, va + 1) ? ra0 + 1 : store(va + 1);
		
		writeReal32(ra0, ra1, newValue);
	}
	
	
	//
	// MDS
	//
	// treat sa as short pointer
	public static int lengthenMDS(char sa) {
		if (Perf.ENABLED) Perf.lengthenMDS++;
		return Processor.MDS + sa;
	}
	
	// convenience methods for MDS data access
	// treat sa as short pointer
	public int fetchMDS(int sa) {
		if (Perf.ENABLED) Perf.fetchMDS++;
		return fetch(Processor.MDS + (sa & 0xFFFF));
	}
	public int storeMDS(int sa) {
		if (Perf.ENABLED) Perf.storeMDS++;
		return store(Processor.MDS + (sa & 0xFFFF));
	}
	public char read16MDS(int sa) {
		if (Perf.ENABLED) Perf.read16MDS++;
		return read16(Processor.MDS + (sa & 0xFFFF));
	}
	public void write16MDS(int sa, char newValue) {
		if (Perf.ENABLED) Perf.write16MDS++;
		write16(Processor.MDS + (sa & 0xFFFF), newValue);
	}
	public int read32MDS(int sa) {
		if (Perf.ENABLED) Perf.read32MDS++;
		return read32(Processor.MDS + (sa & 0xFFFF));
	}
	public void write32MDS(int sa, int newValue) {
		if (Perf.ENABLED) Perf.write32MDS++;
		write32(Processor.MDS + (sa & 0xFFFF), newValue);
	}
	
	
	//
	// PDA
	//
	public static int lengthenPDA(char value) {
		if (Perf.ENABLED) Perf.lengthenPDA++;
		return Constants.mPDA + value;
	}

}

package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Processor.*;

import yokwe.majuro.type.FieldSpec;
import yokwe.majuro.type.GlobalOverhead;
import yokwe.majuro.type.ProcessStateBlock;
import yokwe.majuro.util.FormatLogger;


public final class Memory {
	private static final FormatLogger logger = FormatLogger.getLogger();
	
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
	
	private static int rpSize;
	private static int vpSize;
	private static int ioRegionPage;

	// index of mapFlags and realPages is virtual page
	// value of realPages contains realPage number of virtual page
	private static Map[] maps;
	
	// index of realMemory is real address up to rpSize * Mesa.PAGE_SIZE
	private static short[] realMemory;
	
	// array of cache
	private static Cache[] cacheArray;
	
	public static void init(int vmbits, int rmbits, int ioRegionPage_) {
		logger.info("vmbits       %2d", vmbits);
		logger.info("rmbits       %2d", rmbits);
		logger.info("ioRegionPage 0x%X", ioRegionPage_);
		
		vpSize = 1 << (vmbits - PAGE_BITS);
		rpSize = Integer.max(MAX_REALMEMORY_PAGE_SIZE, 1 << (rmbits - PAGE_BITS));
		ioRegionPage = ioRegionPage_;
		
		logger.info("vpSize %X", vpSize);
		logger.info("rpSize %X", rpSize);
		
		realMemory = new short[rpSize * PAGE_SIZE];
		maps       = new Map[vpSize];
		for(int i = 0; i < maps.length; i++) {
			maps[i] = new Map();
		}
		cacheArray = Cache.getCacheArray();

		clear();
	}
	public static void init(int vmbits, int rmbits) {
		init(vmbits, rmbits, DEFAULT_IO_REGION_PAGE);
	}
	
	public static void clear() {
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
		int rp = 0;
		// vp:[ioRegionPage .. 256) <=> rp:[0..256-ioRegionPage)
		for(int i = ioRegionPage; i < 256; i++) {
			maps[i].setFlags(0);
			maps[i].setRealPage(rp++);
		}
		// vp:[0..ioRegionPage) <=> rp: [256-ioRegionPage .. 256)
		for(int i = 0; i < ioRegionPage; i++) {
			maps[i].setFlags(0);
			maps[i].setRealPage(rp++);
		}
		// vp: [256 .. rpSize)
		for(int i = 256; i < rpSize; i++) {
			maps[i].setFlags(0);
			maps[i].setRealPage(rp++);
		}
		if (rp != rpSize) {
			logger.error("rp != rpSize");
			error();
		}
		// vp: [rpSize .. vpSize)
		for(int i = rpSize; i < vpSize; i++) {
			maps[i].setFlags(Map.FLAG_VACANT);
			maps[i].setRealPage(0);
		}
	}
	
	private static Cache getCache(int vp) {
		return Cache.getCache(cacheArray, vp);
	}

	public static void clearCache(int vp) {
		getCache(vp).clear();
	}
	public static void clearCacheAndMapFlags() {
		for(int i = 0; i < cacheArray.length; i++) {
			cacheArray[i].clear();
		}
		for(int i = 0; i < maps.length; i++) {
			if (maps[i].isVacant()) continue;
			maps[i].setFlags(0);
		}
	}
	
	
	//
	// REAL MEMORY
	//
	public static Map map(@Mesa.LONG_POINTER int va) {
		if (Perf.ENABLED) Perf.map++;
		return maps[va >>> PAGE_BITS];
	}
	public static @Mesa.REAL_POINTER int fetch(@Mesa.LONG_POINTER int va) {
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
			if (ra == 0) error();
			
			cache.vp    = vp;
			cache.ra    = ra;
			cache.dirty = map.isDirty();
		}
		return ra | (va & PAGE_MASK);
	}
	public static @Mesa.REAL_POINTER int store(@Mesa.LONG_POINTER int va) {
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
			if (ra == 0) error();
			
			cache.vp    = vp;
			cache.ra    = ra;
			cache.dirty = true;
		}
		return ra | (va & PAGE_MASK);
	}
	// realAddress returns real address without changing cache and map flag
	public static @Mesa.REAL_POINTER int realAddress(@Mesa.LONG_POINTER int va) {
		if (Perf.ENABLED) Perf.realAddress++;
		
		final int vp = va >>> PAGE_BITS;

		Map map = maps[vp];
		if (map.isVacant()) {
			logger.error("va = %6X  vp = %4X", va, vp);
			logger.error("mf = %4X  rp = %4X", map.getFlags(), map.getRealPage());
			error();
		}
		
		// NO FAULT FROM HERE
		final int ra = map.getRealAddress();
		if (ra == 0) error();

		return ra | (va & PAGE_MASK);
	}
	//
	// 16 bit data
	//
	public static @Mesa.CARD16 int readReal16(@Mesa.REAL_POINTER int ra) {
		if (Perf.ENABLED) Perf.readReal16++;
		return Types.toCARD16(realMemory[ra]);
	}
	public static void writeReal16(@Mesa.REAL_POINTER int ra, @Mesa.CARD16 int newValue) {
		if (Perf.ENABLED) Perf.writeReal16++;
		realMemory[ra] = (short)newValue;
	}
	//
	// 32 bit data
	//
	public static @Mesa.CARD32 int readReal32(@Mesa.REAL_POINTER int ra0, @Mesa.REAL_POINTER int ra1) {
		// ra0 -- low order  16 bit
		// ra1 -- high order 16 bit
		if (Perf.ENABLED) Perf.readReal32++;
		//                    high             low
		return Types.toCARD32(realMemory[ra1], realMemory[ra0]);
	}
	public static void writeReal32(@Mesa.REAL_POINTER int ra0, @Mesa.REAL_POINTER int ra1, @Mesa.CARD32 int newValue) {
		// ra0 -- low order  16 bit
		// ra1 -- high order 16 bit
		if (Perf.ENABLED) Perf.writeReal32++;
		realMemory[ra0] = (short)Types.lowHalf(newValue);
		realMemory[ra1] = (short)Types.highHalf(newValue);
	}
	
	
	//
	// VIRTUAL MEMORY
	//
	//
	// 16 bit data
	//
	public static @Mesa.CARD16 int read16(@Mesa.LONG_POINTER int va) {
		if (Perf.ENABLED) Perf.read16++;
		return readReal16(fetch(va));
	}
	public static void write16(@Mesa.LONG_POINTER int va, @Mesa.CARD16 int newValue) {
		if (Perf.ENABLED) Perf.write16++;
		writeReal16(store(va), newValue);
	}
	//
	// 32 bit data
	//
	public static boolean isSamePage(@Mesa.LONG_POINTER int a, @Mesa.LONG_POINTER int b) {
		return (a & ~PAGE_MASK) == (b & ~PAGE_MASK);
	}
	public static @Mesa.CARD32 int read32(@Mesa.LONG_POINTER int va) {
		if (Perf.ENABLED) Perf.read32++;
		int ra0 = fetch(va);
		int ra1;
		if (isSamePage(va, va + 1)) {
			if (Perf.ENABLED) Perf.read32Same++;
			ra1 = ra0 + 1;
		} else {
			if (Perf.ENABLED) Perf.read32Diff++;
			ra1 = fetch(va + 1);
		}
		//                    high             low
		return Types.toCARD32(realMemory[ra1], realMemory[ra0]);
	}
	public static void write32(@Mesa.LONG_POINTER int va, @Mesa.CARD32 int newValue) {
		if (Perf.ENABLED) Perf.write32++;
		int ra0 = store(va);
		int ra1;
		if (isSamePage(va, va + 1)) {
			if (Perf.ENABLED) Perf.write32Same++;
			ra1 = ra0 + 1;
		} else {
			if (Perf.ENABLED) Perf.write32Diff++;
			ra1 = store(va + 1);
		}
		writeReal32(ra0, ra1, newValue);
	}
	//
	// 8 bit data
	//
	public static @Mesa.CARD8 int read8(@Mesa.LONG_POINTER int ptr, int offset) {
		int word = read16(ptr + offset / 2);
		if ((offset & 1) == 0) {
			// returns left
			return Types.highByte(word);
		} else {
			// returns right
			return Types.lowByte(word);
		}
	}
	public static void write8(@Mesa.LONG_POINTER int ptr, int offset, @Mesa.CARD8 int data) {
		int ra = fetch(ptr + offset / 2);
		int word = readReal16(ra);
		
		if ((offset & 1) == 0) {
			// modify left
			writeReal16(ra, Types.toCARD16(data, word));
		} else {
			// modify right
			writeReal16(ra, Types.toCARD16(word >> 8, data));
		}
	}
	//
	// bit field data
	//
	public static int maskTable(int n) {
		return ((1 << (n + 1)) - 1) & 0xFFFF;
	}
	public static @Mesa.CARD16 int readField(@Mesa.CARD16 int source, FieldSpec spec) {
		int pos  = spec.pos();
		int size = spec.size();
		
		int shift = WORD_BITS - (pos + size + 1);
		if (shift < 0) error();
		
		return Types.toCARD16((source >>> shift) & maskTable(size));
	}
	public static @Mesa.CARD16 int writeField(@Mesa.CARD16 int dest, FieldSpec spec, @Mesa.CARD16 int data) {
		int pos  = spec.pos();
		int size = spec.size();
		
		int shift = WORD_BITS - (pos + size + 1);
		if (shift < 0) error();
		int mask = maskTable(size) << shift;
		
		return Types.toCARD16((dest & ~mask) | ((data << shift) & mask));
	}


	//
	// MAIN DATA SPACE
	//
	public static int lengthenMDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.lengthenMDS++;
		return Processor.MDS + Types.toCARD16(sa);
	}
	//
	// real memory 16 bit data
	//
	public static @Mesa.REAL_POINTER int fetchMDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.fetchMDS++;
		return fetch(lengthenMDS(sa));
	}
	public static @Mesa.REAL_POINTER int storeMDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.storeMDS++;
		return store(lengthenMDS(sa));
	}
	//
	// virtual memory 16 bit data
	//
	public static @Mesa.CARD16 int read16MDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.read16MDS++;
		return read16(lengthenMDS(sa));
	}
	public static void write16MDS(@Mesa.SHORT_POINTER int sa, @Mesa.CARD16 int newValue) {
		if (Perf.ENABLED) Perf.write16MDS++;
		write16(lengthenMDS(sa), newValue);
	}
	//
	// virtual memory 32 bit data
	//
	public static @Mesa.CARD32 int read32MDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.read32MDS++;
		return read32(lengthenMDS(sa));
	}
	public static void write32MDS(@Mesa.SHORT_POINTER int sa, @Mesa.CARD32 int newValue) {
		if (Perf.ENABLED) Perf.write32MDS++;
		write32(lengthenMDS(sa), newValue);
	}
	//
	// virtual memory 8 bit data
	//
	public static @Mesa.CARD8 int read8MDS(@Mesa.SHORT_POINTER int sa, int offset) {
		return read8(lengthenMDS(sa), offset);
	}
	public static void write8MDS(@Mesa.SHORT_POINTER int sa, int offset, @Mesa.CARD8 int data) {
		write8(lengthenMDS(sa), offset, data);
	}

	
	//
	// PDA
	//
	public static @Mesa.LONG_POINTER int lengthenPDA(@Mesa.PDA_POINTER int value) {
		if (Perf.ENABLED) Perf.lengthenPDA++;
		return Constants.mPDA + value;
	}
	public static int psbHandle(int psbIndex) {
		return psbIndex * ProcessStateBlock.WORD_SIZE;
	}
	public static int psbIndex(int psbHandle) {
		return psbHandle / ProcessStateBlock.WORD_SIZE;
	}
	
	
	//
	// LF and GF
	//
	public static @Mesa.LONG_POINTER int globalBase(@Mesa.LONG_POINTER int frame) {
		return frame - GlobalOverhead.WORD_SIZE;
	}

	
	//
	// CB and PC
	//
	private static int cb;
	private static int pc;
	// read and write CB
	public static int CB() {
		return cb;
	}
	public static void CB(int newValue) {
		cb = newValue;
		// invalidate
		vaPC     =  0;
		vaPCLast = ~0; // make (va == vaLast) false
		vaPCPage = ~0; // make Memory.isSamePage(vaPage, va) false
	}
	// read and write PC
	public static int PC() {
		return pc;
	}
	public static void PC(int newValue) {
		pc = Types.toCARD16(newValue);
	}
	
	private static int vaPCPage;
	private static int raPCPage;
	private static int vaPC;
	private static int raPC;
	
	private static int vaPCLast;
	private static int wordLast;
	
	// getCodeByte returns one byte pointed by PC and CB
	// increment PC by 1
	public static @Mesa.CARD8 int getCodeByte() {
		if (Perf.ENABLED) Perf.codeCacheCodeByte++;
		vaPC = cb + (pc / 2);
		if (vaPC == vaPCLast) {
			// point to same address again
			if (Perf.ENABLED) Perf.codeCacheSameLast++;
		} else {
			if (isSamePage(vaPCPage, vaPC)) {
				if (Perf.ENABLED) Perf.codeCacheSamePage++;
				raPC = raPCPage + (vaPC & Constants.PAGE_MASK);
			} else {
				if (Perf.ENABLED) Perf.codeCacheDiffPage++;
				raPC = fetch(vaPC);
				vaPCPage = vaPC & ~Constants.PAGE_MASK;
				raPCPage = raPC & ~Constants.PAGE_MASK;
			}
			wordLast = realMemory[raPC]; // toCARD8() is used, so don't care about sign extension
		}
		vaPCLast = vaPC;
		
		// increment pc
		pc = Types.toCARD16(pc + 1);
		
		// pc is already incremented, so even is odd and odd is even
		//                    even means odd   right       left        
		return Types.toCARD8(((pc & 1) == 0) ? wordLast : (wordLast >>> 8));
	}
	// getCodeByte return one word pointed bye PC and CB
	// increment PC by 2
	public static @Mesa.CARD16 int getCodeWord() {
		if (Perf.ENABLED) Perf.codeCacheCodeWord++;
		int left  = getCodeByte();
		int right = getCodeByte();
		
		return Types.toCARD16(left, right);
	}
	
	public static @Mesa.CARD16 int readCode(@Mesa.CARD16 int offset) {
		return read16(CB() + offset);
	}
}

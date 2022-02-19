package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Processor.*;

import yokwe.majuro.type.FieldSpec;
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
			error();
		}
		// vp: [rpSize .. vpSize)
		for(int i = rpSize; i < vpSize; i++) {
			maps[i].clear();
			maps[i].setVacant();
		}
	}
	
	private static Cache getCache(int vp) {
		return Cache.getCache(cacheArray, vp);
	}

	public static void clearCache(int vp) {
		getCache(vp).clear();
	}
	
	
	//
	// low level memory access
	//   fetch store mapFlag readReal writeReal
	public static Map map(@Mesa.POINTER int va) {
		if (Perf.ENABLED) Perf.map++;
		return maps[va >>> PAGE_BITS];
	}
	// fetch returns real address == offset of realMemory
	public static @Mesa.REAL_POINTER int fetch(@Mesa.POINTER int va) {
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
	// store returns real address == offset of realMemory
	public static @Mesa.REAL_POINTER int store(@Mesa.POINTER int va) {
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
	// realAddress returns real address without changing of cache and flags of map
	public static @Mesa.REAL_POINTER int realAddress(@Mesa.POINTER int va) {
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
	// real memory access
	//
	public static @Mesa.CARD16 int readReal16(@Mesa.REAL_POINTER int ra) {
		if (Perf.ENABLED) Perf.readReal16++;
		return Types.toCARD16(realMemory[ra]);
	}
	public static void writeReal16(@Mesa.REAL_POINTER int ra, @Mesa.CARD16 int newValue) {
		if (Perf.ENABLED) Perf.writeReal16++;
		realMemory[ra] = (short)newValue;
	}
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
	// memory read and write
	//
	public static @Mesa.CARD16 int read16(@Mesa.POINTER int va) {
		if (Perf.ENABLED) Perf.read16++;
		return readReal16(fetch(va));
	}
	public static void write16(@Mesa.POINTER int va, @Mesa.CARD16 int newValue) {
		if (Perf.ENABLED) Perf.write16++;
		writeReal16(store(va), newValue);
	}
	private static final int BYTE_LEFT_MASK = BYTE_MASK << BYTE_BITS;
	public static @Mesa.CARD8 int read8(@Mesa.POINTER int ptr, int offset) {
		int word = read16(ptr + offset / 2);
		if ((offset & 1) == 0) {
			// returns left
			return Types.toCARD8((word >> BYTE_BITS) & BYTE_MASK);
		} else {
			// returns right
			return Types.toCARD8(word & BYTE_MASK);
		}
	}
	public static void write8(@Mesa.POINTER int ptr, int offset, @Mesa.CARD8 int data) {
		int ra = fetch(ptr + offset / 2);
		int word = readReal16(ra);
		
		if ((offset & 1) == 0) {
			// modify left
			writeReal16(ra, ((data << 8) & BYTE_LEFT_MASK) | (word & BYTE_MASK));
		} else {
			// modify right
			writeReal16(ra, (word & BYTE_LEFT_MASK) | (data & BYTE_MASK));
		}
	}
	

	// 2.3.1 Long Types
	// When these types are stored in memory, the low-order (least significant) sixteen bits
	// occupy the first memory word (at the lower numbered address), and the high-order (most
	// significant) sixteen bits occupy the second memory word(at the higher memory address).
	//         |15    31|0   15|
	// address  n        n+1    n+2
	public static boolean isSamePage(@Mesa.POINTER int a, @Mesa.POINTER int b) {
		return (a & ~PAGE_MASK) == (b & ~PAGE_MASK);
	}
	public static @Mesa.CARD32 int read32(@Mesa.POINTER int va) {
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
	public static void write32(@Mesa.POINTER int va, @Mesa.CARD32 int newValue) {
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
	// MDS
	//
	// treat sa as short pointer
	public static int lengthenMDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.lengthenMDS++;
		return Processor.MDS + Types.toCARD16(sa);
	}
	
	// convenience methods for MDS data access
	// treat sa as short pointer
	public static @Mesa.REAL_POINTER int fetchMDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.fetchMDS++;
		return fetch(lengthenMDS(sa));
	}
	public static @Mesa.REAL_POINTER int storeMDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.storeMDS++;
		return store(lengthenMDS(sa));
	}
	public static @Mesa.CARD16 int read16MDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.read16MDS++;
		return read16(lengthenMDS(sa));
	}
	public static void write16MDS(@Mesa.SHORT_POINTER int sa, @Mesa.CARD16 int newValue) {
		if (Perf.ENABLED) Perf.write16MDS++;
		write16(lengthenMDS(sa), newValue);
	}
	public static @Mesa.CARD32 int read32MDS(@Mesa.SHORT_POINTER int sa) {
		if (Perf.ENABLED) Perf.read32MDS++;
		return read32(lengthenMDS(sa));
	}
	public static void write32MDS(@Mesa.SHORT_POINTER int sa, @Mesa.CARD32 int newValue) {
		if (Perf.ENABLED) Perf.write32MDS++;
		write32(lengthenMDS(sa), newValue);
	}
	public static @Mesa.CARD8 int read8MDS(@Mesa.SHORT_POINTER int sa, int offset) {
		return read8(lengthenMDS(sa), offset);
	}
	public static void write8MDS(@Mesa.SHORT_POINTER int sa, int offset, @Mesa.CARD8 int data) {
		write8(lengthenMDS(sa), offset, data);
	}

	
	//
	// PDA
	//
	public static @Mesa.POINTER int lengthenPDA(@Mesa.PDA_POINTER int value) {
		if (Perf.ENABLED) Perf.lengthenPDA++;
		return Constants.mPDA + value;
	}

	
	//
	// CodeCache
	//
	private static int cb;
	private static int pc;

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
		//       even means odd   right       left        
		return Types.toCARD8(((pc & 1) == 0) ? wordLast : (wordLast >>> 8));
	}
	public static @Mesa.CARD16 int getCodeWord() {
		if (Perf.ENABLED) Perf.codeCacheCodeWord++;
		int left  = getCodeByte();
		int right = getCodeByte();
		
		return Types.toCARD16((left << 8) | right);
	}

	
	//
	// Field
	//
	
	private static int maskTable(int n) {
		return ((1 << (n + 1)) - 1) & 0xFFFF;
	}
//	private static final int[] MASK_TABLE = {
//			0b0000_0000_0000_0001,
//			0b0000_0000_0000_0011,
//			0b0000_0000_0000_0111,
//			0b0000_0000_0000_1111,
//			0b0000_0000_0001_1111,
//			0b0000_0000_0011_1111,
//			0b0000_0000_0111_1111,
//			0b0000_0000_1111_1111,
//			0b0000_0001_1111_1111,
//			0b0000_0011_1111_1111,
//			0b0000_0111_1111_1111,
//			0b0000_1111_1111_1111,
//			0b0001_1111_1111_1111,
//			0b0011_1111_1111_1111,
//			0b0111_1111_1111_1111,
//			0b1111_1111_1111_1111,
//	};
	
	public static @Mesa.CARD16 int readField(@Mesa.CARD16 int source, @Mesa.CARD8 int spec8) {
		FieldSpec spec = FieldSpec.value(Types.toCARD8(spec8));
		
		int size = spec.size();
		int shift = WORD_BITS - (spec.pos() + size - 1);
		if (shift < 0) error();
		
		return Types.toCARD16((source >>> shift) & maskTable(size));
	}
	public static @Mesa.CARD16 int writeField(@Mesa.CARD16 int dest, @Mesa.CARD8 int spec8, @Mesa.CARD16 int data) {
		FieldSpec spec = FieldSpec.value(Types.toCARD8(spec8));
		
		int size  = spec.size();
		int shift = WORD_BITS - (spec.pos() + size - 1);
		if (shift < 0) error();
		int mask = maskTable(size) << shift;
		
		return Types.toCARD16((dest & ~mask) | ((data << shift) & mask));
	}

}

/*******************************************************************************
 * Copyright (c) 2020, Yasuhiro Hasegawa
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. The name of the author may not be used to endorse or promote products derived
 *      from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *******************************************************************************/
package yokwe.majuro.mesa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Type.BytePair;
import yokwe.majuro.mesa.Type.LongNumber;

public final class Memory {
	private static final Logger logger = LoggerFactory.getLogger(Memory.class);
	
	// special memory location
	public static final /* PAGE_NUMBER */  int IO_REGION_PAGE = 0x80;
	
	public static final /* LONG_POINTER */ int GFT = Constant.mGFT;
	public static final /* POINTER */      int AV  = Constant.mAV;  // POINTER TO AllocationVector
	public static final /* POINTER */      int SD  = Constant.mSD;
	public static final /* POINTER */      int ETT = Constant.mETT;
	public static final /* LONG_POINTER */ int PDA = Constant.mPDA;
	
	
	// SD: POINTER TO SystemData = LOOPHOLE[mSD];
	// SystemData: TYPE = ARRAY SDIndex OF ControlLink;
	// SDIndex: TYPE = [0..256);
	public static final int SIZE_ControlLink = 2;
	public static final int offsetSD(int index) {
		return SD + (index & 0xFF) * SIZE_ControlLink;
	}
	
	// EscTrapTable: TYPE = ARRAY BYTE OF ControlLink;
	public static final int offsetETT(int index) {
		return ETT + (index & 0xFF) * SIZE_ControlLink;
	}

	
	


	// WORD_SIZE is number of bits in one WORD
	public static final int WORD_SIZE_BITS = 4;
	public static final int WORD_SIZE      = 1 << WORD_SIZE_BITS;

	// PAGE_SIZE is number of WORD in one PAGE
	public static final int PAGE_SIZE_BITS   = 8;
	public static final int PAGE_SIZE        = 1 << PAGE_SIZE_BITS;
	public static final int PAGE_OFFSET_MASK = PAGE_SIZE - 1;
	public static final int PAGE_END         = PAGE_SIZE - 1;
	
	// MAX_RM_PAGE is maximum number of PAGE for real memory
	public static final int MAX_VM_BITS = 30;
	public static final int MAX_RM_BITS = 24;
	public static final int MAX_RM_PAGE = 4086 * WORD_SIZE;
	
	
	private static int vmBits = 0;
	private static int rmBits = 0;
	private static int vmPage = 0;
	private static int rmPage = 0;
	
	private static short[][]  rmPages;
	private static Cache[]    cache;
	private static MapFlags[] mapFlags;
	
	public static void stats() {
		int used = 0;
		for(int i = 0; i < cache.length; i++) {
			if (cache[i].vp != 0) used++;
		}
		
		logger.info(String.format("Cache %5d / %5d", used, cache.length));
	}

	
	public static void initialize(int vmBits_, int rmBits_) {
		vmBits = vmBits_;
		rmBits = rmBits_;
		
		if (MAX_VM_BITS < vmBits) throw new UnexpectedException();
		if (MAX_RM_BITS < rmBits) throw new UnexpectedException();
		
		int vmSize = 1 << vmBits;
		int rmSize = 1 << rmBits;
		
		vmPage = vmSize / PAGE_SIZE;
		rmPage = rmSize / PAGE_SIZE;
		if (MAX_RM_PAGE < rmPage) rmPage = MAX_RM_PAGE;

		rmPages  = new short[rmPage][PAGE_SIZE];
		cache    = new Cache[HASH_SIZE];
		mapFlags = new MapFlags[vmPage];

		// initialize element of cache
		for(int i = 0; i < cache.length; i++) {
			cache[i] = new Cache();
		}

		// initialize element of mapFlags
		for(int i = 0; i < mapFlags.length; i++) {
			mapFlags[i] = new MapFlags();
		}
		
		// See APilot/15.3/Pilot/Private/GermOpsImpl.mesa
		// The BOOTING ACTION defined by the Principles of Operation should include:
		// 	   1. Put real memory behind any special processor pages (I/O pages); then put all remaining usable real memory behind other virtual memory pages beginning at virtual page 0, and working upward sequentially (skipping any already-mapped special processor pages).
		// 	   2. Read consecutive pages of the Germ into virtual memory beginning at page Boot.pageGerm + Boot.mdsiGerm*Environment.MaxPagesInMDS (i.e page 1).  At present, the way the initial microcode figures out the device and device address of the Germ, and the number of pages which comprise the Germ, is processor-dependent.
		// 	   3. Set Boot.pRequest� to e.g. [Boot.currentRequestBasicVersion, Boot.bootPhysicalVolume, locationOfPhysicalVolume].
		// 	   4. Set WDC>0, NWW=0, MDS=Boot.mdsiGerm, STKP=0.
		// 	   5. Xfer[dest: Boot.pInitialLink].

		// set default value for mapFlags
		// map all real page to virtual page
		int rp = 0;
		// map real page page [ioRegionPage..256)
		for (int i = IO_REGION_PAGE; i < 256; i++) {
			mapFlags[i].set(rp++, MapFlags.CLEAR);
		}
		// then map real page [0..ioRegionPage)
		for (int i = 0; i < IO_REGION_PAGE; i++) {
			mapFlags[i].set(rp++, MapFlags.CLEAR);
		}
		// then map real page [256..rmPage)
		for (int i = 256; i < rmPage; i++) {
			mapFlags[i].set(rp++, MapFlags.CLEAR);
		}
		// then set remaining virtual page with vacant [rmPage..vmPage)
		for (int i = rmSize; i < vmPage; i++) {
			mapFlags[i].set(0, MapFlags.VACANT);
		}
	}
	
	public static void invalidate(/* PAGE_NUMBER */ int vp) {
		Cache p = cache[hash(vp)];
		// if cache entry contains vp data, initialize cache entry.
		if (p.vp == vp) p.init();
	}
	
	//
	// raw level access to memory for unit test
	//
	public static short[] rawPage(/* LONG_POINTER */ int va) {
		return rmPages[mapFlags[va >>> PAGE_SIZE_BITS].rmPage];
	}
	public static /* CARD16 */ int rawRead(/* LONG_POINTER */ int va) {
		return (rawPage(va)[va & PAGE_OFFSET_MASK]) & 0xFFFF;
	}
	public static void rawWrite(/* LONG_POINTER */ int va, /* CARD16 */ int newValue) {
		rawPage(va)[va & PAGE_OFFSET_MASK] = (short)newValue;
	}
	public static /* CARD32 */ int rawReadDbl(/* LONG_POINTER */ int va) {
		int t0 = rawRead(va + 0); // low  word - MESA use little endian
		int t1 = rawRead(va + 1); // high word - MESA use little endian
		
		return LongNumber.make(t1, t0);
	}
	public static void rawWriteDbl(/* LONG_POINTER */ int va, /* CARD32 */ int newValue) {
		int t0 = LongNumber.lowHalf(newValue);
		int t1 = LongNumber.highHalf(newValue);
		
		rawWrite(va + 0, t0); // low  word - MESA use little endian
		rawWrite(va + 1, t1); // high word - MESA use little endian
	}

	//
	// map operation
	//
	MapFlags readMap(/* PAGE_NUMBER */ int vp) {
		MapFlags mf = mapFlags[vp];
		if (mf.isVacant()) mf.rmPage = 0;
		return mf;
	}
	void writeMap(/* PAGE_NUMBER */ int vp, MapFlags mf) {
		if (Perf.ENABLE) Perf.writeMap++;
		if (mf.isVacant()) mf.rmPage = 0;
		mapFlags[vp] = mf;
		
		invalidate(vp);
	}
	

	// page level memory access
	public static short[] fetchPage(/* LONG_POINTER */ int va) {
		if (Perf.ENABLE) Perf.fetchPage++;
		
		int vp = va >>> PAGE_SIZE_BITS;

		Cache p = cache[hash(vp)];
		if (p.vp != vp) {
			if (Perf.ENABLE) {
				if (p.vp != 0) Perf.cacheMissConflict++;
				else Perf.cacheMissEmpty++;
			}
			// Fill cache entry
			MapFlags mf = mapFlags[vp];
			mf.checkFetch(va);
			mf.updateFlagFetch();
			
			p.vp   = vp;
			p.mf   = mf;
			p.page = rmPages[mf.rmPage];
		} else {
			if (Perf.ENABLE) Perf.cacheHit++;
		}
		return p.page;
	}
	public static short[] storePage(/* LONG_POINTER */ int va) {
		if (Perf.ENABLE) Perf.storePage++;

		int vp = va >>> PAGE_SIZE_BITS;

		Cache p = cache[hash(vp)];
		if (p.vp != vp) {
			if (Perf.ENABLE) {
				if (p.vp != 0) Perf.cacheMissConflict++;
				else Perf.cacheMissEmpty++;
			}
			// Fill cache entry
			MapFlags mf = mapFlags[vp];
			mf.checkStore(va);
			mf.updateFlagStore();

			p.vp   = vp;
			p.mf   = mf;
			p.page = rmPages[mf.rmPage];
		} else {
			if (Perf.ENABLE) Perf.cacheHit++;
			if (!p.mf.isReferencedDirty()) p.mf.updateFlagStore();
		}
		return p.page;
	}

	// low level memory access
	public static /* CARD16 */ int fetch(/* LONG_POINTER */ int va) {
		if (Perf.ENABLE) Perf.fetch++;
		return fetchPage(va)[va & PAGE_OFFSET_MASK] & 0xFFFF;
	}
	public static void store(/* LONG_POINTER */ int va, /* CARD16 */ int newValue) {
		if (Perf.ENABLE) Perf.store++;
		storePage(va)[va & PAGE_OFFSET_MASK] = (short)newValue;
	}
	public static /* CARD32 */ int readDbl(/* LONG_POINTER */ int va) {
		if (Perf.ENABLE) Perf.readDbl++;
		
		short[] page = fetchPage(va);
		int     vo   = va & PAGE_OFFSET_MASK;
		if (vo != PAGE_END) {
			//                     high word      low word
			return LongNumber.make(page[vo + 1], page[vo]);
		} else {
			//                     high word             low word
			return LongNumber.make(fetchPage(va + 1)[0], page[PAGE_END]);
		}
	}
	public static void writeDbl(/* LONG_POINTER */ int va, /* CARD32 */ int newValue) {
		if (Perf.ENABLE) Perf.writeDbl++;
		
		short[] page = storePage(va);
		int     vo   = va & PAGE_OFFSET_MASK;
		if (vo != PAGE_END) {
			page[vo + 0] = (short)LongNumber.lowHalf(newValue);  // low  word - MESA use little endian
			page[vo + 1] = (short)LongNumber.highHalf(newValue); // high word - MESA use little endian
		} else {
			short[] page1 = storePage(va + 1);
			page [PAGE_END] = (short)LongNumber.lowHalf(newValue);  // low  word - MESA use little endian
			page1[0]        = (short)LongNumber.highHalf(newValue); // high word - MESA use little endian
		}
	}
	
	public interface ToIntBiIntFunction {
		int apply(int value, int newValue);
	}
	public static void modify(/* LONG_POINTER */ int va, ToIntBiIntFunction valueFunc, /* CARD16 */ int newValue) {
		if (Perf.ENABLE) Perf.modify++;
		short[] page = storePage(va);
		int vo = va & PAGE_OFFSET_MASK;
		page[vo] = (short)valueFunc.apply(page[vo] & 0xFFFF, newValue & 0xFFFF);
	}
	public static void modifyDbl(/* LONG_POINTER */ int va, ToIntBiIntFunction valueFunc, /* CARD32 */ int newValue) {
		if (Perf.ENABLE) Perf.modifyDbl++;
		short[] page = storePage(va);
		int     vo   = va & PAGE_OFFSET_MASK;
		if (vo != PAGE_END) {
			int value = valueFunc.apply(LongNumber.make(page[vo + 1], page[vo]), newValue);
			page[vo + 0] = (short)LongNumber.lowHalf(value);  // low  word - MESA use little endian
			page[vo + 1] = (short)LongNumber.highHalf(value); // high word - MESA use little endian
		} else {
			short[] page1 = storePage(va + 1);
			int value = valueFunc.apply(LongNumber.make(page1[0], page[PAGE_END]), newValue);
			page [PAGE_END] = (short)LongNumber.lowHalf(value);  // low  word - MESA use little endian
			page1[0]        = (short)LongNumber.highHalf(value); // high word - MESA use little endian
		}
	}

	
	// displayPage
	private static int displayPage = 0;
	public static void reserveDisplayPage(int displayPage_) {
		if (displayPage_ <= 0) throw new UnexpectedException();
		if (displayPage != 0) throw new UnexpectedException();
		
		displayPage = displayPage_;
		// unmap real page from last real page for display page
		for(int i = rmPage - displayPage; i < rmPage; i++) {
			mapFlags[i].set(0, MapFlags.VACANT);
		}
	}
	
	// mds
	private static /* LONG_POINTER */ int mds;
	public static /* LONG_POINTER */ int getMDS() {
		return mds;
	}
	public static void setMDS(/* LONG_POINTER */ int newValue) {
		mds = newValue;
	}

	public static /* LONG_POINTER */ int lengthenPointer(/* POINTER */ int pointer) {
		return mds | (pointer & 0xFFFF);
	}
	public static /* CARD16 */ int fetchMDS(/* POINTER */ int pointer) {
		if (Perf.ENABLE) Perf.fetchMDS++;
		int va = lengthenPointer(pointer);
		return fetchPage(va)[va & PAGE_OFFSET_MASK] & 0xFFFF;
	}
	public static void storeMDS(/* POINTER */ int pointer, /* CARD16 */ int newValue) {
		if (Perf.ENABLE) Perf.storeMDS++;
		int va = lengthenPointer(pointer);
		fetchPage(va)[va & PAGE_OFFSET_MASK] = (short)newValue;
	}
	public static /* CARD32 */ int readDblMDS(/* POINTER */ int pointer) {
		if (Perf.ENABLE) Perf.readDblMDS++;
		return readDbl(lengthenPointer(pointer));
	}
	public static void writeDblMDS(/* POINTER */ int pointer, /* CARD32 */ int newValue) {
		if (Perf.ENABLE) Perf.writeDblMDS++;
		writeDbl(lengthenPointer(pointer), newValue);
	}
	public static void modifyMDS(/* POINTER */ int pointer, ToIntBiIntFunction valueFunc, /* CARD16 */ int newValue) {
		if (Perf.ENABLE) Perf.modifyMDS++;
		modify(lengthenPointer(pointer), valueFunc, newValue);
	}
	public static void modifyDblMDS(/* POINTER */ int pointer, ToIntBiIntFunction valueFunc, /* CARD32 */ int newValue) {
		if (Perf.ENABLE) Perf.modifyDblMDS++;
		modifyDbl(lengthenPointer(pointer), valueFunc, newValue);
	}

	// code
	public static /* CARD16 */ int readCode(/* CARD16 */ int offset) {
		if (Perf.ENABLE) Perf.readCode++;
		int va = CodeCache.getCB() + (offset & 0xFFFF);
		return fetchPage(va)[va & PAGE_OFFSET_MASK] & 0xFFFF;
	}
	
	// byte
	public static /* CARD8 */ int fetchByte(/* LONG_POINTER */ int ptr, /* CARD32 */ int offset) {
		if (Perf.ENABLE) Perf.fetchByte++;
		int word = fetch(ptr + (offset >>> 1));
		if ((offset & 1) == 0) {
			return BytePair.left(word);
		} else {
			return BytePair.right(word);
		}
	}
	public static /* CARD16 */ int fetchWord(/* LONG_POINTER */ int ptr, /* CARD32 */ int offset) {
		if (Perf.ENABLE) Perf.fetchWord++;
		int     va   = ptr + (offset >>> 1);
		int     vo   = va & PAGE_OFFSET_MASK;
		short[] page = fetchPage(va);
		
		if ((offset & 1) == 0) {
			return page[vo] & 0xFFFF;
		} else {
			if (vo != PAGE_END) {
				return BytePair.make(page[vo + 0], page[vo + 1] >> 8);
			} else {
				return BytePair.make(page[vo + 0], fetchPage(va + 1)[0] >> 8);
			}
		}
	}
	public static void storeByte(/* LONG_POINTER */ int ptr, /* CARD32 */ int offset, /* CARD8 */ int data) {
		if (Perf.ENABLE) Perf.storeByte++;
		int     va   = ptr + (offset >>> 1);
		int     vo   = va & PAGE_OFFSET_MASK;
		short[] page = storePage(va);

		int word = page[vo];
		if ((offset & 1) == 0) {
			page[vo] = (short)BytePair.make(data, word);
		} else {
			page[vo] = (short)((word & 0xFF00) | (data & 0x00FF));
		}
	}

	// field
	// 7.5 Field Instruction
	//const UNSPEC Field_MaskTable[WordSize] = {
    //			0x0001, 0x0003, 0x0007, 0x000f, 0x001f, 0x003f, 0x007f, 0x00ff,
    //			0x01ff, 0x03ff, 0x07ff, 0x0fff, 0x1fff, 0x3fff, 0x7fff, 0xffff
	//};
	public static /* CARD16 */ int fieldMask(int n) {
		return ((1 << (n + 1)) - 1) & 0xFFFF;
	}
	public static /* CARD16 */ int readField(/* CARD16 */ int source, /* FIELD_SPEC */ int spec8) {
		if (Perf.ENABLE) Perf.readField++;
		int pos   = (spec8 >> 4) & 0x0F;
		int size  = spec8        & 0x0F;
		int shift = Memory.WORD_SIZE - (pos + size + 1);
		
		return (source >>> shift) & fieldMask(size);
	}
	
	public static /* CARD16 */ int writeField(/* CARD16 */ int dest, /* CARD8 */ int spec8, /* CARD16 */ int data) {
		if (Perf.ENABLE) Perf.writeField++;
		int pos   = (spec8 >> 4) & 0x0F;
		int size  = spec8        & 0x0F;
		int shift = Memory.WORD_SIZE - (pos + size + 1);
		int mask  = fieldMask(size) << shift;
		
		return (dest & ~mask) | ((data << shift) & mask);
	}
	
	// PDA
	public static /* LONG_POINTER */ int lengthenPDA(/* PDA_POINTER */ int ptr) {
		return PDA | (ptr & 0xFFFF);
	}
	public static /* PDA_POINTER */ int offsetPDA(/* LONG_POINTER */ int ptr) {
		if ((ptr & 0xFFFF0000) != PDA) throw new UnexpectedException();
		return ptr & 0xFFFF;
	}
	public static /* CARD16 */ int fetchPDA(/* PDA_POINTER */ int ptr) {
		if (Perf.ENABLE) Perf.fetchPDA++;
		return fetch(lengthenPDA(ptr));
	}
	public static void storePDA(/* PDA_POINTER */ int ptr, /* CARD16 */ int newValue) {
		if (Perf.ENABLE) Perf.storePDA++;
		store(lengthenPDA(ptr), newValue);
	}

	
	//
	// Cache
	//
	private static final int HASH_BIT = 14;
	private static final int HASH_SIZE = 1 << HASH_BIT;
	private static final int HASH_MASK = (1 << HASH_BIT) - 1;
	private static int hash(/* PAGE_NUMBER */ int vp_) {
		return ((vp_ >> HASH_BIT) ^ vp_) & HASH_MASK;
		// NOTE above expression calculate better hash value than "vp_ & MASK"
	}

	private static class Cache {
		int      vp;
		MapFlags mf;
		short[]  page;
		
		Cache() {
			init();
		}
		void init() {
			vp        = 0;
			mf        = null;
			page      = null;
		}
	};
}

package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.type.MemoryAccess.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.type.AVItemType;
import yokwe.majuro.type.AllocationVector;
import yokwe.majuro.type.FSIndex;
import yokwe.majuro.type.GlobalOverhead;
import yokwe.majuro.type.LocalOverhead;

public class Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	protected static final int DEFAULT_VMBITS = 24;
	protected static final int DEFAULT_RMBITS = 20;
	
	protected static final int DEFAULT_CB  = 0x0003_0000;
	protected static final int DEFAULT_MDS = 0x0004_0000;
	protected static final int DEFAULT_GF  = 0x0005_0000;
	protected static final int DEFAULT_PC  = 0x0020;
	protected static final int DEFAULT_GFI = 1;
	
	int ra_PDA;
	int ra_GFT;
	int ra_CB;
	int ra_MDS;
	int ra_AV;
	int ra_SD;
	int ra_ETT;
	int ra_LF;
	int ra_GF;

	
	@BeforeAll
	protected static void beforeAll() {
		logger.info("beforeAll");
		Memory.init(DEFAULT_VMBITS, DEFAULT_RMBITS);
	}
	
	@AfterAll
	protected static void afterAll() {
		logger.info("afterAll");
		System.gc();
	}
	
	
	private static final int[] FRAME_SIZE_MAP = {
	       8,   12,   16,   20,   24,
		  28,   32,   40,   48,   56,
		  68,   80,   96,  112,  128,
		 148,  168,  192,  224,  252,
		 508,  764, 1020, 1276, 1532,
		1788, 2044, 2556, 3068, 3580, 4092,
	};
	private static final int[] FRAME_WEIGHT_MAP = {
		20, 26, 15, 16, 16,
		12,  8,  8,  5,  5,
		 7,  2,  2,  1,  1,
		 1,  1,  1,  1,  0,
		 0,  0,  0,  0,  0,
		 0,  0,  0,  0,  0, 0,
	};

	private static void initAV(int origin, int limit) {
		if (FRAME_SIZE_MAP.length != FRAME_WEIGHT_MAP.length) throw new UnexpectedException();
		
		AllocationVector av = AllocationVector.pointer(mAV, WRITE);
		
		for(int i = 0; i <= FSIndex.MAX_VALUE; i++) {
			av.get(i).write(AVItemType.EMPTY);
		}
		
		int p = origin;
		for(int fsi = 0; fsi < FRAME_SIZE_MAP.length; fsi++) {
			int size   = FRAME_SIZE_MAP[fsi];
			int weight = FRAME_WEIGHT_MAP[fsi];
			if (weight == 0) continue;
			
			for(int j = 0; j < weight; j++) {
				// align (p mod 4) == 0
				p = (p + 3) & ~0x03;

				// round up to next page boundary
				if (((p + 8) % PAGE_SIZE) < (p % PAGE_SIZE)) {
					p = (p + PAGE_SIZE - 1) & ~PAGE_MASK;
				}
				
				LocalOverhead lo = LocalOverhead.pointer(p, WRITE);
				lo.word().write(fsi);
				lo.returnlink().write(0);
				lo.globallink().write(0);
				lo.pc().write(0);
				lo.local().get(0).write(AVItemType.EMPTY);
				
				av.get(fsi).write(p + LocalOverhead.WORD_SIZE);
				
				p = p + size;
			}
		}
	}
	
	@BeforeEach
	protected void beforeEach() {
//		logger.info("beforeEach START");
		// clear variable
		Memory.clear();
		Processor.clear();
		
		// set default values
		Memory.CB(DEFAULT_CB + 0x80);
		Memory.PC(DEFAULT_PC);
		Processor.MDS = DEFAULT_MDS;
		Processor.GF  = DEFAULT_GF + 0x80 + GlobalOverhead.WORD_SIZE;
		
		// get real address
		ra_PDA = Memory.realAddress(mPDA);
		ra_GFT = Memory.realAddress(mGFT);
		ra_CB  = Memory.realAddress(Processor.MDS);
		ra_AV  = Memory.realAddress(Processor.MDS + mAV);
		ra_SD  = Memory.realAddress(Processor.MDS + mSD);
		ra_ETT = Memory.realAddress(Processor.MDS + mETT);
		ra_GF  = Memory.realAddress(Processor.GF);
		
		// initialize memory
		{
			int p = Memory.realAddress(DEFAULT_CB);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, 0x3000 + i);
		}
		{
			int p = Memory.realAddress(DEFAULT_CB + 0x100);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, 0x3000 + 0x100 + i);
		}
		{
			int p = Memory.realAddress(DEFAULT_CB + 0x200);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, 0x3000 + 0x200 + i);
		}
		{
			int p = Memory.realAddress(DEFAULT_MDS);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, 0x4000 + i);
		}
		{
			int p = Memory.realAddress(DEFAULT_MDS + 0x1000);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, 0x4100 + i);
		}
		{
			int p = Memory.realAddress(DEFAULT_GF);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, 0x5000 + i);
		}
		
		initAV(0x0600, 0x1aff);
		
		// clear cache and map flags again for initAV()
		Memory.clearCacheAndMapFlags();
//		logger.info("beforeEach STOP");
	}

	@AfterEach
	protected void afterEach() {
		System.gc();
	}

}

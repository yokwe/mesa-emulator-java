package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.PAGE_MASK;
import static yokwe.majuro.mesa.Constants.PAGE_SIZE;
import static yokwe.majuro.mesa.Constants.mAV;
import static yokwe.majuro.mesa.Constants.mETT;
import static yokwe.majuro.mesa.Constants.mGFT;
import static yokwe.majuro.mesa.Constants.mPDA;
import static yokwe.majuro.mesa.Constants.mSD;
import static yokwe.majuro.type.MemoryAccess.WRITE;
import static yokwe.majuro.type.MemoryAccess.WRITE_READ;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.opcode.Interpreter;
import yokwe.majuro.type.PrincOps.AVItem;
import yokwe.majuro.type.PrincOps.AVItemType;
import yokwe.majuro.type.PrincOps.AllocationVector;
import yokwe.majuro.type.PrincOps.BLOCK;
import yokwe.majuro.type.PrincOps.EscTrapTable;
import yokwe.majuro.type.PrincOps.FSIndex;
import yokwe.majuro.type.PrincOps.GFTIndex;
import yokwe.majuro.type.PrincOps.GlobalFrameTable;
import yokwe.majuro.type.PrincOps.GlobalOverhead;
import yokwe.majuro.type.PrincOps.LinkType;
import yokwe.majuro.type.PrincOps.LocalOverhead;
import yokwe.majuro.type.PrincOps.NewProcDesc;
import yokwe.majuro.type.PrincOps.SystemData;

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
	int ra_PC;
	
	int GFI_GF;
	int GFI_SD;
	int GFI_ETT;
	int GFI_EFC;
	
	int pc_SD;
	int pc_ETT;
	
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

	private void initAV(int origin, int limit) {
		if (FRAME_SIZE_MAP.length != FRAME_WEIGHT_MAP.length) throw new UnexpectedException();
		
		AVItem avItemEmpty = AVItem.value().data(0).tag(AVItemType.EMPTY);

		AllocationVector av = AllocationVector.pointer(mAV, WRITE_READ);
		
		for(int i = 0; i <= FSIndex.MAX_VALUE; i++) {
			av.get(i).write(avItemEmpty);
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
				lo.local().get(0).write(avItemEmpty);
				
				av.get(fsi).write(p + LocalOverhead.WORD_SIZE);
				
				p = p + size;
			}
		}
		
		int fsi = 10;
		Processor.LF = av.get(fsi).read();
		av.get(fsi).write(Memory.read16MDS(Processor.LF));
	}
	private void initSD() {
		SystemData  sd   = SystemData.pointer(mSD, WRITE);
		BLOCK       cb   = BLOCK.longPointer(Memory.CB(), WRITE);
		NewProcDesc item = NewProcDesc.value().taggedGFI(GFI_SD | LinkType.NEW_PROCEDURE);

		for(int i = 0; i < 256; i++) {
			item.pc(pc_SD | i);
			sd.get(i).write(item);
			cb.get(item.pc() / 2).write(0);
		}
	}
	private void initETT() {
		EscTrapTable ett  = EscTrapTable.pointer(mETT, WRITE);
		BLOCK        cb   = BLOCK.longPointer(Memory.CB(), WRITE);
		NewProcDesc  item = NewProcDesc.value().taggedGFI(GFI_ETT | LinkType.NEW_PROCEDURE);

		for(int i = 0; i < 256; i++) {
			item.pc(pc_ETT | i);
			ett.get(i).write(item.value);
			cb.get(item.pc() / 2).write(0);
		}
	}
	private void initGFT() {
		GlobalFrameTable gft = GlobalFrameTable.longPointer(mGFT, WRITE);
		for(int i = 0; i < GFTIndex.MAX_VALUE; i++) {
			gft.get(i).codebaseValue(0).globalFrameValue(0);
		}
		
		// GFI_GF
		gft.get(GFI_GF).globalFrameValue(Processor.GF).codebaseValue(Memory.CB());
		// GFI_SD
		gft.get(GFI_SD).globalFrameValue(Processor.GF).codebaseValue(Memory.CB());
		// GFI_ETT
		gft.get(GFI_ETT).globalFrameValue(Processor.GF).codebaseValue(Memory.CB());
		// GFI_EFC
		gft.get(GFI_EFC).globalFrameValue(Processor.GF).codebaseValue(Memory.CB());
	}
	
	
	@BeforeAll
	protected static void beforeAll() {
		logger.info("beforeAll");
		Memory.init(DEFAULT_VMBITS, DEFAULT_RMBITS);
		Interpreter.init();
	}
	
	@AfterAll
	protected static void afterAll() {
		logger.info("afterAll");
		System.gc();
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
		Processor.GFI =  1;
		
		GFI_GF  =  4; // 1
		GFI_SD  =  8; // 2
		GFI_ETT = 12; // 3
		GFI_EFC = 16; // 4

		pc_SD  = 0x1000;
		pc_ETT = 0x2000;
		
		int avOrigin = 0x0600;
		int avLimit  = 0x1AFF;
		
		// initialize memory for test case
		// get real address
		ra_PDA = Memory.realAddress(mPDA);
		ra_GFT = Memory.realAddress(mGFT);
		ra_CB  = Memory.realAddress(Memory.CB());
		ra_MDS = Memory.realAddress(Processor.MDS);
		ra_AV  = Memory.realAddress(Processor.MDS + mAV);
		ra_SD  = Memory.realAddress(Processor.MDS + mSD);
		ra_ETT = Memory.realAddress(Processor.MDS + mETT);
		ra_GF  = Memory.realAddress(Processor.GF);
		ra_PC  = ra_CB + (Memory.PC() / 2);
		
		for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16((ra_CB & ~PAGE_MASK) + 0x000 + i, 0x3000 + i);
		for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16((ra_CB & ~PAGE_MASK) + 0x100 + i, 0x3100 + i);
		for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16((ra_CB & ~PAGE_MASK) + 0x200 + i, 0x3200 + i);
		for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(ra_MDS + 0x4000 + i, 0x4000 + i);
		for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(ra_MDS + 0x4100 + i, 0x4100 + i);
		for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16((ra_GF & ~PAGE_MASK) + i, 0x5000 + i);
		for(int i = avOrigin; i <= avLimit; i++) Memory.writeReal16(ra_MDS + i, i);

		initAV(avOrigin, avLimit);
		initSD();
		initETT();
		initGFT();
		
		// initAV() sets value of LF, so set ra_LF after initAV()
		ra_LF  = Memory.realAddress(Processor.MDS + Processor.LF);
		
		// clear cache and map flags again for initAV()
		Memory.clearCacheAndMapFlags();
//		logger.info("beforeEach STOP");
	}

	@AfterEach
	protected void afterEach() {
		System.gc();
	}

}

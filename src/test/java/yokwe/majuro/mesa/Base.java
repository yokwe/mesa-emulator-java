package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import yokwe.majuro.type.GlobalOverhead;

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
		Processor.MDS = DEFAULT_MDS;
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
		Perf.clear();
		
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
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, (char)(0x3000 + i));
		}
		{
			int p = Memory.realAddress(DEFAULT_CB + 0x100);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, (char)(0x3000 + 0x100 + i));
		}
		{
			int p = Memory.realAddress(DEFAULT_CB + 0x200);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, (char)(0x3000 + 0x200 + i));
		}
		{
			int p = Memory.realAddress(DEFAULT_MDS);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, (char)(0x4000 + i));
		}
		{
			int p = Memory.realAddress(DEFAULT_MDS + 0x1000);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, (char)(0x4100 + i));
		}
		{
			int p = Memory.realAddress(DEFAULT_GF);
			for(int i = 0; i < PAGE_SIZE; i++) Memory.writeReal16(p + i, (char)(0x5000 + i));
		}
				
		
//		logger.info("beforeEach STOP");
	}

	@AfterEach
	protected void afterEach() {
		Perf.stats();
		System.gc();
	}

}

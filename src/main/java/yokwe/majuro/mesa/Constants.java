package yokwe.majuro.mesa;

public final class Constants {
	//
	// MESA language related constant
	//
	public static final int WORD_BITS = 16;
	public static final int WORD_SIZE = 1 << WORD_BITS;
	public static final int WORD_MASK = WORD_SIZE - 1;
	
	public static final int BYTE_BITS = 8;
	public static final int BYTE_SIZE = 1 << BYTE_BITS;
	public static final int BYTE_MASK = BYTE_SIZE - 1;

	public static final int NIBBLE_BITS = 4;
	public static final int NIBBLE_SIZE = 1 << NIBBLE_BITS;
	public static final int NIBBLE_MASK = NIBBLE_SIZE - 1;

	//
	// Pilot related constants
	//
	public static final int PAGE_BITS = 8;
	public static final int PAGE_SIZE = 1 << PAGE_BITS;
	public static final int PAGE_MASK = PAGE_SIZE - 1;
	
	public static final int MAX_REALMEMORY_PAGE_SIZE = /* RealMemoryImplGuam::largestArraySize */ 4086 * WORD_BITS;
	
	
	//
	// PrincOps related constant
	//
	public static final int cSS = 14;
	// cSV = SIZE[StateVector] + MAX[SIZE[ControlLink], SIZE[FSIndex], SIZE[LONG POINTER]]
	// SIZE[StateVector] = 16 and SIZE[LONG POINTER] = 2. So cSV should be 16 + 2 = 18
	public static final int cSV = 18;
	//public static final int cWM = 10; // no one use this value - cWDC <= cWM
	public static final int cWDC = 7;
	//public static final int cTickMin = 15;
	//public static final int cTickMax = 60;
	// 1 tick = 40 milliseconds
	public static final int cTick = 40;

	public static final int mPDA = 0x00010000; // 0200000
	public static final int mGFT = 0x00020000; // 0400000

	public static final int mAV  = 0x0100; //  0400
	public static final int mSD  = 0x0200; // 01000
	public static final int mETT = 0x0400; // 02000

	public static final int qFrameFault        = 0;
	public static final int qPageFault         = 1;
	public static final int qWriteProtectFault = 2;

	public static final int sBoot            = 001; //  1
	public static final int sBoundsTrap      = 016; // 14
	public static final int sBreakTrap       = 000; //  0
	public static final int sCodeTrap        = 007; //  7
	public static final int sControlTrap     = 006; //  6
	public static final int sDivCheckTrap    = 013; // 11
	public static final int sDivZeroTrap     = 012; // 10
	public static final int sInterruptError  = 014; // 12
	public static final int sOpcodeTrap      = 005; //  5
	public static final int sPointerTrap     = 017; // 15
	public static final int sProcessTrap     = 015; // 13
	public static final int sRescheduleError = 003; //  3
	public static final int sStackError      = 002; //  2
	public static final int sUnboundTrap     = 011; //  9
	public static final int sXferTrap        = 004; //  4
	public static final int sHardwareError   = 010; //  8


	// Instruction code
	public static final int zNOOP    = 00;
	public static final int zLL0     = 01;
	public static final int zLL1     = 02;
	public static final int zLL2     = 03;
	public static final int zLL3     = 04;
	public static final int zLL4     = 05;
	public static final int zLL5     = 06;
	public static final int zLL6     = 07;

	public static final int zLL7     = 010;
	public static final int zLL8     = 011;
	public static final int zLL9     = 012;
	public static final int zLL10    = 013;
	public static final int zLL11    = 014;
	public static final int zLLB     = 015;
	public static final int zLLD0    = 016;
	public static final int zLLD1    = 017;

	public static final int zLLD2    = 020;
	public static final int zLLD3    = 021;
	public static final int zLLD4    = 022;
	public static final int zLLD5    = 023;
	public static final int zLLD6    = 024;
	public static final int zLLD7    = 025;
	public static final int zLLD8    = 026;
	public static final int zLLD10   = 027;

	public static final int zLLDB    = 030;
	public static final int zSL0     = 031;
	public static final int zSL1     = 032;
	public static final int zSL2     = 033;
	public static final int zSL3     = 034;
	public static final int zSL4     = 035;
	public static final int zSL5     = 036;
	public static final int zSL6     = 037;

	public static final int zSL7     = 040;
	public static final int zSL8     = 041;
	public static final int zSL9     = 042;
	public static final int zSL10    = 043;
	public static final int zSLB     = 044;
	public static final int zSLD0    = 045;
	public static final int zSLD1    = 046;
	public static final int zSLD2    = 047;

	public static final int zSLD3    = 050;
	public static final int zSLD4    = 051;
	public static final int zSLD5    = 052;
	public static final int zSLD6    = 053;
	public static final int zSLD8    = 054;
	public static final int zPL0     = 055;
	public static final int zPL1     = 056;
	public static final int zPL2     = 057;

	public static final int zPL3     = 060;
	public static final int zPLB     = 061;
	public static final int zPLD0    = 062;
	public static final int zPLDB    = 063;
	public static final int zLG0     = 064;
	public static final int zLG1     = 065;
	public static final int zLG2     = 066;
	public static final int zLGB     = 067;

	public static final int zLGD0    = 070;
	public static final int zLGD2    = 071;
	public static final int zLGDB    = 072;
	public static final int zSGB     = 073;
	public static final int zBNDCK   = 074;
	public static final int zBRK     = 075;
	//
	public static final int zSTC     = 077;

	public static final int zR0      = 0100;
	public static final int zR1      = 0101;
	public static final int zRB      = 0102;
	public static final int zRL0     = 0103;
	public static final int zRLB     = 0104;
	public static final int zRD0     = 0105;
	public static final int zRDB     = 0106;
	public static final int zRDL0    = 0107;

	public static final int zRDLB    = 0110;
	public static final int zW0      = 0111;
	public static final int zWB      = 0112;
	public static final int zPSB     = 0113;
	public static final int zWLB     = 0114;
	public static final int zPSLB    = 0115;
	public static final int zWDB     = 0116;
	public static final int zPSD0    = 0117;

	public static final int zPSDB    = 0120;
	public static final int zWDLB    = 0121;
	public static final int zPSDLB   = 0122;
	public static final int zRLI00   = 0123;
	public static final int zRLI01   = 0124;
	public static final int zRLI02   = 0125;
	public static final int zRLI03   = 0126;
	public static final int zRLIP    = 0127;

	public static final int zRLILP   = 0130;
	public static final int zRLDI00  = 0131;
	public static final int zRLDIP   = 0132;
	public static final int zRLDILP  = 0133;
	public static final int zRGIP    = 0134;
	public static final int zRGILP   = 0135;
	public static final int zWLIP    = 0136;
	public static final int zWLILP   = 0137;

	public static final int zWLDILP  = 0140;
	public static final int zRS      = 0141;
	public static final int zRLS     = 0142;
	public static final int zWS      = 0143;
	public static final int zWLS     = 0144;
	public static final int zR0F     = 0145;
	public static final int zRF      = 0146;
	public static final int zRL0F    = 0147;

	public static final int zRLF     = 0150;
	public static final int zRLFS    = 0151;
	public static final int zRLIPF   = 0152;
	public static final int zRLILPF  = 0153;
	public static final int zW0F     = 0154;
	public static final int zWF      = 0155;
	public static final int zPSF     = 0156;
	public static final int zPS0F    = 0157;

	public static final int zWS0F    = 0160;
	public static final int zWL0F    = 0161;
	public static final int zWLF     = 0162;
	public static final int zPSLF    = 0163;
	public static final int zWLFS    = 0164;
	public static final int zSLDB    = 0165;
	public static final int zSGDB    = 0166;
	public static final int zLLKB    = 0167;

	public static final int zRKIB    = 0170;
	public static final int zRKDIB   = 0171;
	public static final int zLKB     = 0172;
	public static final int zSHIFT   = 0173;
	public static final int zSHIFTSB = 0174;
	public static final int zMBP     = 0175;
	public static final int zRBP     = 0176;
	public static final int zWBP     = 0177;


	public static final int zCATCH   = 0200;
	public static final int zJ2      = 0201;
	public static final int zJ3      = 0202;
	public static final int zJ4      = 0203;
	public static final int zJ5      = 0204;
	public static final int zJ6      = 0205;
	public static final int zJ7      = 0206;
	public static final int zJ8      = 0207;

	public static final int zJB      = 0210;
	public static final int zJW      = 0211;
	public static final int zJEP     = 0212;
	public static final int zJEB     = 0213;
	public static final int zJEBB    = 0214;
	public static final int zJNEP    = 0215;
	public static final int zJNEB    = 0216;
	public static final int zJNEBB   = 0217;

	public static final int zJLB     = 0220;
	public static final int zJGEB    = 0221;
	public static final int zJGB     = 0222;
	public static final int zJLEB    = 0223;
	public static final int zJULB    = 0224;
	public static final int zJUGEB   = 0225;
	public static final int zJUGB    = 0226;
	public static final int zJULEB   = 0227;

	public static final int zJZ3     = 0230;
	public static final int zJZ4     = 0231;
	public static final int zJZB     = 0232;
	public static final int zJNZ3    = 0233;
	public static final int zJNZ4    = 0234;
	public static final int zJNZB    = 0235;
	public static final int zJDEB    = 0236;
	public static final int zJDNEB   = 0237;

	public static final int zJIB     = 0240;
	public static final int zJIW     = 0241;
	public static final int zREC     = 0242;
	public static final int zREC2    = 0243;
	public static final int zDIS     = 0244;
	public static final int zDIS2    = 0245;
	public static final int zEXCH    = 0246;
	public static final int zDEXCH   = 0247;

	public static final int zDUP     = 0250;
	public static final int zDDUP    = 0251;
	public static final int zEXDIS   = 0252;
	public static final int zNEG     = 0253;
	public static final int zINC     = 0254;
	public static final int zDEC     = 0255;
	public static final int zDINC    = 0256;
	public static final int zDBL     = 0257;

	public static final int zDDBL    = 0260;
	public static final int zTRPL    = 0261;
	public static final int zAND     = 0262;
	public static final int zIOR     = 0263;
	public static final int zADDSB   = 0264;
	public static final int zADD     = 0265;
	public static final int zSUB     = 0266;
	public static final int zDADD    = 0267;

	public static final int zDSUB    = 0270;
	public static final int zADC     = 0271;
	public static final int zACD     = 0272;
	public static final int zAL0IB   = 0273;
	public static final int zMUL     = 0274;
	public static final int zDCMP    = 0275;
	public static final int zUDCMP   = 0276;
	public static final int zVMFIND  = 0277;

	public static final int zLI0     = 0300;
	public static final int zLI1     = 0301;
	public static final int zLI2     = 0302;
	public static final int zLI3     = 0303;
	public static final int zLI4     = 0304;
	public static final int zLI5     = 0305;
	public static final int zLI6     = 0306;
	public static final int zLI7     = 0307;

	public static final int zLI8     = 0310;
	public static final int zLI9     = 0311;
	public static final int zLI10    = 0312;
	public static final int zLIN1    = 0313;
	public static final int zLINI    = 0314;
	public static final int zLIB     = 0315;
	public static final int zLIW     = 0316;
	public static final int zLINB    = 0317;

	public static final int zLIHB    = 0320;
	public static final int zLID0    = 0321;
	public static final int zLA0     = 0322;
	public static final int zLA1     = 0323;
	public static final int zLA2     = 0324;
	public static final int zLA3     = 0325;
	public static final int zLA6     = 0326;
	public static final int zLA8     = 0327;

	public static final int zLAB     = 0330;
	public static final int zLAW     = 0331;
	public static final int zGA0     = 0332;
	public static final int zGA1     = 0333;
	public static final int zGAB     = 0334;
	public static final int zGAW     = 0335;
	public static final int zCAW     = 0336;
	public static final int zEFC0    = 0337;

	public static final int zEFC1    = 0340;
	public static final int zEFC2    = 0341;
	public static final int zEFC3    = 0342;
	public static final int zEFC4    = 0343;
	public static final int zEFC5    = 0344;
	public static final int zEFC6    = 0345;
	public static final int zEFC7    = 0346;
	public static final int zEFC8    = 0347;

	public static final int zEFC9    = 0350;
	public static final int zEFC10   = 0351;
	public static final int zEFC11   = 0352;
	public static final int zEFC12   = 0353;
	public static final int zEFCB    = 0354;
	public static final int zLFC     = 0355;
	public static final int zSFC     = 0356;
	public static final int zRET     = 0357;

	public static final int zKFCB    = 0360;
	public static final int zME      = 0361;
	public static final int zMX      = 0362;
	public static final int zBLT     = 0363;
	public static final int zBLTL    = 0364;
	public static final int zBLTC    = 0365;
	public static final int zBLTCL   = 0366;
	public static final int zLP      = 0367;

	public static final int zESC     = 0370;
	public static final int zESCL    = 0371;
	public static final int zLGA0    = 0372;
	public static final int zLGAB    = 0373;
	public static final int zLGAW    = 0374;
	public static final int zDESC    = 0375;

	public static final int zRESRVD  = 0377;

	//
	public static final int a00      = 00;
	public static final int a01      = 01;
	public static final int aMW      = 02;
	public static final int aMR      = 03;
	public static final int aNC      = 04;
	public static final int aBC      = 05;
	public static final int aREQ     = 06;
	public static final int aSM      = 07;

	public static final int aSMF     = 010;
	public static final int aGMF     = 011;
	public static final int aAF      = 012;
	public static final int aFF      = 013;
	public static final int aPI      = 014;
	public static final int aPO      = 015;
	public static final int aPOR     = 016;
	public static final int aSPP     = 017;

	public static final int aDI      = 020;
	public static final int aEI      = 021;
	public static final int aXOR     = 022;
	public static final int aDAND    = 023;
	public static final int aDIOR    = 024;
	public static final int aDXOR    = 025;
	public static final int aROTATE  = 026;
	public static final int aDSHIFT  = 027;
	public static final int aLINT    = 030;
	public static final int aJS      = 031;
	public static final int aRCFS    = 032;
	public static final int bRC      = 033;
	public static final int aUDIV    = 034;
	public static final int aLUDIV   = 035;
	public static final int bROB     = 036;
	public static final int bWOB     = 037;

	public static final int bDSK     = 040;
	public static final int bXE      = 041;
	public static final int bXF      = 042;
	public static final int bLSK     = 043;
	public static final int aBNDCKL  = 044;
	public static final int aNILCK   = 045;
	public static final int aNILCKL  = 046;
	public static final int aBLTLR   = 047;
	public static final int aBLEL    = 050;
	public static final int aBLECL   = 051;
	public static final int aCKSUM   = 052;
	public static final int aBITBLT  = 053;
	public static final int aTXTBLT  = 054;
	public static final int aBYTBLT  = 055;
	public static final int aBYTBLTR = 056;
	public static final int aVERSION = 057;

	public static final int aDMUL    = 060;
	public static final int aSDIV    = 061;
	public static final int aSDDIV   = 062;
	public static final int aUDDIV   = 063;
	public static final int a64      = 064;
	public static final int a65      = 065;
	public static final int a66      = 066;
	public static final int a67      = 067;
	public static final int a70      = 070;
	public static final int a71      = 071;
	public static final int a72      = 072;
	public static final int a73      = 073;
	public static final int a74      = 074;
	public static final int a75      = 075;
	public static final int a76      = 076;
	public static final int a77      = 077;

	// Floating Point (100B-137B are reserved)
	public static final int aFADD    = 0100;
	public static final int aFSUB    = 0101;
	public static final int aFMUL    = 0102;
	public static final int aFDIV    = 0103;
	public static final int aFCOMP   = 0104;
	public static final int aFIX     = 0105;
	public static final int aFLOAT   = 0106;
	public static final int aFIXI    = 0107;
	public static final int aFIXC    = 0110;
	public static final int aFSTICKY = 0111;
	public static final int aFREM    = 0112;
	public static final int aROUND   = 0113;
	public static final int aROUNDI  = 0114;
	public static final int aROUNDC  = 0115;
	public static final int aFSQRT   = 0116;
	public static final int aFSC     = 0117;

	// Cedar collector and allocator (140B-157B are reserved)
	//public static final int aRECLAIMREF            = 0140;
	//public static final int aALTERCOUNT            = 0141;
	//public static final int aRESETSTKBITS          = 0142;
	//public static final int aGCSETUP               = 0143;
	//public static final int a144                   = 0144;
	//public static final int aENUMERATERECLAIMABLE  = 0145;
	//public static final int a146                   = 0146;
	//public static final int aCREATEREF             = 0147;
	//public static final int a150                   = 0150;
	//public static final int aREFTYPE               = 0151;
	//public static final int aCANONICALREFTYPE      = 0152;
	//public static final int aALLOCQUANTIZED        = 0153;
	//public static final int aALLOCHEAP             = 0154;
	//public static final int aFREEOBJECT            = 0155;
	//public static final int aFREEQUANTIZED         = 0156;
	//public static final int aFREEPREFIXED          = 0157;

	//  Read / Write Registers
	public static final int aWRPSB   = 0160;
	public static final int aWRMDS   = 0161;
	public static final int aWRWP    = 0162;
	public static final int aWRWDC   = 0163;
	public static final int aWRPTC   = 0164;
	public static final int aWRIT    = 0165;
	public static final int aWRXTS   = 0166;
	public static final int aWRMP    = 0167;
	public static final int aRRPSB   = 0170;
	public static final int aRRMDS   = 0171;
	public static final int aRRWP    = 0172;
	public static final int aRRWDC   = 0173;
	public static final int aRRPTC   = 0174;
	public static final int aRRIT    = 0175;
	public static final int aRRXTS   = 0176;
	public static final int a177     = 0177;

	// Processor Dependent (200B-237B are reserved)
	//public static final int aINPUT   = 0200;
	//public static final int aOUTPUT  = 0201;
	//public static final int aLOADRAMJ= 0202;

	// Dandelion
	//public static final int aBANDBLT = 0203;

	// ESCAlphaExtra3.mesa
	//public static final int aLOCKQUEUE = 206B;

	// Dolphin
	//public static final int aRPRINTER  = 0203;
	//public static final int aWPRINTER  = 0204;
	//public static final int aREADRAM   = 0205;
	//public static final int aJRAM      = 0206;
	//public static final int aCCOPR     = 0207;
	//public static final int aFPOPR     = 0210;
	//public static final int aSTARTIO   = 0211;

	//Guam
	public static final int aCALLAGENT    = 0211;
	public static final int aMAPDISPLAY   = 0212;
	public static final int aSTOPEMULATOR = 0213;
	public static final int a214          = 0214;
	//
	public static final int aCOLORBLT     = 0300;
	public static final int a305          = 0305;
	public static final int a306          = 0306;
}

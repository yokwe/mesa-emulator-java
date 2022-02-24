package yokwe.majuro.opcode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import yokwe.majuro.util.FormatLogger;

public enum Opcode {
	NOOP(Type.MOP, 00, 1, "NOOP"),
	LL0 (Type.MOP, 01, 1, "LL0"),
	LL1 (Type.MOP, 02, 1, "LL1"),
	LL2 (Type.MOP, 03, 1, "LL2"),
	LL3 (Type.MOP, 04, 1, "LL3"),
	LL4 (Type.MOP, 05, 1, "LL4"),
	LL5 (Type.MOP, 06, 1, "LL5"),
	LL6 (Type.MOP, 07, 1, "LL6"),

	LL7 (Type.MOP, 010, 1, "LL7"),
	LL8 (Type.MOP, 011, 1, "LL8"),
	LL9 (Type.MOP, 012, 1, "LL9"),
	LL10(Type.MOP, 013, 1, "LL10"),
	LL11(Type.MOP, 014, 1, "LL11"),
	LLB (Type.MOP, 015, 2, "LLB"),
	LLD0(Type.MOP, 016, 1, "LLD0"),
	LLD1(Type.MOP, 017, 1, "LLD1"),

	LLD2 (Type.MOP, 020, 1, "LLD2"),
	LLD3 (Type.MOP, 021, 1, "LLD3"),
	LLD4 (Type.MOP, 022, 1, "LLD4"),
	LLD5 (Type.MOP, 023, 1, "LLD5"),
	LLD6 (Type.MOP, 024, 1, "LLD6"),
	LLD7 (Type.MOP, 025, 1, "LLD7"),
	LLD8 (Type.MOP, 026, 1, "LLD8"),
	LLD10(Type.MOP, 027, 1, "LLD10"),

	LLDB(Type.MOP, 030, 2, "LLDB"),
	SL0 (Type.MOP, 031, 1, "SL0"),
	SL1 (Type.MOP, 032, 1, "SL1"),
	SL2 (Type.MOP, 033, 1, "SL2"),
	SL3 (Type.MOP, 034, 1, "SL3"),
	SL4 (Type.MOP, 035, 1, "SL4"),
	SL5 (Type.MOP, 036, 1, "SL5"),
	SL6 (Type.MOP, 037, 1, "SL6"),

	SL7 (Type.MOP, 040, 1, "SL7"),
	SL8 (Type.MOP, 041, 1, "SL8"),
	SL9 (Type.MOP, 042, 1, "SL9"),
	SL10(Type.MOP, 043, 1, "SL10"),
	SLB (Type.MOP, 044, 2, "SLB"),
	SLD0(Type.MOP, 045, 1, "SLD0"),
	SLD1(Type.MOP, 046, 1, "SLD1"),
	SLD2(Type.MOP, 047, 1, "SLD2"),

	SLD3(Type.MOP, 050, 1, "SLD3"),
	SLD4(Type.MOP, 051, 1, "SLD4"),
	SLD5(Type.MOP, 052, 1, "SLD5"),
	SLD6(Type.MOP, 053, 1, "SLD6"),
	SLD8(Type.MOP, 054, 1, "SLD8"),
	PL0 (Type.MOP, 055, 1, "PL0"),
	PL1 (Type.MOP, 056, 1, "PL1"),
	PL2 (Type.MOP, 057, 1, "PL2"),

	PL3 (Type.MOP, 060, 1, "PL3"),
	PLB (Type.MOP, 061, 2, "PLB"),
	PLD0(Type.MOP, 062, 1, "PLD0"),
	PLDB(Type.MOP, 063, 2, "PLDB"),
	LG0 (Type.MOP, 064, 1, "LG0"),
	LG1 (Type.MOP, 065, 1, "LG1"),
	LG2 (Type.MOP, 066, 1, "LG2"),
	LGB (Type.MOP, 067, 2, "LGB"),

	LGD0 (Type.MOP, 070, 1, "LGD0"),
	LGD2 (Type.MOP, 071, 1, "LGD2"),
	LGDB (Type.MOP, 072, 2, "LGDB"),
	SGB  (Type.MOP, 073, 2, "SGB"),
	BNDCK(Type.MOP, 074, 1, "BNDCK"),
	BRK  (Type.MOP, 075, 1, "BRK"),
	//
	//

	R0  (Type.MOP, 0100, 1, "R0"),
	R1  (Type.MOP, 0101, 1, "R1"),
	RB  (Type.MOP, 0102, 2, "RB"),
	RL0 (Type.MOP, 0103, 1, "RL0"),
	RLB (Type.MOP, 0104, 2, "RLB"),
	RD0 (Type.MOP, 0105, 1, "RD0"),
	RDB (Type.MOP, 0106, 2, "RDB"),
	RDL0(Type.MOP, 0107, 1, "RDL0"),

	RDLB(Type.MOP, 0110, 2, "RDLB"),
	W0  (Type.MOP, 0111, 1, "W0"),
	WB  (Type.MOP, 0112, 2, "WB"),
	PSB (Type.MOP, 0113, 2, "PSB"),
	WLB (Type.MOP, 0114, 2, "WLB"),
	PSLB(Type.MOP, 0115, 2, "PSLB"),
	WDB (Type.MOP, 0116, 2, "WDB"),
	PSD0(Type.MOP, 0117, 1, "PSD0"),

	PSDB (Type.MOP, 0120, 2, "PSDB"),
	WDLB (Type.MOP, 0121, 2, "WDLB"),
	PSDLB(Type.MOP, 0122, 2, "PSDLB"),
	RLI00(Type.MOP, 0123, 1, "RLI00"),
	RLI01(Type.MOP, 0124, 1, "RLI01"),
	RLI02(Type.MOP, 0125, 1, "RLI02"),
	RLI03(Type.MOP, 0126, 1, "RLI03"),
	RLIP (Type.MOP, 0127, 2, "RLIP"),

	RLILP (Type.MOP, 0130, 2, "RLILP"),
	RLDI00(Type.MOP, 0131, 1, "RLDI00"),
	RLDIP (Type.MOP, 0132, 2, "RLDIP"),
	RLDILP(Type.MOP, 0133, 2, "RLDILP"),
	RGIP  (Type.MOP, 0134, 2, "RGIP"),
	RGILP (Type.MOP, 0135, 2, "RGILP"),
	WLIP  (Type.MOP, 0136, 2, "WLIP"),
	WLILP (Type.MOP, 0137, 2, "WLILP"),

	WLDILP(Type.MOP, 0140, 2, "WLDILP"),
	RS    (Type.MOP, 0141, 2, "RS"),
	RLS   (Type.MOP, 0142, 2, "RLS"),
	WS    (Type.MOP, 0143, 2, "WS"),
	WLS   (Type.MOP, 0144, 2, "WLS"),
	R0F   (Type.MOP, 0145, 2, "R0F"),
	RF    (Type.MOP, 0146, 3, "RF"),
	RL0F  (Type.MOP, 0147, 2, "RL0F"),

	RLF   (Type.MOP, 0150, 3, "RLF"),
	RLFS  (Type.MOP, 0151, 1, "RLFS"),
	RLIPF (Type.MOP, 0152, 3, "RLIPF"),
	RLILPF(Type.MOP, 0153, 3, "RLILPF"),
	W0F   (Type.MOP, 0154, 2, "W0F"),
	WF    (Type.MOP, 0155, 3, "WF"),
	PSF   (Type.MOP, 0156, 3, "PSF"),
	PS0F  (Type.MOP, 0157, 2, "PS0F"),

	WS0F(Type.MOP, 0160, 2, "WS0F"),
	WL0F(Type.MOP, 0161, 2, "WL0F"),
	WLF (Type.MOP, 0162, 3, "WLF"),
	PSLF(Type.MOP, 0163, 3, "PSLF"),
	WLFS(Type.MOP, 0164, 1, "WLFS"),
	SLDB(Type.MOP, 0165, 2, "SLDB"),
	SGDB(Type.MOP, 0166, 2, "SGDB"),
	LLKB(Type.MOP, 0167, 2, "LLKB"),

	RKIB   (Type.MOP, 0170, 2, "RKIB"),
	RKDIB  (Type.MOP, 0171, 2, "RKDIB"),
	LKB    (Type.MOP, 0172, 2, "LKB"),
	SHIFT  (Type.MOP, 0173, 1, "SHIFT"),
	SHIFTSB(Type.MOP, 0174, 2, "SHIFTSB"),
	//
	//
	//


	CATCH(Type.MOP, 0200, 2, "CATCH"),
	J2   (Type.MOP, 0201, 1, "J2"),
	J3   (Type.MOP, 0202, 1, "J3"),
	J4   (Type.MOP, 0203, 1, "J4"),
	J5   (Type.MOP, 0204, 1, "J5"),
	J6   (Type.MOP, 0205, 1, "J6"),
	J7   (Type.MOP, 0206, 1, "J7"),
	J8   (Type.MOP, 0207, 1, "J8"),

	JB   (Type.MOP, 0210, 2, "JB"),
	JW   (Type.MOP, 0211, 3, "JW"),
	JEP  (Type.MOP, 0212, 2, "JEP"),
	JEB  (Type.MOP, 0213, 2, "JEB"),
	JEBB (Type.MOP, 0214, 3, "JEBB"),
	JNEP (Type.MOP, 0215, 2, "JNEP"),
	JNEB (Type.MOP, 0216, 2, "JNEB"),
	JNEBB(Type.MOP, 0217, 3, "JNEBB"),

	JLB  (Type.MOP, 0220, 2, "JLB"),
	JGEB (Type.MOP, 0221, 2, "JGEB"),
	JGB  (Type.MOP, 0222, 2, "JGB"),
	JLEB (Type.MOP, 0223, 2, "JLEB"),
	JULB (Type.MOP, 0224, 2, "JULB"),
	JUGEB(Type.MOP, 0225, 2, "JUGEB"),
	JUGB (Type.MOP, 0226, 2, "JUGB"),
	JULEB(Type.MOP, 0227, 2, "JULEB"),

	JZ3  (Type.MOP, 0230, 1, "JZ3"),
	JZ4  (Type.MOP, 0231, 1, "JZ4"),
	JZB  (Type.MOP, 0232, 2, "JZB"),
	JNZ3 (Type.MOP, 0233, 1, "JNZ3"),
	JNZ4 (Type.MOP, 0234, 1, "JNZ4"),
	JNZB (Type.MOP, 0235, 2, "JNZB"),
	JDEB (Type.MOP, 0236, 2, "JDEB"),
	JDNEB(Type.MOP, 0237, 2, "JDNEB"),

	JIB  (Type.MOP, 0240, 3, "JIB"),
	JIW  (Type.MOP, 0241, 3, "JIW"),
	REC  (Type.MOP, 0242, 1, "REC"),
	REC2 (Type.MOP, 0243, 1, "REC2"),
	DIS  (Type.MOP, 0244, 1, "DIS"),
	DIS2 (Type.MOP, 0245, 1, "DIS2"),
	EXCH (Type.MOP, 0246, 1, "EXCH"),
	DEXCH(Type.MOP, 0247, 1, "DEXCH"),

	DUP  (Type.MOP, 0250, 1, "DUP"),
	DDUP (Type.MOP, 0251, 1, "DDUP"),
	EXDIS(Type.MOP, 0252, 1, "EXDIS"),
	NEG  (Type.MOP, 0253, 1, "NEG"),
	INC  (Type.MOP, 0254, 1, "INC"),
	DEC  (Type.MOP, 0255, 1, "DEC"),
	DINC (Type.MOP, 0256, 1, "DINC"),
	DBL  (Type.MOP, 0257, 1, "DBL"),

	DDBL (Type.MOP, 0260, 1, "DDBL"),
	TRPL (Type.MOP, 0261, 1, "TRPL"),
	AND  (Type.MOP, 0262, 1, "AND"),
	IOR  (Type.MOP, 0263, 1, "IOR"),
	ADDSB(Type.MOP, 0264, 2, "ADDSB"),
	ADD  (Type.MOP, 0265, 1, "ADD"),
	SUB  (Type.MOP, 0266, 1, "SUB"),
	DADD (Type.MOP, 0267, 1, "DADD"),

	DSUB  (Type.MOP, 0270, 1, "DSUB"),
	ADC   (Type.MOP, 0271, 1, "ADC"),
	ACD   (Type.MOP, 0272, 1, "ACD"),
	AL0IB (Type.MOP, 0273, 2, "AL0IB"),
	MUL   (Type.MOP, 0274, 1, "MUL"),
	DCMP  (Type.MOP, 0275, 1, "DCMP"),
	UDCMP (Type.MOP, 0276, 1, "UDCMP"),
	VMFIND(Type.MOP, 0277, 1, "VMFIND"),

	
	LI0(Type.MOP, 0300, 1, "LI0"),
	LI1(Type.MOP, 0301, 1, "LI1"),
	LI2(Type.MOP, 0302, 1, "LI2"),
	LI3(Type.MOP, 0303, 1, "LI3"),
	LI4(Type.MOP, 0304, 1, "LI4"),
	LI5(Type.MOP, 0305, 1, "LI5"),
	LI6(Type.MOP, 0306, 1, "LI6"),
	LI7(Type.MOP, 0307, 1, "LI7"),

	LI8 (Type.MOP, 0310, 1, "LI8"),
	LI9 (Type.MOP, 0311, 1, "LI9"),
	LI10(Type.MOP, 0312, 1, "LI10"),
	LIN1(Type.MOP, 0313, 1, "LIN1"),
	LINI(Type.MOP, 0314, 1, "LINI"),
	LIB (Type.MOP, 0315, 2, "LIB"),
	LIW (Type.MOP, 0316, 3, "LIW"),
	LINB(Type.MOP, 0317, 2, "LINB"),

	LIHB(Type.MOP, 0320, 2, "LIHB"),
	LID0(Type.MOP, 0321, 1, "LID0"),
	LA0 (Type.MOP, 0322, 1, "LA0"),
	LA1 (Type.MOP, 0323, 1, "LA1"),
	LA2 (Type.MOP, 0324, 1, "LA2"),
	LA3 (Type.MOP, 0325, 1, "LA3"),
	LA6 (Type.MOP, 0326, 1, "LA6"),
	LA8 (Type.MOP, 0327, 1, "LA8"),

	LAB (Type.MOP, 0330, 2, "LAB"),
	LAW (Type.MOP, 0331, 3, "LAW"),
	GA0 (Type.MOP, 0332, 1, "GA0"),
	GA1 (Type.MOP, 0333, 1, "GA1"),
	GAB (Type.MOP, 0334, 2, "GAB"),
	GAW (Type.MOP, 0335, 3, "GAW"),
	//
	EFC0(Type.MOP, 0337, 1, "EFC0"),

	EFC1(Type.MOP, 0340, 1, "EFC1"),
	EFC2(Type.MOP, 0341, 1, "EFC2"),
	EFC3(Type.MOP, 0342, 1, "EFC3"),
	EFC4(Type.MOP, 0343, 1, "EFC4"),
	EFC5(Type.MOP, 0344, 1, "EFC5"),
	EFC6(Type.MOP, 0345, 1, "EFC6"),
	EFC7(Type.MOP, 0346, 1, "EFC7"),
	EFC8(Type.MOP, 0347, 1, "EFC8"),

	EFC9 (Type.MOP, 0350, 1, "EFC9"),
	EFC10(Type.MOP, 0351, 1, "EFC10"),
	EFC11(Type.MOP, 0352, 1, "EFC11"),
	EFC12(Type.MOP, 0353, 1, "EFC12"),
	EFCB (Type.MOP, 0354, 2, "EFCB"),
	LFC  (Type.MOP, 0355, 3, "LFC"),
	SFC  (Type.MOP, 0356, 1, "SFC"),
	RET  (Type.MOP, 0357, 1, "RET"),

	KFCB (Type.MOP, 0360, 2, "KFCB"),
	ME   (Type.MOP, 0361, 1, "ME"),
	MX   (Type.MOP, 0362, 1, "MX"),
	BLT  (Type.MOP, 0363, 1, "BLT"),
	BLTL (Type.MOP, 0364, 1, "BLTL"),
	BLTC (Type.MOP, 0365, 1, "BLTC"),
	BLTCL(Type.MOP, 0366, 1, "BLTCL"),
	LP   (Type.MOP, 0367, 1, "LP"),

	ESC   (Type.MOP, 0370, 1, "ESC"),
	ESCL  (Type.MOP, 0371, 1, "ESCL"),
	LGA0  (Type.MOP, 0372, 1, "LGA0"),
	LGAB  (Type.MOP, 0373, 2, "LGAB"),
	LGAW  (Type.MOP, 0374, 3, "LGAW"),
	DESC  (Type.MOP, 0375, 3, "DESC"),
	//
	RESRVD(Type.MOP, 0377, 1, "RESRVD"),

	//
	MW (Type.ESC, 02, 2, "MW"),
	MR (Type.ESC, 03, 2, "MR"),
	NC (Type.ESC, 04, 2, "NC"),
	BC (Type.ESC, 05, 2, "BC"),
	REQ(Type.ESC, 06, 2, "REQ"),
	SM (Type.ESC, 07, 2, "SM"),

	SMF(Type.ESC, 010, 2, "SMF"),
	GMF(Type.ESC, 011, 2, "GMF"),
	AF (Type.ESC, 012, 2, "AF"),
	FF (Type.ESC, 013, 2, "FF"),
	PI (Type.ESC, 014, 2, "PI"),
	PO (Type.ESC, 015, 2, "PO"),
	POR(Type.ESC, 016, 2, "POR"),
	SPP(Type.ESC, 017, 2, "SPP"),

	DI    (Type.ESC, 020, 2, "DI"),
	EI    (Type.ESC, 021, 2, "EI"),
	XOR   (Type.ESC, 022, 2, "XOR"),
	DAND  (Type.ESC, 023, 2, "DAND"),
	DIOR  (Type.ESC, 024, 2, "DIOR"),
	DXOR  (Type.ESC, 025, 2, "DXOR"),
	ROTATE(Type.ESC, 026, 2, "ROTATE"),
	DSHIFT(Type.ESC, 027, 2, "DSHIFT"),
	
	LINT (Type.ESC,  030, 2, "LINT"),
	JS   (Type.ESC,  031, 2, "JS"),
	RCFS (Type.ESC,  032, 2, "RCFS"),
	RC   (Type.ESCL, 033, 3, "RC"),
	UDIV (Type.ESC,  034, 2, "UDIV"),
	LUDIV(Type.ESC,  035, 2, "LUDIV"),
	ROB  (Type.ESCL, 036, 3, "ROB"),
	WOB  (Type.ESCL, 037, 3, "WOB"),

	DSK   (Type.ESCL, 040, 3, "DSK"),
	XE    (Type.ESCL, 041, 3, "XE"),
	XF    (Type.ESCL, 042, 3, "XF"),
	LSK   (Type.ESCL, 043, 3, "LSK"),
	BNDCKL(Type.ESC,  044, 2, "BNDCKL"),
	NILCK (Type.ESC,  045, 2, "NILCK"),
	NILCKL(Type.ESC,  046, 2, "NILCKL"),
	BLTLR (Type.ESC,  047, 2, "BLTLR"),
	
	BLEL   (Type.ESC, 050, 2, "BLEL"),
	BLECL  (Type.ESC, 051, 2, "BLECL"),
	CKSUM  (Type.ESC, 052, 2, "CKSUM"),
	BITBLT (Type.ESC, 053, 2, "BITBLT"),
	TXTBLT (Type.ESC, 054, 2, "TXTBLT"),
	BYTBLT (Type.ESC, 055, 2, "BYTBLT"),
	BYTBLTR(Type.ESC, 056, 2, "BYTBLTR"),
	VERSION(Type.ESC, 057, 2, "VERSION"),

	DMUL (Type.ESC, 060, 2, "DMUL"),
	SDIV (Type.ESC, 061, 2, "SDIV"),
	SDDIV(Type.ESC, 062, 2, "SDDIV"),
	UDDIV(Type.ESC, 063, 2, "UDDIV"),

	// Floating Point (100B-137B are reserved)
//	FADD (Type.ESC, 0100, "FADD"),
//	FSUB (Type.ESC, 0101, "FSUB"),
//	FMUL (Type.ESC, 0102, "FMUL"),
//	FDIV (Type.ESC, 0103, "FDIV"),
//	FCOMP(Type.ESC, 0104, "FCOMP"),
//	FIX  (Type.ESC, 0105, "FIX"),
//	FLOAT(Type.ESC, 0106, "FLOAT"),
//	FIXI (Type.ESC, 0107, "FIXI"),
//	
//	FIXC   (Type.ESC, 0110, "FIXC"),
//	FSTICKY(Type.ESC, 0111, "FSTICKY"),
//	FREM   (Type.ESC, 0112, "FREM"),
//	ROUND  (Type.ESC, 0113, "ROUND"),
//	ROUNDI (Type.ESC, 0114, "ROUNDI"),
//	ROUNDC (Type.ESC, 0115, "ROUNDC"),
//	FSQRT  (Type.ESC, 0116, "FSQRT"),
	//

	//  Read / Write Registers
	WRPSB(Type.ESC, 0160, 2, "WRPSB"),
	WRMDS(Type.ESC, 0161, 2, "WRMDS"),
	WRWP (Type.ESC, 0162, 2, "WRWP"),
	WRWDC(Type.ESC, 0163, 2, "WRWDC"),
	WRPTC(Type.ESC, 0164, 2, "WRPTC"),
	WRIT (Type.ESC, 0165, 2, "WRIT"),
	WRXTS(Type.ESC, 0166, 2, "WRXTS"),
	WRMP (Type.ESC, 0167, 2, "WRMP"),
	
	RRPSB(Type.ESC, 0170, 2, "RRPSB"),
	RRMDS(Type.ESC, 0171, 2, "RRMDS"),
	RRWP (Type.ESC, 0172, 2, "RRWP"),
	RRWDC(Type.ESC, 0173, 2, "RRWDC"),
	RRPTC(Type.ESC, 0174, 2, "RRPTC"),
	RRIT (Type.ESC, 0175, 2, "RRIT"),
	RRXTS(Type.ESC, 0176, 2, "RRXTS"),
	//

	
	// Guam
	//   GuamInputOutput.mesa
	CALLAGENT   (Type.ESC, 0211, 2, "CALLAGENT"),
	MAPDISPLAY  (Type.ESC, 0212, 2, "MAPDISPLAY"),
	STOPEMULATOR(Type.ESC, 0213, 2, "STOPEMULATOR"),
	A214        (Type.ESC, 0214, 2, "A214"),
	//
	//
	//


	// ColorBlt.mesa
	COLORBLT  (Type.ESC, 0300, 2, "COLORBLT"),
	WRITEPIXEL(Type.ESC, 0301, 2, "WRITEPIXEL"),
	BITBLTX   (Type.ESC, 0302, 2, "BITBLTX"),
	//
	//
	A305      (Type.ESC, 0305, 2, "A305"),
	A306      (Type.ESC, 0306, 2, "A306");

	
	public enum Type {MOP, ESC, ESCL};

	public final Type   type;
	public final int    code;
	public final int    len;
	public       String name;
	
	Opcode(Type type, int code, int len, String name) {
		this.type = type;
		this.code = code;
		this.len  = len;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("{%-4s %04o %d %s}", type, code, len, name);
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
	public @interface Register {
		Opcode value();
	}

	
	public static void main(String[] args) {
		FormatLogger logger = FormatLogger.getLogger();
		for(var e: Opcode.values()) {
			if (e.type != Opcode.Type.MOP && 0100 <= e.code && e.code <= 0377) {
				logger.info("// %03o %s", e.code, e.name);
				logger.info("@Register(Opcode.%s)", e.name);
				logger.info("public static final void OP_%s() {", e.name);
				logger.info("if (Debug.ENABLE_TRACE_OPCODE) logger.debug(\"TRACE %%6o  %%-6s\", Processor.savedPC, Opcode.%s.name);", e.name);
				logger.info("throw new UnexpectedException(); // FIXME");
				logger.info("}");
			}
		}
	}

}

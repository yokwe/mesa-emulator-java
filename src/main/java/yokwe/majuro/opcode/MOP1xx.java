package yokwe.majuro.opcode;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.util.FormatLogger;

public class MOP1xx {
	private static final FormatLogger logger = FormatLogger.getLogger(MOP1xx.class);
	
	// 100 R0
	@Register(Opcode.R0)
	public static final void OP_R0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.R0.name);
		Rn(0);
	}
	
	private static void Rn(int arg) {
		char ptr = Processor.pop();
		int  ra = Memory.fetchMDS(ptr + arg);
		// NO PAGE FAULT AFTER HERE
		Processor.push(Memory.readReal16(ra));
	}
	
	// 101 R1
	@Register(Opcode.R1)
	public static final void OP_R1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.R1.name);
		Rn(1);
	}

	// 102 RB
	@Register(Opcode.RB)
	public static final void OP_RB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RB.name);
		int alpha = Memory.getCodeByte();
		Rn(alpha);
	}

	// 103 RL0
	@Register(Opcode.RL0)
	public static final void OP_RL0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RL0.name);
		RLn(0);
	}
	
	private static void RLn(int arg) {
		int ptr = Processor.popLong();
		int ra  = Memory.fetch(ptr + arg);
		// NO PAGE FAULT AFTER HERE
		Processor.push(Memory.readReal16(ra));
	}
	
	// 104 RLB
	@Register(Opcode.RLB)
	public static final void OP_RLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLB.name);
		int alpha = Memory.getCodeByte();
		RLn(alpha);
	}

	// 105 RD0
	@Register(Opcode.RD0)
	public static final void OP_RD0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RD0.name);
		RDn(0);
	}

	private static void RDn(int arg) {
		char ptr = Processor.pop();
		int ra0 = Memory.fetchMDS(ptr + arg + 0);
		int ra1 = Memory.fetchMDS(ptr + arg + 1);
		// NO PAGE FAULT AFTER HERE
		char u = Memory.readReal16(ra0);
		char v = Memory.readReal16(ra1);
		Processor.push(u);
		Processor.push(v);
	}

	// 106 RDB
	@Register(Opcode.RDB)
	public static final void OP_RDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RDB.name);
		int alpha = Memory.getCodeByte();
		RDn(alpha);
	}

	// 107 RDL0
	@Register(Opcode.RDL0)
	public static final void OP_RDL0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RDL0.name);
		RDLn(0);
	}

	private static void RDLn(int arg) {
		int ptr = Processor.popLong();
		int ra0 = Memory.fetch(ptr + arg + 0);
		int ra1 = Memory.fetch(ptr + arg + 1);
		// NO PAGE FAULT AFTER HERE
		char u = Memory.readReal16(ra0);
		char v = Memory.readReal16(ra1);
		Processor.push(u);
		Processor.push(v);
	}

	// 110 RDLB
	@Register(Opcode.RDLB)
	public static final void OP_RDLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RDLB.name);
		int alpha = Memory.getCodeByte();
		RDLn(alpha);
	}

	// 111 W0
	@Register(Opcode.W0)
	public static final void OP_W0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.W0.name);
		Wn(0);
	}
	
	private static void Wn(int arg) {
		char ptr = Processor.pop();
		int  ra  = Memory.storeMDS(ptr + arg);
		// NO PAGE FAULT AFTER HERE
		Memory.writeReal16(ra, Processor.pop());
	}

	// 112 WB
	@Register(Opcode.WB)
	public static final void OP_WB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WB.name);
		int alpha = Memory.getCodeByte();
		Wn(alpha);
	}

	// 113 PSB
	@Register(Opcode.PSB)
	public static final void OP_PSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PSB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 114 WLB
	@Register(Opcode.WLB)
	public static final void OP_WLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WLB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 115 PSLB
	@Register(Opcode.PSLB)
	public static final void OP_PSLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PSLB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 116 WDB
	@Register(Opcode.WDB)
	public static final void OP_WDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WDB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 117 PSD0
	@Register(Opcode.PSD0)
	public static final void OP_PSD0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PSD0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 120 PSDB
	@Register(Opcode.PSDB)
	public static final void OP_PSDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PSDB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 121 WDLB
	@Register(Opcode.WDLB)
	public static final void OP_WDLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WDLB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 122 PSDLB
	@Register(Opcode.PSDLB)
	public static final void OP_PSDLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PSDLB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 123 RLI00
	@Register(Opcode.RLI00)
	public static final void OP_RLI00() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLI00.name);
		throw new UnexpectedException(); // FIXME
	}

	// 124 RLI01
	@Register(Opcode.RLI01)
	public static final void OP_RLI01() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLI01.name);
		throw new UnexpectedException(); // FIXME
	}

	// 125 RLI02
	@Register(Opcode.RLI02)
	public static final void OP_RLI02() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLI02.name);
		throw new UnexpectedException(); // FIXME
	}

	// 126 RLI03
	@Register(Opcode.RLI03)
	public static final void OP_RLI03() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLI03.name);
		throw new UnexpectedException(); // FIXME
	}

	// 127 RLIP
	@Register(Opcode.RLIP)
	public static final void OP_RLIP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLIP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 130 RLILP
	@Register(Opcode.RLILP)
	public static final void OP_RLILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLILP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 131 RLDI00
	@Register(Opcode.RLDI00)
	public static final void OP_RLDI00() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLDI00.name);
		throw new UnexpectedException(); // FIXME
	}

	// 132 RLDIP
	@Register(Opcode.RLDIP)
	public static final void OP_RLDIP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLDIP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 133 RLDILP
	@Register(Opcode.RLDILP)
	public static final void OP_RLDILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLDILP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 134 RGIP
	@Register(Opcode.RGIP)
	public static final void OP_RGIP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RGIP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 135 RGILP
	@Register(Opcode.RGILP)
	public static final void OP_RGILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RGILP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 136 WLIP
	@Register(Opcode.WLIP)
	public static final void OP_WLIP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WLIP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 137 WLILP
	@Register(Opcode.WLILP)
	public static final void OP_WLILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WLILP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 140 WLDILP
	@Register(Opcode.WLDILP)
	public static final void OP_WLDILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WLDILP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 141 RS
	@Register(Opcode.RS)
	public static final void OP_RS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 142 RLS
	@Register(Opcode.RLS)
	public static final void OP_RLS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 143 WS
	@Register(Opcode.WS)
	public static final void OP_WS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 144 WLS
	@Register(Opcode.WLS)
	public static final void OP_WLS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WLS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 145 R0F
	@Register(Opcode.R0F)
	public static final void OP_R0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.R0F.name);
		throw new UnexpectedException(); // FIXME
	}

	// 146 RF
	@Register(Opcode.RF)
	public static final void OP_RF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 147 RL0F
	@Register(Opcode.RL0F)
	public static final void OP_RL0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RL0F.name);
		throw new UnexpectedException(); // FIXME
	}

	// 150 RLF
	@Register(Opcode.RLF)
	public static final void OP_RLF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 151 RLFS
	@Register(Opcode.RLFS)
	public static final void OP_RLFS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLFS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 152 RLIPF
	@Register(Opcode.RLIPF)
	public static final void OP_RLIPF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLIPF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 153 RLILPF
	@Register(Opcode.RLILPF)
	public static final void OP_RLILPF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RLILPF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 154 W0F
	@Register(Opcode.W0F)
	public static final void OP_W0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.W0F.name);
		throw new UnexpectedException(); // FIXME
	}

	// 155 WF
	@Register(Opcode.WF)
	public static final void OP_WF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 156 PSF
	@Register(Opcode.PSF)
	public static final void OP_PSF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PSF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 157 PS0F
	@Register(Opcode.PS0F)
	public static final void OP_PS0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PS0F.name);
		throw new UnexpectedException(); // FIXME
	}

	// 160 WS0F
	@Register(Opcode.WS0F)
	public static final void OP_WS0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WS0F.name);
		throw new UnexpectedException(); // FIXME
	}

	// 161 WL0F
	@Register(Opcode.WL0F)
	public static final void OP_WL0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WL0F.name);
		throw new UnexpectedException(); // FIXME
	}

	// 162 WLF
	@Register(Opcode.WLF)
	public static final void OP_WLF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WLF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 163 PSLF
	@Register(Opcode.PSLF)
	public static final void OP_PSLF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PSLF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 164 WLFS
	@Register(Opcode.WLFS)
	public static final void OP_WLFS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.WLFS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 165 SLDB
	@Register(Opcode.SLDB)
	public static final void OP_SLDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLDB.name);
		int alpha = Memory.getCodeByte();
		MOP0xx.SLDn(alpha);
	}

	// 166 SGDB
	@Register(Opcode.SGDB)
	public static final void OP_SGDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SGDB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 167 LLKB
	@Register(Opcode.LLKB)
	public static final void OP_LLKB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLKB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 170 RKIB
	@Register(Opcode.RKIB)
	public static final void OP_RKIB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RKIB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 171 RKDIB
	@Register(Opcode.RKDIB)
	public static final void OP_RKDIB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.RKDIB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 172 LKB
	@Register(Opcode.LKB)
	public static final void OP_LKB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LKB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 173 SHIFT
	@Register(Opcode.SHIFT)
	public static final void OP_SHIFT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SHIFT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 174 SHIFTSB
	@Register(Opcode.SHIFTSB)
	public static final void OP_SHIFTSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SHIFTSB.name);
		throw new UnexpectedException(); // FIXME
	}

}

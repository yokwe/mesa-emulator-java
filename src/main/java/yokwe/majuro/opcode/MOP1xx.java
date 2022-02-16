package yokwe.majuro.opcode;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.opcode.Opcode.Register;

public class MOP1xx {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MOP1xx.class);

	// 100 R0
	@Register(Opcode.R0)
	public static final void OP_R0() {
		throw new UnexpectedException(); // FIXME
	}

	// 101 R1
	@Register(Opcode.R1)
	public static final void OP_R1() {
		throw new UnexpectedException(); // FIXME
	}

	// 102 RB
	@Register(Opcode.RB)
	public static final void OP_RB() {
		throw new UnexpectedException(); // FIXME
	}

	// 103 RL0
	@Register(Opcode.RL0)
	public static final void OP_RL0() {
		throw new UnexpectedException(); // FIXME
	}

	// 104 RLB
	@Register(Opcode.RLB)
	public static final void OP_RLB() {
		throw new UnexpectedException(); // FIXME
	}

	// 105 RD0
	@Register(Opcode.RD0)
	public static final void OP_RD0() {
		throw new UnexpectedException(); // FIXME
	}

	// 106 RDB
	@Register(Opcode.RDB)
	public static final void OP_RDB() {
		throw new UnexpectedException(); // FIXME
	}

	// 107 RDL0
	@Register(Opcode.RDL0)
	public static final void OP_RDL0() {
		throw new UnexpectedException(); // FIXME
	}

	// 110 RDLB
	@Register(Opcode.RDLB)
	public static final void OP_RDLB() {
		throw new UnexpectedException(); // FIXME
	}

	// 111 W0
	@Register(Opcode.W0)
	public static final void OP_W0() {
		throw new UnexpectedException(); // FIXME
	}

	// 112 WB
	@Register(Opcode.WB)
	public static final void OP_WB() {
		throw new UnexpectedException(); // FIXME
	}

	// 113 PSB
	@Register(Opcode.PSB)
	public static final void OP_PSB() {
		throw new UnexpectedException(); // FIXME
	}

	// 114 WLB
	@Register(Opcode.WLB)
	public static final void OP_WLB() {
		throw new UnexpectedException(); // FIXME
	}

	// 115 PSLB
	@Register(Opcode.PSLB)
	public static final void OP_PSLB() {
		throw new UnexpectedException(); // FIXME
	}

	// 116 WDB
	@Register(Opcode.WDB)
	public static final void OP_WDB() {
		throw new UnexpectedException(); // FIXME
	}

	// 117 PSD0
	@Register(Opcode.PSD0)
	public static final void OP_PSD0() {
		throw new UnexpectedException(); // FIXME
	}

	// 120 PSDB
	@Register(Opcode.PSDB)
	public static final void OP_PSDB() {
		throw new UnexpectedException(); // FIXME
	}

	// 121 WDLB
	@Register(Opcode.WDLB)
	public static final void OP_WDLB() {
		throw new UnexpectedException(); // FIXME
	}

	// 122 PSDLB
	@Register(Opcode.PSDLB)
	public static final void OP_PSDLB() {
		throw new UnexpectedException(); // FIXME
	}

	// 123 RLI00
	@Register(Opcode.RLI00)
	public static final void OP_RLI00() {
		throw new UnexpectedException(); // FIXME
	}

	// 124 RLI01
	@Register(Opcode.RLI01)
	public static final void OP_RLI01() {
		throw new UnexpectedException(); // FIXME
	}

	// 125 RLI02
	@Register(Opcode.RLI02)
	public static final void OP_RLI02() {
		throw new UnexpectedException(); // FIXME
	}

	// 126 RLI03
	@Register(Opcode.RLI03)
	public static final void OP_RLI03() {
		throw new UnexpectedException(); // FIXME
	}

	// 127 RLIP
	@Register(Opcode.RLIP)
	public static final void OP_RLIP() {
		throw new UnexpectedException(); // FIXME
	}

	// 130 RLILP
	@Register(Opcode.RLILP)
	public static final void OP_RLILP() {
		throw new UnexpectedException(); // FIXME
	}

	// 131 RLDI00
	@Register(Opcode.RLDI00)
	public static final void OP_RLDI00() {
		throw new UnexpectedException(); // FIXME
	}

	// 132 RLDIP
	@Register(Opcode.RLDIP)
	public static final void OP_RLDIP() {
		throw new UnexpectedException(); // FIXME
	}

	// 133 RLDILP
	@Register(Opcode.RLDILP)
	public static final void OP_RLDILP() {
		throw new UnexpectedException(); // FIXME
	}

	// 134 RGIP
	@Register(Opcode.RGIP)
	public static final void OP_RGIP() {
		throw new UnexpectedException(); // FIXME
	}

	// 135 RGILP
	@Register(Opcode.RGILP)
	public static final void OP_RGILP() {
		throw new UnexpectedException(); // FIXME
	}

	// 136 WLIP
	@Register(Opcode.WLIP)
	public static final void OP_WLIP() {
		throw new UnexpectedException(); // FIXME
	}

	// 137 WLILP
	@Register(Opcode.WLILP)
	public static final void OP_WLILP() {
		throw new UnexpectedException(); // FIXME
	}

	// 140 WLDILP
	@Register(Opcode.WLDILP)
	public static final void OP_WLDILP() {
		throw new UnexpectedException(); // FIXME
	}

	// 141 RS
	@Register(Opcode.RS)
	public static final void OP_RS() {
		throw new UnexpectedException(); // FIXME
	}

	// 142 RLS
	@Register(Opcode.RLS)
	public static final void OP_RLS() {
		throw new UnexpectedException(); // FIXME
	}

	// 143 WS
	@Register(Opcode.WS)
	public static final void OP_WS() {
		throw new UnexpectedException(); // FIXME
	}

	// 144 WLS
	@Register(Opcode.WLS)
	public static final void OP_WLS() {
		throw new UnexpectedException(); // FIXME
	}

	// 145 R0F
	@Register(Opcode.R0F)
	public static final void OP_R0F() {
		throw new UnexpectedException(); // FIXME
	}

	// 146 RF
	@Register(Opcode.RF)
	public static final void OP_RF() {
		throw new UnexpectedException(); // FIXME
	}

	// 147 RL0F
	@Register(Opcode.RL0F)
	public static final void OP_RL0F() {
		throw new UnexpectedException(); // FIXME
	}

	// 150 RLF
	@Register(Opcode.RLF)
	public static final void OP_RLF() {
		throw new UnexpectedException(); // FIXME
	}

	// 151 RLFS
	@Register(Opcode.RLFS)
	public static final void OP_RLFS() {
		throw new UnexpectedException(); // FIXME
	}

	// 152 RLIPF
	@Register(Opcode.RLIPF)
	public static final void OP_RLIPF() {
		throw new UnexpectedException(); // FIXME
	}

	// 153 RLILPF
	@Register(Opcode.RLILPF)
	public static final void OP_RLILPF() {
		throw new UnexpectedException(); // FIXME
	}

	// 154 W0F
	@Register(Opcode.W0F)
	public static final void OP_W0F() {
		throw new UnexpectedException(); // FIXME
	}

	// 155 WF
	@Register(Opcode.WF)
	public static final void OP_WF() {
		throw new UnexpectedException(); // FIXME
	}

	// 156 PSF
	@Register(Opcode.PSF)
	public static final void OP_PSF() {
		throw new UnexpectedException(); // FIXME
	}

	// 157 PS0F
	@Register(Opcode.PS0F)
	public static final void OP_PS0F() {
		throw new UnexpectedException(); // FIXME
	}

	// 160 WS0F
	@Register(Opcode.WS0F)
	public static final void OP_WS0F() {
		throw new UnexpectedException(); // FIXME
	}

	// 161 WL0F
	@Register(Opcode.WL0F)
	public static final void OP_WL0F() {
		throw new UnexpectedException(); // FIXME
	}

	// 162 WLF
	@Register(Opcode.WLF)
	public static final void OP_WLF() {
		throw new UnexpectedException(); // FIXME
	}

	// 163 PSLF
	@Register(Opcode.PSLF)
	public static final void OP_PSLF() {
		throw new UnexpectedException(); // FIXME
	}

	// 164 WLFS
	@Register(Opcode.WLFS)
	public static final void OP_WLFS() {
		throw new UnexpectedException(); // FIXME
	}

	// 165 SLDB
	@Register(Opcode.SLDB)
	public static final void OP_SLDB() {
		throw new UnexpectedException(); // FIXME
	}

	// 166 SGDB
	@Register(Opcode.SGDB)
	public static final void OP_SGDB() {
		throw new UnexpectedException(); // FIXME
	}

	// 167 LLKB
	@Register(Opcode.LLKB)
	public static final void OP_LLKB() {
		throw new UnexpectedException(); // FIXME
	}

	// 170 RKIB
	@Register(Opcode.RKIB)
	public static final void OP_RKIB() {
		throw new UnexpectedException(); // FIXME
	}

	// 171 RKDIB
	@Register(Opcode.RKDIB)
	public static final void OP_RKDIB() {
		throw new UnexpectedException(); // FIXME
	}

	// 172 LKB
	@Register(Opcode.LKB)
	public static final void OP_LKB() {
		throw new UnexpectedException(); // FIXME
	}

	// 173 SHIFT
	@Register(Opcode.SHIFT)
	public static final void OP_SHIFT() {
		throw new UnexpectedException(); // FIXME
	}

	// 174 SHIFTSB
	@Register(Opcode.SHIFTSB)
	public static final void OP_SHIFTSB() {
		throw new UnexpectedException(); // FIXME
	}
}

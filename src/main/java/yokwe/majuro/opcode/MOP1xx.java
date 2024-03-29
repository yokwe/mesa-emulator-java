package yokwe.majuro.opcode;

import static yokwe.majuro.mesa.ControlTransfers.fetchLink;
import static yokwe.majuro.mesa.Memory.getCodeByte;
import static yokwe.majuro.mesa.Memory.getCodeWord;
import static yokwe.majuro.mesa.Memory.read16;
import static yokwe.majuro.mesa.Memory.read16MDS;
import static yokwe.majuro.mesa.Memory.read32;
import static yokwe.majuro.mesa.Memory.read32MDS;
import static yokwe.majuro.mesa.Memory.read8;
import static yokwe.majuro.mesa.Memory.read8MDS;
import static yokwe.majuro.mesa.Memory.readField;
import static yokwe.majuro.mesa.Memory.readReal16;
import static yokwe.majuro.mesa.Memory.store;
import static yokwe.majuro.mesa.Memory.storeMDS;
import static yokwe.majuro.mesa.Memory.write16;
import static yokwe.majuro.mesa.Memory.write16MDS;
import static yokwe.majuro.mesa.Memory.write8;
import static yokwe.majuro.mesa.Memory.write8MDS;
import static yokwe.majuro.mesa.Memory.writeField;
import static yokwe.majuro.mesa.Memory.writeReal16;
import static yokwe.majuro.mesa.Processor.GF;
import static yokwe.majuro.mesa.Processor.LF;
import static yokwe.majuro.mesa.Processor.SP;
import static yokwe.majuro.mesa.Processor.error;
import static yokwe.majuro.mesa.Processor.pop;
import static yokwe.majuro.mesa.Processor.popLong;
import static yokwe.majuro.mesa.Processor.push;
import static yokwe.majuro.mesa.Processor.pushLong;
import static yokwe.majuro.mesa.Processor.savedPC;
import static yokwe.majuro.mesa.Types.shift;
import static yokwe.majuro.mesa.Types.signExtend;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.mesa.Types;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.type.PrincOps.FieldDesc;
import yokwe.majuro.type.PrincOps.FieldSpec;
import yokwe.majuro.type.PrincOps.NibblePair;

public final class MOP1xx {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	// 100 R0
	@Register(Opcode.R0)
	public static final void OP_R0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.R0.name);
		Rn(0);
	}
	
	private static void Rn(int arg) {
		int ptr = pop();
		push(read16MDS(ptr + arg));
		// NO PAGE FAULT AFTER HERE
	}
	
	// 101 R1
	@Register(Opcode.R1)
	public static final void OP_R1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.R1.name);
		Rn(1);
	}

	// 102 RB
	@Register(Opcode.RB)
	public static final void OP_RB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RB.name);
		int alpha = getCodeByte();
		Rn(alpha);
	}

	// 103 RL0
	@Register(Opcode.RL0)
	public static final void OP_RL0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RL0.name);
		RLn(0);
	}
	
	private static void RLn(int arg) {
		int ptr = popLong();
		push(read16(ptr + arg));
		// NO PAGE FAULT AFTER HERE
	}
	
	// 104 RLB
	@Register(Opcode.RLB)
	public static final void OP_RLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLB.name);
		int alpha = getCodeByte();
		RLn(alpha);
	}

	// 105 RD0
	@Register(Opcode.RD0)
	public static final void OP_RD0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RD0.name);
		RDn(0);
	}

	private static void RDn(int arg) {
		int ptr = pop();
		int u   = read16MDS(ptr + arg + 0);
		int v   = read16MDS(ptr + arg + 1);
		// NO PAGE FAULT AFTER HERE
		push(u);
		push(v);
	}

	// 106 RDB
	@Register(Opcode.RDB)
	public static final void OP_RDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RDB.name);
		int alpha = getCodeByte();
		RDn(alpha);
	}

	// 107 RDL0
	@Register(Opcode.RDL0)
	public static final void OP_RDL0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RDL0.name);
		RDLn(0);
	}

	private static void RDLn(int arg) {
		int ptr = popLong();
		int u   = read16(ptr + arg + 0);
		int v   = read16(ptr + arg + 1);
		// NO PAGE FAULT AFTER HERE
		push(u);
		push(v);
	}

	// 110 RDLB
	@Register(Opcode.RDLB)
	public static final void OP_RDLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RDLB.name);
		int alpha = getCodeByte();
		RDLn(alpha);
	}

	// 111 W0
	@Register(Opcode.W0)
	public static final void OP_W0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.W0.name);
		Wn(0);
	}
	
	private static void Wn(int arg) {
		int ptr = pop();
		write16MDS(ptr + arg, pop());
		// NO PAGE FAULT AFTER HERE
	}

	// 112 WB
	@Register(Opcode.WB)
	public static final void OP_WB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WB.name);
		int alpha = getCodeByte();
		Wn(alpha);
	}

	// 113 PSB
	@Register(Opcode.PSB)
	public static final void OP_PSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PSB.name);
		int alpha = getCodeByte();
		int u     = pop();
		int ptr   = pop();
		write16MDS(ptr + alpha, u);		
		// NO PAGE FAULT AFTER HERE
		SP++; // Recover()
	}

	// 114 WLB
	@Register(Opcode.WLB)
	public static final void OP_WLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WLB.name);
		int alpha = getCodeByte();
		int ptr   = popLong();
		write16(ptr + alpha, pop());
		// NO PAGE FAULT AFTER HERE
	}

	// 115 PSLB
	@Register(Opcode.PSLB)
	public static final void OP_PSLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PSLB.name);
		int alpha = getCodeByte();
		int u     = pop();
		int ptr   = popLong();
		write16(ptr + alpha, u);
		// NO PAGE FAULT AFTER HERE
		SP += 2; // Recover(); Recover();
	}

	// 116 WDB
	@Register(Opcode.WDB)
	public static final void OP_WDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WDB.name);
		int alpha = getCodeByte();
		int ptr   = pop();
		int v     = pop();
		int u     = pop();
		write16MDS(ptr + alpha + 1, v);
		write16MDS(ptr + alpha + 0, u);
		// NO PAGE FAULT AFTER HERE
	}

	// 117 PSD0
	@Register(Opcode.PSD0)
	public static final void OP_PSD0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PSD0.name);
		PSDn(0);
	}
	
	private static void PSDn(int arg) {
		int v     = pop();
		int u     = pop();
		int ptr   = pop();
		write16MDS(ptr + arg + 1, v);
		write16MDS(ptr + arg + 0, u);
		// NO PAGE FAULT AFTER HERE
		SP++; // Recover()
	}

	// 120 PSDB
	@Register(Opcode.PSDB)
	public static final void OP_PSDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PSDB.name);
		int alpha = getCodeByte();
		PSDn(alpha);
	}

	// 121 WDLB
	@Register(Opcode.WDLB)
	public static final void OP_WDLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WDLB.name);
		int alpha = getCodeByte();
		int ptr   = popLong();
		int v     = pop();
		int u     = pop();
		write16(ptr + alpha + 1, v);
		write16(ptr + alpha + 0, u);
		// NO PAGE FAULT AFTER HERE
	}

	// 122 PSDLB
	@Register(Opcode.PSDLB)
	public static final void OP_PSDLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PSDLB.name);
		int alpha = getCodeByte();
		int v     = pop();
		int u     = pop();
		int ptr   = popLong();
		write16(ptr + alpha + 1, v);
		write16(ptr + alpha + 0, u);
		// NO PAGE FAULT AFTER HERE
		SP += 2; // Recover(); Recover();
	}

	// 123 RLI00
	@Register(Opcode.RLI00)
	public static final void OP_RLI00() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLI00.name);
		RLInm(0, 0);
	}
	
	private static void RLInm(int left, int right) {
		int ptr = read16MDS(LF + left);
		push(read16MDS(ptr + right));
		// NO PAGE FAULT AFTER HERE
	}
	
	// 124 RLI01
	@Register(Opcode.RLI01)
	public static final void OP_RLI01() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLI01.name);
		RLInm(0, 1);
	}
	
	// 125 RLI02
	@Register(Opcode.RLI02)
	public static final void OP_RLI02() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLI02.name);
		RLInm(0, 2);
	}

	// 126 RLI03
	@Register(Opcode.RLI03)
	public static final void OP_RLI03() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLI03.name);
		RLInm(0, 3);
	}

	// 127 RLIP
	@Register(Opcode.RLIP)
	public static final void OP_RLIP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLIP.name);
		var alpha = NibblePair.value(getCodeByte());
		RLInm(alpha.left(), alpha.right());
	}

	// 130 RLILP
	@Register(Opcode.RLILP)
	public static final void OP_RLILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLILP.name);
		var alpha = NibblePair.value(getCodeByte());
		int ptr   = read32MDS(LF + alpha.left());
		push(read16(ptr + alpha.right()));
		// NO PAGE FAULT AFTER HERE
	}

	// 131 RLDI00
	@Register(Opcode.RLDI00)
	public static final void OP_RLDI00() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLDI00.name);
		RLDInm(0, 0);
	}
	
	private static void RLDInm(int left, int right) {
		int ptr = read16MDS(LF + left);
		int u   = read16MDS(ptr + right + 0);
		int v   = read16MDS(ptr + right + 1);
		// NO PAGE FAULT AFTER HERE
		push(u);
		push(v);
	}

	// 132 RLDIP
	@Register(Opcode.RLDIP)
	public static final void OP_RLDIP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLDIP.name);
		var alpha = NibblePair.value(getCodeByte());
		RLDInm(alpha.left(), alpha.right());
	}

	// 133 RLDILP
	@Register(Opcode.RLDILP)
	public static final void OP_RLDILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLDILP.name);
		var alpha = NibblePair.value(getCodeByte());
		int ptr   = read32MDS(LF + alpha.left());
		int right = alpha.right();
		int u     = read16(ptr + right + 0);
		int v     = read16(ptr + right + 1);
		// NO PAGE FAULT AFTER HERE
		push(u);
		push(v);
	}

	// 134 RGIP
	@Register(Opcode.RGIP)
	public static final void OP_RGIP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RGIP.name);
		var alpha = NibblePair.value(getCodeByte());
		int ptr   = read16(GF + alpha.left());
		push(read16MDS(ptr + alpha.right()));
		// NO PAGE FAULT AFTER HERE
	}

	// 135 RGILP
	@Register(Opcode.RGILP)
	public static final void OP_RGILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RGILP.name);
		var alpha = NibblePair.value(getCodeByte());
		int ptr   = read32(GF + alpha.left());
		push(read16(ptr + alpha.right()));
		// NO PAGE FAULT AFTER HERE
	}

	// 136 WLIP
	@Register(Opcode.WLIP)
	public static final void OP_WLIP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WLIP.name);
		var alpha = NibblePair.value(getCodeByte());
		int ptr   = read16MDS(LF + alpha.left());
		write16MDS(ptr + alpha.right(), pop());
		// NO PAGE FAULT AFTER HERE
	}

	// 137 WLILP
	@Register(Opcode.WLILP)
	public static final void OP_WLILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WLILP.name);
		var alpha = NibblePair.value(getCodeByte());
		int ptr   = read32MDS(LF + alpha.left());
		write16(ptr + alpha.right(), pop());
		// NO PAGE FAULT AFTER HERE
	}

	// 140 WLDILP
	@Register(Opcode.WLDILP)
	public static final void OP_WLDILP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WLDILP.name);
		var alpha = NibblePair.value(getCodeByte());
		int ptr   = read32MDS(LF + alpha.left());
		int right = alpha.right();
		write16(ptr + right + 1, pop());
		write16(ptr + right + 0, pop());
		// NO PAGE FAULT AFTER HERE
	}

	// 141 RS
	@Register(Opcode.RS)
	public static final void OP_RS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RS.name);
		int alpha = getCodeByte();
		int index = pop();
		int ptr   = pop();
		push(read8MDS(ptr, alpha + index));
		// NO PAGE FAULT AFTER HERE
	}

	// 142 RLS
	@Register(Opcode.RLS)
	public static final void OP_RLS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLS.name);
		int alpha = getCodeByte();
		int index = pop();
		int ptr   = popLong();
		push(read8(ptr, alpha + index));
		// NO PAGE FAULT AFTER HERE
	}

	// 143 WS
	@Register(Opcode.WS)
	public static final void OP_WS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WS.name);
		int alpha = getCodeByte();
		int index = pop();
		int ptr   = pop();
		int data  = Types.toCARD8(pop());
		write8MDS(ptr, alpha + index, data);
		// NO PAGE FAULT AFTER HERE
	}

	// 144 WLS
	@Register(Opcode.WLS)
	public static final void OP_WLS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WLS.name);
		int alpha = getCodeByte();
		int index = pop();
		int ptr   = popLong();
		int data  = Types.toCARD8(pop());
		write8(ptr, alpha + index, data);
		// NO PAGE FAULT AFTER HERE
	}

	// 145 R0F
	@Register(Opcode.R0F)
	public static final void OP_R0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.R0F.name);
		FieldSpec spec = FieldSpec.value(getCodeByte());
		RFnm(0, spec);
	}

	// 146 RF
	@Register(Opcode.RF)
	public static final void OP_RF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RF.name);
		FieldDesc desc = FieldDesc.value(getCodeWord());
		RFnm(desc.offset(), desc.field());
	}
	
	private static void RFnm(int offset, FieldSpec spec) {
		int ptr = pop();
		push(readField(read16MDS(ptr + offset), spec));
	}

	// 147 RL0F
	@Register(Opcode.RL0F)
	public static final void OP_RL0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RL0F.name);
		FieldSpec field = FieldSpec.value(getCodeByte());
		RLFnm(0, field);
	}

	// 150 RLF
	@Register(Opcode.RLF)
	public static final void OP_RLF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLF.name);
		FieldDesc desc = FieldDesc.value(getCodeWord());
		RLFnm(desc.offset(), desc.field());
	}

	private static void RLFnm(int offset, FieldSpec spec) {
		int ptr = popLong();
		push(readField(read16(ptr + offset), spec));
		// NO PAGE FAULT AFTER HERE
	}

	// 151 RLFS
	@Register(Opcode.RLFS)
	public static final void OP_RLFS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLFS.name);
		FieldDesc desc = FieldDesc.value(pop());
		RLFnm(desc.offset(), desc.field());
	}

	// 152 RLIPF
	@Register(Opcode.RLIPF)
	public static final void OP_RLIPF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLIPF.name);
		NibblePair pair = NibblePair.value(getCodeByte());
		FieldSpec  spec = FieldSpec.value(getCodeByte());
		int        base = read16MDS(LF + pair.left());
		push(readField(read16MDS(base + pair.right()), spec));
		// NO PAGE FAULT AFTER HERE
	}
	
	// 153 RLILPF
	@Register(Opcode.RLILPF)
	public static final void OP_RLILPF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RLILPF.name);
		NibblePair pair = NibblePair.value(getCodeByte());
		FieldSpec  spec = FieldSpec.value(getCodeByte());
		int base = read32MDS(LF + pair.left());
		push(readField(read16(base + pair.right()), spec));
		// NO PAGE FAULT AFTER HERE
	}

	// 154 W0F
	@Register(Opcode.W0F)
	public static final void OP_W0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.W0F.name);
		FieldSpec spec = FieldSpec.value(getCodeByte());
		Wnm(0, spec);
	}
	
	private static void Wnm(int offset, FieldSpec spec) {
		int base = pop();
		int data = pop();
		int ra   = storeMDS(base + offset);
		W(ra, data, spec);
		// NO PAGE FAULT AFTER HERE
	}
	private static void W(int ra, int data, FieldSpec spec) {
		writeReal16(ra, writeField(readReal16(ra), spec, data));
	}


	// 155 WF
	@Register(Opcode.WF)
	public static final void OP_WF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WF.name);
		FieldDesc desc = FieldDesc.value(getCodeWord());
		Wnm(desc.offset(), desc.field());
	}

	// 156 PSF
	@Register(Opcode.PSF)
	public static final void OP_PSF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PSF.name);
		FieldDesc desc = FieldDesc.value(getCodeWord());
		Wnm(desc.offset(), desc.field());
		SP++; // recover();
	}

	// 157 PS0F
	@Register(Opcode.PS0F)
	public static final void OP_PS0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PS0F.name);
		FieldSpec spec = FieldSpec.value(getCodeByte());
		Wnm(0, spec);
		SP++; // recover();
	}

	// 160 WS0F
	@Register(Opcode.WS0F)
	public static final void OP_WS0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WS0F.name);
		FieldSpec spec = FieldSpec.value(getCodeByte());
		int data = pop();
		int base = pop();
		int ra   = storeMDS(base);
		// NO PAGE FAULT AFTER HERE
		W(ra, data, spec);
	}

	// 161 WL0F
	@Register(Opcode.WL0F)
	public static final void OP_WL0F() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WL0F.name);
		FieldSpec spec = FieldSpec.value(getCodeByte());
		WLnm(0, spec);
	}

	// 162 WLF
	@Register(Opcode.WLF)
	public static final void OP_WLF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WLF.name);
		FieldDesc desc = FieldDesc.value(getCodeWord());
		WLnm(desc.offset(), desc.field());
	}
	
	private static void WLnm(int offset, FieldSpec spec) {
		int base = popLong();
		int data = pop();
		int ra   = store(base + offset);
		// NO PAGE FAULT AFTER HERE
		W(ra, data, spec);
	}

	// 163 PSLF
	@Register(Opcode.PSLF)
	public static final void OP_PSLF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PSLF.name);
		FieldDesc desc = FieldDesc.value(getCodeWord());
		int data = pop();
		int base = popLong();
		int ra   = store(base + desc.offset());
		// NO PAGE FAULT AFTER HERE
		W(ra, data, desc.field());
	}

	// 164 WLFS
	@Register(Opcode.WLFS)
	public static final void OP_WLFS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WLFS.name);
		FieldDesc desc = FieldDesc.value(pop());
		int base = popLong();
		int data = pop();
		int ra   = store(base + desc.offset());
		// NO PAGE FAULT AFTER HERE
		W(ra, data, desc.field());
	}

	// 165 SLDB
	@Register(Opcode.SLDB)
	public static final void OP_SLDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SLDB.name);
		int alpha = getCodeByte();
		MOP0xx.SLDn(alpha);
	}

	// 166 SGDB
	@Register(Opcode.SGDB)
	public static final void OP_SGDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SGDB.name);
		int alpha = getCodeByte();
		write16(Processor.GF + alpha + 1, pop());
		write16(Processor.GF + alpha + 0, pop());
		// NO PAGE FAULT AFTER HERE
	}

	// 167 LLKB
	@Register(Opcode.LLKB)
	public static final void OP_LLKB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LLKB.name);
		int alpha = getCodeByte();
		pushLong(fetchLink(alpha));
	}

	// 170 RKIB
	@Register(Opcode.RKIB)
	public static final void OP_RKIB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RKIB.name);
		int alpha = getCodeByte();
		int va    = fetchLink(alpha);
		push(read16(va));
	}

	// 171 RKDIB
	@Register(Opcode.RKDIB)
	public static final void OP_RKDIB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RKDIB.name);
		int alpha = getCodeByte();
		int va    = fetchLink(alpha);
		push(read16(va + 0));
		push(read16(va + 1));
	}

	// 172 LKB
	@Register(Opcode.LKB)
	public static final void OP_LKB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LKB.name);
		int alpha = getCodeByte();
		SP++; // recover();
		int link = pop();
		write16MDS(Processor.LF, (link - alpha));
	}

	// 173 SHIFT
	@Register(Opcode.SHIFT)
	public static final void OP_SHIFT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SHIFT.name);
		int shift = pop();
		int u     = pop();
		push(shift(u, shift));
	}

	// 174 SHIFTSB
	@Register(Opcode.SHIFTSB)
	public static final void OP_SHIFTSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SHIFTSB.name);
		int shift = signExtend(getCodeByte());
		int u     = pop();
		if (shift < -15 || 15 < shift) error();
		push(shift(u, shift));
	}

}

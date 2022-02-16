package yokwe.majuro.opcode;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.CodeCache;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.util.FormatLogger;

public final class MOP0xx {
	private static final FormatLogger logger = FormatLogger.getLogger(MOP0xx.class);

	// 000 NOOP
	@Register(Opcode.NOOP)
	public static final void OP_NOOP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.NOOP.name);
		throw new UnexpectedException();
	}

	// 001 LL0
	@Register(Opcode.LL0)
	public static final void OP_LL0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL0.name);
		LLn(0);
	}
	
	private static final void LLn(int arg) {
		char data = Memory.instance.read16MDS(Processor.LF + arg);
		Processor.push(data);		
	}

	// 002 LL1
	@Register(Opcode.LL1)
	public static final void OP_LL1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL1.name);
		LLn(1);
	}

	// 003 LL2
	@Register(Opcode.LL2)
	public static final void OP_LL2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL2.name);
		LLn(2);
	}

	// 004 LL3
	@Register(Opcode.LL3)
	public static final void OP_LL3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL3.name);
		LLn(3);
	}

	// 005 LL4
	@Register(Opcode.LL4)
	public static final void OP_LL4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL4.name);
		LLn(4);
	}

	// 006 LL5
	@Register(Opcode.LL5)
	public static final void OP_LL5() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL5.name);
		LLn(5);
	}

	// 007 LL6
	@Register(Opcode.LL6)
	public static final void OP_LL6() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL6.name);
		LLn(6);
	}

	// 010 LL7
	@Register(Opcode.LL7)
	public static final void OP_LL7() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL7.name);
		LLn(7);
	}

	// 011 LL8
	@Register(Opcode.LL8)
	public static final void OP_LL8() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL8.name);
		LLn(8);
	}

	// 012 LL9
	@Register(Opcode.LL9)
	public static final void OP_LL9() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL9.name);
		LLn(9);
	}

	// 013 LL10
	@Register(Opcode.LL10)
	public static final void OP_LL10() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL10.name);
		LLn(10);
	}

	// 014 LL11
	@Register(Opcode.LL11)
	public static final void OP_LL11() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LL11.name);
		LLn(11);
	}

	// 015 LLB
	@Register(Opcode.LLB)
	public static final void OP_LLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLB.name);
		int alpha = CodeCache.getCodeByte();
		LLn(alpha);
	}

	// 016 LLD0
	@Register(Opcode.LLD0)
	public static final void OP_LLD0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 017 LLD1
	@Register(Opcode.LLD1)
	public static final void OP_LLD1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 020 LLD2
	@Register(Opcode.LLD2)
	public static final void OP_LLD2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 021 LLD3
	@Register(Opcode.LLD3)
	public static final void OP_LLD3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 022 LLD4
	@Register(Opcode.LLD4)
	public static final void OP_LLD4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD4.name);
		throw new UnexpectedException(); // FIXME
	}

	// 023 LLD5
	@Register(Opcode.LLD5)
	public static final void OP_LLD5() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD5.name);
		throw new UnexpectedException(); // FIXME
	}

	// 024 LLD6
	@Register(Opcode.LLD6)
	public static final void OP_LLD6() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD6.name);
		throw new UnexpectedException(); // FIXME
	}

	// 025 LLD7
	@Register(Opcode.LLD7)
	public static final void OP_LLD7() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD7.name);
		throw new UnexpectedException(); // FIXME
	}

	// 026 LLD8
	@Register(Opcode.LLD8)
	public static final void OP_LLD8() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD8.name);
		throw new UnexpectedException(); // FIXME
	}

	// 027 LLD10
	@Register(Opcode.LLD10)
	public static final void OP_LLD10() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLD10.name);
		throw new UnexpectedException(); // FIXME
	}

	// 030 LLDB
	@Register(Opcode.LLDB)
	public static final void OP_LLDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LLDB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 031 SL0
	@Register(Opcode.SL0)
	public static final void OP_SL0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 032 SL1
	@Register(Opcode.SL1)
	public static final void OP_SL1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 033 SL2
	@Register(Opcode.SL2)
	public static final void OP_SL2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 034 SL3
	@Register(Opcode.SL3)
	public static final void OP_SL3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 035 SL4
	@Register(Opcode.SL4)
	public static final void OP_SL4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL4.name);
		throw new UnexpectedException(); // FIXME
	}

	// 036 SL5
	@Register(Opcode.SL5)
	public static final void OP_SL5() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL5.name);
		throw new UnexpectedException(); // FIXME
	}

	// 037 SL6
	@Register(Opcode.SL6)
	public static final void OP_SL6() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL6.name);
		throw new UnexpectedException(); // FIXME
	}

	// 040 SL7
	@Register(Opcode.SL7)
	public static final void OP_SL7() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL7.name);
		throw new UnexpectedException(); // FIXME
	}

	// 041 SL8
	@Register(Opcode.SL8)
	public static final void OP_SL8() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL8.name);
		throw new UnexpectedException(); // FIXME
	}

	// 042 SL9
	@Register(Opcode.SL9)
	public static final void OP_SL9() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL9.name);
		throw new UnexpectedException(); // FIXME
	}

	// 043 SL10
	@Register(Opcode.SL10)
	public static final void OP_SL10() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SL10.name);
		throw new UnexpectedException(); // FIXME
	}

	// 044 SLB
	@Register(Opcode.SLB)
	public static final void OP_SLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 045 SLD0
	@Register(Opcode.SLD0)
	public static final void OP_SLD0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLD0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 046 SLD1
	@Register(Opcode.SLD1)
	public static final void OP_SLD1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLD1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 047 SLD2
	@Register(Opcode.SLD2)
	public static final void OP_SLD2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLD2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 050 SLD3
	@Register(Opcode.SLD3)
	public static final void OP_SLD3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLD3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 051 SLD4
	@Register(Opcode.SLD4)
	public static final void OP_SLD4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLD4.name);
		throw new UnexpectedException(); // FIXME
	}

	// 052 SLD5
	@Register(Opcode.SLD5)
	public static final void OP_SLD5() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLD5.name);
		throw new UnexpectedException(); // FIXME
	}

	// 053 SLD6
	@Register(Opcode.SLD6)
	public static final void OP_SLD6() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLD6.name);
		throw new UnexpectedException(); // FIXME
	}

	// 054 SLD8
	@Register(Opcode.SLD8)
	public static final void OP_SLD8() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SLD8.name);
		throw new UnexpectedException(); // FIXME
	}

	// 055 PL0
	@Register(Opcode.PL0)
	public static final void OP_PL0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PL0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 056 PL1
	@Register(Opcode.PL1)
	public static final void OP_PL1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PL1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 057 PL2
	@Register(Opcode.PL2)
	public static final void OP_PL2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PL2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 060 PL3
	@Register(Opcode.PL3)
	public static final void OP_PL3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PL3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 061 PLB
	@Register(Opcode.PLB)
	public static final void OP_PLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PLB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 062 PLD0
	@Register(Opcode.PLD0)
	public static final void OP_PLD0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PLD0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 063 PLDB
	@Register(Opcode.PLDB)
	public static final void OP_PLDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.PLDB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 064 LG0
	@Register(Opcode.LG0)
	public static final void OP_LG0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LG0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 065 LG1
	@Register(Opcode.LG1)
	public static final void OP_LG1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LG1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 066 LG2
	@Register(Opcode.LG2)
	public static final void OP_LG2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LG2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 067 LGB
	@Register(Opcode.LGB)
	public static final void OP_LGB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LGB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 070 LGD0
	@Register(Opcode.LGD0)
	public static final void OP_LGD0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LGD0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 071 LGD2
	@Register(Opcode.LGD2)
	public static final void OP_LGD2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LGD2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 072 LGDB
	@Register(Opcode.LGDB)
	public static final void OP_LGDB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.LGDB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 073 SGB
	@Register(Opcode.SGB)
	public static final void OP_SGB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.SGB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 074 BNDCK
	@Register(Opcode.BNDCK)
	public static final void OP_BNDCK() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.BNDCK.name);
		throw new UnexpectedException(); // FIXME
	}

	// 075 BRK
	@Register(Opcode.BRK)
	public static final void OP_BRK() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", Processor.savedPC, Opcode.BRK.name);
		throw new UnexpectedException(); // FIXME
	}
}

package yokwe.majuro.opcode;

import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.util.FormatLogger;

public class ESC0xx {
	private static final FormatLogger logger = FormatLogger.getLogger(ESC0xx.class);

	// 002 MW
	@Register(Opcode.MW)
	public static final void OP_MW() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.MW.name);
		throw new UnexpectedException(); // FIXME
	}

	// 003 MR
	@Register(Opcode.MR)
	public static final void OP_MR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.MR.name);
		throw new UnexpectedException(); // FIXME
	}

	// 004 NC
	@Register(Opcode.NC)
	public static final void OP_NC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.NC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 005 BC
	@Register(Opcode.BC)
	public static final void OP_BC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 006 REQ
	@Register(Opcode.REQ)
	public static final void OP_REQ() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.REQ.name);
		throw new UnexpectedException(); // FIXME
	}

	// 007 SM
	@Register(Opcode.SM)
	public static final void OP_SM() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SM.name);
		throw new UnexpectedException(); // FIXME
	}

	// 010 SMF
	@Register(Opcode.SMF)
	public static final void OP_SMF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SMF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 011 GMF
	@Register(Opcode.GMF)
	public static final void OP_GMF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.GMF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 012 AF
	@Register(Opcode.AF)
	public static final void OP_AF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.AF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 013 FF
	@Register(Opcode.FF)
	public static final void OP_FF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.FF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 014 PI
	@Register(Opcode.PI)
	public static final void OP_PI() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PI.name);
		throw new UnexpectedException(); // FIXME
	}

	// 015 PO
	@Register(Opcode.PO)
	public static final void OP_PO() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.PO.name);
		throw new UnexpectedException(); // FIXME
	}

	// 016 POR
	@Register(Opcode.POR)
	public static final void OP_POR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.POR.name);
		throw new UnexpectedException(); // FIXME
	}

	// 017 SPP
	@Register(Opcode.SPP)
	public static final void OP_SPP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SPP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 020 DI
	@Register(Opcode.DI)
	public static final void OP_DI() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DI.name);
		throw new UnexpectedException(); // FIXME
	}

	// 021 EI
	@Register(Opcode.EI)
	public static final void OP_EI() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EI.name);
		throw new UnexpectedException(); // FIXME
	}

	// 022 XOR
	@Register(Opcode.XOR)
	public static final void OP_XOR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.XOR.name);
		throw new UnexpectedException(); // FIXME
	}

	// 023 DAND
	@Register(Opcode.DAND)
	public static final void OP_DAND() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DAND.name);
		throw new UnexpectedException(); // FIXME
	}

	// 024 DIOR
	@Register(Opcode.DIOR)
	public static final void OP_DIOR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DIOR.name);
		throw new UnexpectedException(); // FIXME
	}

	// 025 DXOR
	@Register(Opcode.DXOR)
	public static final void OP_DXOR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DXOR.name);
		throw new UnexpectedException(); // FIXME
	}

	// 026 ROTATE
	@Register(Opcode.ROTATE)
	public static final void OP_ROTATE() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ROTATE.name);
		throw new UnexpectedException(); // FIXME
	}

	// 027 DSHIFT
	@Register(Opcode.DSHIFT)
	public static final void OP_DSHIFT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DSHIFT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 030 LINT
	@Register(Opcode.LINT)
	public static final void OP_LINT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LINT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 031 JS
	@Register(Opcode.JS)
	public static final void OP_JS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 032 RCFS
	@Register(Opcode.RCFS)
	public static final void OP_RCFS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RCFS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 033 RC
	@Register(Opcode.RC)
	public static final void OP_RC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 034 UDIV
	@Register(Opcode.UDIV)
	public static final void OP_UDIV() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.UDIV.name);
		throw new UnexpectedException(); // FIXME
	}

	// 035 LUDIV
	@Register(Opcode.LUDIV)
	public static final void OP_LUDIV() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LUDIV.name);
		throw new UnexpectedException(); // FIXME
	}

	// 036 ROB
	@Register(Opcode.ROB)
	public static final void OP_ROB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ROB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 037 WOB
	@Register(Opcode.WOB)
	public static final void OP_WOB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WOB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 040 DSK
	@Register(Opcode.DSK)
	public static final void OP_DSK() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DSK.name);
		throw new UnexpectedException(); // FIXME
	}

	// 041 XE
	@Register(Opcode.XE)
	public static final void OP_XE() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.XE.name);
		throw new UnexpectedException(); // FIXME
	}

	// 042 XF
	@Register(Opcode.XF)
	public static final void OP_XF() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.XF.name);
		throw new UnexpectedException(); // FIXME
	}

	// 043 LSK
	@Register(Opcode.LSK)
	public static final void OP_LSK() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LSK.name);
		throw new UnexpectedException(); // FIXME
	}

	// 044 BNDCKL
	@Register(Opcode.BNDCKL)
	public static final void OP_BNDCKL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BNDCKL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 045 NILCK
	@Register(Opcode.NILCK)
	public static final void OP_NILCK() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.NILCK.name);
		throw new UnexpectedException(); // FIXME
	}

	// 046 NILCKL
	@Register(Opcode.NILCKL)
	public static final void OP_NILCKL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.NILCKL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 047 BLTLR
	@Register(Opcode.BLTLR)
	public static final void OP_BLTLR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BLTLR.name);
		throw new UnexpectedException(); // FIXME
	}

	// 050 BLEL
	@Register(Opcode.BLEL)
	public static final void OP_BLEL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BLEL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 051 BLECL
	@Register(Opcode.BLECL)
	public static final void OP_BLECL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BLECL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 052 CKSUM
	@Register(Opcode.CKSUM)
	public static final void OP_CKSUM() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.CKSUM.name);
		throw new UnexpectedException(); // FIXME
	}

	// 053 BITBLT
	@Register(Opcode.BITBLT)
	public static final void OP_BITBLT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BITBLT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 054 TXTBLT
	@Register(Opcode.TXTBLT)
	public static final void OP_TXTBLT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.TXTBLT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 055 BYTBLT
	@Register(Opcode.BYTBLT)
	public static final void OP_BYTBLT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BYTBLT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 056 BYTBLTR
	@Register(Opcode.BYTBLTR)
	public static final void OP_BYTBLTR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BYTBLTR.name);
		throw new UnexpectedException(); // FIXME
	}

	// 057 VERSION
	@Register(Opcode.VERSION)
	public static final void OP_VERSION() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.VERSION.name);
		throw new UnexpectedException(); // FIXME
	}

	// 060 DMUL
	@Register(Opcode.DMUL)
	public static final void OP_DMUL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DMUL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 061 SDIV
	@Register(Opcode.SDIV)
	public static final void OP_SDIV() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SDIV.name);
		throw new UnexpectedException(); // FIXME
	}

	// 062 SDDIV
	@Register(Opcode.SDDIV)
	public static final void OP_SDDIV() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SDDIV.name);
		throw new UnexpectedException(); // FIXME
	}

	// 063 UDDIV
	@Register(Opcode.UDDIV)
	public static final void OP_UDDIV() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.UDDIV.name);
		throw new UnexpectedException(); // FIXME
	}

}

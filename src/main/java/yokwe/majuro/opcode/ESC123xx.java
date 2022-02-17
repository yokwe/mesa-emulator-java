package yokwe.majuro.opcode;

import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.util.FormatLogger;

public class ESC123xx {
	private static final FormatLogger logger = FormatLogger.getLogger(ESC123xx.class);

	// 160 WRPSB
	@Register(Opcode.WRPSB)
	public static final void OP_WRPSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRPSB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 161 WRMDS
	@Register(Opcode.WRMDS)
	public static final void OP_WRMDS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRMDS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 162 WRWP
	@Register(Opcode.WRWP)
	public static final void OP_WRWP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRWP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 163 WRWDC
	@Register(Opcode.WRWDC)
	public static final void OP_WRWDC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRWDC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 164 WRPTC
	@Register(Opcode.WRPTC)
	public static final void OP_WRPTC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRPTC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 165 WRIT
	@Register(Opcode.WRIT)
	public static final void OP_WRIT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRIT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 166 WRXTS
	@Register(Opcode.WRXTS)
	public static final void OP_WRXTS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRXTS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 167 WRMP
	@Register(Opcode.WRMP)
	public static final void OP_WRMP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRMP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 170 RRPSB
	@Register(Opcode.RRPSB)
	public static final void OP_RRPSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRPSB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 171 RRMDS
	@Register(Opcode.RRMDS)
	public static final void OP_RRMDS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRMDS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 172 RRWP
	@Register(Opcode.RRWP)
	public static final void OP_RRWP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRWP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 173 RRWDC
	@Register(Opcode.RRWDC)
	public static final void OP_RRWDC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRWDC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 174 RRPTC
	@Register(Opcode.RRPTC)
	public static final void OP_RRPTC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRPTC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 175 RRIT
	@Register(Opcode.RRIT)
	public static final void OP_RRIT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRIT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 176 RRXTS
	@Register(Opcode.RRXTS)
	public static final void OP_RRXTS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRXTS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 211 CALLAGENT
	@Register(Opcode.CALLAGENT)
	public static final void OP_CALLAGENT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.CALLAGENT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 212 MAPDISPLAY
	@Register(Opcode.MAPDISPLAY)
	public static final void OP_MAPDISPLAY() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.MAPDISPLAY.name);
		throw new UnexpectedException(); // FIXME
	}

	// 213 STOPEMULATOR
	@Register(Opcode.STOPEMULATOR)
	public static final void OP_STOPEMULATOR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.STOPEMULATOR.name);
		throw new UnexpectedException(); // FIXME
	}

	// 214 A214
	@Register(Opcode.A214)
	public static final void OP_A214() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.A214.name);
		throw new UnexpectedException(); // FIXME
	}

	// 300 COLORBLT
	@Register(Opcode.COLORBLT)
	public static final void OP_COLORBLT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.COLORBLT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 301 WRITEPIXEL
	@Register(Opcode.WRITEPIXEL)
	public static final void OP_WRITEPIXEL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRITEPIXEL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 302 BITBLTX
	@Register(Opcode.BITBLTX)
	public static final void OP_BITBLTX() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BITBLTX.name);
		throw new UnexpectedException(); // FIXME
	}

	// 305 A305
	@Register(Opcode.A305)
	public static final void OP_A305() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.A305.name);
		throw new UnexpectedException(); // FIXME
	}

	// 306 A306
	@Register(Opcode.A306)
	public static final void OP_A306() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.A306.name);
		throw new UnexpectedException(); // FIXME
	}

}

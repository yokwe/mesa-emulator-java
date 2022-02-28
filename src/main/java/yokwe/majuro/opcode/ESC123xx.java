package yokwe.majuro.opcode;

import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.util.FormatLogger;
import yokwe.majuro.util.MesaUtil;

public final class ESC123xx {
	private static final FormatLogger logger = FormatLogger.getLogger();

	// 160 WRPSB
	@Register(Opcode.WRPSB)
	public static final void OP_WRPSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRPSB.name);
		Processor.PSB = psbIndex(pop());
	}

	// 161 WRMDS
	@Register(Opcode.WRMDS)
	public static final void OP_WRMDS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRMDS.name);
		Processor.MDS = pop() << WORD_BITS;
	}

	// 162 WRWP
	@Register(Opcode.WRWP)
	public static final void OP_WRWP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRWP.name);
		Processor.WP = pop();
	}

	// 163 WRWDC
	@Register(Opcode.WRWDC)
	public static final void OP_WRWDC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRWDC.name);
		Processor.WDC = pop();
	}

	// 164 WRPTC
	@Register(Opcode.WRPTC)
	public static final void OP_WRPTC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRPTC.name);
		Processor.PTC = pop();
		lastTimeoutTime = Processor.IT(); // FIXME
	}

	// 165 WRIT
	@Register(Opcode.WRIT)
	public static final void OP_WRIT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRIT.name);
		error();
	}

	// 166 WRXTS
	@Register(Opcode.WRXTS)
	public static final void OP_WRXTS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRXTS.name);
		Processor.XTS = pop();
	}

	// 167 WRMP
	@Register(Opcode.WRMP)
	public static final void OP_WRMP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.WRMP.name);
		Processor.MP = pop();
		logger.info("%s", MesaUtil.getMPCodeMessage(Processor.MP));
	}

	// 170 RRPSB
	@Register(Opcode.RRPSB)
	public static final void OP_RRPSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRPSB.name);
		push(psbHandle(Processor.PSB));
	}

	// 171 RRMDS
	@Register(Opcode.RRMDS)
	public static final void OP_RRMDS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRMDS.name);
		push(Processor.MDS >>> WORD_BITS);
	}

	// 172 RRWP
	@Register(Opcode.RRWP)
	public static final void OP_RRWP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRWP.name);
		push(Processor.WP);
	}

	// 173 RRWDC
	@Register(Opcode.RRWDC)
	public static final void OP_RRWDC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRWDC.name);
		push(Processor.WDC);
	}

	// 174 RRPTC
	@Register(Opcode.RRPTC)
	public static final void OP_RRPTC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRPTC.name);
		push(Processor.PTC);
	}

	// 175 RRIT
	@Register(Opcode.RRIT)
	public static final void OP_RRIT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRIT.name);
		pushLong(Processor.IT());
	}

	// 176 RRXTS
	@Register(Opcode.RRXTS)
	public static final void OP_RRXTS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RRXTS.name);
		push(Processor.XTS);
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
		// FIXME Implements OP_A214
		popLong();
		popLong();
		popLong();
		push(0);
	}
	//14:59:24.02 DEBUG block        TRACE   1426  zBLTL     307E7    AB63F     4
	//14:59:24.02 DEBUG Opcode       TRACE   1427  zLLD1
	//14:59:24.02 DEBUG Opcode       TRACE   1430  zLLD7
	//14:59:24.02 DEBUG Opcode       TRACE   1431  zLLDB 09
	//14:59:24.02 DEBUG guam         TRACE   1433  a214  <= ##
	//14:59:24.02 DEBUG Opcode       TRACE   1435  zDIS
	//14:59:24.02 DEBUG control      TRACE   1436  zRET


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
		// FIXME Implements OP_A305
	}

	// 306 A306
	@Register(Opcode.A306)
	public static final void OP_A306() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.A306.name);
		// FIXME Implements OP_A306
		pop();
		push(0);
	}
	//13:02:26.01 DEBUG control      TRACE    222  zEFC2
	//13:02:26.01 DEBUG Opcode       TRACE  13635  zSL0
	//13:02:26.01 DEBUG Opcode       TRACE  13636  zSLD1
	//13:02:26.01 DEBUG Opcode       TRACE  13637  zLI2
	//13:02:26.01 DEBUG bootguam     TRACE  13640  a306  2
	//13:02:26.01 DEBUG Opcode       TRACE  13642  zSL3     <= #1
	//13:02:26.01 DEBUG Opcode       TRACE  13643  zLLD1
	//13:02:26.01 DEBUG Opcode       TRACE  13644  zPLDB 04
	//13:02:26.01 DEBUG Opcode       TRACE  13646  zIOR

}

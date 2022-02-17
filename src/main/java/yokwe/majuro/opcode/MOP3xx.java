package yokwe.majuro.opcode;

import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.util.FormatLogger;

public class MOP3xx {
	private static final FormatLogger logger = FormatLogger.getLogger(MOP3xx.class);

	// 300 LI0
	@Register(Opcode.LI0)
	public static final void OP_LI0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 301 LI1
	@Register(Opcode.LI1)
	public static final void OP_LI1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 302 LI2
	@Register(Opcode.LI2)
	public static final void OP_LI2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 303 LI3
	@Register(Opcode.LI3)
	public static final void OP_LI3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 304 LI4
	@Register(Opcode.LI4)
	public static final void OP_LI4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI4.name);
		throw new UnexpectedException(); // FIXME
	}

	// 305 LI5
	@Register(Opcode.LI5)
	public static final void OP_LI5() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI5.name);
		throw new UnexpectedException(); // FIXME
	}

	// 306 LI6
	@Register(Opcode.LI6)
	public static final void OP_LI6() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI6.name);
		throw new UnexpectedException(); // FIXME
	}

	// 307 LI7
	@Register(Opcode.LI7)
	public static final void OP_LI7() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI7.name);
		throw new UnexpectedException(); // FIXME
	}

	// 310 LI8
	@Register(Opcode.LI8)
	public static final void OP_LI8() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI8.name);
		throw new UnexpectedException(); // FIXME
	}

	// 311 LI9
	@Register(Opcode.LI9)
	public static final void OP_LI9() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI9.name);
		throw new UnexpectedException(); // FIXME
	}

	// 312 LI10
	@Register(Opcode.LI10)
	public static final void OP_LI10() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LI10.name);
		throw new UnexpectedException(); // FIXME
	}

	// 313 LIN1
	@Register(Opcode.LIN1)
	public static final void OP_LIN1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LIN1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 314 LINI
	@Register(Opcode.LINI)
	public static final void OP_LINI() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LINI.name);
		throw new UnexpectedException(); // FIXME
	}

	// 315 LIB
	@Register(Opcode.LIB)
	public static final void OP_LIB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LIB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 316 LIW
	@Register(Opcode.LIW)
	public static final void OP_LIW() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LIW.name);
		throw new UnexpectedException(); // FIXME
	}

	// 317 LINB
	@Register(Opcode.LINB)
	public static final void OP_LINB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LINB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 320 LIHB
	@Register(Opcode.LIHB)
	public static final void OP_LIHB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LIHB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 321 LID0
	@Register(Opcode.LID0)
	public static final void OP_LID0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LID0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 322 LA0
	@Register(Opcode.LA0)
	public static final void OP_LA0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LA0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 323 LA1
	@Register(Opcode.LA1)
	public static final void OP_LA1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LA1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 324 LA2
	@Register(Opcode.LA2)
	public static final void OP_LA2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LA2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 325 LA3
	@Register(Opcode.LA3)
	public static final void OP_LA3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LA3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 326 LA6
	@Register(Opcode.LA6)
	public static final void OP_LA6() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LA6.name);
		throw new UnexpectedException(); // FIXME
	}

	// 327 LA8
	@Register(Opcode.LA8)
	public static final void OP_LA8() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LA8.name);
		throw new UnexpectedException(); // FIXME
	}

	// 330 LAB
	@Register(Opcode.LAB)
	public static final void OP_LAB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LAB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 331 LAW
	@Register(Opcode.LAW)
	public static final void OP_LAW() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LAW.name);
		throw new UnexpectedException(); // FIXME
	}

	// 332 GA0
	@Register(Opcode.GA0)
	public static final void OP_GA0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.GA0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 333 GA1
	@Register(Opcode.GA1)
	public static final void OP_GA1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.GA1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 334 GAB
	@Register(Opcode.GAB)
	public static final void OP_GAB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.GAB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 335 GAW
	@Register(Opcode.GAW)
	public static final void OP_GAW() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.GAW.name);
		throw new UnexpectedException(); // FIXME
	}

	// 337 EFC0
	@Register(Opcode.EFC0)
	public static final void OP_EFC0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 340 EFC1
	@Register(Opcode.EFC1)
	public static final void OP_EFC1() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC1.name);
		throw new UnexpectedException(); // FIXME
	}

	// 341 EFC2
	@Register(Opcode.EFC2)
	public static final void OP_EFC2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 342 EFC3
	@Register(Opcode.EFC3)
	public static final void OP_EFC3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 343 EFC4
	@Register(Opcode.EFC4)
	public static final void OP_EFC4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC4.name);
		throw new UnexpectedException(); // FIXME
	}

	// 344 EFC5
	@Register(Opcode.EFC5)
	public static final void OP_EFC5() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC5.name);
		throw new UnexpectedException(); // FIXME
	}

	// 345 EFC6
	@Register(Opcode.EFC6)
	public static final void OP_EFC6() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC6.name);
		throw new UnexpectedException(); // FIXME
	}

	// 346 EFC7
	@Register(Opcode.EFC7)
	public static final void OP_EFC7() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC7.name);
		throw new UnexpectedException(); // FIXME
	}

	// 347 EFC8
	@Register(Opcode.EFC8)
	public static final void OP_EFC8() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC8.name);
		throw new UnexpectedException(); // FIXME
	}

	// 350 EFC9
	@Register(Opcode.EFC9)
	public static final void OP_EFC9() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC9.name);
		throw new UnexpectedException(); // FIXME
	}

	// 351 EFC10
	@Register(Opcode.EFC10)
	public static final void OP_EFC10() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC10.name);
		throw new UnexpectedException(); // FIXME
	}

	// 352 EFC11
	@Register(Opcode.EFC11)
	public static final void OP_EFC11() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC11.name);
		throw new UnexpectedException(); // FIXME
	}

	// 353 EFC12
	@Register(Opcode.EFC12)
	public static final void OP_EFC12() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFC12.name);
		throw new UnexpectedException(); // FIXME
	}

	// 354 EFCB
	@Register(Opcode.EFCB)
	public static final void OP_EFCB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EFCB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 355 LFC
	@Register(Opcode.LFC)
	public static final void OP_LFC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LFC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 356 SFC
	@Register(Opcode.SFC)
	public static final void OP_SFC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SFC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 357 RET
	@Register(Opcode.RET)
	public static final void OP_RET() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RET.name);
		throw new UnexpectedException(); // FIXME
	}

	// 360 KFCB
	@Register(Opcode.KFCB)
	public static final void OP_KFCB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.KFCB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 361 ME
	@Register(Opcode.ME)
	public static final void OP_ME() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ME.name);
		throw new UnexpectedException(); // FIXME
	}

	// 362 MX
	@Register(Opcode.MX)
	public static final void OP_MX() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.MX.name);
		throw new UnexpectedException(); // FIXME
	}

	// 363 BLT
	@Register(Opcode.BLT)
	public static final void OP_BLT() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BLT.name);
		throw new UnexpectedException(); // FIXME
	}

	// 364 BLTL
	@Register(Opcode.BLTL)
	public static final void OP_BLTL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BLTL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 365 BLTC
	@Register(Opcode.BLTC)
	public static final void OP_BLTC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BLTC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 366 BLTCL
	@Register(Opcode.BLTCL)
	public static final void OP_BLTCL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.BLTCL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 367 LP
	@Register(Opcode.LP)
	public static final void OP_LP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 370 ESC
	@Register(Opcode.ESC)
	public static final void OP_ESC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ESC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 371 ESCL
	@Register(Opcode.ESCL)
	public static final void OP_ESCL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ESCL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 372 LGA0
	@Register(Opcode.LGA0)
	public static final void OP_LGA0() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LGA0.name);
		throw new UnexpectedException(); // FIXME
	}

	// 373 LGAB
	@Register(Opcode.LGAB)
	public static final void OP_LGAB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LGAB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 374 LGAW
	@Register(Opcode.LGAW)
	public static final void OP_LGAW() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.LGAW.name);
		throw new UnexpectedException(); // FIXME
	}

	// 375 DESC
	@Register(Opcode.DESC)
	public static final void OP_DESC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DESC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 377 RESRVD
	@Register(Opcode.RESRVD)
	public static final void OP_RESRVD() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.RESRVD.name);
		throw new UnexpectedException(); // FIXME
	}

}

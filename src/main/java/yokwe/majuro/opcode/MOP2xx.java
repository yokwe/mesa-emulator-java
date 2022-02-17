package yokwe.majuro.opcode;

import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.util.FormatLogger;

public class MOP2xx {
	private static final FormatLogger logger = FormatLogger.getLogger(MOP2xx.class);

	// 200 CATCH
	@Register(Opcode.CATCH)
	public static final void OP_CATCH() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.CATCH.name);
		throw new UnexpectedException(); // FIXME
	}

	// 201 J2
	@Register(Opcode.J2)
	public static final void OP_J2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 202 J3
	@Register(Opcode.J3)
	public static final void OP_J3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 203 J4
	@Register(Opcode.J4)
	public static final void OP_J4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J4.name);
		throw new UnexpectedException(); // FIXME
	}

	// 204 J5
	@Register(Opcode.J5)
	public static final void OP_J5() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J5.name);
		throw new UnexpectedException(); // FIXME
	}

	// 205 J6
	@Register(Opcode.J6)
	public static final void OP_J6() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J6.name);
		throw new UnexpectedException(); // FIXME
	}

	// 206 J7
	@Register(Opcode.J7)
	public static final void OP_J7() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J7.name);
		throw new UnexpectedException(); // FIXME
	}

	// 207 J8
	@Register(Opcode.J8)
	public static final void OP_J8() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J8.name);
		throw new UnexpectedException(); // FIXME
	}

	// 210 JB
	@Register(Opcode.JB)
	public static final void OP_JB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 211 JW
	@Register(Opcode.JW)
	public static final void OP_JW() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JW.name);
		throw new UnexpectedException(); // FIXME
	}

	// 212 JEP
	@Register(Opcode.JEP)
	public static final void OP_JEP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JEP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 213 JEB
	@Register(Opcode.JEB)
	public static final void OP_JEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JEB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 214 JEBB
	@Register(Opcode.JEBB)
	public static final void OP_JEBB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JEBB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 215 JNEP
	@Register(Opcode.JNEP)
	public static final void OP_JNEP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNEP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 216 JNEB
	@Register(Opcode.JNEB)
	public static final void OP_JNEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNEB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 217 JNEBB
	@Register(Opcode.JNEBB)
	public static final void OP_JNEBB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNEBB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 220 JLB
	@Register(Opcode.JLB)
	public static final void OP_JLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JLB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 221 JGEB
	@Register(Opcode.JGEB)
	public static final void OP_JGEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JGEB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 222 JGB
	@Register(Opcode.JGB)
	public static final void OP_JGB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JGB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 223 JLEB
	@Register(Opcode.JLEB)
	public static final void OP_JLEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JLEB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 224 JULB
	@Register(Opcode.JULB)
	public static final void OP_JULB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JULB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 225 JUGEB
	@Register(Opcode.JUGEB)
	public static final void OP_JUGEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JUGEB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 226 JUGB
	@Register(Opcode.JUGB)
	public static final void OP_JUGB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JUGB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 227 JULEB
	@Register(Opcode.JULEB)
	public static final void OP_JULEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JULEB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 230 JZ3
	@Register(Opcode.JZ3)
	public static final void OP_JZ3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JZ3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 231 JZ4
	@Register(Opcode.JZ4)
	public static final void OP_JZ4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JZ4.name);
		throw new UnexpectedException(); // FIXME
	}

	// 232 JZB
	@Register(Opcode.JZB)
	public static final void OP_JZB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JZB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 233 JNZ3
	@Register(Opcode.JNZ3)
	public static final void OP_JNZ3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNZ3.name);
		throw new UnexpectedException(); // FIXME
	}

	// 234 JNZ4
	@Register(Opcode.JNZ4)
	public static final void OP_JNZ4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNZ4.name);
		throw new UnexpectedException(); // FIXME
	}

	// 235 JNZB
	@Register(Opcode.JNZB)
	public static final void OP_JNZB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNZB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 236 JDEB
	@Register(Opcode.JDEB)
	public static final void OP_JDEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JDEB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 237 JDNEB
	@Register(Opcode.JDNEB)
	public static final void OP_JDNEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JDNEB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 240 JIB
	@Register(Opcode.JIB)
	public static final void OP_JIB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JIB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 241 JIW
	@Register(Opcode.JIW)
	public static final void OP_JIW() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JIW.name);
		throw new UnexpectedException(); // FIXME
	}

	// 242 REC
	@Register(Opcode.REC)
	public static final void OP_REC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.REC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 243 REC2
	@Register(Opcode.REC2)
	public static final void OP_REC2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.REC2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 244 DIS
	@Register(Opcode.DIS)
	public static final void OP_DIS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DIS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 245 DIS2
	@Register(Opcode.DIS2)
	public static final void OP_DIS2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DIS2.name);
		throw new UnexpectedException(); // FIXME
	}

	// 246 EXCH
	@Register(Opcode.EXCH)
	public static final void OP_EXCH() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EXCH.name);
		throw new UnexpectedException(); // FIXME
	}

	// 247 DEXCH
	@Register(Opcode.DEXCH)
	public static final void OP_DEXCH() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DEXCH.name);
		throw new UnexpectedException(); // FIXME
	}

	// 250 DUP
	@Register(Opcode.DUP)
	public static final void OP_DUP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DUP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 251 DDUP
	@Register(Opcode.DDUP)
	public static final void OP_DDUP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DDUP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 252 EXDIS
	@Register(Opcode.EXDIS)
	public static final void OP_EXDIS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EXDIS.name);
		throw new UnexpectedException(); // FIXME
	}

	// 253 NEG
	@Register(Opcode.NEG)
	public static final void OP_NEG() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.NEG.name);
		throw new UnexpectedException(); // FIXME
	}

	// 254 INC
	@Register(Opcode.INC)
	public static final void OP_INC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.INC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 255 DEC
	@Register(Opcode.DEC)
	public static final void OP_DEC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DEC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 256 DINC
	@Register(Opcode.DINC)
	public static final void OP_DINC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DINC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 257 DBL
	@Register(Opcode.DBL)
	public static final void OP_DBL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DBL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 260 DDBL
	@Register(Opcode.DDBL)
	public static final void OP_DDBL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DDBL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 261 TRPL
	@Register(Opcode.TRPL)
	public static final void OP_TRPL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.TRPL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 262 AND
	@Register(Opcode.AND)
	public static final void OP_AND() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.AND.name);
		throw new UnexpectedException(); // FIXME
	}

	// 263 IOR
	@Register(Opcode.IOR)
	public static final void OP_IOR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.IOR.name);
		throw new UnexpectedException(); // FIXME
	}

	// 264 ADDSB
	@Register(Opcode.ADDSB)
	public static final void OP_ADDSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ADDSB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 265 ADD
	@Register(Opcode.ADD)
	public static final void OP_ADD() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ADD.name);
		throw new UnexpectedException(); // FIXME
	}

	// 266 SUB
	@Register(Opcode.SUB)
	public static final void OP_SUB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SUB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 267 DADD
	@Register(Opcode.DADD)
	public static final void OP_DADD() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DADD.name);
		throw new UnexpectedException(); // FIXME
	}

	// 270 DSUB
	@Register(Opcode.DSUB)
	public static final void OP_DSUB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DSUB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 271 ADC
	@Register(Opcode.ADC)
	public static final void OP_ADC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ADC.name);
		throw new UnexpectedException(); // FIXME
	}

	// 272 ACD
	@Register(Opcode.ACD)
	public static final void OP_ACD() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ACD.name);
		throw new UnexpectedException(); // FIXME
	}

	// 273 AL0IB
	@Register(Opcode.AL0IB)
	public static final void OP_AL0IB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.AL0IB.name);
		throw new UnexpectedException(); // FIXME
	}

	// 274 MUL
	@Register(Opcode.MUL)
	public static final void OP_MUL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.MUL.name);
		throw new UnexpectedException(); // FIXME
	}

	// 275 DCMP
	@Register(Opcode.DCMP)
	public static final void OP_DCMP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DCMP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 276 UDCMP
	@Register(Opcode.UDCMP)
	public static final void OP_UDCMP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.UDCMP.name);
		throw new UnexpectedException(); // FIXME
	}

	// 277 VMFIND
	@Register(Opcode.VMFIND)
	public static final void OP_VMFIND() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.VMFIND.name);
		throw new UnexpectedException(); // FIXME
	}
}

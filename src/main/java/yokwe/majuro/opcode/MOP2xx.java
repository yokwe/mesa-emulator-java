package yokwe.majuro.opcode;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.opcode.Opcode.Register;

public class MOP2xx {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MOP2xx.class);

	// 200 CATCH
	@Register(Opcode.CATCH)
	public static final void OP_CATCH() {
		throw new UnexpectedException(); // FIXME
	}

	// 201 J2
	@Register(Opcode.J2)
	public static final void OP_J2() {
		throw new UnexpectedException(); // FIXME
	}

	// 202 J3
	@Register(Opcode.J3)
	public static final void OP_J3() {
		throw new UnexpectedException(); // FIXME
	}

	// 203 J4
	@Register(Opcode.J4)
	public static final void OP_J4() {
		throw new UnexpectedException(); // FIXME
	}

	// 204 J5
	@Register(Opcode.J5)
	public static final void OP_J5() {
		throw new UnexpectedException(); // FIXME
	}

	// 205 J6
	@Register(Opcode.J6)
	public static final void OP_J6() {
		throw new UnexpectedException(); // FIXME
	}

	// 206 J7
	@Register(Opcode.J7)
	public static final void OP_J7() {
		throw new UnexpectedException(); // FIXME
	}

	// 207 J8
	@Register(Opcode.J8)
	public static final void OP_J8() {
		throw new UnexpectedException(); // FIXME
	}

	// 210 JB
	@Register(Opcode.JB)
	public static final void OP_JB() {
		throw new UnexpectedException(); // FIXME
	}

	// 211 JW
	@Register(Opcode.JW)
	public static final void OP_JW() {
		throw new UnexpectedException(); // FIXME
	}

	// 212 JEP
	@Register(Opcode.JEP)
	public static final void OP_JEP() {
		throw new UnexpectedException(); // FIXME
	}

	// 213 JEB
	@Register(Opcode.JEB)
	public static final void OP_JEB() {
		throw new UnexpectedException(); // FIXME
	}

	// 214 JEBB
	@Register(Opcode.JEBB)
	public static final void OP_JEBB() {
		throw new UnexpectedException(); // FIXME
	}

	// 215 JNEP
	@Register(Opcode.JNEP)
	public static final void OP_JNEP() {
		throw new UnexpectedException(); // FIXME
	}

	// 216 JNEB
	@Register(Opcode.JNEB)
	public static final void OP_JNEB() {
		throw new UnexpectedException(); // FIXME
	}

	// 217 JNEBB
	@Register(Opcode.JNEBB)
	public static final void OP_JNEBB() {
		throw new UnexpectedException(); // FIXME
	}

	// 220 JLB
	@Register(Opcode.JLB)
	public static final void OP_JLB() {
		throw new UnexpectedException(); // FIXME
	}

	// 221 JGEB
	@Register(Opcode.JGEB)
	public static final void OP_JGEB() {
		throw new UnexpectedException(); // FIXME
	}

	// 222 JGB
	@Register(Opcode.JGB)
	public static final void OP_JGB() {
		throw new UnexpectedException(); // FIXME
	}

	// 223 JLEB
	@Register(Opcode.JLEB)
	public static final void OP_JLEB() {
		throw new UnexpectedException(); // FIXME
	}

	// 224 JULB
	@Register(Opcode.JULB)
	public static final void OP_JULB() {
		throw new UnexpectedException(); // FIXME
	}

	// 225 JUGEB
	@Register(Opcode.JUGEB)
	public static final void OP_JUGEB() {
		throw new UnexpectedException(); // FIXME
	}

	// 226 JUGB
	@Register(Opcode.JUGB)
	public static final void OP_JUGB() {
		throw new UnexpectedException(); // FIXME
	}

	// 227 JULEB
	@Register(Opcode.JULEB)
	public static final void OP_JULEB() {
		throw new UnexpectedException(); // FIXME
	}

	// 230 JZ3
	@Register(Opcode.JZ3)
	public static final void OP_JZ3() {
		throw new UnexpectedException(); // FIXME
	}

	// 231 JZ4
	@Register(Opcode.JZ4)
	public static final void OP_JZ4() {
		throw new UnexpectedException(); // FIXME
	}

	// 232 JZB
	@Register(Opcode.JZB)
	public static final void OP_JZB() {
		throw new UnexpectedException(); // FIXME
	}

	// 233 JNZ3
	@Register(Opcode.JNZ3)
	public static final void OP_JNZ3() {
		throw new UnexpectedException(); // FIXME
	}

	// 234 JNZ4
	@Register(Opcode.JNZ4)
	public static final void OP_JNZ4() {
		throw new UnexpectedException(); // FIXME
	}

	// 235 JNZB
	@Register(Opcode.JNZB)
	public static final void OP_JNZB() {
		throw new UnexpectedException(); // FIXME
	}

	// 236 JDEB
	@Register(Opcode.JDEB)
	public static final void OP_JDEB() {
		throw new UnexpectedException(); // FIXME
	}

	// 237 JDNEB
	@Register(Opcode.JDNEB)
	public static final void OP_JDNEB() {
		throw new UnexpectedException(); // FIXME
	}

	// 240 JIB
	@Register(Opcode.JIB)
	public static final void OP_JIB() {
		throw new UnexpectedException(); // FIXME
	}

	// 241 JIW
	@Register(Opcode.JIW)
	public static final void OP_JIW() {
		throw new UnexpectedException(); // FIXME
	}

	// 242 REC
	@Register(Opcode.REC)
	public static final void OP_REC() {
		throw new UnexpectedException(); // FIXME
	}

	// 243 REC2
	@Register(Opcode.REC2)
	public static final void OP_REC2() {
		throw new UnexpectedException(); // FIXME
	}

	// 244 DIS
	@Register(Opcode.DIS)
	public static final void OP_DIS() {
		throw new UnexpectedException(); // FIXME
	}

	// 245 DIS2
	@Register(Opcode.DIS2)
	public static final void OP_DIS2() {
		throw new UnexpectedException(); // FIXME
	}

	// 246 EXCH
	@Register(Opcode.EXCH)
	public static final void OP_EXCH() {
		throw new UnexpectedException(); // FIXME
	}

	// 247 DEXCH
	@Register(Opcode.DEXCH)
	public static final void OP_DEXCH() {
		throw new UnexpectedException(); // FIXME
	}

	// 250 DUP
	@Register(Opcode.DUP)
	public static final void OP_DUP() {
		throw new UnexpectedException(); // FIXME
	}

	// 251 DDUP
	@Register(Opcode.DDUP)
	public static final void OP_DDUP() {
		throw new UnexpectedException(); // FIXME
	}

	// 252 EXDIS
	@Register(Opcode.EXDIS)
	public static final void OP_EXDIS() {
		throw new UnexpectedException(); // FIXME
	}

	// 253 NEG
	@Register(Opcode.NEG)
	public static final void OP_NEG() {
		throw new UnexpectedException(); // FIXME
	}

	// 254 INC
	@Register(Opcode.INC)
	public static final void OP_INC() {
		throw new UnexpectedException(); // FIXME
	}

	// 255 DEC
	@Register(Opcode.DEC)
	public static final void OP_DEC() {
		throw new UnexpectedException(); // FIXME
	}

	// 256 DINC
	@Register(Opcode.DINC)
	public static final void OP_DINC() {
		throw new UnexpectedException(); // FIXME
	}

	// 257 DBL
	@Register(Opcode.DBL)
	public static final void OP_DBL() {
		throw new UnexpectedException(); // FIXME
	}

	// 260 DDBL
	@Register(Opcode.DDBL)
	public static final void OP_DDBL() {
		throw new UnexpectedException(); // FIXME
	}

	// 261 TRPL
	@Register(Opcode.TRPL)
	public static final void OP_TRPL() {
		throw new UnexpectedException(); // FIXME
	}

	// 262 AND
	@Register(Opcode.AND)
	public static final void OP_AND() {
		throw new UnexpectedException(); // FIXME
	}

	// 263 IOR
	@Register(Opcode.IOR)
	public static final void OP_IOR() {
		throw new UnexpectedException(); // FIXME
	}

	// 264 ADDSB
	@Register(Opcode.ADDSB)
	public static final void OP_ADDSB() {
		throw new UnexpectedException(); // FIXME
	}

	// 265 ADD
	@Register(Opcode.ADD)
	public static final void OP_ADD() {
		throw new UnexpectedException(); // FIXME
	}

	// 266 SUB
	@Register(Opcode.SUB)
	public static final void OP_SUB() {
		throw new UnexpectedException(); // FIXME
	}

	// 267 DADD
	@Register(Opcode.DADD)
	public static final void OP_DADD() {
		throw new UnexpectedException(); // FIXME
	}

	// 270 DSUB
	@Register(Opcode.DSUB)
	public static final void OP_DSUB() {
		throw new UnexpectedException(); // FIXME
	}

	// 271 ADC
	@Register(Opcode.ADC)
	public static final void OP_ADC() {
		throw new UnexpectedException(); // FIXME
	}

	// 272 ACD
	@Register(Opcode.ACD)
	public static final void OP_ACD() {
		throw new UnexpectedException(); // FIXME
	}

	// 273 AL0IB
	@Register(Opcode.AL0IB)
	public static final void OP_AL0IB() {
		throw new UnexpectedException(); // FIXME
	}

	// 274 MUL
	@Register(Opcode.MUL)
	public static final void OP_MUL() {
		throw new UnexpectedException(); // FIXME
	}

	// 275 DCMP
	@Register(Opcode.DCMP)
	public static final void OP_DCMP() {
		throw new UnexpectedException(); // FIXME
	}

	// 276 UDCMP
	@Register(Opcode.UDCMP)
	public static final void OP_UDCMP() {
		throw new UnexpectedException(); // FIXME
	}

	// 277 VMFIND
	@Register(Opcode.VMFIND)
	public static final void OP_VMFIND() {
		throw new UnexpectedException(); // FIXME
	}
}

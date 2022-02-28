package yokwe.majuro.opcode;

import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Types;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.type.BytePair;
import yokwe.majuro.type.NibblePair;
import yokwe.majuro.util.FormatLogger;

public final class MOP2xx {
	private static final FormatLogger logger = FormatLogger.getLogger();

	// 200 CATCH
	@Register(Opcode.CATCH)
	public static final void OP_CATCH() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.CATCH.name);
		getCodeByte();
	}

	// 201 J2
	@Register(Opcode.J2)
	public static final void OP_J2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J2.name);
		Jn(2);
	}
	
	private static final void Jn(int n) {
		PC(savedPC + n);
	}

	// 202 J3
	@Register(Opcode.J3)
	public static final void OP_J3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J3.name);
		Jn(3);
	}

	// 203 J4
	@Register(Opcode.J4)
	public static final void OP_J4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J4.name);
		Jn(4);
	}

	// 204 J5
	@Register(Opcode.J5)
	public static final void OP_J5() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J5.name);
		Jn(5);
	}

	// 205 J6
	@Register(Opcode.J6)
	public static final void OP_J6() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J6.name);
		Jn(6);
	}

	// 206 J7
	@Register(Opcode.J7)
	public static final void OP_J7() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J7.name);
		Jn(7);
	}

	// 207 J8
	@Register(Opcode.J8)
	public static final void OP_J8() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.J8.name);
		Jn(8);
	}

	// 210 JB
	@Register(Opcode.JB)
	public static final void OP_JB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JB.name);
		Jn(getCodeByte());
	}

	// 211 JW
	@Register(Opcode.JW)
	public static final void OP_JW() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JW.name);
		Jn(getCodeWord());
	}

	// 212 JEP
	@Register(Opcode.JEP)
	public static final void OP_JEP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JEP.name);
		NibblePair pair = NibblePair.value(getCodeByte());
		int data = pop();
		if (data == pair.left()) Jn(pair.right() + 4);
	}

	// 213 JEB
	@Register(Opcode.JEB)
	public static final void OP_JEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JEB.name);
		int disp = getCodeByte();
		int v    = pop();
		int u    = pop();
		if (u == v) Jn(Types.signExtend(disp));
	}

	// 214 JEBB
	@Register(Opcode.JEBB)
	public static final void OP_JEBB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JEBB.name);
		int alpha = getCodeByte();
		int disp  = getCodeByte();
		int data  = pop();
		if (data == alpha) Jn(Types.signExtend(disp));
	}

	// 215 JNEP
	@Register(Opcode.JNEP)
	public static final void OP_JNEP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNEP.name);
		NibblePair pair = NibblePair.value(getCodeByte());
		int        data = pop();
		if (data != pair.left()) Jn(pair.right() + 4);
	}

	// 216 JNEB
	@Register(Opcode.JNEB)
	public static final void OP_JNEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNEB.name);
		int disp = getCodeByte();
		int v    = pop();
		int u    = pop();
		if (u != v) Jn(Types.signExtend(disp));
	}

	// 217 JNEBB
	@Register(Opcode.JNEBB)
	public static final void OP_JNEBB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNEBB.name);
		int alpha = getCodeByte();
		int disp  = getCodeByte();
		int data  = pop();
		if (data != alpha) Jn(Types.signExtend(disp));
	}

	// 220 JLB
	@Register(Opcode.JLB)
	public static final void OP_JLB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JLB.name);
		int disp = getCodeByte();
		int k    = Types.toINT16(pop());
		int j    = Types.toINT16(pop());
		if (j < k) Jn(Types.signExtend(disp));
	}

	// 221 JGEB
	@Register(Opcode.JGEB)
	public static final void OP_JGEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JGEB.name);
		int disp = getCodeByte();
		int k    = Types.toINT16(pop());
		int j    = Types.toINT16(pop());
		if (j >= k) Jn(Types.signExtend(disp));
	}

	// 222 JGB
	@Register(Opcode.JGB)
	public static final void OP_JGB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JGB.name);
		int disp = getCodeByte();
		int k    = Types.toINT16(pop());
		int j    = Types.toINT16(pop());
		if (j > k) Jn(Types.signExtend(disp));
	}

	// 223 JLEB
	@Register(Opcode.JLEB)
	public static final void OP_JLEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JLEB.name);
		int disp = getCodeByte();
		int k    = Types.toINT16(pop());
		int j    = Types.toINT16(pop());
		if (j <= k) Jn(Types.signExtend(disp));
	}

	// 224 JULB
	@Register(Opcode.JULB)
	public static final void OP_JULB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JULB.name);
		int disp = getCodeByte();
		int v    = pop();
		int u    = pop();
		if (u < v) Jn(Types.signExtend(disp));
	}

	// 225 JUGEB
	@Register(Opcode.JUGEB)
	public static final void OP_JUGEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JUGEB.name);
		int disp = getCodeByte();
		int v    = pop();
		int u    = pop();
		if (u >= v) Jn(Types.signExtend(disp));
	}

	// 226 JUGB
	@Register(Opcode.JUGB)
	public static final void OP_JUGB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JUGB.name);
		int disp = getCodeByte();
		int v    = pop();
		int u    = pop();
		if (u > v) Jn(Types.signExtend(disp));
	}

	// 227 JULEB
	@Register(Opcode.JULEB)
	public static final void OP_JULEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JULEB.name);
		int disp = getCodeByte();
		int v    = pop();
		int u    = pop();
		if (u <= v) Jn(Types.signExtend(disp));
	}

	// 230 JZ3
	@Register(Opcode.JZ3)
	public static final void OP_JZ3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JZ3.name);
		JZn(3);
	}
	
	private static void JZn(int n) {
		int u = pop();
		if (u == 0) Jn(n);
	}

	// 231 JZ4
	@Register(Opcode.JZ4)
	public static final void OP_JZ4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JZ4.name);
		JZn(4);
	}

	// 232 JZB
	@Register(Opcode.JZB)
	public static final void OP_JZB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JZB.name);
		int disp = getCodeByte();
		JZn(Types.signExtend(disp));
	}

	// 233 JNZ3
	@Register(Opcode.JNZ3)
	public static final void OP_JNZ3() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNZ3.name);
		JNZn(3);
	}
	
	private static void JNZn(int n) {
		int u = pop();
		if (u != 0) Jn(n);
	}

	// 234 JNZ4
	@Register(Opcode.JNZ4)
	public static final void OP_JNZ4() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNZ4.name);
		JNZn(4);
	}

	// 235 JNZB
	@Register(Opcode.JNZB)
	public static final void OP_JNZB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JNZB.name);
		int disp = getCodeByte();
		JNZn(Types.signExtend(disp));
	}

	// 236 JDEB
	@Register(Opcode.JDEB)
	public static final void OP_JDEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JDEB.name);
		int disp = getCodeByte();
		int v    = popLong();
		int u    = popLong();
		if (u == v) Jn(Types.signExtend(disp));
	}

	// 237 JDNEB
	@Register(Opcode.JDNEB)
	public static final void OP_JDNEB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JDNEB.name);
		int disp = getCodeByte();
		int v    = popLong();
		int u    = popLong();
		if (u != v) Jn(Types.signExtend(disp));
	}

	// 240 JIB
	@Register(Opcode.JIB)
	public static final void OP_JIB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JIB.name);
		int base = getCodeWord();
		int limit = pop();
		int index = pop();
		if (index < limit) {
			BytePair disp = BytePair.value(readCode(base + (index / 2)));
			Jn((index & 1) == 0 ? disp.left() : disp.right());
		}
	}

	// 241 JIW
	@Register(Opcode.JIW)
	public static final void OP_JIW() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.JIW.name);
		int base = getCodeWord();
		int limit = pop();
		int index = pop();
		if (index < limit) {
			int disp = readCode(base + index);
			Jn(disp);
		}
	}

	// 242 REC
	@Register(Opcode.REC)
	public static final void OP_REC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.REC.name);
		recover();
	}

	// 243 REC2
	@Register(Opcode.REC2)
	public static final void OP_REC2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.REC2.name);
		recover();
		recover();
	}

	// 244 DIS
	@Register(Opcode.DIS)
	public static final void OP_DIS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DIS.name);
		discard();
	}

	// 245 DIS2
	@Register(Opcode.DIS2)
	public static final void OP_DIS2() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DIS2.name);
		discard();
		discard();
	}

	// 246 EXCH
	@Register(Opcode.EXCH)
	public static final void OP_EXCH() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EXCH.name);
		int v = pop();
		int u = pop();
		push(v);
		push(u);
	}

	// 247 DEXCH
	@Register(Opcode.DEXCH)
	public static final void OP_DEXCH() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DEXCH.name);
		int v = popLong();
		int u = popLong();
		pushLong(v);
		pushLong(u);
	}

	// 250 DUP
	@Register(Opcode.DUP)
	public static final void OP_DUP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DUP.name);
		int u = pop();
		push(u);
		push(u);
	}

	// 251 DDUP
	@Register(Opcode.DDUP)
	public static final void OP_DDUP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DDUP.name);
		int u = popLong();
		pushLong(u);
		pushLong(u);
	}

	// 252 EXDIS
	@Register(Opcode.EXDIS)
	public static final void OP_EXDIS() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.EXDIS.name);
		int u = pop();
		pop();
		push(u);
	}

	// 253 NEG
	@Register(Opcode.NEG)
	public static final void OP_NEG() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.NEG.name);
		int i = Types.toINT16(pop());
		push(-i);
	}

	// 254 INC
	@Register(Opcode.INC)
	public static final void OP_INC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.INC.name);
		int s = pop();
		push(s + 1);
	}

	// 255 DEC
	@Register(Opcode.DEC)
	public static final void OP_DEC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DEC.name);
		int s = pop();
		push(s - 1);
	}

	// 256 DINC
	@Register(Opcode.DINC)
	public static final void OP_DINC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DINC.name);
		int s = popLong();
		pushLong(s + 1);
	}

	// 257 DBL
	@Register(Opcode.DBL)
	public static final void OP_DBL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DBL.name);
		int s = pop();
		push(s << 1);
	}

	// 260 DDBL
	@Register(Opcode.DDBL)
	public static final void OP_DDBL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DDBL.name);
		int s = popLong();
		pushLong(s << 1);
	}

	// 261 TRPL
	@Register(Opcode.TRPL)
	public static final void OP_TRPL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.TRPL.name);
		int s = pop();
		push(s * 3);
	}

	// 262 AND
	@Register(Opcode.AND)
	public static final void OP_AND() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.AND.name);
		int v = pop();
		int u = pop();
		push(u & v);
	}

	// 263 IOR
	@Register(Opcode.IOR)
	public static final void OP_IOR() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.IOR.name);
		int v = pop();
		int u = pop();
		push(u | v);
	}

	// 264 ADDSB
	@Register(Opcode.ADDSB)
	public static final void OP_ADDSB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ADDSB.name);
		int alpha = getCodeByte();
		int i = Types.toINT16(pop());
		push(i + Types.signExtend(alpha));
	}

	// 265 ADD
	@Register(Opcode.ADD)
	public static final void OP_ADD() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ADD.name);
		int t = pop();
		int s = pop();
		push(s + t);
	}

	// 266 SUB
	@Register(Opcode.SUB)
	public static final void OP_SUB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.SUB.name);
		int t = pop();
		int s = pop();
		push(s - t);
	}

	// 267 DADD
	@Register(Opcode.DADD)
	public static final void OP_DADD() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DADD.name);
		int t = popLong();
		int s = popLong();
		pushLong(s + t);
	}

	// 270 DSUB
	@Register(Opcode.DSUB)
	public static final void OP_DSUB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DSUB.name);
		int t = popLong();
		int s = popLong();
		pushLong(s - t);
	}

	// 271 ADC
	@Register(Opcode.ADC)
	public static final void OP_ADC() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ADC.name);
		int t = pop();
		int s = popLong();
		pushLong(s + t);
	}

	// 272 ACD
	@Register(Opcode.ACD)
	public static final void OP_ACD() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.ACD.name);
		int t = popLong();
		int s = pop();
		pushLong(s + t);
	}

	// 273 AL0IB
	@Register(Opcode.AL0IB)
	public static final void OP_AL0IB() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.AL0IB.name);
		int alpha = getCodeByte();
		push(read16MDS(LF) + alpha);
	}

	// 274 MUL
	@Register(Opcode.MUL)
	public static final void OP_MUL() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.MUL.name);
		int t = pop();
		int s = pop();
		pushLong(s * t);
		discard();
	}

	// 275 DCMP
	@Register(Opcode.DCMP)
	public static final void OP_DCMP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.DCMP.name);
		int k = popLong();
		int j = popLong();
		int c = Integer.compare(k, j);
		push((c == 0) ? 0 : ((0 < c) ? 1 : -1));
	}

	// 276 UDCMP
	@Register(Opcode.UDCMP)
	public static final void OP_UDCMP() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.UDCMP.name);
		int t = popLong();
		int s = popLong();
		int c = Integer.compareUnsigned(s, t);
		push((c == 0) ? 0 : ((0 < c) ? 1 : -1));
	}

	// 277 VMFIND
	@Register(Opcode.VMFIND)
	public static final void OP_VMFIND() {
		if (Debug.ENABLE_TRACE_OPCODE) logger.debug("TRACE %6o  %-6s", savedPC, Opcode.VMFIND.name);
		throw new UnexpectedException(); // FIXME
	}
}

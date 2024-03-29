package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static yokwe.majuro.mesa.Memory.PC;
import static yokwe.majuro.mesa.Memory.read16;
import static yokwe.majuro.mesa.Memory.read16MDS;
import static yokwe.majuro.mesa.Memory.write16;
import static yokwe.majuro.mesa.Memory.write16MDS;
import static yokwe.majuro.mesa.Memory.write32;
import static yokwe.majuro.mesa.Memory.write32MDS;
import static yokwe.majuro.mesa.Memory.writeReal16;
import static yokwe.majuro.mesa.Processor.GF;
import static yokwe.majuro.mesa.Processor.LF;
import static yokwe.majuro.mesa.Processor.SP;
import static yokwe.majuro.mesa.Processor.push;
import static yokwe.majuro.mesa.Processor.pushLong;
import static yokwe.majuro.mesa.Processor.savedPC;
import static yokwe.majuro.mesa.Processor.stack;

import org.junit.jupiter.api.Test;

import yokwe.majuro.opcode.Interpreter;
import yokwe.majuro.opcode.Opcode;
import yokwe.majuro.type.PrincOps.BytePair;
import yokwe.majuro.type.PrincOps.FieldDesc;
import yokwe.majuro.type.PrincOps.FieldSpec;
import yokwe.majuro.util.StackUtil;


public class MOP1xxTest extends Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	private void Rn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int base  = 0x1000;
		int sa    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16MDS(sa, value);
		push(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(read16MDS(sa), stack[0]);
		// memory
	}
	@Test
	public void R0() {
		logger.info(StackUtil.getCallerMethodName());
		Rn(Opcode.R0, 0);
	}
	@Test
	public void R1() {
		logger.info(StackUtil.getCallerMethodName());
		Rn(Opcode.R1, 1);
	}
	@Test
	public void RB() {
		logger.info(StackUtil.getCallerMethodName());
		Rn(Opcode.RB, 20);
	}

	private void RLn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int base  = 0x30_0000;
		int va    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16(va, value);
		pushLong(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(read16(va), stack[0]);
		// memory
	}
	@Test
	public void RL0() {
		logger.info(StackUtil.getCallerMethodName());
		RLn(Opcode.RL0, 0);
	}
	@Test
	public void RLB() {
		logger.info(StackUtil.getCallerMethodName());
		RLn(Opcode.RLB, 20);
	}
	
	private void RDn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int base  = 0x1000;
		int sa    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16MDS(sa + 0, Types.lowHalf(value));
		write16MDS(sa + 1, Types.highHalf(value));
		push(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(2, SP);
		// stack contents
		assertEquals(read16MDS(sa + 0), stack[0]);
		assertEquals(read16MDS(sa + 1), stack[1]);
		// memory
	}
	@Test
	public void RDB() {
		logger.info(StackUtil.getCallerMethodName());
		RDn(Opcode.RDB, 20);
	}
	
	private void RDLn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int base  = 0x30_0000;
		int va    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16(va + 0, Types.lowHalf(value));
		write16(va + 1, Types.highHalf(value));
		pushLong(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(2, SP);
		// stack contents
		assertEquals(read16(va + 0), stack[0]);
		assertEquals(read16(va + 1), stack[1]);
		// memory
	}
	@Test
	public void RDLB() {
		logger.info(StackUtil.getCallerMethodName());
		RDLn(Opcode.RDLB, 20);
	}
	
	private void Wn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int base  = 0x1000;
		int sa    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		push(value);
		push(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		// memory
		assertEquals(value, read16MDS(sa));
	}
	@Test
	public void W0() {
		logger.info(StackUtil.getCallerMethodName());
		Wn(Opcode.W0, 0);
	}
	@Test
	public void WB() {
		logger.info(StackUtil.getCallerMethodName());
		Wn(Opcode.WB, 20);
	}
	
	private void PSn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int base  = 0x1000;
		int sa    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		push(base);
		push(value);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		// memory
		assertEquals(value, read16MDS(sa));
	}
	@Test
	public void PSB() {
		logger.info(StackUtil.getCallerMethodName());
		PSn(Opcode.PSB, 20);
	}
	
	private void WLn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int base  = 0x30_0000;
		int va    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		push(value);
		pushLong(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		// memory
		assertEquals(value, read16(va));
	}
	@Test
	public void WLB() {
		logger.info(StackUtil.getCallerMethodName());
		WLn(Opcode.WLB, 20);
	}
	
	private void PSLn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int base  = 0x30_0000;
		int va    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		pushLong(base);
		push(value);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(2, SP);
		// stack contents
		// memory
		assertEquals(value, read16(va));
	}
	@Test
	public void PSLB() {
		logger.info(StackUtil.getCallerMethodName());
		PSLn(Opcode.PSLB, 20);
	}
	
	private void WDn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int base  = 0x1000;
		int sa    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		push(Types.lowHalf(value));
		push(Types.highHalf(value));
		push(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		assertEquals(stack[0], read16MDS(sa + 0));
		assertEquals(stack[1], read16MDS(sa + 1));
		// memory
		assertEquals(Types.lowHalf(value),  read16MDS(sa + 0));
		assertEquals(Types.highHalf(value), read16MDS(sa + 1));
	}
	@Test
	public void WDB() {
		logger.info(StackUtil.getCallerMethodName());
		WDn(Opcode.WDB, 20);
	}

	private void PSDn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int base  = 0x1000;
		int sa    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		push(base);
		push(Types.lowHalf(value));
		push(Types.highHalf(value));
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(stack[1], read16MDS(sa + 0));
		assertEquals(stack[2], read16MDS(sa + 1));
		// memory
		assertEquals(Types.lowHalf(value),  read16MDS(sa + 0));
		assertEquals(Types.highHalf(value), read16MDS(sa + 1));
	}
	@Test
	public void PSD0() {
		logger.info(StackUtil.getCallerMethodName());
		PSDn(Opcode.PSD0, 0);
	}
	@Test
	public void PSDB() {
		logger.info(StackUtil.getCallerMethodName());
		PSDn(Opcode.PSDB, 20);
	}
	
	private void WDLn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int base  = 0x30_0000;
		int va    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		push(Types.lowHalf(value));
		push(Types.highHalf(value));
		pushLong(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		assertEquals(stack[0], read16(va + 0));
		assertEquals(stack[1], read16(va + 1));
		// memory
		assertEquals(Types.lowHalf(value),  read16(va + 0));
		assertEquals(Types.highHalf(value), read16(va + 1));
	}
	@Test
	public void WDLB() {
		logger.info(StackUtil.getCallerMethodName());
		WDLn(Opcode.WDLB, 20);
	}
	
	private void PSDLn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int base  = 0x30_0000;
		int va    = base + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		pushLong(base);
		push(Types.lowHalf(value));
		push(Types.highHalf(value));
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(2, SP);
		// stack contents
		assertEquals(stack[2], read16(va + 0));
		assertEquals(stack[3], read16(va + 1));
		// memory
		assertEquals(Types.lowHalf(value),  read16(va + 0));
		assertEquals(Types.highHalf(value), read16(va + 1));
	}
	@Test
	public void PSDLB() {
		logger.info(StackUtil.getCallerMethodName());
		PSDLn(Opcode.PSDLB, 20);
	}
	
	private void RLInm(Opcode opcode, int left, int right) {
		int value = 0xCAFE;
		int base  = 0x1000;
		int sa    = LF + left;
		int sb    = base + right;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, Types.toCARD8(left, right)));
		// data
		write16MDS(sa, base);
		write16MDS(sb, value);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(value, stack[0]);
		// memory
		assertEquals(read16MDS(sb), stack[0]);
	}
	@Test
	public void RLI00() {
		logger.info(StackUtil.getCallerMethodName());
		RLInm(Opcode.RLI00, 0, 0);
	}
	@Test
	public void RLI01() {
		logger.info(StackUtil.getCallerMethodName());
		RLInm(Opcode.RLI01, 0, 1);
	}
	@Test
	public void RLI02() {
		logger.info(StackUtil.getCallerMethodName());
		RLInm(Opcode.RLI02, 0, 2);
	}
	@Test
	public void RLI03() {
		logger.info(StackUtil.getCallerMethodName());
		RLInm(Opcode.RLI03, 0, 3);
	}
	@Test
	public void RLIIP() {
		logger.info(StackUtil.getCallerMethodName());
		RLInm(Opcode.RLIP, 7, 5);
	}
	
	private void RLDInm(Opcode opcode, int left, int right) {
		int value = 0xCAFEBABE;
		int base  = 0x1000;
		int sa    = LF + left;
		int sb    = base + right;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, Types.toCARD8(left, right)));
		// data
		write16MDS(sa, base);
		write16MDS(sb + 0, Types.lowHalf(value));
		write16MDS(sb + 1, Types.highHalf(value));
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(2, SP);
		// stack contents
		assertEquals(Types.lowHalf(value),  stack[0]);
		assertEquals(Types.highHalf(value), stack[1]);
		// memory
		assertEquals(read16MDS(sb + 0), stack[0]);
		assertEquals(read16MDS(sb + 1), stack[1]);
	}
	@Test
	public void RLDI00() {
		logger.info(StackUtil.getCallerMethodName());
		RLDInm(Opcode.RLDI00, 0, 0);
	}
	@Test
	public void RLDIP() {
		logger.info(StackUtil.getCallerMethodName());
		RLDInm(Opcode.RLDIP, 7, 5);
	}

	private void RLDILnm(Opcode opcode, int left, int right) {
		int value = 0xCAFEBABE;
		int base  = 0x30_0000;
		int sa    = LF + left;
		int sb    = base + right;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, Types.toCARD8(left, right)));
		// data
		write32MDS(sa, base);
		write16(sb + 0, Types.lowHalf(value));
		write16(sb + 1, Types.highHalf(value));
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(2, SP);
		// stack contents
		assertEquals(Types.lowHalf(value),  stack[0]);
		assertEquals(Types.highHalf(value), stack[1]);
		// memory
		assertEquals(read16(sb + 0), stack[0]);
		assertEquals(read16(sb + 1), stack[1]);
	}
	@Test
	public void RLDILP() {
		logger.info(StackUtil.getCallerMethodName());
		RLDILnm(Opcode.RLDILP, 7, 5);
	}

	private void RGInm(Opcode opcode, int left, int right) {
		int value = 0xCAFE;
		int base  = 0x1000;
		int va    = GF + left;
		int sb    = base + right;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, Types.toCARD8(left, right)));
		// data
		write16   (va, base);
		write16MDS(sb, value);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(value, stack[0]);
		// memory
		assertEquals(read16MDS(sb), stack[0]);
	}
	@Test
	public void RGIP() {
		logger.info(StackUtil.getCallerMethodName());
		RGInm(Opcode.RGIP, 7, 5);
	}
	
	private void RGILnm(Opcode opcode, int left, int right) {
		int value = 0xCAFE;
		int base  = 0x30_0000;
		int va    = GF + left;
		int vb    = base + right;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, Types.toCARD8(left, right)));
		// data
		write32(va, base);
		write16(vb, value);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(value, stack[0]);
		// memory
		assertEquals(read16(vb), stack[0]);
	}
	@Test
	public void RGILP() {
		logger.info(StackUtil.getCallerMethodName());
		RGILnm(Opcode.RGILP, 7, 5);
	}
	
	private void WLInm(Opcode opcode, int left, int right) {
		int value = 0xCAFE;
		int base  = 0x1000;
		int sa    = LF + left;
		int sb    = base + right;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, Types.toCARD8(left, right)));
		// data
		write16MDS(sa, base);
		push(value);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		// memory
		assertEquals(value, read16MDS(sb));
	}
	@Test
	public void WLIP() {
		logger.info(StackUtil.getCallerMethodName());
		WLInm(Opcode.WLIP, 7, 5);
	}
	
	private void WLILnm(Opcode opcode, int left, int right) {
		int value = 0xCAFE;
		int base  = 0x30_0000;
		int sa    = LF + left;
		int vb    = base + right;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, Types.toCARD8(left, right)));
		// data
		write32MDS(sa, base);
		push(value);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		// memory
		assertEquals(value, read16(vb));
	}
	@Test
	public void WLILP() {
		logger.info(StackUtil.getCallerMethodName());
		WLILnm(Opcode.WLILP, 7, 5);
	}
	
	private void WLDILnm(Opcode opcode, int left, int right) {
//		var alpha = NibblePair.value(getCodeByte());
//		int ptr   = read32MDS(LF + alpha.left());
//		int right = alpha.right();
//		write16(ptr + right + 1, pop());
//		write16(ptr + right + 0, pop());

		int value = 0xCAFEBABE;
		int base  = 0x30_0000;
		int sa    = LF + left;
		int vb    = base + right;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, Types.toCARD8(left, right)));
		// data
		write32MDS(sa, base);
		push(Types.lowHalf(value));
		push(Types.highHalf(value));
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		// memory
		assertEquals(Types.lowHalf(value),  read16(vb + 0));
		assertEquals(Types.highHalf(value), read16(vb + 1));
	}
	@Test
	public void WLDILP() {
		logger.info(StackUtil.getCallerMethodName());
		WLDILnm(Opcode.WLDILP, 7, 5);
	}
	
	private void RSn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int index = 0x30;
		int base  = 0x1000;
		int sa    = base + (n + index) / 2;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16MDS(sa, value);
		push(base);
		push(index);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		int expect = ((n + index) & 1) == 0 ? Types.highByte(value) : Types.lowByte(value);
		assertEquals(expect, stack[0]);
		// memory
	}
	@Test
	public void RS_A() {
		logger.info(StackUtil.getCallerMethodName());
		RSn(Opcode.RS, 20);
	}
	@Test
	public void RS_B() {
		logger.info(StackUtil.getCallerMethodName());
		RSn(Opcode.RS, 21);
	}
	
	private void RLSn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int index = 0x30;
		int base  = 0x30_0000;
		int va    = base + (n + index) / 2;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16(va, value);
		pushLong(base);
		push(index);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		int expect = ((n + index) & 1) == 0 ? Types.highByte(value) : Types.lowByte(value);
		assertEquals(expect, stack[0]);
		// memory
	}
	@Test
	public void RLS_A() {
		logger.info(StackUtil.getCallerMethodName());
		RLSn(Opcode.RLS, 20);
	}
	@Test
	public void RLS_B() {
		logger.info(StackUtil.getCallerMethodName());
		RLSn(Opcode.RLS, 21);
	}
	
	private void WSn(Opcode opcode, int n) {
		BytePair word = BytePair.value(0xCAFE);
		int data  = 0xAABB;
		int index = 0x30;
		int base  = 0x1000;
		int sa    = base + (n + index) / 2;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16MDS(sa, word.value);
		push(data);
		push(base);
		push(index);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		if (((n + index) & 1) == 0) {
			// left
			word.left(data);
		} else {
			// right
			word.right(data);
		}
		assertEquals(word.value, read16MDS(sa));
		// memory
	}
	@Test
	public void WS_A() {
		logger.info(StackUtil.getCallerMethodName());
		WSn(Opcode.WS, 20);
	}
	@Test
	public void WS_B() {
		logger.info(StackUtil.getCallerMethodName());
		WSn(Opcode.WS, 21);
	}

	private void WLSn(Opcode opcode, int n) {
		BytePair word = BytePair.value(0xCAFE);
		int data  = 0xAABB;
		int index = 0x30;
		int base  = 0x30_0000;
		int va    = base + (n + index) / 2;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16(va, word.value);
		push(data);
		pushLong(base);
		push(index);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		if (((n + index) & 1) == 0) {
			// left
			word.left(data);
		} else {
			// right
			word.right(data);
		}
		assertEquals(word.value, read16(va));
		// memory
	}
	@Test
	public void WLS_A() {
		logger.info(StackUtil.getCallerMethodName());
		WLSn(Opcode.WLS, 20);
	}
	@Test
	public void WLS_B() {
		logger.info(StackUtil.getCallerMethodName());
		WLSn(Opcode.WLS, 21);
	}

	private void R0F(Opcode opcode, int field, int expect) {
		int value = 0xCAFE;
		int base  = 0x1000;
		int sa    = base;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, field));
		// data
		write16MDS(sa, value);
		push(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(expect, stack[0]);
		// memory
	}
	@Test
	public void R0F_A() {
		logger.info(StackUtil.getCallerMethodName());
		R0F(Opcode.R0F, FieldSpec.value().pos( 0).size(3).value, 0xC);
	}
	@Test
	public void R0F_B() {
		logger.info(StackUtil.getCallerMethodName());
		R0F(Opcode.R0F, FieldSpec.value().pos( 4).size(3).value, 0xA);
	}
	@Test
	public void R0F_C() {
		logger.info(StackUtil.getCallerMethodName());
		R0F(Opcode.R0F, FieldSpec.value().pos( 8).size(3).value, 0xF);
	}
	@Test
	public void R0F_D() {
		logger.info(StackUtil.getCallerMethodName());
		R0F(Opcode.R0F, FieldSpec.value().pos(12).size(3).value, 0xE);
	}
	
	private void RF(Opcode opcode, int offset, int field, int expect) {
		int value = 0xCAFE;
		int base  = 0x1000;
		int sa    = base;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, offset));
		writeReal16(ra_PC + 1, Types.toCARD16(field, 0));
		// data
		write16MDS(sa + offset, value);
		push(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(expect, stack[0]);
		// memory
	}
	@Test
	public void RF_A() {
		logger.info(StackUtil.getCallerMethodName());
		RF(Opcode.RF, 20, FieldSpec.value().pos( 0).size(3).value, 0xC);
	}
	@Test
	public void RF_B() {
		logger.info(StackUtil.getCallerMethodName());
		RF(Opcode.RF, 20, FieldSpec.value().pos( 4).size(3).value, 0xA);
	}
	@Test
	public void RF_C() {
		logger.info(StackUtil.getCallerMethodName());
		RF(Opcode.RF, 20, FieldSpec.value().pos( 8).size(3).value, 0xF);
	}
	@Test
	public void RF_D() {
		logger.info(StackUtil.getCallerMethodName());
		RF(Opcode.RF, 20, FieldSpec.value().pos(12).size(3).value, 0xE);
	}
	
	private void RL0F(Opcode opcode, int field, int expect) {
		int value = 0xCAFE;
		int base  = 0x30_0000;
		int va    = base;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, field));
		// data
		write16(va, value);
		pushLong(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(expect, stack[0]);
		// memory
	}
	@Test
	public void RL0F_A() {
		logger.info(StackUtil.getCallerMethodName());
		RL0F(Opcode.RL0F, FieldSpec.value().pos( 0).size(3).value, 0xC);
	}
	@Test
	public void RL0F_B() {
		logger.info(StackUtil.getCallerMethodName());
		RL0F(Opcode.RL0F, FieldSpec.value().pos( 4).size(3).value, 0xA);
	}
	@Test
	public void RL0F_C() {
		logger.info(StackUtil.getCallerMethodName());
		RL0F(Opcode.RL0F, FieldSpec.value().pos( 8).size(3).value, 0xF);
	}
	@Test
	public void RL0F_D() {
		logger.info(StackUtil.getCallerMethodName());
		RL0F(Opcode.RL0F, FieldSpec.value().pos(12).size(3).value, 0xE);
	}
	
	private void RLF(Opcode opcode, int offset, int field, int expect) {
		int value = 0xCAFE;
		int base  = 0x30_0000;
		int va    = base + offset;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, offset));
		writeReal16(ra_PC + 1, Types.toCARD16(field, 0));
		// data
		write16(va, value);
		pushLong(base);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(expect, stack[0]);
		// memory
	}
	@Test
	public void RLF_A() {
		logger.info(StackUtil.getCallerMethodName());
		RLF(Opcode.RLF, 0x30, FieldSpec.value().pos( 0).size(3).value, 0xC);
	}
	@Test
	public void RLF_B() {
		logger.info(StackUtil.getCallerMethodName());
		RLF(Opcode.RLF, 0x30, FieldSpec.value().pos( 4).size(3).value, 0xA);
	}
	@Test
	public void RLF_C() {
		logger.info(StackUtil.getCallerMethodName());
		RLF(Opcode.RLF, 0x30, FieldSpec.value().pos( 8).size(3).value, 0xF);
	}
	@Test
	public void RLF_D() {
		logger.info(StackUtil.getCallerMethodName());
		RLF(Opcode.RLF, 0x30, FieldSpec.value().pos(12).size(3).value, 0xE);
	}
	
	private void RLFS(Opcode opcode, int offset, int field, int expect) {
		int value = 0xCAFE;
		int base  = 0x30_0000;
		int va    = base + offset;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		write16(va, value);
		pushLong(base);
		FieldDesc desc = FieldDesc.value().offset(offset).field(FieldSpec.value(field));
		push(desc.value);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(expect, stack[0]);
		// memory
	}
	@Test
	public void RLFS_A() {
		logger.info(StackUtil.getCallerMethodName());
		RLFS(Opcode.RLFS, 0x30, FieldSpec.value().pos( 0).size(3).value, 0xC);
	}
	@Test
	public void RLFS_B() {
		logger.info(StackUtil.getCallerMethodName());
		RLFS(Opcode.RLFS, 0x30, FieldSpec.value().pos( 4).size(3).value, 0xA);
	}
	@Test
	public void RLFS_C() {
		logger.info(StackUtil.getCallerMethodName());
		RLFS(Opcode.RLFS, 0x30, FieldSpec.value().pos( 8).size(3).value, 0xF);
	}
	@Test
	public void RLFS_D() {
		logger.info(StackUtil.getCallerMethodName());
		RLFS(Opcode.RLFS, 0x30, FieldSpec.value().pos(12).size(3).value, 0xE);
	}
	


}

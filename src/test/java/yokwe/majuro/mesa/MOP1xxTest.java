package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import org.junit.jupiter.api.Test;

import yokwe.majuro.opcode.Interpreter;
import yokwe.majuro.opcode.Opcode;
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

}

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

}

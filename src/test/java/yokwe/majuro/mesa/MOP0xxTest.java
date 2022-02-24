package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import org.junit.jupiter.api.Test;

import yokwe.majuro.opcode.Interpreter;
import yokwe.majuro.opcode.Opcode;
import yokwe.majuro.util.StackUtil;

public class MOP0xxTest extends Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	private void LLn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int sa    = LF + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16MDS(sa, value);
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
	public void LL0() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL0, 0);
	}
	@Test
	public void LL1() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL1, 1);
	}
	@Test
	public void LL2() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL2, 2);
	}
	@Test
	public void LL3() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL3, 3);
	}
	@Test
	public void LL4() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL4, 4);
	}
	@Test
	public void LL5() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL5, 5);
	}
	@Test
	public void LL6() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL6, 6);
	}
	@Test
	public void LL7() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL7, 7);
	}
	@Test
	public void LL8() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL8, 8);
	}
	@Test
	public void LL9() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL9, 9);
	}
	@Test
	public void LL10() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL10, 10);
	}
	@Test
	public void LL11() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LL11, 11);
	}
	@Test
	public void LLB() {
		logger.info(StackUtil.getCallerMethodName());
		LLn(Opcode.LLB, 20);
	}
	
	private void LLDn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int sa    = LF + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16MDS(sa + 0, Types.lowHalf(value));
		write16MDS(sa + 1, Types.highHalf(value));
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
	public void LLD0() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD0, 0);
	}
	@Test
	public void LLD1() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD1, 1);
	}
	@Test
	public void LLD2() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD2, 2);
	}
	@Test
	public void LLD3() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD3, 3);
	}
	@Test
	public void LLD4() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD4, 4);
	}
	@Test
	public void LLD5() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD5, 5);
	}
	@Test
	public void LLD6() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD6, 6);
	}
	@Test
	public void LLD7() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD7, 7);
	}
	@Test
	public void LLD8() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD8, 8);
	}
	@Test
	public void LLD10() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLD10, 10);
	}
	@Test
	public void LLDB() {
		logger.info(StackUtil.getCallerMethodName());
		LLDn(Opcode.LLDB, 20);
	}
	
	private void SLn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int sa    = LF + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		push(value);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(0, SP);
		// stack contents
		assertEquals(read16MDS(sa), stack[0]);
		// memory
	}
	@Test
	public void SL0() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL0, 0);
	}
	@Test
	public void SL1() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL1, 1);
	}
	@Test
	public void SL2() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL2, 2);
	}
	@Test
	public void SL3() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL3, 3);
	}
	@Test
	public void SL4() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL4, 4);
	}
	@Test
	public void SL5() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL5, 5);
	}
	@Test
	public void SL6() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL6, 6);
	}
	@Test
	public void SL7() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL7, 7);
	}
	@Test
	public void SL8() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL8, 8);
	}
	@Test
	public void SL9() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL9, 9);
	}
	@Test
	public void SL10() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SL10, 10);
	}
	@Test
	public void SLB() {
		logger.info(StackUtil.getCallerMethodName());
		SLn(Opcode.SLB, 20);
	}

}

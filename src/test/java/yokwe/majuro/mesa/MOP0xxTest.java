package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static yokwe.majuro.mesa.Memory.PC;
import static yokwe.majuro.mesa.Memory.read16;
import static yokwe.majuro.mesa.Memory.read16MDS;
import static yokwe.majuro.mesa.Memory.write16;
import static yokwe.majuro.mesa.Memory.write16MDS;
import static yokwe.majuro.mesa.Memory.writeReal16;
import static yokwe.majuro.mesa.Processor.GF;
import static yokwe.majuro.mesa.Processor.LF;
import static yokwe.majuro.mesa.Processor.SP;
import static yokwe.majuro.mesa.Processor.breakByte;
import static yokwe.majuro.mesa.Processor.push;
import static yokwe.majuro.mesa.Processor.savedPC;
import static yokwe.majuro.mesa.Processor.stack;

import org.junit.jupiter.api.Test;

import yokwe.majuro.mesa.Processor.AbortRuntimeException;
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
	
	private void SLDn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int sa    = LF + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
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
		assertEquals(read16MDS(sa + 0), stack[0]);
		assertEquals(read16MDS(sa + 1), stack[1]);
		// memory
	}
	@Test
	public void SLD0() {
		logger.info(StackUtil.getCallerMethodName());
		SLDn(Opcode.SLD0, 0);
	}
	@Test
	public void SLD1() {
		logger.info(StackUtil.getCallerMethodName());
		SLDn(Opcode.SLD1, 1);
	}
	@Test
	public void SLD2() {
		logger.info(StackUtil.getCallerMethodName());
		SLDn(Opcode.SLD2, 2);
	}
	@Test
	public void SLD3() {
		logger.info(StackUtil.getCallerMethodName());
		SLDn(Opcode.SLD3, 3);
	}
	@Test
	public void SLD4() {
		logger.info(StackUtil.getCallerMethodName());
		SLDn(Opcode.SLD4, 4);
	}
	@Test
	public void SLD5() {
		logger.info(StackUtil.getCallerMethodName());
		SLDn(Opcode.SLD5, 5);
	}
	@Test
	public void SLD6() {
		logger.info(StackUtil.getCallerMethodName());
		SLDn(Opcode.SLD6, 6);
	}
	@Test
	public void SLD8() {
		logger.info(StackUtil.getCallerMethodName());
		SLDn(Opcode.SLD8, 8);
	}
	
	@Test
	private void PLn(Opcode opcode, int n) {
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
		assertEquals(1, SP);
		// stack contents
		assertEquals(read16MDS(sa), stack[0]);
		assertEquals(value, stack[0]);
		// memory
	}
	@Test
	public void PL0() {
		logger.info(StackUtil.getCallerMethodName());
		PLn(Opcode.PL0, 0);
	}
	@Test
	public void PL1() {
		logger.info(StackUtil.getCallerMethodName());
		PLn(Opcode.PL1, 1);
	}
	@Test
	public void PL2() {
		logger.info(StackUtil.getCallerMethodName());
		PLn(Opcode.PL2, 2);
	}
	@Test
	public void PL3() {
		logger.info(StackUtil.getCallerMethodName());
		PLn(Opcode.PL3, 3);
	}
	@Test
	public void PLB() {
		logger.info(StackUtil.getCallerMethodName());
		PLn(Opcode.PLB, 20);
	}
	
	private void PLDn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int sa    = LF + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
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
		assertEquals(read16MDS(sa + 0), stack[0]);
		assertEquals(read16MDS(sa + 1), stack[1]);
		assertEquals(Types.lowHalf (value), stack[0]);
		assertEquals(Types.highHalf(value), stack[1]);
		// memory
	}
	@Test
	public void PLD0() {
		logger.info(StackUtil.getCallerMethodName());
		PLDn(Opcode.PLD0, 0);
	}
	@Test
	public void PLDB() {
		logger.info(StackUtil.getCallerMethodName());
		PLDn(Opcode.PLDB, 20);
	}

	private void LGn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int va    = GF + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16(va, value);
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
	public void LG0() {
		logger.info(StackUtil.getCallerMethodName());
		LGn(Opcode.LG0, 0);
	}
	@Test
	public void LG1() {
		logger.info(StackUtil.getCallerMethodName());
		LGn(Opcode.LG1, 1);
	}
	@Test
	public void LG2() {
		logger.info(StackUtil.getCallerMethodName());
		LGn(Opcode.LG2, 2);
	}
	@Test
	public void LGB() {
		logger.info(StackUtil.getCallerMethodName());
		LGn(Opcode.LGB, 20);
	}

	private void LGDn(Opcode opcode, int n) {
		int value = 0xCAFEBABE;
		int va    = GF + n;
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		write16(va + 0, Types.lowHalf(value));
		write16(va + 1, Types.highHalf(value));
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
	public void LGD0() {
		logger.info(StackUtil.getCallerMethodName());
		LGDn(Opcode.LGD0, 0);
	}
	@Test
	public void LGD2() {
		logger.info(StackUtil.getCallerMethodName());
		LGDn(Opcode.LGD2, 2);
	}
	@Test
	public void LGDB() {
		logger.info(StackUtil.getCallerMethodName());
		LGDn(Opcode.LGDB, 20);
	}
	
	private void SGn(Opcode opcode, int n) {
		int value = 0xCAFE;
		int va    = GF + n;
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
		assertEquals(read16(va), stack[0]);
		// memory
	}
	@Test
	public void SGB() {
		logger.info(StackUtil.getCallerMethodName());
		SGn(Opcode.SGB, 20);
	}

	private void BNDCK(int range, int index) {
		Opcode opcode = Opcode.BNDCK;
		
		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		push(index);
		push(range);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(savedPC + opcode.len, PC());
		// sp
		assertEquals(1, SP);
		// stack contents
		assertEquals(index, stack[0]);
		// memory
	}
	@Test
	public void BNDCK_OK() {
		logger.info(StackUtil.getCallerMethodName());
		int range = 10;
		int index = 1;
		
		boolean abortObserved = false;
		String message = null;
		try {
			BNDCK(range, index);
		} catch(AbortRuntimeException e) {
			abortObserved = true;
			message = e.getMessage();
		}
		assertEquals(false, abortObserved);
		assertEquals(null, message); // FIXME
	}
	@Test
	public void BNDCK_NG() {
		logger.info(StackUtil.getCallerMethodName());
		int range = 10;
		int index = 20;

		boolean abortObserved = false;
		String message = null;
		try {
			BNDCK(range, index);
		} catch(AbortRuntimeException e) {
			abortObserved = true;
			message = e.getMessage();
		}
		assertEquals(true, abortObserved);
		assertEquals("boundsTrap", message); // FIXME
	}

	@Test
	public void BRK_OK() {
		Opcode opcode = Opcode.BRK;

		int value = 0xCAFE;
		int sa    = LF + 0;

		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		breakByte = Opcode.LL0.code;
		write16MDS(sa, value); // LL0
		// execute
		boolean abortObserved = false;
		String message = null;
		try {
			Interpreter.execute();
		} catch(AbortRuntimeException e) {
			abortObserved = true;
			message = e.getMessage();
		}
		assertEquals(false, abortObserved);
		assertEquals(null, message); // FIXME

		// pc
		assertEquals(savedPC + opcode.len, PC()); // LL0
		// sp
		assertEquals(1, SP); // LL0
		// stack contents
		assertEquals(read16MDS(sa), stack[0]); // LL0
		// memory
		assertEquals(0, breakByte);
	}
	@Test
	public void BRK_NG() {
		Opcode opcode = Opcode.BRK;

		int value = 0xCAFE;
		int sa    = LF + 0;

		// opcode
		writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		breakByte = 0;
		write16MDS(sa, value); // LL0
		// execute
		boolean abortObserved = false;
		String message = null;
		try {
			Interpreter.execute();
		} catch(AbortRuntimeException e) {
			abortObserved = true;
			message = e.getMessage();
		}
		assertEquals(true, abortObserved);
		assertEquals("breakTrap", message); // FIXME

		// pc
		assertEquals(savedPC + opcode.len, PC()); // LL0
		// sp
//		assertEquals(1, SP); // LL0
		// stack contents
//		assertEquals(read16MDS(sa), stack[0]); // LL0
		// memory
		assertEquals(0, breakByte);
	}

}

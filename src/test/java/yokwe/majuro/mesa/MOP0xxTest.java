package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import yokwe.majuro.opcode.Interpreter;
import yokwe.majuro.opcode.Opcode;
import yokwe.majuro.util.StackUtil;

public class MOP0xxTest extends Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	@Test
	public void LL0() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 0;
		var opcode = Opcode.LL0;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}
	
	@Test
	public void LL1() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 1;
		var opcode = Opcode.LL1;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}
	
	@Test
	public void LL2() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 2;
		var opcode = Opcode.LL2;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}
	
	@Test
	public void LL3() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 3;
		var opcode = Opcode.LL3;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}
	
	@Test
	public void LL4() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 4;
		var opcode = Opcode.LL4;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}

	@Test
	public void LL5() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 5;
		var opcode = Opcode.LL5;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}

	@Test
	public void LL6() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 6;
		var opcode = Opcode.LL6;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}

	@Test
	public void LL7() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 7;
		var opcode = Opcode.LL7;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}

	@Test
	public void LL8() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 8;
		var opcode = Opcode.LL8;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}

	@Test
	public void LL9() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 9;
		var opcode = Opcode.LL9;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}

	@Test
	public void LL10() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 10;
		var opcode = Opcode.LL10;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}

	@Test
	public void LL11() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 11;
		var opcode = Opcode.LL11;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, 0));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 1, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}

	@Test
	public void LLB() {
		logger.info(StackUtil.getCallerMethodName());

		int n = 20;
		var opcode = Opcode.LLB;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(opcode.code, n));
		// data
		Memory.writeReal16(ra_LF + n, 0xCAFE);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 2, Memory.PC());
		// sp
		assertEquals(1, Processor.SP);
		// stack contents
		assertEquals(Memory.readReal16(ra_LF + n), Processor.stack[0]);
		// memory
	}
	
}

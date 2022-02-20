package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import yokwe.majuro.opcode.Interpreter;
import yokwe.majuro.opcode.Opcode;

public class MOP0xxTest extends Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	@Test
	public void LL0() {
		logger.info("LL0");

		int n = 0;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(Opcode.LL0.code, 0));
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
		logger.info("LL1");

		int n = 1;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(Opcode.LL1.code, 0));
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
		logger.info("LL2");

		int n = 2;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(Opcode.LL2.code, 0));
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
		logger.info("LL3");

		int n = 3;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(Opcode.LL3.code, 0));
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
		logger.info("LL4");

		int n = 4;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(Opcode.LL4.code, 0));
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

	
}

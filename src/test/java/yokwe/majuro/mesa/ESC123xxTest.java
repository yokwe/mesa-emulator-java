package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;
import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import org.junit.jupiter.api.Test;

import yokwe.majuro.opcode.Interpreter;
import yokwe.majuro.opcode.Opcode;

public class ESC123xxTest extends Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	@Test
	public void WRMDS() {
		logger.info("WRMDS");

		int n = 0x1234;
		
		// opcode
		Memory.writeReal16(ra_PC + 0, Types.toCARD16(Opcode.ESC.code, Opcode.WRMDS.code));
		// data
		push(n);
		// execute
		Interpreter.execute();
		// check result
		// pc
		assertEquals(Processor.savedPC + 2, Memory.PC());
		// sp
		assertEquals(0, Processor.SP);
		// stack contents
		// memory
		// register
		assertEquals(n << WORD_BITS, Processor.MDS);
	}

}

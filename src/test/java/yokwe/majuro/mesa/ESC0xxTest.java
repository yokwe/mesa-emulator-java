package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;
import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import org.junit.jupiter.api.Test;

import yokwe.majuro.opcode.Interpreter;
import yokwe.majuro.opcode.Opcode;

public class ESC0xxTest extends Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	@Test
	void dummp() {
		logger.info("dummy");
		assertEquals(0, 0);
	}

}

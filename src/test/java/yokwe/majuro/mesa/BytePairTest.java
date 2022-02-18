package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import yokwe.majuro.type.BytePair;

public class BytePairTest extends Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	@Test
	public void valueA() {
		logger.info("valueA");

		char value = 0x89AB;

		// prepare
		// execute
		BytePair bytePair = BytePair.value(value);
		
		// check result
		assertEquals(value >>> 8, bytePair.left());
		assertEquals(value & 0xFF, bytePair.right());
		assertEquals(value, bytePair.value);
	}

	@Test
	public void value_B() {
		logger.info("valueB");

		char left  = 0x89;
		char right = 0xAB;

		// prepare
		// execute
		BytePair bytePair = BytePair.value((char)0);
		bytePair.left(left);
		bytePair.right(right);
		
		// check result
		assertEquals(left,  bytePair.left());
		assertEquals(right, bytePair.right());
		assertEquals(left << 8 | right, bytePair.value);
	}

}

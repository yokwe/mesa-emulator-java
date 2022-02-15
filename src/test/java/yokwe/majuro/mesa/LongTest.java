package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import yokwe.majuro.type.Long;
import yokwe.majuro.type.MemoryAccess;

public class LongTest extends Base {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LongTest.class);

	@Test
	public void valueA() {
		logger.info("valueA");

		int value = 0x89AB_CDEF;

		// prepare
		// execute
		Long object = Long.value(value);
		
		// check result
		assertEquals(value & 0xFFFF, object.low());
		assertEquals(value >>> 16, object.high());
	}
	@Test
	public void valueB() {
		logger.info("valueB");

		int  value = 0xabcd1234;
		char low   = Types.lowHalf(value);
		char high  = Types.highHalf(value);

		// prepare
		// execute
		Long object = Long.value(0);
		object.low(low);
		object.high(high);
		
		// check result
		assertEquals(low,  object.low());
		assertEquals(high, object.high());
		assertEquals(value, object.value);
	}
	
	@Test
	public void read() {
		logger.info("read");

		int va    = 0x20_1234;
		int value = 0x89AB_CDEF;

		// prepare
		Memory.instance.write32(va, value);
		
		// execute
		Long object = Long.longPointer(va, MemoryAccess.READ);
		
		// check result
		assertEquals(Types.lowHalf(value), object.low());
		assertEquals(Types.highHalf(value), object.high());
		assertEquals(value, object.value);
	}
	
	@Test
	public void write() {
		logger.info("write");

		int va    = 0x20_1234;
		int value = 0x89AB_CDEF;

		// prepare
		// execute
		Long object = Long.longPointer(va, MemoryAccess.WRITE);
		object.value = value;
		object.write();
		
		// check result
		assertEquals(Types.lowHalf(value),  Memory.instance.read16(va + 0));
		assertEquals(Types.highHalf(value), Memory.instance.read16(va + 1));
		assertEquals(value, object.value);
	}

}
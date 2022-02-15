package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import yokwe.majuro.type.CARDINAL;
import yokwe.majuro.type.MemoryAccess;

public class CARDINALTest extends Base {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CARDINALTest.class);

	@Test
	public void value() {
		logger.info("value");

		char value = 0x89AB;

		// prepare
		// execute
		CARDINAL t = CARDINAL.value(value);
		
		// check result
		assertEquals(value, t.value);
	}
	
	@Test
	public void longPointer_read() {
		logger.info("longPointer_read");

		int  va    = 0x0020_0123;
		char value = 0x89AB;

		// prepare
		Mesa.writeReal16(va, value);
		
		// execute
		CARDINAL t = CARDINAL.longPointer(va, MemoryAccess.READ);
		Map map = memory.map(va);
		
		// check result
		assertEquals(value, t.value);
		assertEquals(false, map.isProtect());
		assertEquals(false, map.isDirty());
		assertEquals(true,  map.isReferenced());
	}
	@Test
	public void longPointer_write() {
		logger.info("longPointer_write");

		int  va    = 0x0020_0123;

		// prepare
		// execute
		CARDINAL t = CARDINAL.longPointer(va, MemoryAccess.WRITE);
		Map map = memory.map(va);
		
		// check result
		assertEquals(0, t.value);
		assertEquals(false, map.isProtect());
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
	}
	@Test
	public void longPointer_readWrite() {
		logger.info("longPointer_readWrite");

		int  va    = 0x0020_0123;
		char value = 0x89AB;

		// prepare
		Mesa.writeReal16(va, value);
		// execute
		CARDINAL t = CARDINAL.longPointer(va, MemoryAccess.READ_WRITE);
		Map map = memory.map(va);
		
		// check result
		assertEquals(value, t.value);
		assertEquals(false, map.isProtect());
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
	}

	
	@Test
	public void pointer_read() {
		logger.info("pointer_read");

		char sa    = 0x0123;
		int  va    = DEFAULT_MDS + sa;
		char value = 0x89AB;
		
		// prepare
		Mesa.writeReal16(va, value);
		
		// execute
		CARDINAL t = CARDINAL.pointer(sa, MemoryAccess.READ);
		Map map = memory.map(va);
		
		// check result
		assertEquals(value, t.value);
		assertEquals(false, map.isProtect());
		assertEquals(false, map.isDirty());
		assertEquals(true,  map.isReferenced());
	}
	@Test
	public void pointer_write() {
		logger.info("pointer_write");

		char sa = 0x0123;
		int  va = DEFAULT_MDS + sa;

		// prepare
		// execute
		CARDINAL t = CARDINAL.pointer(sa, MemoryAccess.WRITE);
		Map map = memory.map(va);
		
		// check result
		assertEquals(0, t.value);
		assertEquals(false, map.isProtect());
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
	}
	@Test
	public void pointer_readWrite() {
		logger.info("pointer_readWrite");

		char sa    = 0x0123;
		int  va    = DEFAULT_MDS + sa;
		char value = 0x89AB;

		// prepare
		Mesa.writeReal16(va, value);
		
		// execute
		CARDINAL t = CARDINAL.pointer(sa, MemoryAccess.READ_WRITE);
		Map map = memory.map(va);
		
		// check result
		assertEquals(value, t.value);
		assertEquals(false, map.isProtect());
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
	}

}

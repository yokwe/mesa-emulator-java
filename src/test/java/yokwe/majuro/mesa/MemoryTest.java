package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;
import static yokwe.majuro.mesa.Constants.*;
import static yokwe.majuro.mesa.Memory.*;
import static yokwe.majuro.mesa.Processor.*;

import org.junit.jupiter.api.Test;

import yokwe.majuro.type.BytePair;
import yokwe.majuro.type.FieldSpec;
import yokwe.majuro.util.StackUtil;

public class MemoryTest extends Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	// REAL MEMORY
	@Test
	public void fetch() {
		logger.info(StackUtil.getCallerMethodName());

		int va = 0x0020_0123;
		
		// prepare
		// execute
		int ra = Memory.fetch(va);
		
		// check result
		assertEquals(va, ra);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(false, map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}
	@Test
	public void store() {
		logger.info(StackUtil.getCallerMethodName());

		int va = 0x0020_0123;
		
		// prepare
		// execute
		int ra = Memory.store(va);
		
		// check result
		assertEquals(va, ra);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}
	
	// real memory 16 bit data
	@Test
	public void readReal16() {
		logger.info(StackUtil.getCallerMethodName());

		int  ra    = 0x0020_0100;
		char value = 0x1234;
		
		// prepare
		Memory.writeReal16(ra, value);
		
		// execute
		int actual = Memory.readReal16(ra);
		
		// check result
		assertEquals(value, actual);
	}
	@Test
	public void writeReal16() {
		logger.info(StackUtil.getCallerMethodName());

		int  ra    = 0x0020_0100;
		char value = 0x1234;
		
		// prepare
		Memory.writeReal16(ra, value);
		
		// execute
		int actual = Memory.readReal16(ra);
		
		// check result
		assertEquals(value, actual);
	}
	
	// real memory 32 bit data
	@Test
	public void readReal32() {
		logger.info(StackUtil.getCallerMethodName());

		int ra    = 0x0020_0100;
		int value = 0x12345678;
		
		// prepare
		Memory.writeReal16(ra + 0, (char)value);
		Memory.writeReal16(ra + 1, (char)(value >>> WORD_BITS));
		
		// execute
		int actual = Memory.readReal32(ra + 0, ra + 1);
		
		// check result
		assertEquals(value, actual);
	}
	@Test
	public void writeReal32() {
		logger.info(StackUtil.getCallerMethodName());

		int ra    = 0x0020_0100;
		int value = 0x12345678;
		
		// prepare
		// execute
		Memory.writeReal32(ra + 0, ra + 1, value);
		
		// check result
		assertEquals((char)value,                 Memory.readReal16(ra + 0));
		assertEquals((char)(value >>> WORD_BITS), Memory.readReal16(ra + 1));
	}

	// VIRTUAL MEMORY
	// 16 bit data
	@Test
	public void read16() {
		logger.info(StackUtil.getCallerMethodName());

		int va    = 0x0020_0100;
		int value = 0x1234;
		
		// prepare
		Memory.writeReal16(va, value);
		
		// execute
		int actual = Memory.read16(va);
		
		// check result
		assertEquals(value, actual);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(false, map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}
	@Test
	public void write16() {
		logger.info(StackUtil.getCallerMethodName());

		int  va    = 0x0020_0100;
		char value = 0x1234;
		
		// prepare
		// execute
		Memory.write16(va, value);
		
		// check result
		int actual = Memory.readReal16(va);
		assertEquals(value, actual);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}

	// 32 bit data
	@Test
	public void isSamePage() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		// check result
		assertEquals(true,  Memory.isSamePage(0x0012_3400, 0x0012_3456));
		assertEquals(false, Memory.isSamePage(0x0012_3500, 0x0012_3456));
	}
	@Test
	public void read32() {
		logger.info(StackUtil.getCallerMethodName());

		int va    = 0x0020_0100;
		int value = 0x12345678;
		
		// prepare
		Memory.writeReal32(va + 0, va + 1, value);
		
		// execute
		int actual = Memory.read32(va);
		
		// check result
		assertEquals(value, actual);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(false, map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}
	@Test
	public void write32() {
		logger.info(StackUtil.getCallerMethodName());

		int va    = 0x0020_0100;
		int value = 0x12345678;
		
		// prepare
		// execute
		Memory.write32(va, value);
		
		// check result
		int actual = Memory.readReal32(va + 0, va + 1);
		assertEquals(value, actual);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}

	// 8 bit data
	@Test
	public void read8_A() {
		logger.info(StackUtil.getCallerMethodName());
		int value  = 0xCAFE;
		int base   = 0x10_0000;
		int offset = 10;
		int va     = base + (offset / 2);
		// data
		Memory.write16(va, value);
		// execute
		int value8 = Memory.read8(base, offset);
		
		// check result
		BytePair data = BytePair.value(value);
		assertEquals(data.left(), value8);
	}
	@Test
	public void read8_B() {
		logger.info(StackUtil.getCallerMethodName());
		int value  = 0xCAFE;
		int base   = 0x10_0000;
		int offset = 11;
		int va     = base + (offset / 2);
		// data
		Memory.write16(va, value);
		// execute
		int value8 = Memory.read8(base, offset);
		
		// check result
		BytePair data = BytePair.value(value);
		assertEquals(data.right(), value8);
	}
	@Test
	public void write8_A() {
		logger.info(StackUtil.getCallerMethodName());
		int value  = 0xAB;
		int base   = 0x10_0000;
		int offset = 10;
		int va     = base + (offset / 2);
		// data
		Memory.write16(va, 0);
		// execute
		Memory.write8(base, offset, value);
		
		// check result
		assertEquals(Types.toCARD16(value, 0), Memory.read16(va));
	}
	@Test
	public void write8_B() {
		logger.info(StackUtil.getCallerMethodName());
		int value  = 0xAB;
		int base   = 0x10_0000;
		int offset = 11;
		int va     = base + (offset / 2);
		// data
		Memory.write16(va, 0);
		// execute
		Memory.write8(base, offset, value);
		
		// check result
		assertEquals(Types.toCARD16(0, value), Memory.read16(va));
	}
	
	// bit field data
	@Test
	public void maskTable() {
		assertEquals(0b0000_0000_0000_0001, Memory.maskTable(0));
		assertEquals(0b0000_0000_0000_0011, Memory.maskTable(1));
		
		assertEquals(0b0111_1111_1111_1111, Memory.maskTable(14));
		assertEquals(0b1111_1111_1111_1111, Memory.maskTable(15));
	}
	@Test
	public void readField() {
		int value = 0xABCD;
		assertEquals(0x0A, Memory.readField(value, FieldSpec.value().pos(0).size(3)));
		assertEquals(0x0B, Memory.readField(value, FieldSpec.value().pos(4).size(3)));
		assertEquals(0x0C, Memory.readField(value, FieldSpec.value().pos(8).size(3)));
		assertEquals(0x0D, Memory.readField(value, FieldSpec.value().pos(12).size(3)));
	}
	@Test
	public void writeField() {
		int value = 0xABCD;
		assertEquals(0xFBCD, Memory.writeField(value, FieldSpec.value().pos(0).size(3), 0x0F));
		assertEquals(0xAFCD, Memory.writeField(value, FieldSpec.value().pos(4).size(3), 0x0F));
		assertEquals(0xABFD, Memory.writeField(value, FieldSpec.value().pos(8).size(3), 0x0F));
		assertEquals(0xABCF, Memory.writeField(value, FieldSpec.value().pos(12).size(3), 0x0F));
	}
	
	// MAIN DATA SPACE
	@Test
	public void lengthenMDS() {
		logger.info(StackUtil.getCallerMethodName());

		char sa = 0xFEDC;
		
		// prepare
		// execute
		int va = Memory.lengthenMDS(sa);
		
		// check result
		assertEquals(sa + DEFAULT_MDS, va);
		
		// check side effect
	}
	
	// PROCESS DATA AREA
	@Test
	public void lengthenPDA() {
		logger.info(StackUtil.getCallerMethodName());

		char sa = 0xFEDC;
		
		// prepare
		// execute
		int va = Memory.lengthenPDA(sa);
		
		// check result
		assertEquals(sa + Constants.mPDA, va);
		
		// check side effect
	}

	// CB and PC
	@Test
	public void getCodeByte() {
		logger.info(StackUtil.getCallerMethodName());
		int value  = 0xCAFE;
		int offset = 0x10;
		PC(offset);
		int va = CB() + (PC() / 2);
		// data
		Memory.write16(va, value);
		// execute
		assertEquals((value >>> 8) & 0xFF, Memory.getCodeByte());
		assertEquals(value & 0xFF, Memory.getCodeByte());
	}
	@Test
	public void getCodeWord() {
		logger.info(StackUtil.getCallerMethodName());
		int value  = 0xCAFE;
		int offset = 0x10;
		PC(offset);
		int va = CB() + (PC() / 2);
		// data
		Memory.write16(va, value);
		// execute
		assertEquals(value, Memory.getCodeWord());
	}
	@Test
	public void readCode() {
		logger.info(StackUtil.getCallerMethodName());
		int value  = 0xCAFE;
		int offset = 0x10;
		PC(offset);
		int va = CB() + offset;
		// data
		Memory.write16(va, value);
		// execute
		assertEquals(value, Memory.readCode(offset));
	}
}

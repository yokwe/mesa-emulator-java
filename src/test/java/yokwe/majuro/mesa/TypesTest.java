package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import yokwe.majuro.util.StackUtil;

public class TypesTest extends Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	//
	// LONG
	//
	@Test
	public void lowHalf() {
		logger.info(StackUtil.getCallerMethodName());

		int value = 0xCAFEBABE;
		
		// prepare
		// execute
		int result = Types.lowHalf(value);
		// check result
		assertEquals(0xBABE, result);
		
		// check side effect
	}

	@Test
	public void highHalf() {
		logger.info(StackUtil.getCallerMethodName());

		int value = 0xCAFEBABE;
		
		// prepare
		// execute
		int result = Types.highHalf(value);
		// check result
		assertEquals(0xCAFE, result);
		// check side effect
	}

	@Test
	public void toCARD32() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.toCARD32(0xCAFE, 0xBABE);
		// check result
		assertEquals(0xCAFEBABE, result);
		// check side effect
	}
	
	//
	// toCARD8
	//
	@Test
	public void lowByte() {
		logger.info(StackUtil.getCallerMethodName());

		int value = 0xCAFE;
		
		// prepare
		// execute
		int result = Types.lowByte(value);
		// check result
		assertEquals(0xFE, result);
		// check side effect
	}
	@Test
	public void highByte() {
		logger.info(StackUtil.getCallerMethodName());

		int value = 0xCAFE;
		
		// prepare
		// execute
		int result = Types.highByte(value);
		// check result
		assertEquals(0xCA, result);
		// check side effect
	}
	@Test
	public void toCARD8_A() {
		logger.info(StackUtil.getCallerMethodName());

		int value = 0xCAFEBABE;
		
		// prepare
		// execute
		int result = Types.toCARD8(value);
		// check result
		assertEquals(0xBE, result);
		// check side effect
	}
	@Test
	public void toCARD8_B() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.toCARD8(0xCA, 0xFE);
		// check result
		assertEquals(0xAE, result);
		// check side effect
	}
	
	//
	// toCARD16
	//
	@Test
	public void toCARD16_A() {
		logger.info(StackUtil.getCallerMethodName());

		int value = 0xCAFEBABE;
		
		// prepare
		// execute
		int result = Types.toCARD16(value);
		// check result
		assertEquals(0xBABE, result);
		// check side effect
	}
	@Test
	public void toCARD16_B() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.toCARD16(0xCAFE, 0xBABE);
		// check result
		assertEquals(0xFEBE, result);
		// check side effect
	}
	
	//
	// 2.1.3.1 Basic Logical Operators
	//
	@Test
	public void shift_A() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.shift(0xCAFEBABE, 4);
		// check result
		assertEquals(0xABE0, result);
		// check side effect
	}
	@Test
	public void shift_B() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.shift(0xCAFEBABE, 16);
		// check result
		assertEquals(0, result);
		// check side effect
	}
	@Test
	public void shift_C() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.shift(0xCAFEBABE, -4);
		// check result
		assertEquals(0x0BAB, result);
		// check side effect
	}
	public void shift_D() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.shift(0xCAFEBABE, -16);
		// check result
		assertEquals(0, result);
		// check side effect
	}
	@Test
	public void shift_E() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.shift(0xCAFEBABE, 0);
		// check result
		assertEquals(0xBABE, result);
		// check side effect
	}

	@Test
	public void rotate_A() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.rotate(0xCAFEBABE, 4);
		// check result
		assertEquals(0xABEB, result);
		// check side effect
	}
	@Test
	public void rotate_B() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.rotate(0xCAFEBABE, 20);
		// check result
		assertEquals(0xABEB, result);
		// check side effect
	}
	@Test
	public void rotate_C() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.rotate(0xCAFEBABE, -4);
		// check result
		assertEquals(0xEBAB, result);
		// check side effect
	}
	public void rotate_D() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.rotate(0xCAFEBABE, -20);
		// check result
		assertEquals(0xEBAB, result);
		// check side effect
	}
	@Test
	public void rotate_E() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.rotate(0xCAFEBABE, 0);
		// check result
		assertEquals(0xBABE, result);
		// check side effect
	}
	
	@Test
	public void arithShift_A() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0x1234, 4);
		// check result
		assertEquals(0x2340, result);
		// check side effect
	}
	@Test
	public void arithShift_B() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0x1A2B, 4);
		// check result
		assertEquals(0x22B0, result);
		// check side effect
	}
	@Test
	public void arithShift_C() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0xA1B2, 4);
		// check result
		assertEquals(0x9B20, result);
		// check side effect
	}
	@Test
	public void arithShift_D() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0x1234, 15);
		// check result
		assertEquals(0, result);
		// check side effect
	}
	@Test
	public void arithShift_E() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0xA1B2, 15);
		// check result
		assertEquals(0x8000, result);
		// check side effect
	}
	public void arithShift_F() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0x1234, -4);
		// check result
		assertEquals(0x0123, result);
		// check side effect
	}
	@Test
	public void arithShift_G() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0x1A2B, -4);
		// check result
		assertEquals(0x01A2, result);
		// check side effect
	}
	@Test
	public void arithShift_H() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0xA1B2, -4);
		// check result
		assertEquals(0xFA1B, result);
		// check side effect
	}
	@Test
	public void arithShift_I() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0x1234, -15);
		// check result
		assertEquals(0, result);
		// check side effect
	}
	@Test
	public void arithShift_J() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0xA1B2, -15);
		// check result
		assertEquals(0xFFFF, result);
		// check side effect
	}
	@Test
	public void arithShift_K() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.arithShift(0xCAFEBABE, 0);
		// check result
		assertEquals(0xBABE, result);
		// check side effect
	}
	
	@Test
	public void longShift_A() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0x12345678, 4);
		// check result
		assertEquals(0x23456780, result);
		// check side effect
	}
	@Test
	public void longShift_B() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0x1A2B_3C4D, 4);
		logger.info("result %X", result);
		// check result
		assertEquals(0xA2B3C4D0, result);
		// check side effect
	}
	@Test
	public void longShift_C() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0xCAFEBABE, 4);
		// check result
		assertEquals(0xAFEBABE0, result);
		// check side effect
	}
	@Test
	public void longShift_D() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0x12345678, 32);
		// check result
		assertEquals(0, result);
		// check side effect
	}
	@Test
	public void longShift_E() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0xCAFEBABE, 32);
		// check result
		assertEquals(0, result);
		// check side effect
	}
	@Test
	public void longShift_F() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0x12345678, -4);
		// check result
		assertEquals(0x01234567, result);
		// check side effect
	}
	@Test
	public void longShift_G() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0x1A2B_3C4D, -4);
		logger.info("result %X", result);
		// check result
		assertEquals(0x01A2B3C4, result);
		// check side effect
	}
	@Test
	public void longShift_H() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0xCAFEBABE, -4);
		// check result
		assertEquals(0x0CAFEBAB, result);
		// check side effect
	}
	@Test
	public void longShift_I() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0x12345678, -32);
		// check result
		assertEquals(0, result);
		// check side effect
	}
	@Test
	public void longShift_J() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0x12345678, 0);
		// check result
		assertEquals(0x12345678, result);
		// check side effect
	}
	@Test
	public void longShift_K() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longShift(0xCAFEBABE, 0);
		// check result
		assertEquals(0xCAFEBABE, result);
		// check side effect
	}
	
	@Test
	public void longArithShift_A() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0x12345678, 4);
		// check result
		assertEquals(0x23456780, result);
		// check side effect
	}
	@Test
	public void longArithShift_B() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0x1A2B_3C4D, 4);
		logger.info("result %X", result);
		// check result
		assertEquals(0x22B3C4D0, result);
		// check side effect
	}
	@Test
	public void longArithShift_C() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0xCAFEBABE, 4);
		// check result
		assertEquals(0xAFEBABE0, result);
		// check side effect
	}
	@Test
	public void longArithShift_C2() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0xC1FEBABE, 4);
		// check result
		assertEquals(0x9FEBABE0, result);
		// check side effect
	}
	@Test
	public void longArithShift_D() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0x12345678, 32);
		// check result
		assertEquals(0, result);
		// check side effect
	}
	@Test
	public void longArithShift_E() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0xCAFEBABE, 32);
		// check result
		assertEquals(0x8000_0000, result);
		// check side effect
	}
	@Test
	public void longArithShift_F() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0x12345678, -4);
		// check result
		assertEquals(0x01234567, result);
		// check side effect
	}
	@Test
	public void longArithShift_G() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0xA1B2_C3D4, -4);
		logger.info("result %X", result);
		// check result
		assertEquals(0xFA1B2C3D, result);
		// check side effect
	}
	@Test
	public void longArithShift_H() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0xCAFEBABE, -4);
		// check result
		assertEquals(0xFCAFEBAB, result);
		// check side effect
	}
	@Test
	public void longArithShift_I() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0x12345678, -32);
		// check result
		assertEquals(0, result);
		// check side effect
	}
	@Test
	public void longArithShift_I2() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0xCAFEBABE, -32);
		// check result
		assertEquals(0xFFFF_FFFF, result);
		// check side effect
	}
	@Test
	public void longArithShift_J() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0x12345678, 0);
		// check result
		assertEquals(0x12345678, result);
		// check side effect
	}
	@Test
	public void longArithShift_K() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.longArithShift(0xCAFEBABE, 0);
		// check result
		assertEquals(0xCAFEBABE, result);
		// check side effect
	}

	@Test
	public void signExtend_A() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.signExtend(0x11);
		// check result
		assertEquals(0x0011, result);
		// check side effect
	}
	@Test
	public void signExtend_B() {
		logger.info(StackUtil.getCallerMethodName());

		// prepare
		// execute
		int result = Types.signExtend(0xAA);
		// check result
		assertEquals(0xFFAA, result);
		// check side effect
	}

}

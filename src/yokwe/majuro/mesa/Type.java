/*******************************************************************************
 * Copyright (c) 2020, Yasuhiro Hasegawa
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. The name of the author may not be used to endorse or promote products derived
 *      from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *******************************************************************************/
package yokwe.majuro.mesa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteOrder;

public final class Type {
	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface CARD8 {
	}

	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface CARD16 {
	}

	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface CARD32 {
	}

	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface INT8 {
	}
	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface INT16 {
	}

	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface POINTER {
	}

	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface LONG_POINTER {
	}

	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface PDA_POINTER {
	}
	
	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface PAGE_NUMBER {
	}

	//FieldSpec: TYPE = MACHINE DEPENDENT RECORD [
	// pos (0:0..3):  NIBBLE,
	// size (0:4..7): NIBBLE];
	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface FIELD_SPEC {
	}

	//FieldDesc: TYPE = MACHINE DEPENDENT RECORD [
    //  offset(0:0..7) : BYTE,
    //  field(0:8..15) : FieldSpec];
	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface FIELD_DESC {
	}
	
	//PsbIndex: TYPE = [0..1024)
	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface PSB_INDEX {
	}
	
	// Faultlndex: TYPE = [0..8);
	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.TYPE_USE})
	public static @interface FAULT_INDEX {
	}


	
	// 2.1.2 Bit, Nibble, Byte
	// NibblePair: TYPE = MACHINE DEPENDENT RECORD[left (0:0..3) NIBBLE, right(0:4..7) NIBBLE];
	public static final class NibblePair {
		public static @CARD16 int left(@CARD16 int value) {
			return (value >>> 4) & 0x0F;
		}
		public static @CARD16 int right(@CARD16 int value) {
			return value & 0x0F;
		}
		public static @CARD16 int make(@CARD16 int left, @CARD16 int right) {
			return ((left << 4) & 0xF0) | (right & 0x0f);
		}
	}
	// BytePair: TYPE =  MACHINE DEPENDENT RECORD [left (0: 0..7), right (0: 8..15): BYTE];
	public static final class BytePair {
		public static @CARD8 int left(@CARD16 int value) {
			return (value >>> 8) & 0xFF;
		}
		public static @CARD8 int right(@CARD16 int value) {
			return value & 0xFF;
		}
		public static @CARD16 int make(@CARD8 int left, @CARD8 int right) {
			return ((left << 8) & 0xFF00) | (right & 0x00FF);
		}
	}

	// 2.1.3.1 Basic Logical Operators
	public static @CARD16 int shift(@CARD16 int data, int count) {
		if (0 < count) {
			return (data << count) & 0xFFFF;
		} else if (count < 0) {
			return (data >>> (-count)) & 0xFFFF;
		} else {
			return data;
		}
	}
	public static @CARD16 int Rotate(@CARD16 int data, int count) {
		if (0 < count) {
			if (16 <= count) count = count % 16;
			int t = (data & 0xFFFF) << count;
			return (t & (0xFFFF)) | ((t >> 16) & 0xFFFF);
		} else if (count < 0) {
			if (count <= -16) count = -(-count % 16);
			int t = (data & 0xFFFF) << (16 + count);
			return (t & (0xFFFF)) | ((t >> 16) & 0xFFFF);
		} else {
			return data;
		}
	}

	// 2.1.3.2 Basic Arithmetic Operator
	public static @INT16 int ArithShift(@INT16 int data, int count) {
		// FIXME is this correct?
		if (0 < count) {
			return data << count;
		} else if (count < 0) {
			return data >> (-count);
		} else {
			return data;
		}
	}

	// 2.3.1 Long Types
//	  Long, LongNumber: TYPE = MACHINE DEPENDENT RECORD [
//	     SELECT OVERLAID * FROM
//	       lc => [lc: LONG CARDINAL],
//	       li => [li: LONG INTEGER],
//	       lp => [lp: LONG POINTER],
//	       lu => [lu: LONG UNSPECIFIED],
//	       num => [lowbits, highbits: CARDINAL],
//	       any => [low, high: UNSPECIFIED],
//       ENDCASE];
	//   Mesa CPU use big endian and x86 CPU use little endian
	//   Memory layout of LongNumber depends on CPU endianness.
	//   So LongNuber implementation depend of CPU endianness.
	//   And LongNuber implementation need to be selected at run time. 
	private static final class LongNumber_BE {
		public static @CARD16 int lowHalf(@CARD32 int value) {
			return (value >>> 16) & 0xFFFF;
		}
		public static @CARD16 int highHalf(@CARD32 int value) {
			return value & 0xFFFF;
		}
		public static @CARD32 int make(@CARD16 int high, @CARD16 int low) {
			return ((low << 16) & 0xFFFF0000) | (high & 0x0000FFFF);
		}
	}
	private static final class LongNumber_LE {
		public static @CARD16 int lowHalf(@CARD32 int value) {
			return value & 0xFFFF;
		}
		public static @CARD16 int highHalf(@CARD32 int value) {
			return (value >>> 16) & 0xFFFF;
		}
		public static @CARD32 int make(@CARD16 int high, @CARD16 int low) {
			return ((high << 16) & 0xFFFF0000) | (low & 0x0000FFFF);
		}
	}
	private static final ByteOrder NATIVE_ORDER = ByteOrder.nativeOrder();
	public static final class LongNumber {
		public static @CARD16 int lowHalf(@CARD32 int value) {
			return NATIVE_ORDER == ByteOrder.BIG_ENDIAN ? LongNumber_BE.lowHalf(value)  : LongNumber_LE.lowHalf(value);
		}
		public static @CARD16 int highHalf(@CARD32 int value) {
			return NATIVE_ORDER == ByteOrder.BIG_ENDIAN ? LongNumber_BE.highHalf(value) : LongNumber_LE.highHalf(value);
		}
		public static @CARD32 int make(@CARD16 int high, @CARD16 int low) {
			return NATIVE_ORDER == ByteOrder.BIG_ENDIAN ? LongNumber_BE.make(high, low) : LongNumber_LE.make(high, low);
		}
	}

}

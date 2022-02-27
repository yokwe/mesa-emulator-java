package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.BYTE_BITS;
import static yokwe.majuro.mesa.Constants.BYTE_MASK;
import static yokwe.majuro.mesa.Constants.NIBBLE_BITS;
import static yokwe.majuro.mesa.Constants.NIBBLE_MASK;
import static yokwe.majuro.mesa.Constants.WORD_BITS;
import static yokwe.majuro.mesa.Constants.WORD_MASK;

public final class Types {
	//
	// Long
	//
	// returns low order word of double word
	public static @Mesa.CARD16 int lowHalf(@Mesa.CARD32 int value) {
		return value & 0xFFFF;
	}
	// returns high order word of double word
	public static @Mesa.CARD16 int highHalf(@Mesa.CARD32 int value) {
		return value >>> WORD_BITS;
	}
	public static @Mesa.CARD32 int toCARD32(@Mesa.CARD16 int high, @Mesa.CARD16 int low) {
		return (high << WORD_BITS) | (low & WORD_MASK);
	}

	//
	// toCARD8
	//
	// returns low order byte of word
	public static @Mesa.CARD8 int lowByte(@Mesa.CARD16 int value) {
		return toCARD8(value);
	}
	// returns high order byte of word
	public static @Mesa.CARD8 int highByte(@Mesa.CARD16 int value) {
		return toCARD8(value >>> BYTE_BITS);
	}
	public static @Mesa.CARD8 int toCARD8(int value) {
		return value & 0xFF;
	}
	public static @Mesa.CARD8 int toCARD8(@Mesa.CARD8 int left, @Mesa.CARD8 int right) {
		return toCARD8((left << NIBBLE_BITS) | (right & NIBBLE_MASK));
	}
	
	//
	// toCARD16
	//
	public static @Mesa.CARD16 int toCARD16(int value) {
		return value & 0xFFFF;
	}
	public static @Mesa.CARD16 int toCARD16(@Mesa.CARD8 int left, @Mesa.CARD8 int right) {
		return toCARD16((left << BYTE_BITS) | (right & BYTE_MASK));
	}
	
	// 2.1.3.1 Basic Logical Operators
	public static int shift(@Mesa.CARD16 int data, int count) {
		if (0 < count) {
			if (16 <= count) return 0;
			return toCARD16(data << count);
		}
		if (count < 0) {
			if (count <= -16) return 0;
			return toCARD16(data >>> (-count));
		}
		return data;
	}
	
	public static int rotate(@Mesa.CARD16 int data, int count) {
		if (0 < count) {
			if (16 <= count) count = count % 16;
			int t = data << count;
			return toCARD16(t) | toCARD16(t >> 16);
		}
		if (count < 0) {
			if (count <= -16) count = -(-count % 16);
			int t = data << (16 + count);
			return toCARD16(t) | toCARD16(t >> 16);
		}
		return data;
	}
	
	// 2.1.3.2 Basic Arithmetic Operator
	public static @Mesa.INT16 int arithShift(@Mesa.INT16 int data, int count) {
		if (0 < count) {
			if (16 <= count) return 0;
			return toCARD16(((data << count) & 0x7fff) | (data & 0x8000));
		}
		if (count < 0) {
			if (count <= -16) return 0;
			return toCARD16(data >> (-count));
		}
		return data;
	}
	
	public static @Mesa.CARD32 int longShift(@Mesa.CARD32 int data, int count) {
		if (0 < count) {
			if (32 <= count) return 0;
			return data << count;
		}
		if (count < 0) {
			if (count <= -32) return 0;
			return data >>> -count;
		}
		return data;
	}
	public static @Mesa.INT32 int longArithShift(@Mesa.INT32 int data, int count) {
		if (0 < count) {
			if (32 <= count) return 0;
			return ((data << count) & 0x7fffffff) | (data & 0x80000000);
		}
		if (count < 0) {
			if (count <= -32) return 0;
			return data >> -count;
		}
		return data;
	}

	public static @Mesa.INT16 int signExtend(@Mesa.CARD8 int z) {
		return (byte)z;
	}

}

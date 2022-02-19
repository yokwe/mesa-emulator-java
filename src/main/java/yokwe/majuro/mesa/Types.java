package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.*;

public final class Types {
	//
	// Long
	//
	// returns low order word of double word
	public static int lowHalf(int value) {
		return value & 0xFFFF;
	}
	// returns high order word of double word
	public static int highHalf(int value) {
		return value >>> WORD_BITS;
	}
	public static int toCARD32(int high, int low) {
		return (high << WORD_BITS) | (low & WORD_MASK);
	}

	//
	// toCARD8
	//
	public static int toCARD8(int value) {
		return value & 0xFF;
	}
	//
	// toCARD16
	//
	public static int toCARD16(int value) {
		return value & 0xFFFF;
	}
}

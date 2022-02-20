package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.BYTE_BITS;
import static yokwe.majuro.mesa.Constants.BYTE_MASK;
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
	public static @Mesa.CARD8 int toCARD8(int value) {
		return value & 0xFF;
	}
	//
	// toCARD16
	//
	public static @Mesa.CARD16 int toCARD16(int value) {
		return value & 0xFFFF;
	}
	public static @Mesa.CARD16 int toCARD16(@Mesa.CARD8 int left, @Mesa.CARD8 int right) {
		return ((left & BYTE_MASK) << BYTE_BITS) | (right & BYTE_MASK);
	}

}

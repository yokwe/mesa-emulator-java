package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.*;

public class Types {
	//
	// Long
	//
	// returns low order word of double word
	public static char lowHalf(int value) {
		return (char)value;
	}
	// returns high order word of double word
	public static char highHalf(int value) {
		return (char)(value >>> WORD_BITS);
	}
	public static int makeLong(int high, int low) {
		return (high << WORD_BITS) | (low & WORD_MASK);
	}

	//
	// NibblePair
	//
	public static int nibblePairLeft(int value) {
		return (value >>> 4) & 0x0F;
	}
	public static int nibblePairRight(int value) {
		return value & 0x0F;
	}
	public static int makeNibblePair(int left, int right) {
		return ((left & 0x0F) << 4) | (right & 0x0F);
	}
}

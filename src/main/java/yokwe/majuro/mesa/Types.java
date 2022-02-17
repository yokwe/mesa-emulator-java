package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.*;

public final class Types {
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
	// BytePair
	//
	public static final class BytePair {
		public static int left(int value) {
			return (value >>> BYTE_BITS) & BYTE_MASK;
		}
		public static int right(int value) {
			return value & BYTE_MASK;
		}
		public static int make(int left, int right) {
			return ((left & BYTE_MASK) << BYTE_BITS) | (right & BYTE_MASK);
		}
	}
	//
	// NibblePair
	//
	public static final class NibblePair {
		public static int left(int value) {
			return (value >>> NIBBLE_BITS) & NIBBLE_MASK;
		}
		public static int right(int value) {
			return value & NIBBLE_MASK;
		}
		public static int make(int left, int right) {
			return ((left & NIBBLE_MASK) << NIBBLE_BITS) | (right & NIBBLE_MASK);
		}
	}
}

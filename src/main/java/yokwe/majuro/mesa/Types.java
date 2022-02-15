package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.*;

public class Types {
	// returns low order word of double word
	public static char lowHalf(int value) {
		return (char)value;
	}
	// returns high order word of double word
	public static char highHalf(int value) {
		return (char)(value >>> WORD_BITS);
	}
	public static int makeLong(char high, char low) {
		return (high << WORD_BITS) | low;
	}
	public static int makeLong(int high, int low) {
		return makeLong((char)high, (char)low);
	}

}

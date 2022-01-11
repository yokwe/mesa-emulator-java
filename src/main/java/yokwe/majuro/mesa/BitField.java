package yokwe.majuro.mesa;

public final class BitField {
	public static final int shift(int wholeBits, int stopBit) {
		return wholeBits - stopBit - 1;
	}
	public static final int shift16(int stopBit) {
		return shift(16, stopBit);
	}
	public static final int shift32(int stopBit) {
		return shift(32, stopBit);
	}
	
	public static final int mask(int bits) {
		return (1 << bits) - 1;
	}
	public static final int mask(int startBit, int stopBit) {
		return mask(stopBit - startBit + 1);
	}
	
	public static final int mask16(int startBit, int stopBit) {
		return mask(startBit, stopBit) << shift16(stopBit);
	}
	public static final int mask32(int startBit, int stopBit) {
		return mask(startBit, stopBit) << shift32(stopBit);
	}
}
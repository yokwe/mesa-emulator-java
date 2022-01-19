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


// Use constant expression for performance
// Use int type to reduce implicit or explicit type conversion

// StateAllocationTable: TYPE = ARRAY Priority OF POINTER
final class StateAllocationTable {
	public static final int ELEMENT_SIZE = 1;
	
	public static int offset(int n) {
		return n * ELEMENT_SIZE;
	}
}

// final int StateAllocationTable(

//NewGlobalOverhead: TYPE = RECORD [
//	available(0: 0..15): UNSPECIFIED,
//	word     (1: 0..15): GlobalWord,
//	global   (2): GlobalVariables];
final class NewGlobalOverhead {
	public static final int SIZE = 2;

	public static final int available = 0;
	public static final int word = 1;
	public static final int global = 2;
	
	// calculate offset of member using field
	//   int word = Memory.fetch(p + NewGlobalOverhead.word);
}


//NibblePair: TYPE = RECORD[
//  left  (0: 0..3): NIBBLE,
//  right (0: 4..7): NIBBLE];
final class NibblePair {
	public static final int SIZE_BITS = 8;

	private static final int LEFT_START = 0;
	private static final int LEFT_STOP = 3;

	private static final int RIGHT_START = 4;
	private static final int RIGHT_STOP = 7;

	private static final int LEFT_BITS = LEFT_STOP - LEFT_START + 1;
	private static final int LEFT_PAT = (1 << LEFT_BITS) - 1;
	private static final int LEFT_SHIFT = SIZE_BITS - LEFT_STOP - 1;
	private static final int LEFT_MASK = LEFT_PAT << LEFT_SHIFT;

	private static final int RIGHT_BITS = RIGHT_STOP - RIGHT_START + 1;
	private static final int RIGHT_PAT = (1 << RIGHT_BITS) - 1;
	private static final int RIGHT_SHIFT = SIZE_BITS - RIGHT_STOP - 1;
	private static final int RIGHT_MASK = RIGHT_PAT << RIGHT_SHIFT;
	
	private int value;
	
	public NibblePair(int newValue) {
		value = newValue;
	}
	public NibblePair() {
		this(0);
	}
	
	public int rawValue() {
		return value;
	}
	
	public int left() {
		return (value & LEFT_MASK) >> LEFT_SHIFT;
	}
	public void left(int newValue) {
		value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
	}
	public int right() {
		return (value & RIGHT_MASK) >> RIGHT_SHIFT;
	}
	public void right(int newValue) {
		value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
	}
}


//Monitor: TYPE = RECORD[
//   reserved (0: 0.. 2): UNSPECIFIED,
//   tail     (0: 3..12): PsbIndex,
//   available(0:13..14): UNSPECIFIED,
//   locked   (0:15..15): BOOLEAN];
final class Monitor {
	public static final int SIZE = 1;
	public static final int SIZE_BITS = 16;

	private static final int TAIL_START = 3;
	private static final int TAIL_STOP = 12;

	private static final int LOCKED_START = 15;
	private static final int LOCKED_STOP = 15;

	private static final int TAIL_BITS = TAIL_STOP - TAIL_START + 1;
	private static final int TAIL_PAT = (1 << TAIL_BITS) - 1;
	private static final int TAIL_SHIFT = SIZE_BITS - TAIL_STOP - 1;
	private static final int TAIL_MASK = TAIL_PAT << TAIL_SHIFT;

	private static final int LOCKED_BITS = LOCKED_STOP - LOCKED_START + 1;
	private static final int LOCKED_PAT = (1 << LOCKED_BITS) - 1;
	private static final int LOCKED_SHIFT = SIZE_BITS - LOCKED_START - 1;
	private static final int LOCKED_MASK  = LOCKED_PAT << LOCKED_SHIFT;

	
	private int value;
	
	public Monitor(int newValue) {
		value = newValue;
	}
	public Monitor() {
		this(0);
	}
	
	public int rawValue() {
		return value;
	}
	
	public int tail() {
		return (value & TAIL_MASK) >> TAIL_SHIFT;
	}

	public void tail(int newValue) {
		value = (value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK);
	}

	public boolean locked() {
		return (value & LOCKED_MASK) != 0;
	}

	public void locked(boolean newValue) {
		if (newValue) {
			value |= LOCKED_MASK; // set LOCKED_START_BIT
		} else {
			value &= ~LOCKED_MASK; // clear LOCKED_START_BIT
		}
	}
}

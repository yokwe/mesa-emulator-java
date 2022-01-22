package yokwe.majuro.mesa;

import yokwe.majuro.UnexpectedException;

public final class GeneratedType {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GeneratedType.class);
	
	public interface CheckValue {
		public void check(long value);
	}
	
	public static final class EnumContext implements CheckValue {
		public final String   name;
		public final int[]    validValues;
		public final String[] validNames;
		
		public EnumContext(String name, int[] validValues, String[] validNames) {
			this.name        = name;
			this.validValues = validValues;
			this.validNames  = validNames;
		}
		
		public void check(long value) {
			toString(value);
		}
		public String toString(long value) {
			for(int i = 0; i < validValues.length; i++) {
				if (validValues[i] == value) return validNames[i];
			}
			logger.error("Unexpected");
			logger.error("  class {}", name);
			logger.error("  value {}", value);
			throw new UnexpectedException("Unexpected");
		}
	}

	public static final class SubrangeContext implements CheckValue {
		public final String name;
		public final long   minValue;
		public final long   maxValue;
		
		public SubrangeContext(String name, long minValue, long maxValue) {
			this.name     = name;
			this.minValue = minValue;
			this.maxValue = maxValue;
		}
		
		public void check(long value) {
			if (minValue <= value && value <= maxValue) return;
			logger.error("Unexpected");
			logger.error("  class     {}", name);
			logger.error("  value     {}", value);
			throw new UnexpectedException("Unexpected");
		}
	}
	
	public static final class UNSPECIFIED {
		public static final String NAME = "UNSPECIFIED";
		public static final int    SIZE       = 1;
		public static final long   MIN_VALUE  = 0;
		public static final long   MAX_VALUE  = 0xFFFF;
		public static final long   SIZE_VALUE = MAX_VALUE - MIN_VALUE + 1;
		
		private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);
		
		public static void checkValue(int value) {
			checkValue.check(value);
		}
	}
	public static final class LONG_UNSPECIFIED {
		public static final String NAME       = "LONG_UNSPECIFIED";
		public static final int    SIZE       = 2;
		public static final long   MIN_VALUE  = 0;
		public static final long   MAX_VALUE  = 0xFFFF_FFFF;
		public static final long   SIZE_VALUE = MAX_VALUE - MIN_VALUE + 1;
		
		private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);
		
		public static void checkValue(int value) {
			// treat as unsigned integer
			checkValue.check(Integer.toUnsignedLong(value));
		}
	}
	                                                          
	public static final class CARDINAL {
		public static final String NAME       = "CARDINAL";
		public static final int    SIZE       = 1;
		public static final long   MIN_VALUE  = 0;
		public static final long   MAX_VALUE  = 0xFFFF;
		public static final long   SIZE_VALUE = MAX_VALUE - MIN_VALUE + 1;
		
		private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);
		
		public static void checkValue(int value) {
			checkValue.check(value);
		}
	}
	public static final class LONG_CARDINAL {
		public static final String NAME       = "LONG_CARDINAL";
		public static final int    SIZE       = 2;
		public static final long   MIN_VALUE  = 0;
		public static final long   MAX_VALUE  = 0xFFFF_FFFF;
		public static final long   SIZE_VALUE = MAX_VALUE - MIN_VALUE + 1;
		
		private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);
		
		public static void checkValue(int value) {
			// treat as unsigned integer
			checkValue.check(Integer.toUnsignedLong(value));
		}
	}
	
	public static final class INTEGER {
		public static final String NAME       = "INTEGER";
		public static final int    SIZE       = 1;
		public static final long   MIN_VALUE  = Short.MIN_VALUE;
		public static final long   MAX_VALUE  = Short.MAX_VALUE;
		public static final long   SIZE_VALUE = MAX_VALUE - MIN_VALUE + 1;
		
		private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);
		
		public static void checkValue(int value) {
			checkValue.check(value);
		}
	}

	
//    BLOCK: TYPE = ARRAY [0..0) OF UNSPECIFIED;
	public static final class BLOCK {
		public static final String NAME = "BLOCK";
		public static final int    SIZE = 0;
		
		private static final int ELEMENT_SIZE = UNSPECIFIED.SIZE;
		
		public static int offset(int index) {
			if (Debug.ENABLE_CHECK_VALUE) CARDINAL.checkValue(index);
			int value = ELEMENT_SIZE * index;
			return value;
		}
	}

	
	
//  FSIndex: TYPE = [0..256);
	// type of value is char
	public static final class FSIndex  {
		public static final String NAME       = "FSIndex";
		public static final int    MIN_VALUE  = 0;
		public static final int    MAX_VALUE  = 255;
		public static final int    SIZE_VALUE = MAX_VALUE - MIN_VALUE + 1;
		
		private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);
		
		public static void checkValue(int value) {
			checkValue.check(Integer.toUnsignedLong(value));
		}
	}

//  AVItemType: TYPE = {frame(0), empty(1), indirect(2), unused(3)};
	// type of value is char
	public static final class AVItemType {
		public static final String NAME     = "AVItemType";
		public static final char   frame    = 0;
		public static final char   empty    = 1;
		public static final char   indirect = 2;
		public static final char   unused   = 3;
		
		private static final int[] VALID_VALUES = {
				frame, empty, indirect, unused,
		};
		private static final String[] VALID_NAMES = {
				"frmae", "empty", "indirect", "unused",
		};
		private static final EnumContext checkValue = new EnumContext(NAME, VALID_VALUES, VALID_NAMES);
		
		public static final void checkValue(int value) {
			if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
		}
		
		public static final String toString(int value) {
			return checkValue.toString(value);
		}
	}

	
//  AllocationVector: TYPE = ARRAY FSIndex OF AVItem;
	public static final class AllocationVector {
		public static final String NAME = "AllocationVector";
		public static final int    SIZE = FSIndex.SIZE_VALUE * AVItem.SIZE;
		
		private static final int ELEMENT_SIZE = AVItem.SIZE;
		
		public static int offset(int index) {
			if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(index);
			return ELEMENT_SIZE * index;
		}
	}

//  AV: POINTER TO AllocationVector = yokwe.majuro.mesa.Constant.mAV;
	// AV is SHORT POINTER
	public static class AV {
		public final char base = yokwe.majuro.mesa.Constant.mAV;
		
		public AVItem read(int index) {
			if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(index);
			return new AVItem(Mesa.read16MDS((char)(base + AllocationVector.offset(index))));
		}
		public void write(int index, AVItem value) {
			if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(index);
			Mesa.write16MDS((char)(base + AllocationVector.offset(index)), value.value);
		}
	}


	//
	// Below is good for one word record
	//
	
	
//  AVItem: TYPE = RECORD [
//    data(0:  0 .. 13): UNSPECIFIED,
//    tag (0: 14 .. 15): AVItemType];
	public static final class AVItem {
		public static final int SIZE = 1;
		
		private static final int DATA_MASK  = 0xFFFC;
		private static final int DATA_SHIFT = 2;
		private static final int TAG_MASK   = 0x0003;
		private static final int TAG_SHIFT  = 0;
		
		private static final CheckValue dataCheckValue = new SubrangeContext("AVItem.data", 0, DATA_MASK >>> DATA_SHIFT);
		private static final CheckValue tagCheckValue  = AVItemType.checkValue;
		
		public char value;
		
		public AVItem(char value) {
			this.value = value;
		}
		
		public int data() {
			int ret = (value & DATA_MASK) >>> DATA_SHIFT;
			// no need to check value range
			return ret;
		}
		public int tag() {
			int ret = (value & TAG_MASK) >>> TAG_SHIFT;
			if (Debug.ENABLE_CHECK_VALUE) AVItemType.checkValue(ret);
			return ret;
		}
		public void data(int newValue) {
			if (Debug.ENABLE_CHECK_VALUE) dataCheckValue.check(newValue);
			value = (char)((value & ~DATA_MASK) | (newValue << DATA_SHIFT) & DATA_MASK);
		}
		public void tag(int newValue) {
			if (Debug.ENABLE_CHECK_VALUE) AVItemType.checkValue(newValue);
			value = (char)((value & ~TAG_MASK) | (newValue << TAG_SHIFT) & TAG_MASK);
		}
	}
	
	
	//  StateWord: TYPE = RECORD [
	//      instByte(0: 0.. 7): BYTE,
	//      stkPtr  (0: 8..15): BYTE];
	public static final class StateWord {
		public char value;
		
		public StateWord(char value) {
			this.value = value;
		}
	}

	public static final int StackDepth = 14;

	//    StateVector: TYPE = RECORD [
	//        stack( 0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED, // StackDepth = 14
	//        word (14:0.. 15): StateWord,
	//        frame(15:0.. 15): LocalFrameHandle,
	//        data (16): BLOCK];
	public static final class StateVector {
		public static final long SIZE = 16;
		
		private static final int OFFSET_stack =  0;
		private static final int OFFSET_word  = 14;
		private static final int OFFSET_frame = 15;
		private static final int OFFSET_data  = 16;
		
		private static final SubrangeContext stackIndexCheck = new SubrangeContext("StateVector.stack#index", 0, StackDepth);
//		private static final SubrangeContext stackElementCheck = new SubrangeContext("StateVector.stack#element", 0, StackDepth);
		
		public int base;
		
		public StateVector(int base) {
			this.base = base;
		}
		
		// type of stack is array
		public char stack(int index) {
			if (Debug.ENABLE_CHECK_VALUE) stackIndexCheck.check(index);
			return Mesa.read16(base + OFFSET_stack + (index * UNSPECIFIED.SIZE));
		}
		public void statck(int index, char newValue) {
			if (Debug.ENABLE_CHECK_VALUE) stackIndexCheck.check(index);
			Mesa.write16(base + OFFSET_data + (index * UNSPECIFIED.SIZE), newValue);
		}

		// type of word is 16 bits record
		public StateWord word() {
			return new StateWord(Mesa.read16(base + OFFSET_word));
		}
		public void word(StateWord value) {
			Mesa.write16(base + OFFSET_word, value.value);
		}
		
		// type of word is primitive type POINTER and java type of POINER is char
		public char frame() {
			return Mesa.read16(base + OFFSET_frame);
		}
		public void frame(char value) {
			Mesa.write16(base + OFFSET_frame, value);
		}
		
		// type of data is array
		public char data(int index) {
			return Mesa.read16(base + OFFSET_data + BLOCK.offset(index));
		}
		public void data(int index, char newValue) {
			Mesa.write16(base + OFFSET_data + BLOCK.offset(index), newValue);
		}
	}

}

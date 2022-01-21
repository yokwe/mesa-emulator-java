package yokwe.majuro.mesa;

import yokwe.majuro.UnexpectedException;

public final class GeneratedType {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GeneratedType.class);
			
	public static final class EnumContext {
		public final Class<?> clazz;
		public final int[]    validValues;
		public final String[] validNames;
		
		public EnumContext(Class<?> clazz, int[] validValues, String[] validNames) {
			this.clazz       = clazz;
			this.validValues = validValues;
			this.validNames  = validNames;
		}
		
		public void checkValue(int value) {
			toString(value);
		}
		public String toString(int value) {
			for(int i = 0; i < validValues.length; i++) {
				if (validValues[i] == value) return validNames[i];
			}
			logger.error("Unexpected");
			logger.error("  class {}", clazz.getName());
			logger.error("  value {}", value);
			throw new UnexpectedException("Unexpected");
		}
	}

	public static final class SubrangeContext {
		public final Class<?> clazz;
		public final long minValue;
		public final long maxValue;
		
		public SubrangeContext(Class<?> clazz, long minValue, long maxValue) {
			this.clazz    = clazz;
			this.minValue = minValue;
			this.maxValue = maxValue;
		}
		
		public void checkValue(long value) {
			if (minValue <= value && value <= maxValue) return;
			logger.error("Unexpected");
			logger.error("  class {}", clazz.getName());
			logger.error("  value {}", value);
			throw new UnexpectedException("Unexpected");
		}
	}

	
	//
	// Below is good for one word record
	//
	
//  FSIndex: TYPE = [0..256);
	public static final class FSIndex  {
		public static final long MIN_VALUE = 0;
		public static final long MAX_VALUE = 255;
		
		private static SubrangeContext context = new SubrangeContext(FSIndex.class, MIN_VALUE, MAX_VALUE);
		
		public static void checkValue(long value) {
			context.checkValue(value);
		}
	}

//  AVItemType: TYPE = {frame(0), empty(1), indirect(2), unused(3)};
	public static final class AVItemType {
		public static final int frame    = 0;
		public static final int empty    = 1;
		public static final int indirect = 2;
		public static final int unused   = 3;
		
		private static final int[] VALID_VALUES = {
				frame, empty, indirect, unused,
		};
		private static final String[] VALID_NAMES = {
				"frmae", "empty", "indirect", "unused",
		};
		private static final EnumContext context = new EnumContext(AVItem.class, VALID_VALUES, VALID_NAMES);
		
		public static final void checkValue(int value) {
			if (Debug.ENABLE_CHECK_VALUE) context.checkValue(value);
		}
		
		public static final String toString(int value) {
			return context.toString(value);
		}
	}
//  AVItem: TYPE = RECORD [
//    data(0:  0 .. 13): UNSPECIFIED,
//    tag (0: 14 .. 15): AVItemType];
	public static final class AVItem {
		public static final int SIZE = 1;
		
		private static final int DATA_MASK  = 0xFFFC;
		private static final int DATA_SHIFT = 2;
		private static final int TAG_MASK   = 0x0003;
		private static final int TAG_SHIFT  = 0;
		
		public char value;
		
		public AVItem(char value) {
			this.value = value;
		}
		
		public int data() {
			int ret = (value & DATA_MASK) >>> DATA_SHIFT;
//			if (Debug.ENABLE_CHECK_VALUE) Nibble.checkValue(ret);
			return ret;
		}
		public int tag() {
			int ret = (value & TAG_MASK) >>> TAG_SHIFT;
			if (Debug.ENABLE_CHECK_VALUE) AVItemType.checkValue(ret);
			return ret;
		}
		public void data(int newValue) {
//			if (Debug.ENABLE_CHECK_VALUE) Nibble.checkValue(newValue);
			value = (char)((value & ~DATA_MASK) | (newValue << DATA_SHIFT) & DATA_MASK);
		}
		public void tag(int newValue) {
			if (Debug.ENABLE_CHECK_VALUE) AVItemType.checkValue(newValue);
			value = (char)((value & ~TAG_MASK) | (newValue << TAG_SHIFT) & TAG_MASK);
		}
	}
	
//  AllocationVector: TYPE = ARRAY FSIndex OF AVItem;
	public static class AllocationVector {
		public static int offset(int index) {
			if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(index);
			return AVItem.SIZE * index;
		}
	}

//  AV: POINTER TO AllocationVector = yokwe.majuro.mesa.Constant.mAV;s
	public static class AV {
		public final int base = yokwe.majuro.mesa.Constant.mAV;
		
		public AVItem read(int va, int index) {
			return new AVItem(Mesa.read16(va + AllocationVector.offset(index)));
		}
		public void write(int va, int index, AVItem value) {
			Mesa.write16(va + AllocationVector.offset(index), value.value);
		}
	}


}

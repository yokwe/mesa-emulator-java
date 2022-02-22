package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// BytePair: TYPE = RECORD[left (0:0..7): BYTE, right (0:8..15): BYTE];
public final class BytePair extends MemoryData16 {
    public static final String NAME = "BytePair";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final BytePair value(@Mesa.CARD16 int value) {
        return new BytePair(value);
    }
    public static final BytePair value() {
        return new BytePair(0);
    }
    public static final BytePair longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new BytePair(base, access);
    }
    public static final BytePair pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new BytePair(Memory.lengthenMDS(base), access);
    }
    
    private BytePair(@Mesa.CARD16 int value) {
        super(value);
    }
    private BytePair(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // left  (0:0..7):  BYTE
    // right (0:8..15): BYTE
    
    private static final int LEFT_MASK   = 0b1111_1111_0000_0000;
    private static final int LEFT_SHIFT  =                     8;
    private static final int RIGHT_MASK  = 0b0000_0000_1111_1111;
    private static final int RIGHT_SHIFT =                     0;
    
    //
    // Bit Field Access Methods
    //
    // @Mesa.CARD8 is BYTE
    public final @Mesa.CARD8 int left() {
        return (value & LEFT_MASK) >>> LEFT_SHIFT;
    }
    public final BytePair left(@Mesa.CARD8 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
        value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
        return this;
    }
    
    // @Mesa.CARD8 is BYTE
    public final @Mesa.CARD8 int right() {
        return (value & RIGHT_MASK) >>> RIGHT_SHIFT;
    }
    public final BytePair right(@Mesa.CARD8 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
        value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
        return this;
    }
    
}

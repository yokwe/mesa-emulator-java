package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// NibblePair: TYPE = RECORD[left (0:0..3): NIBBLE, right (0:4..7): NIBBLE];
public final class NibblePair extends MemoryData16 {
    public static final String NAME = "NibblePair";
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 8;
    
    //
    // Constructor
    //
    public static final NibblePair value(@Mesa.CARD16 int value) {
        return new NibblePair(value);
    }
    public static final NibblePair value() {
        return new NibblePair(0);
    }
    public static final NibblePair longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new NibblePair(base, access);
    }
    public static final NibblePair pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new NibblePair(Memory.lengthenMDS(base), access);
    }
    
    private NibblePair(@Mesa.CARD16 int value) {
        super(value);
    }
    private NibblePair(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // left  (0:0..3): NIBBLE
    // right (0:4..7): NIBBLE
    
    private static final int LEFT_MASK   = 0b1111_0000;
    private static final int LEFT_SHIFT  =           4;
    private static final int RIGHT_MASK  = 0b0000_1111;
    private static final int RIGHT_SHIFT =           0;
    
    //
    // Bit Field Access Methods
    //
    public final @Mesa.CARD16 int left() {
        return Types.toCARD16((value & LEFT_MASK) >>> LEFT_SHIFT);
    }
    public final NibblePair left(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int right() {
        return Types.toCARD16((value & RIGHT_MASK) >>> RIGHT_SHIFT);
    }
    public final NibblePair right(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK));
        return this;
    }
    
}

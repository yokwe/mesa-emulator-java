package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// BitField8: TYPE = RECORD[left (0:0..3): BIT4, right (0:4..7): BIT4];
public final class BitField8 extends MemoryData16 {
    public static final String NAME = "BitField8";
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 8;
    
    //
    // Constructor
    //
    public static final BitField8 value(@Mesa.CARD16 int value) {
        return new BitField8(value);
    }
    public static final BitField8 value() {
        return new BitField8(0);
    }
    public static final BitField8 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new BitField8(base, access);
    }
    public static final BitField8 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new BitField8(Memory.lengthenMDS(base), access);
    }
    
    private BitField8(@Mesa.CARD16 int value) {
        super(value);
    }
    private BitField8(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // left  (0:0..3): BIT4
    // right (0:4..7): BIT4
    
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
    public final BitField8 left(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int right() {
        return Types.toCARD16((value & RIGHT_MASK) >>> RIGHT_SHIFT);
    }
    public final BitField8 right(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK));
        return this;
    }
    
}

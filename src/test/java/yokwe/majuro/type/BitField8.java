package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// BitField8: TYPE = RECORD[left (0:0..3): BIT4, right (0:4..7): BIT4];
public final class BitField8 extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 8;
    
    //
    // Constructor
    //
    public static final BitField8 value(char value) {
        return new BitField8(value);
    }
    public static final BitField8 longPointer(int base, MemoryAccess access) {
        return new BitField8(base, access);
    }
    public static final BitField8 pointer(char base, MemoryAccess access) {
        return new BitField8(Memory.instance.lengthenMDS(base), access);
    }
    
    private BitField8(char value) {
        super(value);
    }
    private BitField8(int base, MemoryAccess access) {
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
    public final char left() {
        return (char)((value & LEFT_MASK) >>> LEFT_SHIFT);
    }
    public final void left(char newValue) {
        value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
    }
    
    public final char right() {
        return (char)((value & RIGHT_MASK) >>> RIGHT_SHIFT);
    }
    public final void right(char newValue) {
        value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
    }
    
}

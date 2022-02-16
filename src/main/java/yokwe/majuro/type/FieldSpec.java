package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// FieldSpec: TYPE = RECORD[pos (0:0..3): NIBBLE, size (0:4..7): NIBBLE];
public final class FieldSpec extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 8;
    
    //
    // Constructor
    //
    public static final FieldSpec value(char value) {
        return new FieldSpec(value);
    }
    public static final FieldSpec longPointer(int base, MemoryAccess access) {
        return new FieldSpec(base, access);
    }
    public static final FieldSpec pointer(char base, MemoryAccess access) {
        return new FieldSpec(Memory.lengthenMDS(base), access);
    }
    
    private FieldSpec(char value) {
        super(value);
    }
    private FieldSpec(int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // pos  (0:0..3): NIBBLE
    // size (0:4..7): NIBBLE
    
    private static final int POS_MASK   = 0b1111_0000;
    private static final int POS_SHIFT  =           4;
    private static final int SIZE_MASK  = 0b0000_1111;
    private static final int SIZE_SHIFT =           0;
    
    //
    // Bit Field Access Methods
    //
    public final char pos() {
        return (char)((value & POS_MASK) >>> POS_SHIFT);
    }
    public final void pos(char newValue) {
        value = (value & ~POS_MASK) | ((newValue << POS_SHIFT) & POS_MASK);
    }
    
    public final char size() {
        return (char)((value & SIZE_MASK) >>> SIZE_SHIFT);
    }
    public final void size(char newValue) {
        value = (value & ~SIZE_MASK) | ((newValue << SIZE_SHIFT) & SIZE_MASK);
    }
    
}

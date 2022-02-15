package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// FieldDesc: TYPE = RECORD[offset (0:0..7): BYTE, field (0:8..15): FieldSpec];
public final class FieldDesc extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final FieldDesc value(char value) {
        return new FieldDesc(value);
    }
    public static final FieldDesc longPointer(int base, MemoryAccess access) {
        return new FieldDesc(base, access);
    }
    public static final FieldDesc pointer(char base, MemoryAccess access) {
        return new FieldDesc(Memory.instance.lengthenMDS(base), access);
    }
    
    private FieldDesc(char value) {
        super(value);
    }
    private FieldDesc(int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // offset (0:0..7):  BYTE
    // field  (0:8..15): FieldSpec
    
    private static final int OFFSET_MASK  = 0b1111_1111_0000_0000;
    private static final int OFFSET_SHIFT =                     8;
    private static final int FIELD_MASK   = 0b0000_0000_1111_1111;
    private static final int FIELD_SHIFT  =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final char offset() {
        return (char)((value & OFFSET_MASK) >>> OFFSET_SHIFT);
    }
    public final void offset(char newValue) {
        value = (value & ~OFFSET_MASK) | ((newValue << OFFSET_SHIFT) & OFFSET_MASK);
    }
    
    public final char field() {
        return (char)((value & FIELD_MASK) >>> FIELD_SHIFT);
    }
    public final void field(char newValue) {
        value = (value & ~FIELD_MASK) | ((newValue << FIELD_SHIFT) & FIELD_MASK);
    }
    
}

package yokwe.majuro.type;

// FieldDesc: TYPE = RECORD[offset (0:0..7): BYTE, field (0:8..15): FieldSpec];
public class FieldDesc extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public FieldDesc(char value) {
        super(value);
    }
    public FieldDesc(int base, MemoryAccess access) {
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
    public final int offset() {
        return (value & OFFSET_MASK) >> OFFSET_SHIFT;
    }
    public final void offset(int newValue) {
        value = (value & ~OFFSET_MASK) | ((newValue << OFFSET_SHIFT) & OFFSET_MASK);
    }
    
    public final int field() {
        return (value & FIELD_MASK) >> FIELD_SHIFT;
    }
    public final void field(int newValue) {
        value = (value & ~FIELD_MASK) | ((newValue << FIELD_SHIFT) & FIELD_MASK);
    }
    
}

package yokwe.majuro.type;

// FieldDesc: TYPE = RECORD[offset (0:0..7): BYTE, field (0:8..15): FieldSpec];
public final class FieldDesc extends MemoryData16 {
    public static final String NAME     = "FieldDesc";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =          16;

    public static final int OFFSET_MASK  = 0b1111_1111_0000_0000;
    public static final int OFFSET_SHIFT =                     8;
    public static final int FIELD_MASK   = 0b0000_0000_1111_1111;
    public static final int FIELD_SHIFT  =                     0;

    public FieldDesc(char value) {
        super(value);
    }
    public FieldDesc(int base, MemoryAccess access) {
        super(base, access);
    }


    // field access
    public int offset() {
        return (value & OFFSET_MASK) >> OFFSET_SHIFT;
    }
    public void offset(int newValue) {
        value = (value & ~OFFSET_MASK) | ((newValue << OFFSET_SHIFT) & OFFSET_MASK);
    }

    public int field() {
        return (value & FIELD_MASK) >> FIELD_SHIFT;
    }
    public void field(int newValue) {
        value = (value & ~FIELD_MASK) | ((newValue << FIELD_SHIFT) & FIELD_MASK);
    }

}

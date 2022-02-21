package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// FieldDesc: TYPE = RECORD[offset (0:0..7): BYTE, field (0:8..15): FieldSpec];
public final class FieldDesc extends MemoryData16 {
    public static final String NAME = "FieldDesc";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final FieldDesc value(@Mesa.CARD16 int value) {
        return new FieldDesc(value);
    }
    public static final FieldDesc value() {
        return new FieldDesc(0);
    }
    public static final FieldDesc longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new FieldDesc(base, access);
    }
    public static final FieldDesc pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new FieldDesc(Memory.lengthenMDS(base), access);
    }
    
    private FieldDesc(@Mesa.CARD16 int value) {
        super(value);
    }
    private FieldDesc(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    public final @Mesa.CARD16 int offset() {
        return Types.toCARD16((value & OFFSET_MASK) >>> OFFSET_SHIFT);
    }
    public final FieldDesc offset(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~OFFSET_MASK) | ((newValue << OFFSET_SHIFT) & OFFSET_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int field() {
        return Types.toCARD16((value & FIELD_MASK) >>> FIELD_SHIFT);
    }
    public final FieldDesc field(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~FIELD_MASK) | ((newValue << FIELD_SHIFT) & FIELD_MASK));
        return this;
    }
    
}

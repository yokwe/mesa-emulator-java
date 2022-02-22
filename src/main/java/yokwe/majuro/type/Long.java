package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// Long: TYPE = RECORD32[low (0:0..15): UNSPECIFIED, high (1:0..15): UNSPECIFIED];
public final class Long extends MemoryData32 {
    public static final String NAME = "Long";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final Long value(@Mesa.CARD32 int value) {
        return new Long(value);
    }
    public static final Long value() {
        return new Long(0);
    }
    public static final Long longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new Long(base, access);
    }
    public static final Long pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Long(Memory.lengthenMDS(base), access);
    }
    
    private Long(@Mesa.CARD32 int value) {
        super(value);
    }
    private Long(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // low  (0:0..15): UNSPECIFIED
    // high (1:0..15): UNSPECIFIED
    
    private static final int LOW_MASK   = 0b0000_0000_0000_0000_1111_1111_1111_1111;
    private static final int LOW_SHIFT  =                                         0;
    private static final int HIGH_MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    private static final int HIGH_SHIFT =                                        16;
    
    //
    // Bit Field Access Methods
    //
    // @Mesa.CARD16 is UNSPECIFIED
    public final @Mesa.CARD16 int low() {
        return Types.toCARD16((value & LOW_MASK) >>> LOW_SHIFT);
    }
    public final Long low(@Mesa.CARD16 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
        value = Types.toCARD16((value & ~LOW_MASK) | ((newValue << LOW_SHIFT) & LOW_MASK));
        return this;
    }
    
    // @Mesa.CARD16 is UNSPECIFIED
    public final @Mesa.CARD16 int high() {
        return Types.toCARD16((value & HIGH_MASK) >>> HIGH_SHIFT);
    }
    public final Long high(@Mesa.CARD16 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
        value = Types.toCARD16((value & ~HIGH_MASK) | ((newValue << HIGH_SHIFT) & HIGH_MASK));
        return this;
    }
    
}

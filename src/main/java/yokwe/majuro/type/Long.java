package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// Long: TYPE = RECORD32[low (0:0..15): UNSPECIFIED, high (1:0..15): UNSPECIFIED];
public final class Long extends MemoryData32 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final Long value(int value) {
        return new Long(value);
    }
    public static final Long longPointer(int base, MemoryAccess access) {
        return new Long(base, access);
    }
    public static final Long pointer(char base, MemoryAccess access) {
        return new Long(Mesa.lengthenMDS(base), access);
    }
    
    private Long(int value) {
        super(value);
    }
    private Long(int base, MemoryAccess access) {
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
    public final int low() {
        return (value & LOW_MASK) >>> LOW_SHIFT;
    }
    public final void low(int newValue) {
        value = (value & ~LOW_MASK) | ((newValue << LOW_SHIFT) & LOW_MASK);
    }
    
    public final int high() {
        return (value & HIGH_MASK) >>> HIGH_SHIFT;
    }
    public final void high(int newValue) {
        value = (value & ~HIGH_MASK) | ((newValue << HIGH_SHIFT) & HIGH_MASK);
    }
    
}

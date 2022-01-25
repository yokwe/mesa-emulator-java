package yokwe.majuro.type;

// Long: TYPE = RECORD32[low (0:0..15): UNSPECIFIED, high (1:0..15): UNSPECIFIED];
public final class Long extends MemoryData32 {
    public static final String NAME     = "Long";
    public static final int    SIZE     =      2;
    public static final int    BIT_SIZE =     32;

    public static final int LOW_MASK   = 0b0000_0000_0000_0000_1111_1111_1111_1111;
    public static final int LOW_SHIFT  =                                         0;
    public static final int HIGH_MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    public static final int HIGH_SHIFT =                                        16;

    public Long(int value) {
        super(value);
    }
    public Long(int base, MemoryAccess access) {
        super(base, access);
    }


    // field access
    public int low() {
        return (value & LOW_MASK) >> LOW_SHIFT;
    }
    public void low(int newValue) {
        value = (value & ~LOW_MASK) | ((newValue << LOW_SHIFT) & LOW_MASK);
    }

    public int high() {
        return (value & HIGH_MASK) >> HIGH_SHIFT;
    }
    public void high(int newValue) {
        value = (value & ~HIGH_MASK) | ((newValue << HIGH_SHIFT) & HIGH_MASK);
    }

}

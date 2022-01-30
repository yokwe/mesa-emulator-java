package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// BIT: TYPE = [0..2);
public final class BIT extends MemoryData16 {
    public static final String NAME      = "BIT";
    public static final int    WORD_SIZE =     1;
    public static final int    BIT_SIZE  =     1;

    public static final long MIN_VALUE  = 0;
    public static final long MAX_VALUE  = 1;
    public static final long SIZE_VALUE = 2;

    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(Integer.toUnsignedLong(value));
    }

    //
    // Constructor
    //
    public BIT(char value) {
        super(value);
    }
    public BIT(int base, MemoryAccess access) {
        super(base, access);
    }
}

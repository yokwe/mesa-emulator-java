package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// NIBBLE: TYPE = [0..16);
public final class NIBBLE extends MemoryData16 {
    public static final String NAME      = "NIBBLE";
    public static final int    WORD_SIZE =        1;
    public static final int    BIT_SIZE  =        4;

    public static final long MIN_VALUE  =  0;
    public static final long MAX_VALUE  = 15;
    public static final long SIZE_VALUE = 16;

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
    public NIBBLE(char value) {
        super(value);
    }
    public NIBBLE(int base, MemoryAccess access) {
        super(base, access);
    }
}

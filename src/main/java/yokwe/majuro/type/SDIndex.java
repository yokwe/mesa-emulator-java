package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// SDIndex: TYPE = [0..256);
public final class SDIndex {
    public static final String NAME     = "SDIndex";
    public static final int    SIZE     =         1;
    public static final int    BIT_SIZE =         8;

    public static final long MIN_VALUE  =    0;
    public static final long MAX_VALUE  = 0xFF;
    public static final long SIZE_VALUE =  256;

    private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(Integer.toUnsignedLong(value));
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// LONG UNSPECIFIED: TYPE = [0..4294967295];
public final class LONG_UNSPECIFIED {
    public static final String NAME     = "LONG_UNSPECIFIED";
    public static final int    SIZE     =                  1;
    public static final int    BIT_SIZE =                 32;

    public static final long MIN_VALUE  =            0;
    public static final long MAX_VALUE  = 0xFFFF_FFFFL;
    public static final long SIZE_VALUE =  4294967296L;

    private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(Integer.toUnsignedLong(value));
    }
}

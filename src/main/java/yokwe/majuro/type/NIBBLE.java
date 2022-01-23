package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// NIBBLE: TYPE = [0..16);
public final class NIBBLE {
    public static final String NAME = "NIBBLE";

    public static final long MIN_VALUE  =  0;
    public static final long MAX_VALUE  = 15;
    public static final long SIZE_VALUE = 16;

    private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(Integer.toUnsignedLong(value));
    }
}

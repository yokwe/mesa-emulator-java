package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// GFTIndex: TYPE = [0..16384);
public final class GFTIndex {
    public static final String NAME = "GFTIndex";

    public static final long MIN_VALUE  =     0;
    public static final long MAX_VALUE  = 16383;
    public static final long SIZE_VALUE = 16384;

    private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(Integer.toUnsignedLong(value));
    }
}
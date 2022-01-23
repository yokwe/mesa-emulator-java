package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// CARDINAL: TYPE = [0..65535];
public final class CARDINAL {
    public static final String NAME = "CARDINAL";

    public static final long MIN_VALUE  = 0;
    public static final long MAX_VALUE  = 0xFFFF;
    public static final long SIZE_VALUE = MAX_VALUE - MIN_VALUE + 1L;

    private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(Integer.toUnsignedLong(value));
    }
}

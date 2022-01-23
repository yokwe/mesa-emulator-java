package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// InterruptLevel: TYPE = [0..16);
public final class InterruptLevel {
    public static final String NAME = "InterruptLevel";

    public static final long MIN_VALUE  = 0;
    public static final long MAX_VALUE  = 15;
    public static final long SIZE_VALUE = MAX_VALUE - MIN_VALUE + 1L;

    private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(Integer.toUnsignedLong(value));
    }
}

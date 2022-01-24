package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// PsbIndex: TYPE = [0..1024);
public final class PsbIndex {
    public static final String NAME     = "PsbIndex";
    public static final int    SIZE     =          1;
    public static final int    BIT_SIZE =         10;

    public static final long MIN_VALUE  =    0;
    public static final long MAX_VALUE  = 1023;
    public static final long SIZE_VALUE = 1024;

    private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(Integer.toUnsignedLong(value));
    }
}

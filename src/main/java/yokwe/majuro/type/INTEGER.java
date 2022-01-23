package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// INTEGER: TYPE = [-32768..32767];
public final class INTEGER {
    public static final String NAME = "INTEGER";

    public static final long MIN_VALUE  = Short.MIN_VALUE;
    public static final long MAX_VALUE  = Short.MAX_VALUE;
    public static final long SIZE_VALUE =           65536;

    private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// AVItemType: TYPE = {frame(0), empty(1), indirect(2), unused(3)};
public final class AVItemType {
    public static final String NAME     = "AVItemType";
    public static final int    SIZE     =            1;
    public static final int    BIT_SIZE =            2;

    public static final char FRAME    = 0;
    public static final char EMPTY    = 1;
    public static final char INDIRECT = 2;
    public static final char UNUSED   = 3;

    private static final int[] values = {
        FRAME, EMPTY, INDIRECT, UNUSED
    };
    private static final String[] names = {
        "FRAME", "EMPTY", "INDIRECT", "UNUSED"
    };
    private static final EnumContext checkValue = new EnumContext(NAME, values, names);

    public static final String toString(int value) {
        return checkValue.toString(value);
    }

    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// Direction: TYPE = {forward(0), backward(1)};
public final class Direction {
    public static final String NAME     = "Direction";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =           1;

    public static final char FORWARD  = 0;
    public static final char BACKWARD = 1;

    private static final int[] values = {
        FORWARD, BACKWARD
    };
    private static final String[] names = {
        "FORWARD", "BACKWARD"
    };
    private static final EnumContext checkValue = new EnumContext(NAME, values, names);

    public static final String toString(int value) {
        return checkValue.toString(value);
    }

    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// DstFunc: TYPE = {null(0), and(1), or(2), xor(3)};
public final class DstFunc {
    public static final String NAME = "DstFunc";

    public static final char NULL = 0;
    public static final char AND  = 1;
    public static final char OR   = 2;
    public static final char XOR  = 3;

    private static final int[] values = {
        NULL, AND, OR, XOR
    };
    private static final String[] names = {
        "NULL", "AND", "OR", "XOR"
    };
    private static final EnumContext checkValue = new EnumContext(NAME, values, names);

    public static final String toString(int value) {
        return checkValue.toString(value);
    }

    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// SrcFunc: TYPE = {null(0), complement(1)};
public final class SrcFunc {
    public static final String NAME     = "SrcFunc";
    public static final int    SIZE     =         1;
    public static final int    BIT_SIZE =         1;

    public static final char NULL       = 0;
    public static final char COMPLEMENT = 1;

    private static final int[] values = {
        NULL, COMPLEMENT
    };
    private static final String[] names = {
        "NULL", "COMPLEMENT"
    };
    private static final EnumContext checkValue = new EnumContext(NAME, values, names);

    public static final String toString(int value) {
        return checkValue.toString(value);
    }

    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
}

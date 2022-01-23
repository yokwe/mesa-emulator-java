package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// XferType: TYPE = {return(0), call(1), localCall(2), part(3), xfer(4), trap(5), processSwitch(6), unused(7)};
public final class XferType {
    public static final String NAME = "XferType";

    public static final char RETURN         = 0;
    public static final char CALL           = 1;
    public static final char LOCAL_CALL     = 2;
    public static final char PART           = 3;
    public static final char XFER           = 4;
    public static final char TRAP           = 5;
    public static final char PROCESS_SWITCH = 6;
    public static final char UNUSED         = 7;

    private static final int[] values = {
        RETURN, CALL, LOCAL_CALL, PART, XFER, TRAP, PROCESS_SWITCH, UNUSED
    };
    private static final String[] names = {
        "RETURN", "CALL", "LOCAL_CALL", "PART", "XFER", "TRAP", "PROCESS_SWITCH", "UNUSED"
    };
    private static final EnumContext checkValue = new EnumContext(NAME, values, names);

    public static final String toString(int value) {
        return checkValue.toString(value);
    }

    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }
}

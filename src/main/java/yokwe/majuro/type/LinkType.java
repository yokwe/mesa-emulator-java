package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// LinkType: TYPE = {frame(0), oldProcedure(1), indirect(2), newProcedure(3)};
public final class LinkType extends MemoryData16 {
    public static final String NAME     = "LinkType";
    public static final int    SIZE     =          1;
    public static final int    BIT_SIZE =          2;

    public static final char FRAME         = 0;
    public static final char OLD_PROCEDURE = 1;
    public static final char INDIRECT      = 2;
    public static final char NEW_PROCEDURE = 3;

    private static final int[] values = {
        FRAME, OLD_PROCEDURE, INDIRECT, NEW_PROCEDURE
    };
    private static final String[] names = {
        "FRAME", "OLD_PROCEDURE", "INDIRECT", "NEW_PROCEDURE"
    };
    private static final ContextEnum checkValue = new ContextEnum(NAME, values, names);

    public static final String toString(int value) {
        return checkValue.toString(value);
    }

    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);
    }

    public LinkType(char value) {
        super(value);
    }
    public LinkType(int base, MemoryAccess access) {
        super(base, access);
    }

}

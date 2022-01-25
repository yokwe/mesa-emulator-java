package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// XferType: TYPE = {return(0), call(1), localCall(2), part(3), xfer(4), trap(5), processSwitch(6), unused(7)};
public final class XferType extends MemoryData16 {
    public static final String NAME      = "XferType";
    public static final int    WORD_SIZE =          1;
    public static final int    BIT_SIZE  =          3;

    //
    // Enum Value Constants
    //
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
    private static final ContextEnum context = new ContextEnum(NAME, values, names);

    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }

    //
    // Constructor
    //
    public XferType(char value) {
        super(value);
    }
    public XferType(int base, MemoryAccess access) {
        super(base, access);
    }
    public XferType(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }

    @Override
    public String toString() {
        return context.toString(value);
    }
}

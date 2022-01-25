package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// AVItemType: TYPE = {frame(0), empty(1), indirect(2), unused(3)};
public final class AVItemType extends MemoryData16 {
    public static final String NAME      = "AVItemType";
    public static final int    WORD_SIZE =            1;
    public static final int    BIT_SIZE  =            2;

    //
    // Enum Value Constants
    //
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
    private static final ContextEnum context = new ContextEnum(NAME, values, names);

    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }

    //
    // Constructor
    //
    public AVItemType(char value) {
        super(value);
    }
    public AVItemType(int base, MemoryAccess access) {
        super(base, access);
    }
    public AVItemType(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }

    @Override
    public String toString() {
        return context.toString(value);
    }
}

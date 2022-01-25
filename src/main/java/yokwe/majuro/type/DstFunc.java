package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// DstFunc: TYPE = {null(0), and(1), or(2), xor(3)};
public final class DstFunc extends MemoryData16 {
    public static final String NAME      = "DstFunc";
    public static final int    WORD_SIZE =         1;
    public static final int    BIT_SIZE  =         2;

    //
    // Enum Value Constants
    //
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
    private static final ContextEnum context = new ContextEnum(NAME, values, names);

    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }

    //
    // Constructor
    //
    public DstFunc(char value) {
        super(value);
    }
    public DstFunc(int base, MemoryAccess access) {
        super(base, access);
    }
    public DstFunc(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }

    @Override
    public String toString() {
        return context.toString(value);
    }
}

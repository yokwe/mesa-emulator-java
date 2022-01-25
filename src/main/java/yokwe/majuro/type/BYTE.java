package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// BYTE: TYPE = [0..256);
public final class BYTE extends MemoryData16 {
    public static final String NAME      = "BYTE";
    public static final int    WORD_SIZE =      1;
    public static final int    BIT_SIZE  =      8;

    public static final long MIN_VALUE  =    0;
    public static final long MAX_VALUE  = 0xFF;
    public static final long SIZE_VALUE =  256;

    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);

    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(Integer.toUnsignedLong(value));
    }

    //
    // Constructor
    //
    public BYTE(char value) {
        super(value);
    }
    public BYTE(int base, MemoryAccess access) {
        super(base, access);
    }
    public BYTE(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }
}

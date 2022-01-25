package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// Priority: TYPE = [0..7];
public final class Priority extends MemoryData16 {
    public static final String NAME      = "Priority";
    public static final int    WORD_SIZE =          1;
    public static final int    BIT_SIZE  =          3;

    public static final long MIN_VALUE  = 0;
    public static final long MAX_VALUE  = 7;
    public static final long SIZE_VALUE = 8;

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
    public Priority(char value) {
        super(value);
    }
    public Priority(int base, MemoryAccess access) {
        super(base, access);
    }
    public Priority(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }
}

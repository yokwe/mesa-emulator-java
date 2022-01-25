package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// GFTIndex: TYPE = [0..16384);
public final class GFTIndex extends MemoryData16 {
    public static final String NAME      = "GFTIndex";
    public static final int    WORD_SIZE =          1;
    public static final int    BIT_SIZE  =         14;

    public static final long MIN_VALUE  =     0;
    public static final long MAX_VALUE  = 16383;
    public static final long SIZE_VALUE = 16384;

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
    public GFTIndex(char value) {
        super(value);
    }
    public GFTIndex(int base, MemoryAccess access) {
        super(base, access);
    }
    public GFTIndex(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }
}

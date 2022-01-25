package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// FSIndex: TYPE = [0..256);
public final class FSIndex extends MemoryData16 {
    public static final String NAME     = "FSIndex";
    public static final int    SIZE     =         1;
    public static final int    BIT_SIZE =         8;

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
    public FSIndex(char value) {
        super(value);
    }
    public FSIndex(int base, MemoryAccess access) {
        super(base, access);
    }
}

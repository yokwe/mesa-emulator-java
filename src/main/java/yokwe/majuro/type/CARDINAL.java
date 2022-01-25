package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// CARDINAL: TYPE = [0..65535];
public final class CARDINAL extends MemoryData16 {
    public static final String NAME     = "CARDINAL";
    public static final int    SIZE     =          1;
    public static final int    BIT_SIZE =         16;

    public static final long MIN_VALUE  =      0;
    public static final long MAX_VALUE  = 0xFFFF;
    public static final long SIZE_VALUE =  65536;

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
    public CARDINAL(char value) {
        super(value);
    }
    public CARDINAL(int base, MemoryAccess access) {
        super(base, access);
    }
}

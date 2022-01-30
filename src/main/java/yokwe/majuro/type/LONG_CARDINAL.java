package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// LONG CARDINAL: TYPE = [0..4294967295];
public class LONG_CARDINAL extends MemoryData32 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();

    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;

    public static final long MIN_VALUE  =            0;
    public static final long MAX_VALUE  = 0xFFFF_FFFFL;
    public static final long SIZE_VALUE =  4294967296L;

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
    public LONG_CARDINAL(int value) {
        super(value);
    }
    public LONG_CARDINAL(int base, MemoryAccess access) {
        super(base, access);
    }
}

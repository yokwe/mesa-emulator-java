package yokwe.majuro.type;

// LONG POINTER: TYPE = LONG POINTER;
public final class LONG_POINTER extends MemoryBase {
    public static final String NAME      = "LONG_POINTER";
    public static final int    WORD_SIZE =              2;
    public static final int    BIT_SIZE  =             32;

    //
    // Constructor
    //
    public LONG_POINTER(int base) {
        super(base);
    }
    public LONG_POINTER(int base, int index) {
        super(base + (LONG_POINTER.WORD_SIZE * index));
    }
}

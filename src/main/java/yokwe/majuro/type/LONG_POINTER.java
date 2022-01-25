package yokwe.majuro.type;

// LONG POINTER: TYPE = LONG POINTER;
public final class LONG_POINTER extends MemoryData32 {
    public static final String NAME      = "LONG_POINTER";
    public static final int    WORD_SIZE =              2;
    public static final int    BIT_SIZE  =             32;

    //
    // Constructor
    //
    public LONG_POINTER(int value) {
        super(value);
    }
    public LONG_POINTER(int base, MemoryAccess access) {
        super(base, access);
    }
    public LONG_POINTER(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }
}

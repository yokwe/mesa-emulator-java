package yokwe.majuro.type;

// POINTER: TYPE = POINTER;
public final class POINTER extends MemoryData16 {
    public static final String NAME      = "POINTER";
    public static final int    WORD_SIZE =         1;
    public static final int    BIT_SIZE  =        16;

    //
    // Constructor
    //
    public POINTER(char value) {
        super(value);
    }
    public POINTER(int base, MemoryAccess access) {
        super(base, access);
    }
    public POINTER(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }
}

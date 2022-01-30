package yokwe.majuro.type;

// POINTER: TYPE = POINTER;
public final class POINTER extends MemoryBase {
    public static final String NAME      = "POINTER";
    public static final int    WORD_SIZE =         1;
    public static final int    BIT_SIZE  =        16;

    //
    // Constructor
    //
    public POINTER(int base) {
        super(base);
    }
}

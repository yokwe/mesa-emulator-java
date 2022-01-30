package yokwe.majuro.type;

// LocalFrameHandle: TYPE = POINTER TO LocalVariables;
public final class LocalFrameHandle extends MemoryBase {
    public static final String NAME      = "LocalFrameHandle";
    public static final int    WORD_SIZE =                  1;
    public static final int    BIT_SIZE  =                 16;

    //
    // Constructor
    //
    public LocalFrameHandle(int base) {
        super(base);
    }
}

package yokwe.majuro.type;

// GlobalFrameHandle: TYPE = LONG POINTER TO GlobalVariables;
public final class GlobalFrameHandle extends MemoryBase {
    public static final String NAME      = "GlobalFrameHandle";
    public static final int    WORD_SIZE =                   2;
    public static final int    BIT_SIZE  =                  32;

    //
    // Constructor
    //
    public GlobalFrameHandle(int base) {
        super(base);
    }
}

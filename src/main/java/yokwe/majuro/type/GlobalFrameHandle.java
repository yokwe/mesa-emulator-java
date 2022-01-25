package yokwe.majuro.type;

// GlobalFrameHandle: TYPE = LONG POINTER TO GlobalVariables;
public final class GlobalFrameHandle extends MemoryData32 {
    public static final String NAME     = "GlobalFrameHandle";
    public static final int    SIZE     =                   2;
    public static final int    BIT_SIZE =                  32;

    //
    // Constructor
    //
    public GlobalFrameHandle(int value) {
        super(value);
    }
    public GlobalFrameHandle(int base, MemoryAccess access) {
        super(base, access);
    }
}

package yokwe.majuro.type;

// LocalFrameHandle: TYPE = POINTER TO LocalVariables;
public final class LocalFrameHandle extends MemoryData16 {
    public static final String NAME      = "LocalFrameHandle";
    public static final int    WORD_SIZE =                  1;
    public static final int    BIT_SIZE  =                 16;

    //
    // Constructor
    //
    public LocalFrameHandle(char value) {
        super(value);
    }
    public LocalFrameHandle(int base, MemoryAccess access) {
        super(base, access);
    }
    public LocalFrameHandle(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }
}

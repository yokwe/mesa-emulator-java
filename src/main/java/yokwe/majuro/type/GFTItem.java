package yokwe.majuro.type;

// GFTItem: TYPE = RECORD[globalFrame (0:0..31): GlobalFrameHandle, codebase (2:0..31): LONG POINTER TO CodeSegment];
public final class GFTItem extends MemoryBase {
    public static final String NAME      = "GFTItem";
    public static final int    WORD_SIZE =         4;
    public static final int    BIT_SIZE  =        64;

    //
    // Constructor
    //
    public GFTItem(int base) {
        super(base);
    }
    public GFTItem(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Constants for field access
    //
    public static final int OFFSET_GLOBAL_FRAME = 0; // globalFrame (0:0..31): GlobalFrameHandle
    public static final int OFFSET_CODEBASE     = 2; // codebase    (2:0..31): LONG POINTER TO CodeSegment

    public GlobalFrameHandle globalFrame(MemoryAccess memoryAccess) {
        return new GlobalFrameHandle(base + OFFSET_GLOBAL_FRAME, memoryAccess);
    }
    // FIXME
    // public GFTItem#codebase codebase(MemoryAccess memoryAccess) {
    // return new GFTItem#codebase(base + OFFSET_CODEBASE, memoryAccess);
    // }
}

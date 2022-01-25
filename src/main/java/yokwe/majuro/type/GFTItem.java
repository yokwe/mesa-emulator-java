package yokwe.majuro.type;

// GFTItem: TYPE = RECORD[globalFrame (0:0..31): GlobalFrameHandle, codebase (2:0..31): LONG POINTER TO CodeSegment];
public final class GFTItem {
    public static final String NAME     = "GFTItem";
    public static final int    SIZE     =         4;
    public static final int    BIT_SIZE =        64;

    //
    // Constants for field access
    //
    public static final int OFFSET_GLOBAL_FRAME = 0; // globalFrame (0:0..31): GlobalFrameHandle
    public static final int OFFSET_CODEBASE     = 2; // codebase    (2:0..31): LONG POINTER TO CodeSegment

    public final int base;

    //
    // Constructor
    //
    public GFTItem(int value) {
        this.base = value;
    }

}

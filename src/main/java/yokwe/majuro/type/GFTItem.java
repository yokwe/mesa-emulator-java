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
    // Access to Field of Record
    //
    // globalFrame (0:0..31): GlobalFrameHandle
    private static final int OFFSET_GLOBAL_FRAME = 0;
    public BLOCK globalFrame() {
        return new BLOCK(base + OFFSET_GLOBAL_FRAME);
    }
    // codebase (2:0..31): LONG POINTER TO CodeSegment
    private static final int OFFSET_CODEBASE = 2;
    public CodeSegment codebase() {
        return new CodeSegment(base + OFFSET_CODEBASE);
    }
}

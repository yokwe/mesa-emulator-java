package yokwe.majuro.type;

// ProcessStateBlock: TYPE = RECORD[link (0:0..15): PsbLink, flags (1:0..15): PsbFlags, context (2:0..15): POINTER, timeout (3:0..15): Ticks, mds (4:0..15): CARDINAL, available (5:0..15): UNSPECIFIED, stickty (6:0..31): LONG UNSPECIFIED];
public final class ProcessStateBlock extends MemoryBase {
    public static final String NAME      = "ProcessStateBlock";
    public static final int    WORD_SIZE =                   8;
    public static final int    BIT_SIZE  =                 128;

    //
    // Constructor
    //
    public ProcessStateBlock(int base) {
        super(base);
    }
    public ProcessStateBlock(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Constants for field access
    //
    public static final int OFFSET_LINK      = 0; // link      (0:0..15): PsbLink
    public static final int OFFSET_FLAGS     = 1; // flags     (1:0..15): PsbFlags
    public static final int OFFSET_CONTEXT   = 2; // context   (2:0..15): POINTER
    public static final int OFFSET_TIMEOUT   = 3; // timeout   (3:0..15): Ticks
    public static final int OFFSET_MDS       = 4; // mds       (4:0..15): CARDINAL
    public static final int OFFSET_AVAILABLE = 5; // available (5:0..15): UNSPECIFIED
    public static final int OFFSET_STICKTY   = 6; // stickty   (6:0..31): LONG UNSPECIFIED

    public PsbLink link(MemoryAccess memoryAccess) {
        return new PsbLink(base + OFFSET_LINK, memoryAccess);
    }
    public PsbFlags flags(MemoryAccess memoryAccess) {
        return new PsbFlags(base + OFFSET_FLAGS, memoryAccess);
    }
    public POINTER context(MemoryAccess memoryAccess) {
        return new POINTER(base + OFFSET_CONTEXT, memoryAccess);
    }
    public CARDINAL timeout(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_TIMEOUT, memoryAccess);
    }
    public CARDINAL mds(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_MDS, memoryAccess);
    }
    public UNSPECIFIED available(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_AVAILABLE, memoryAccess);
    }
    public LONG_UNSPECIFIED stickty(MemoryAccess memoryAccess) {
        return new LONG_UNSPECIFIED(base + OFFSET_STICKTY, memoryAccess);
    }
}

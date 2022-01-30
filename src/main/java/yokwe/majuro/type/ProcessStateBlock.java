package yokwe.majuro.type;

// ProcessStateBlock: TYPE = RECORD[link (0:0..15): PsbLink, flags (1:0..15): PsbFlags, context (2:0..15): POINTER, timeout (3:0..15): Ticks, mds (4:0..15): CARDINAL, available (5:0..15): UNSPECIFIED, sticky (6:0..31): LONG UNSPECIFIED];
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

    //
    // Access to Field of Record
    //
    // link (0:0..15): PsbLink
    private static final int OFFSET_LINK = 0;
    public PsbLink link(MemoryAccess memoryAccess) {
        return new PsbLink(base + OFFSET_LINK, memoryAccess);
    }
    // flags (1:0..15): PsbFlags
    private static final int OFFSET_FLAGS = 1;
    public PsbFlags flags(MemoryAccess memoryAccess) {
        return new PsbFlags(base + OFFSET_FLAGS, memoryAccess);
    }
    // context (2:0..15): POINTER
    private static final int OFFSET_CONTEXT = 2;
    public POINTER context() {
        return new POINTER(base + OFFSET_CONTEXT);
    }
    // timeout (3:0..15): Ticks
    private static final int OFFSET_TIMEOUT = 3;
    public CARDINAL timeout(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_TIMEOUT, memoryAccess);
    }
    // mds (4:0..15): CARDINAL
    private static final int OFFSET_MDS = 4;
    public CARDINAL mds(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_MDS, memoryAccess);
    }
    // available (5:0..15): UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 5;
    public UNSPECIFIED available(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_AVAILABLE, memoryAccess);
    }
    // sticky (6:0..31): LONG UNSPECIFIED
    private static final int OFFSET_STICKY = 6;
    public LONG_UNSPECIFIED sticky(MemoryAccess memoryAccess) {
        return new LONG_UNSPECIFIED(base + OFFSET_STICKY, memoryAccess);
    }
}

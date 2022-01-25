package yokwe.majuro.type;

// ProcessStateBlock: TYPE = RECORD[link (0:0..15): PsbLink, flags (1:0..15): PsbFlags, context (2:0..15): POINTER, timeout (3:0..15): Ticks, mds (4:0..15): CARDINAL, available (5:0..15): UNSPECIFIED, stickty (6:0..31): LONG UNSPECIFIED];
public final class ProcessStateBlock {
    public static final String NAME     = "ProcessStateBlock";
    public static final int    SIZE     =                   8;
    public static final int    BIT_SIZE =                 128;

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

    public final int base;

    //
    // Constructor
    //
    public ProcessStateBlock(int value) {
        this.base = value;
    }

}

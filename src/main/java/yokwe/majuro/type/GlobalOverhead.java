package yokwe.majuro.type;

// GlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, codebase (2:0..31): LONG POINTER TO CodeSegment, global (4): GlobalVariables];
public final class GlobalOverhead {
    public static final String NAME     = "GlobalOverhead";
    public static final int    SIZE     =                4;
    public static final int    BIT_SIZE =               64;

    public final int base;

    public GlobalOverhead(int value) {
        this.base = value;
    }

    public static final int OFFSET_AVAILABLE = 0; // available (0:0..15): UNSPECIFIED
    public static final int OFFSET_WORD      = 1; // word      (1:0..15): GlobalWord
    public static final int OFFSET_CODEBASE  = 2; // codebase  (2:0..31): LONG POINTER TO CodeSegment
    public static final int OFFSET_GLOBAL    = 4; // global    (4):       GlobalVariables

}

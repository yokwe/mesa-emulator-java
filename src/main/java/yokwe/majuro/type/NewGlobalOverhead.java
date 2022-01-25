package yokwe.majuro.type;

// NewGlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, global (2): GlobalVariables];
public final class NewGlobalOverhead {
    public static final String NAME     = "NewGlobalOverhead";
    public static final int    SIZE     =                   2;
    public static final int    BIT_SIZE =                  32;

    //
    // Constants for field access
    //
    public static final int OFFSET_AVAILABLE = 0; // available (0:0..15): UNSPECIFIED
    public static final int OFFSET_WORD      = 1; // word      (1:0..15): GlobalWord
    public static final int OFFSET_GLOBAL    = 2; // global    (2):       GlobalVariables

    public final int base;

    //
    // Constructor
    //
    public NewGlobalOverhead(int value) {
        this.base = value;
    }

}

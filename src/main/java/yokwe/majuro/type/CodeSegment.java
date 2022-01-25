package yokwe.majuro.type;

// CodeSegment: TYPE = RECORD[available (0:0..63): ARRAY [0..4) OF UNSPECIFIED, code (4): BLOCK];
public final class CodeSegment {
    public static final String NAME     = "CodeSegment";
    public static final int    SIZE     =             4;
    public static final int    BIT_SIZE =            64;

    //
    // Constants for field access
    //
    public static final int OFFSET_AVAILABLE = 0; // available (0:0..63): ARRAY [0..4) OF UNSPECIFIED
    public static final int OFFSET_CODE      = 4; // code      (4):       BLOCK

    public final int base;

    //
    // Constructor
    //
    public CodeSegment(int value) {
        this.base = value;
    }

}

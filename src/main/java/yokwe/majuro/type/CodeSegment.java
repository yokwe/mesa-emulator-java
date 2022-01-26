package yokwe.majuro.type;

// CodeSegment: TYPE = RECORD[available (0:0..63): ARRAY [0..4) OF UNSPECIFIED, code (4): BLOCK];
public final class CodeSegment extends MemoryBase {
    public static final String NAME      = "CodeSegment";
    public static final int    WORD_SIZE =             4;
    public static final int    BIT_SIZE  =            64;

    //
    // Constructor
    //
    public CodeSegment(int base) {
        super(base);
    }
    public CodeSegment(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Constants for field access
    //
    public static final int OFFSET_AVAILABLE = 0; // available (0:0..63): ARRAY [0..4) OF UNSPECIFIED
    public static final int OFFSET_CODE      = 4; // code      (4):       BLOCK

    // FIXME
    // public CodeSegment#available available() {
    // return new CodeSegment#available(base + OFFSET_AVAILABLE);
    // }
    public BLOCK code() {
        return new BLOCK(base + OFFSET_CODE);
    }
}

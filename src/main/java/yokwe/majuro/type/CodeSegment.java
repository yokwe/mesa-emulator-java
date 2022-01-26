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
    // Access to Field of Record
    //
    // available (0:0..63): ARRAY [0..4) OF UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 0;
    public UNSPECIFIED available(int index, MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_AVAILABLE + (UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
    // code (4): BLOCK
    private static final int OFFSET_CODE = 4;
    public UNSPECIFIED code(int index, MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_CODE + (UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
}

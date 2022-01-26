package yokwe.majuro.type;

// GlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, codebase (2:0..31): LONG POINTER TO CodeSegment, global (4): GlobalVariables];
public final class GlobalOverhead extends MemoryBase {
    public static final String NAME      = "GlobalOverhead";
    public static final int    WORD_SIZE =                4;
    public static final int    BIT_SIZE  =               64;

    //
    // Constructor
    //
    public GlobalOverhead(int base) {
        super(base);
    }
    public GlobalOverhead(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Access to Field of Record
    //
    // available (0:0..15): UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 0;
    public UNSPECIFIED available(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_AVAILABLE, memoryAccess);
    }
    // word (1:0..15): GlobalWord
    private static final int OFFSET_WORD = 1;
    public GlobalWord word(MemoryAccess memoryAccess) {
        return new GlobalWord(base + OFFSET_WORD, memoryAccess);
    }
    // codebase (2:0..31): LONG POINTER TO CodeSegment
    private static final int OFFSET_CODEBASE = 2;
    public CodeSegment codebase() {
        return new CodeSegment(base + OFFSET_CODEBASE);
    }
    // global (4): GlobalVariables
    private static final int OFFSET_GLOBAL = 4;
    public UNSPECIFIED global(int index, MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_GLOBAL + (UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
}

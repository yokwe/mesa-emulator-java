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
    // Constants for field access
    //
    public static final int OFFSET_AVAILABLE = 0; // available (0:0..15): UNSPECIFIED
    public static final int OFFSET_WORD      = 1; // word      (1:0..15): GlobalWord
    public static final int OFFSET_CODEBASE  = 2; // codebase  (2:0..31): LONG POINTER TO CodeSegment
    public static final int OFFSET_GLOBAL    = 4; // global    (4):       GlobalVariables

    public UNSPECIFIED available(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_AVAILABLE, memoryAccess);
    }
    public GlobalWord word(MemoryAccess memoryAccess) {
        return new GlobalWord(base + OFFSET_WORD, memoryAccess);
    }
    // FIXME
    // public GlobalOverhead#codebase codebase(MemoryAccess memoryAccess) {
    // return new GlobalOverhead#codebase(base + OFFSET_CODEBASE, memoryAccess);
    // }
    public BLOCK global() {
        return new BLOCK(base + OFFSET_GLOBAL);
    }
}

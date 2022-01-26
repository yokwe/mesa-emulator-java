package yokwe.majuro.type;

// NewGlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, global (2): GlobalVariables];
public final class NewGlobalOverhead extends MemoryBase {
    public static final String NAME      = "NewGlobalOverhead";
    public static final int    WORD_SIZE =                   2;
    public static final int    BIT_SIZE  =                  32;

    //
    // Constructor
    //
    public NewGlobalOverhead(int base) {
        super(base);
    }
    public NewGlobalOverhead(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Constants for field access
    //
    public static final int OFFSET_AVAILABLE = 0; // available (0:0..15): UNSPECIFIED
    public static final int OFFSET_WORD      = 1; // word      (1:0..15): GlobalWord
    public static final int OFFSET_GLOBAL    = 2; // global    (2):       GlobalVariables

    public UNSPECIFIED available(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_AVAILABLE, memoryAccess);
    }
    public GlobalWord word(MemoryAccess memoryAccess) {
        return new GlobalWord(base + OFFSET_WORD, memoryAccess);
    }
    public BLOCK global() {
        return new BLOCK(base + OFFSET_GLOBAL);
    }
}

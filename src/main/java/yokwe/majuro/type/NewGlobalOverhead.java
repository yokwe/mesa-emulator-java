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
    // global (2): GlobalVariables
    private static final int OFFSET_GLOBAL = 2;
    public UNSPECIFIED global(int index, MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_GLOBAL + (UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
}

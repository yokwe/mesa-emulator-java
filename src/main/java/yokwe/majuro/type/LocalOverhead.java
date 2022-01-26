package yokwe.majuro.type;

// LocalOverhead: TYPE = RECORD[word (0:0..15): LocalWord, returnlink (1:0..15): ShortControlLink, globallink (2:0..15): GFTHandle, pc (3:0..15): CARDINAL, local (4): LocalVariables];
public final class LocalOverhead extends MemoryBase {
    public static final String NAME      = "LocalOverhead";
    public static final int    WORD_SIZE =               4;
    public static final int    BIT_SIZE  =              64;

    //
    // Constructor
    //
    public LocalOverhead(int base) {
        super(base);
    }
    public LocalOverhead(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Access to Field of Record
    //
    // word (0:0..15): LocalWord
    private static final int OFFSET_WORD = 0;
    public LocalWord word(MemoryAccess memoryAccess) {
        return new LocalWord(base + OFFSET_WORD, memoryAccess);
    }
    // returnlink (1:0..15): ShortControlLink
    private static final int OFFSET_RETURNLINK = 1;
    public UNSPECIFIED returnlink(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_RETURNLINK, memoryAccess);
    }
    // globallink (2:0..15): GFTHandle
    private static final int OFFSET_GLOBALLINK = 2;
    public CARDINAL globallink(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_GLOBALLINK, memoryAccess);
    }
    // pc (3:0..15): CARDINAL
    private static final int OFFSET_PC = 3;
    public CARDINAL pc(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_PC, memoryAccess);
    }
    // local (4): LocalVariables
    private static final int OFFSET_LOCAL = 4;
    public UNSPECIFIED local(int index, MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_LOCAL + (UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
}

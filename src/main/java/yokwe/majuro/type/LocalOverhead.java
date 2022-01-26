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
    // Constants for field access
    //
    public static final int OFFSET_WORD       = 0; // word       (0:0..15): LocalWord
    public static final int OFFSET_RETURNLINK = 1; // returnlink (1:0..15): ShortControlLink
    public static final int OFFSET_GLOBALLINK = 2; // globallink (2:0..15): GFTHandle
    public static final int OFFSET_PC         = 3; // pc         (3:0..15): CARDINAL
    public static final int OFFSET_LOCAL      = 4; // local      (4):       LocalVariables

    public LocalWord word(MemoryAccess memoryAccess) {
        return new LocalWord(base + OFFSET_WORD, memoryAccess);
    }
    public UNSPECIFIED returnlink(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_RETURNLINK, memoryAccess);
    }
    public CARDINAL globallink(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_GLOBALLINK, memoryAccess);
    }
    public CARDINAL pc(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_PC, memoryAccess);
    }
    public BLOCK local() {
        return new BLOCK(base + OFFSET_LOCAL);
    }
}

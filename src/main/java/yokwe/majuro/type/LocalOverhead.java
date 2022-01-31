package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// LocalOverhead: TYPE = RECORD[word (0:0..15): LocalWord, returnlink (1:0..15): ShortControlLink, globallink (2:0..15): GFTHandle, pc (3:0..15): CARDINAL, local (4): LocalVariables];
public final class LocalOverhead extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final LocalOverhead longPointer(int base) {
        return new LocalOverhead(base);
    }
    public static final LocalOverhead pointer(char base) {
        return new LocalOverhead(Mesa.lengthenMDS(base));
    }
    
    private LocalOverhead(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // word (0:0..15): LocalWord
    private static final int OFFSET_WORD = 0;
    public LocalWord word(MemoryAccess access) {
        return LocalWord.longPointer(base + OFFSET_WORD, access);
    }
    // returnlink (1:0..15): ShortControlLink
    private static final int OFFSET_RETURNLINK = 1;
    public UNSPECIFIED returnlink(MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_RETURNLINK, access);
    }
    // globallink (2:0..15): GFTHandle
    private static final int OFFSET_GLOBALLINK = 2;
    public CARDINAL globallink(MemoryAccess access) {
        return CARDINAL.longPointer(base + OFFSET_GLOBALLINK, access);
    }
    // pc (3:0..15): CARDINAL
    private static final int OFFSET_PC = 3;
    public CARDINAL pc(MemoryAccess access) {
        return CARDINAL.longPointer(base + OFFSET_PC, access);
    }
    // local (4): LocalVariables
    private static final int OFFSET_LOCAL = 4;
    public UNSPECIFIED local(int index, MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_LOCAL + (UNSPECIFIED.WORD_SIZE * index), access);
    }
}

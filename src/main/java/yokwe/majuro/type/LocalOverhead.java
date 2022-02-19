package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// LocalOverhead: TYPE = RECORD[word (0:0..15): LocalWord, returnlink (1:0..15): ShortControlLink, globallink (2:0..15): GFTHandle, pc (3:0..15): CARDINAL, local (4): LocalVariables];
public final class LocalOverhead extends MemoryBase {
    public static final String NAME = "LocalOverhead";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final LocalOverhead longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new LocalOverhead(base, access);
    }
    public static final LocalOverhead pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new LocalOverhead(Memory.lengthenMDS(base), access);
    }
    
    private LocalOverhead(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Access to Field of Record
    //
    // word (0:0..15): LocalWord
    private static final int OFFSET_WORD = 0;
    public LocalWord word() {
        int longPointer = base + OFFSET_WORD;
        return LocalWord.longPointer(longPointer, access);
    }
    // returnlink (1:0..15): ShortControlLink
    private static final int OFFSET_RETURNLINK = 1;
    public UNSPECIFIED returnlink() {
        int longPointer = base + OFFSET_RETURNLINK;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // globallink (2:0..15): GFTHandle
    private static final int OFFSET_GLOBALLINK = 2;
    public CARDINAL globallink() {
        int longPointer = base + OFFSET_GLOBALLINK;
        return CARDINAL.longPointer(longPointer, access);
    }
    // pc (3:0..15): CARDINAL
    private static final int OFFSET_PC = 3;
    public CARDINAL pc() {
        int longPointer = base + OFFSET_PC;
        return CARDINAL.longPointer(longPointer, access);
    }
    // local (4): LocalVariables
    private static final int OFFSET_LOCAL = 4;
    public BLOCK local() {
        int longPointer = base + OFFSET_LOCAL;
        return BLOCK.longPointer(longPointer, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// GlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, codebase (2:0..31): LONG POINTER TO CodeSegment, global (4): GlobalVariables];
public final class GlobalOverhead extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final GlobalOverhead longPointer(int base) {
        return new GlobalOverhead(base);
    }
    public static final GlobalOverhead pointer(char base) {
        return new GlobalOverhead(Mesa.lengthenMDS(base));
    }
    
    private GlobalOverhead(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // available (0:0..15): UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 0;
    public UNSPECIFIED available(MemoryAccess access) {
        int longPointer = base + OFFSET_AVAILABLE;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // word (1:0..15): GlobalWord
    private static final int OFFSET_WORD = 1;
    public GlobalWord word(MemoryAccess access) {
        int longPointer = base + OFFSET_WORD;
        return GlobalWord.longPointer(longPointer, access);
    }
    // codebase (2:0..31): LONG POINTER TO CodeSegment
    private static final int OFFSET_CODEBASE = 2;
    public CodeSegment codebase() {
        int longPointer = Mesa.read32(base + OFFSET_CODEBASE);
        return CodeSegment.longPointer(longPointer);
    }
    // global (4): GlobalVariables
    private static final int OFFSET_GLOBAL = 4;
    public BLOCK global() {
        int longPointer = base + OFFSET_GLOBAL;
        return BLOCK.longPointer(longPointer);
    }
}

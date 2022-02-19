package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// NewGlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, global (2): GlobalVariables];
public final class NewGlobalOverhead extends MemoryBase {
    public static final String NAME = "NewGlobalOverhead";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final NewGlobalOverhead longPointer(@Mesa.POINTER int base) {
        return new NewGlobalOverhead(base);
    }
    public static final NewGlobalOverhead pointer(@Mesa.SHORT_POINTER int base) {
        return new NewGlobalOverhead(Memory.lengthenMDS(base));
    }
    
    private NewGlobalOverhead(@Mesa.POINTER int base) {
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
    // global (2): GlobalVariables
    private static final int OFFSET_GLOBAL = 2;
    public BLOCK global() {
        int longPointer = base + OFFSET_GLOBAL;
        return BLOCK.longPointer(longPointer);
    }
}

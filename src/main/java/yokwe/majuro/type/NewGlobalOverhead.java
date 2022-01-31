package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// NewGlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, global (2): GlobalVariables];
public final class NewGlobalOverhead extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final NewGlobalOverhead longPointer(int base) {
        return new NewGlobalOverhead(base);
    }
    public static final NewGlobalOverhead pointer(char base) {
        return new NewGlobalOverhead(Mesa.lengthenMDS(base));
    }
    
    private NewGlobalOverhead(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // available (0:0..15): UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 0;
    public UNSPECIFIED available(MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_AVAILABLE, access);
    }
    // word (1:0..15): GlobalWord
    private static final int OFFSET_WORD = 1;
    public GlobalWord word(MemoryAccess access) {
        return GlobalWord.longPointer(base + OFFSET_WORD, access);
    }
    // global (2): GlobalVariables
    private static final int OFFSET_GLOBAL = 2;
    public UNSPECIFIED global(int index, MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_GLOBAL + (UNSPECIFIED.WORD_SIZE * index), access);
    }
}

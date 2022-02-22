package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// GlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, codebase (2:0..31): LONG POINTER TO CodeSegment, global (4): GlobalVariables];
public final class GlobalOverhead extends MemoryBase {
    public static final String NAME = "GlobalOverhead";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final GlobalOverhead longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new GlobalOverhead(base, access);
    }
    public static final GlobalOverhead pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new GlobalOverhead(Memory.lengthenMDS(base), access);
    }
    
    private GlobalOverhead(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Access to Field of Record
    //
    // available (0:0..15): UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 0;
    public UNSPECIFIED available() {
        int longPointer = base + OFFSET_AVAILABLE;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // word (1:0..15): GlobalWord
    private static final int OFFSET_WORD = 1;
    public GlobalWord word() {
        int longPointer = base + OFFSET_WORD;
        return GlobalWord.longPointer(longPointer, access);
    }
    // codebase (2:0..31): LONG POINTER TO CodeSegment
    private static final int OFFSET_CODEBASE = 2;
    public CodeSegment codebase() {
        int longPointer = Memory.read32(base + OFFSET_CODEBASE);
        return CodeSegment.longPointer(longPointer, access);
    }
    public final @Mesa.LONG_POINTER int codebaseValue() {
        return Memory.read32(base + OFFSET_CODEBASE);
    }
    public final GlobalOverhead codebaseValue(@Mesa.LONG_POINTER int newValue) {
        Memory.write32(base + OFFSET_CODEBASE, newValue);
        return this;
    }
    // global (4): GlobalVariables
    private static final int OFFSET_GLOBAL = 4;
    public BLOCK global() {
        int longPointer = base + OFFSET_GLOBAL;
        return BLOCK.longPointer(longPointer, access);
    }
}

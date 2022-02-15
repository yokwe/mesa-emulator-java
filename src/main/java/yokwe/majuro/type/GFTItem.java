package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// GFTItem: TYPE = RECORD[globalFrame (0:0..31): GlobalFrameHandle, codebase (2:0..31): LONG POINTER TO CodeSegment];
public final class GFTItem extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final GFTItem longPointer(int base) {
        return new GFTItem(base);
    }
    public static final GFTItem pointer(char base) {
        return new GFTItem(Memory.instance.lengthenMDS(base));
    }
    
    private GFTItem(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // globalFrame (0:0..31): GlobalFrameHandle
    private static final int OFFSET_GLOBAL_FRAME = 0;
    public BLOCK globalFrame() {
        int longPointer = Memory.instance.read32(base + OFFSET_GLOBAL_FRAME);
        return BLOCK.longPointer(longPointer);
    }
    // codebase (2:0..31): LONG POINTER TO CodeSegment
    private static final int OFFSET_CODEBASE = 2;
    public CodeSegment codebase() {
        int longPointer = Memory.instance.read32(base + OFFSET_CODEBASE);
        return CodeSegment.longPointer(longPointer);
    }
}

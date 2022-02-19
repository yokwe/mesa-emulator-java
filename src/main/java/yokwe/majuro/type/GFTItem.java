package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// GFTItem: TYPE = RECORD[globalFrame (0:0..31): GlobalFrameHandle, codebase (2:0..31): LONG POINTER TO CodeSegment];
public final class GFTItem extends MemoryBase {
    public static final String NAME = "GFTItem";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final GFTItem longPointer(@Mesa.POINTER int base) {
        return new GFTItem(base);
    }
    public static final GFTItem pointer(@Mesa.SHORT_POINTER int base) {
        return new GFTItem(Memory.lengthenMDS(base));
    }
    
    private GFTItem(@Mesa.POINTER int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // globalFrame (0:0..31): GlobalFrameHandle
    private static final int OFFSET_GLOBAL_FRAME = 0;
    public BLOCK globalFrame() {
        int longPointer = Memory.read32(base + OFFSET_GLOBAL_FRAME);
        return BLOCK.longPointer(longPointer);
    }
    // codebase (2:0..31): LONG POINTER TO CodeSegment
    private static final int OFFSET_CODEBASE = 2;
    public CodeSegment codebase() {
        int longPointer = Memory.read32(base + OFFSET_CODEBASE);
        return CodeSegment.longPointer(longPointer);
    }
}

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
    public static final GFTItem longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new GFTItem(base, access);
    }
    public static final GFTItem pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new GFTItem(Memory.lengthenMDS(base), access);
    }
    
    private GFTItem(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Access to Field of Record
    //
    // globalFrame (0:0..31): GlobalFrameHandle
    private static final int OFFSET_GLOBAL_FRAME = 0;
    public BLOCK globalFrame() {
        int longPointer = Memory.read32(base + OFFSET_GLOBAL_FRAME);
        return BLOCK.longPointer(longPointer, access);
    }
    public final @Mesa.LONG_POINTER int globalFrameValue() {
        return Memory.read32(base + OFFSET_GLOBAL_FRAME);
    }
    public final GFTItem globalFrameValue(@Mesa.LONG_POINTER int newValue) {
        Memory.write32(base + OFFSET_GLOBAL_FRAME, newValue);
        return this;
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
    public final GFTItem codebaseValue(@Mesa.LONG_POINTER int newValue) {
        Memory.write32(base + OFFSET_CODEBASE, newValue);
        return this;
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// StateVector: TYPE = RECORD[stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED, word (14:0..15): StateWord, frame (15:0..15): LocalFrameHandle, data (16): BLOCK];
public final class StateVector extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  16;
    public static final int BIT_SIZE  = 256;
    
    //
    // Constructor
    //
    public static final StateVector longPointer(int base) {
        return new StateVector(base);
    }
    public static final StateVector pointer(char base) {
        return new StateVector(Mesa.lengthenMDS(base));
    }
    
    private StateVector(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED
    private static final int OFFSET_STACK = 0;
    public UNSPECIFIED stack(int index, MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_STACK + (UNSPECIFIED.WORD_SIZE * index), access);
    }
    // word (14:0..15): StateWord
    private static final int OFFSET_WORD = 14;
    public StateWord word(MemoryAccess access) {
        return StateWord.longPointer(base + OFFSET_WORD, access);
    }
    // frame (15:0..15): LocalFrameHandle
    private static final int OFFSET_FRAME = 15;
    public BLOCK frame() {
        return BLOCK.pointer(Mesa.read16(base + OFFSET_FRAME));
    }
    // data (16): BLOCK
    private static final int OFFSET_DATA = 16;
    public UNSPECIFIED data(int index, MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_DATA + (UNSPECIFIED.WORD_SIZE * index), access);
    }
}

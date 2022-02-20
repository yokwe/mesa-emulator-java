package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// StateVector: TYPE = RECORD[stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED, word (14:0..15): StateWord, frame (15:0..15): LocalFrameHandle, data (16): BLOCK];
public final class StateVector extends MemoryBase {
    public static final String NAME = "StateVector";
    
    public static final int WORD_SIZE =  16;
    public static final int BIT_SIZE  = 256;
    
    //
    // Constructor
    //
    public static final StateVector longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new StateVector(base, access);
    }
    public static final StateVector pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new StateVector(Memory.lengthenMDS(base), access);
    }
    
    private StateVector(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Access to Field of Record
    //
    // stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED
    private static final int OFFSET_STACK = 0;
    private static final class StackIndex {
        private static final ContextSubrange context = new ContextSubrange("StateVector", 0, 13);
        private static final void checkValue(int value) {
            context.check(value);
        }
    }
    public final UNSPECIFIED stack(int index) {
        if (Debug.ENABLE_CHECK_VALUE) StackIndex.checkValue(index);
        int longPointer = base + OFFSET_STACK + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // word (14:0..15): StateWord
    private static final int OFFSET_WORD = 14;
    public StateWord word() {
        int longPointer = base + OFFSET_WORD;
        return StateWord.longPointer(longPointer, access);
    }
    // frame (15:0..15): LocalFrameHandle
    private static final int OFFSET_FRAME = 15;
    public BLOCK frame() {
        int pointer = Memory.read16(base + OFFSET_FRAME);
        return BLOCK.pointer(pointer, access);
    }
    public final @Mesa.SHORT_POINTER int frameValue() {
        return Memory.read16(base + OFFSET_FRAME);
    }
    public final void frameValue(@Mesa.SHORT_POINTER int newValue) {
        Memory.write16(base + OFFSET_FRAME, newValue);
    }
    // data (16): BLOCK
    private static final int OFFSET_DATA = 16;
    public BLOCK data() {
        int longPointer = base + OFFSET_DATA;
        return BLOCK.longPointer(longPointer, access);
    }
}

package yokwe.majuro.type;

// StateVector: TYPE = RECORD[stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED, word (14:0..15): StateWord, frame (15:0..15): LocalFrameHandle, data (16): BLOCK];
public final class StateVector extends MemoryBase {
    public static final String NAME      = "StateVector";
    public static final int    WORD_SIZE =            16;
    public static final int    BIT_SIZE  =           256;

    //
    // Constructor
    //
    public StateVector(int base) {
        super(base);
    }
    public StateVector(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Constants for field access
    //
    public static final int OFFSET_STACK =  0; // stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED
    public static final int OFFSET_WORD  = 14; // word  (14:0..15): StateWord
    public static final int OFFSET_FRAME = 15; // frame (15:0..15): LocalFrameHandle
    public static final int OFFSET_DATA  = 16; // data  (16):       BLOCK

    // FIXME
    // public StateVector#stack stack() {
    // return new StateVector#stack(base + OFFSET_STACK);
    // }
    public StateWord word(MemoryAccess memoryAccess) {
        return new StateWord(base + OFFSET_WORD, memoryAccess);
    }
    public LocalFrameHandle frame(MemoryAccess memoryAccess) {
        return new LocalFrameHandle(base + OFFSET_FRAME, memoryAccess);
    }
    public BLOCK data() {
        return new BLOCK(base + OFFSET_DATA);
    }
}

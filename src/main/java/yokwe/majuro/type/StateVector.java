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
    // Access to Field of Record
    //
    // stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED
    private static final int OFFSET_STACK = 0;
    public UNSPECIFIED stack(int index, MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_STACK + (UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
    // word (14:0..15): StateWord
    private static final int OFFSET_WORD = 14;
    public StateWord word(MemoryAccess memoryAccess) {
        return new StateWord(base + OFFSET_WORD, memoryAccess);
    }
    // frame (15:0..15): LocalFrameHandle
    private static final int OFFSET_FRAME = 15;
    public BLOCK frame() {
        return new BLOCK(base + OFFSET_FRAME);
    }
    // data (16): BLOCK
    private static final int OFFSET_DATA = 16;
    public UNSPECIFIED data(int index, MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_DATA + (UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
}

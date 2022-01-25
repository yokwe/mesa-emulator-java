package yokwe.majuro.type;

// StateVector: TYPE = RECORD[stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED, word (14:0..15): StateWord, frame (15:0..15): LocalFrameHandle, data (16): BLOCK];
public final class StateVector {
    public static final String NAME     = "StateVector";
    public static final int    SIZE     =            16;
    public static final int    BIT_SIZE =           256;

    public final int base;

    public StateVector(int value) {
        this.base = value;
    }

    public static final int OFFSET_STACK =  0; // stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED
    public static final int OFFSET_WORD  = 14; // word  (14:0..15): StateWord
    public static final int OFFSET_FRAME = 15; // frame (15:0..15): LocalFrameHandle
    public static final int OFFSET_DATA  = 16; // data  (16):       BLOCK

}

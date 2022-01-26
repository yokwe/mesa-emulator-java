package yokwe.majuro.type;

// FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition];
public final class FaultQueue {
    public static final String NAME      = "FaultQueue";
    public static final int    WORD_SIZE =            2;
    public static final int    BIT_SIZE  =           32;

    //
    // Constants for field access
    //
    public static final int OFFSET_QUEUE     = 0; // queue     (0:0..15): Queue
    public static final int OFFSET_CONDITION = 1; // condition (1:0..15): Condition

    //
    // Constructor
    //
    public final int base;

    public FaultQueue(int base) {
        this.base = base;
    }
    public FaultQueue(int base, int index) {
        this.base = base + (WORD_SIZE * index);
    }

}
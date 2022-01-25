package yokwe.majuro.type;

// FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition];
public final class FaultQueue {
    public static final String NAME     = "FaultQueue";
    public static final int    SIZE     =            2;
    public static final int    BIT_SIZE =           32;

    public final int base;

    public FaultQueue(int value) {
        this.base = value;
    }

    public static final int OFFSET_QUEUE     = 0; // queue     (0:0..15): Queue
    public static final int OFFSET_CONDITION = 1; // condition (1:0..15): Condition

}

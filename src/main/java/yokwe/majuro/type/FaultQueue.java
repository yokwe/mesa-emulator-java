package yokwe.majuro.type;

// FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition];
public final class FaultQueue extends MemoryBase {
    public static final String NAME      = "FaultQueue";
    public static final int    WORD_SIZE =            2;
    public static final int    BIT_SIZE  =           32;

    //
    // Constructor
    //
    public FaultQueue(int base) {
        super(base);
    }
    public FaultQueue(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Constants for field access
    //
    public static final int OFFSET_QUEUE     = 0; // queue     (0:0..15): Queue
    public static final int OFFSET_CONDITION = 1; // condition (1:0..15): Condition

    public Queue queue(MemoryAccess memoryAccess) {
        return new Queue(base + OFFSET_QUEUE, memoryAccess);
    }
    public Condition condition(MemoryAccess memoryAccess) {
        return new Condition(base + OFFSET_CONDITION, memoryAccess);
    }
}

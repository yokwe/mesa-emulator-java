package yokwe.majuro.type;

// FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition];
public class FaultQueue extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();

    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;

    //
    // Constructor
    //
    public FaultQueue(int base) {
        super(base);
    }

    //
    // Access to Field of Record
    //
    // queue (0:0..15): Queue
    private static final int OFFSET_QUEUE = 0;
    public Queue queue(MemoryAccess memoryAccess) {
        return new Queue(base + OFFSET_QUEUE, memoryAccess);
    }
    // condition (1:0..15): Condition
    private static final int OFFSET_CONDITION = 1;
    public Condition condition(MemoryAccess memoryAccess) {
        return new Condition(base + OFFSET_CONDITION, memoryAccess);
    }
}

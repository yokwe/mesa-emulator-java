package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition];
public final class FaultQueue extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final FaultQueue longPointer(int base) {
        return new FaultQueue(base);
    }
    public static final FaultQueue pointer(char base) {
        return new FaultQueue(Memory.instance.lengthenMDS(base));
    }
    
    private FaultQueue(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // queue (0:0..15): Queue
    private static final int OFFSET_QUEUE = 0;
    public Queue queue(MemoryAccess access) {
        int longPointer = base + OFFSET_QUEUE;
        return Queue.longPointer(longPointer, access);
    }
    // condition (1:0..15): Condition
    private static final int OFFSET_CONDITION = 1;
    public Condition condition(MemoryAccess access) {
        int longPointer = base + OFFSET_CONDITION;
        return Condition.longPointer(longPointer, access);
    }
}

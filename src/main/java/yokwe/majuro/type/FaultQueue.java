package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition];
public final class FaultQueue extends MemoryBase {
    public static final String NAME = "FaultQueue";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final FaultQueue longPointer(@Mesa.LONG_POINTER int base) {
        return new FaultQueue(base);
    }
    public static final FaultQueue pointer(@Mesa.SHORT_POINTER int base) {
        return new FaultQueue(Memory.lengthenMDS(base));
    }
    
    private FaultQueue(@Mesa.LONG_POINTER int base) {
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

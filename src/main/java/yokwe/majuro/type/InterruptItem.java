package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// InterruptItem: TYPE = RECORD[condition (0:0..15): Condition, available (1:0..15): UNSPECIFIED];
public final class InterruptItem extends MemoryBase {
    public static final String NAME = "InterruptItem";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final InterruptItem longPointer(@Mesa.LONG_POINTER int base) {
        return new InterruptItem(base);
    }
    public static final InterruptItem pointer(@Mesa.SHORT_POINTER int base) {
        return new InterruptItem(Memory.lengthenMDS(base));
    }
    
    private InterruptItem(@Mesa.LONG_POINTER int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // condition (0:0..15): Condition
    private static final int OFFSET_CONDITION = 0;
    public Condition condition(MemoryAccess access) {
        int longPointer = base + OFFSET_CONDITION;
        return Condition.longPointer(longPointer, access);
    }
    // available (1:0..15): UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 1;
    public UNSPECIFIED available(MemoryAccess access) {
        int longPointer = base + OFFSET_AVAILABLE;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

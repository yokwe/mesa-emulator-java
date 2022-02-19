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
    public static final InterruptItem longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new InterruptItem(base, access);
    }
    public static final InterruptItem pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new InterruptItem(Memory.lengthenMDS(base), access);
    }
    
    private InterruptItem(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Access to Field of Record
    //
    // condition (0:0..15): Condition
    private static final int OFFSET_CONDITION = 0;
    public Condition condition() {
        int longPointer = base + OFFSET_CONDITION;
        return Condition.longPointer(longPointer, access);
    }
    // available (1:0..15): UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 1;
    public UNSPECIFIED available() {
        int longPointer = base + OFFSET_AVAILABLE;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

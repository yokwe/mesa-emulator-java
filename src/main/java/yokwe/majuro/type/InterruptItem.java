package yokwe.majuro.type;

// InterruptItem: TYPE = RECORD[condition (0:0..15): Condition, available (1:0..15): UNSPECIFIED];
public final class InterruptItem extends MemoryBase {
    public static final String NAME      = "InterruptItem";
    public static final int    WORD_SIZE =               2;
    public static final int    BIT_SIZE  =              32;

    //
    // Constructor
    //
    public InterruptItem(int base) {
        super(base);
    }
    public InterruptItem(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Constants for field access
    //
    public static final int OFFSET_CONDITION = 0; // condition (0:0..15): Condition
    public static final int OFFSET_AVAILABLE = 1; // available (1:0..15): UNSPECIFIED

    public Condition condition(MemoryAccess memoryAccess) {
        return new Condition(base + OFFSET_CONDITION, memoryAccess);
    }
    public UNSPECIFIED available(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_AVAILABLE, memoryAccess);
    }
}

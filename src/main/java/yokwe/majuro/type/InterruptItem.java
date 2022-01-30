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

    //
    // Access to Field of Record
    //
    // condition (0:0..15): Condition
    private static final int OFFSET_CONDITION = 0;
    public Condition condition(MemoryAccess memoryAccess) {
        return new Condition(base + OFFSET_CONDITION, memoryAccess);
    }
    // available (1:0..15): UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 1;
    public UNSPECIFIED available(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_AVAILABLE, memoryAccess);
    }
}

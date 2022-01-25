package yokwe.majuro.type;

// InterruptItem: TYPE = RECORD[condition (0:0..15): Condition, available (1:0..15): UNSPECIFIED];
public final class InterruptItem {
    public static final String NAME      = "InterruptItem";
    public static final int    WORD_SIZE =               2;
    public static final int    BIT_SIZE  =              32;

    //
    // Constants for field access
    //
    public static final int OFFSET_CONDITION = 0; // condition (0:0..15): Condition
    public static final int OFFSET_AVAILABLE = 1; // available (1:0..15): UNSPECIFIED

    //
    // Constructor
    //
    public final int base;

    public InterruptItem(int base) {
        this.base = base;
    }
    public InterruptItem(int base, int index) {
        this.base = base + (WORD_SIZE * index);
    }

}

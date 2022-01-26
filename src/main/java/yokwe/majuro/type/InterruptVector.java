package yokwe.majuro.type;

// InterruptVector: TYPE = ARRAY InterruptLevel OF InterruptItem;
public final class InterruptVector extends MemoryBase {
    public static final String NAME      = "InterruptVector";
    public static final int    WORD_SIZE =                32;
    public static final int    BIT_SIZE  =               512;

    public static final int INDEX_MIN_VALUE   = (int)InterruptLevel.MIN_VALUE;
    public static final int INDEX_MAX_VALUE   = (int)InterruptLevel.MAX_VALUE;
    public static final int ELEMENT_WORD_SIZE =       InterruptItem.WORD_SIZE;

    public static final ContextSubrange context = new ContextSubrange("InterruptVector#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public InterruptVector(int base) {
        super(base);
    }

    //
    // Access to Element of Array
    //
    public InterruptItem element(int index) {
        return new InterruptItem(base + (ELEMENT_WORD_SIZE * index));
    }
}

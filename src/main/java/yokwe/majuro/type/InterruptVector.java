package yokwe.majuro.type;

// InterruptVector: TYPE = ARRAY InterruptLevel OF InterruptItem;
public final class InterruptVector extends MemoryBase {
    public static final String NAME      = "InterruptVector";
    public static final int    WORD_SIZE =                32;
    public static final int    BIT_SIZE  =               512;

    public static final int INDEX_MIN_VALUE   =  0;
    public static final int INDEX_MAX_VALUE   = 15;
    public static final int ELEMENT_WORD_SIZE =  2;

    public static final ContextSubrange context = new ContextSubrange("InterruptVector#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public InterruptVector(int base) {
        super(base);
    }
    public InterruptVector(int base, int index) {
        super(base + (ELEMENT_WORD_SIZE * index));
    }

}

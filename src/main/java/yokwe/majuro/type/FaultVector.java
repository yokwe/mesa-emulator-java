package yokwe.majuro.type;

// FaultVector: TYPE = ARRAY FaultIndex OF FaultQueue;
public final class FaultVector extends MemoryBase {
    public static final String NAME      = "FaultVector";
    public static final int    WORD_SIZE =            16;
    public static final int    BIT_SIZE  =           256;

    public static final int INDEX_MIN_VALUE   = (int)FaultIndex.MIN_VALUE;
    public static final int INDEX_MAX_VALUE   = (int)FaultIndex.MAX_VALUE;
    public static final int ELEMENT_WORD_SIZE =      FaultQueue.WORD_SIZE;

    public static final ContextSubrange context = new ContextSubrange("FaultVector#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public FaultVector(int base) {
        super(base);
    }

    //
    // Access to Element of Array
    //
    public FaultQueue element(int index) {
        return new FaultQueue(base + (ELEMENT_WORD_SIZE * index));
    }
}

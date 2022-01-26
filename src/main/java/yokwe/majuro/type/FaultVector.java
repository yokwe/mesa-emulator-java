package yokwe.majuro.type;

// FaultVector: TYPE = ARRAY FaultIndex OF FaultQueue;
public final class FaultVector extends MemoryBase {
    public static final String NAME      = "FaultVector";
    public static final int    WORD_SIZE =            16;
    public static final int    BIT_SIZE  =           256;

    public static final int INDEX_MIN_VALUE   = 0;
    public static final int INDEX_MAX_VALUE   = 7;
    public static final int ELEMENT_WORD_SIZE = 2;

    public static final ContextSubrange context = new ContextSubrange("FaultVector#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public FaultVector(int base) {
        super(base);
    }
    public FaultVector(int base, int index) {
        super(base + (ELEMENT_WORD_SIZE * index));
    }

}

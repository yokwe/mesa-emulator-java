package yokwe.majuro.type;

// BLOCK: TYPE = ARRAY [0..0) OF UNSPECIFIED;
public final class BLOCK extends MemoryBase {
    public static final String NAME      = "BLOCK";
    public static final int    WORD_SIZE =       0;
    public static final int    BIT_SIZE  =       0;

    public static final int INDEX_MIN_VALUE   =  (int)INTEGER.MIN_VALUE;
    public static final int INDEX_MAX_VALUE   = (int)CARDINAL.MAX_VALUE;
    public static final int ELEMENT_WORD_SIZE =   UNSPECIFIED.WORD_SIZE;

    public static final ContextSubrange context = new ContextSubrange("BLOCK#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public BLOCK(int base) {
        super(base);
    }

    //
    // Access to Element of Array
    //
    public UNSPECIFIED element(int index, MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + (ELEMENT_WORD_SIZE * index), memoryAccess);
    }
}

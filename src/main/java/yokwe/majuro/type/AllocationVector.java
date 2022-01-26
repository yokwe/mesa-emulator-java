package yokwe.majuro.type;

// AllocationVector: TYPE = ARRAY FSIndex OF AVItem;
public final class AllocationVector extends MemoryBase {
    public static final String NAME      = "AllocationVector";
    public static final int    WORD_SIZE =                256;
    public static final int    BIT_SIZE  =               4096;

    public static final int INDEX_MIN_VALUE   =   0;
    public static final int INDEX_MAX_VALUE   = 255;
    public static final int ELEMENT_WORD_SIZE =   1;

    public static final ContextSubrange context = new ContextSubrange("AllocationVector#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public AllocationVector(int base) {
        super(base);
    }
    public AllocationVector(int base, int index) {
        super(base + (ELEMENT_WORD_SIZE * index));
    }

}

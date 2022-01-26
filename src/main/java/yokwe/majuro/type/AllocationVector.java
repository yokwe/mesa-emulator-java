package yokwe.majuro.type;

// AllocationVector: TYPE = ARRAY FSIndex OF AVItem;
public final class AllocationVector extends MemoryBase {
    public static final String NAME      = "AllocationVector";
    public static final int    WORD_SIZE =                256;
    public static final int    BIT_SIZE  =               4096;

    public static final int INDEX_MIN_VALUE   = (int)FSIndex.MIN_VALUE;
    public static final int INDEX_MAX_VALUE   = (int)FSIndex.MAX_VALUE;
    public static final int ELEMENT_WORD_SIZE =       AVItem.WORD_SIZE;

    public static final ContextSubrange context = new ContextSubrange("AllocationVector#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public AllocationVector(int base) {
        super(base);
    }

    //
    // Access to Element of Array
    //
    public AVItem element(int index, MemoryAccess memoryAccess) {
        return new AVItem(base + (ELEMENT_WORD_SIZE * index), memoryAccess);
    }
}

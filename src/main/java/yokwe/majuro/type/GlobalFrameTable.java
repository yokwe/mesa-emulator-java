package yokwe.majuro.type;

// GlobalFrameTable: TYPE = ARRAY GFTIndex OF GFTItem;
public final class GlobalFrameTable extends MemoryBase {
    public static final String NAME      = "GlobalFrameTable";
    public static final int    WORD_SIZE =              65536;
    public static final int    BIT_SIZE  =            1048576;

    public static final int INDEX_MIN_VALUE   = (int)GFTIndex.MIN_VALUE;
    public static final int INDEX_MAX_VALUE   = (int)GFTIndex.MAX_VALUE;
    public static final int ELEMENT_WORD_SIZE =       GFTItem.WORD_SIZE;

    public static final ContextSubrange context = new ContextSubrange("GlobalFrameTable#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public GlobalFrameTable(int base) {
        super(base);
    }

    //
    // Access to Element of Array
    //
    public GFTItem element(int index) {
        return new GFTItem(base + (ELEMENT_WORD_SIZE * index));
    }
}

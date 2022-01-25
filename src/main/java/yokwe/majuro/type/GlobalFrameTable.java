package yokwe.majuro.type;

// GlobalFrameTable: TYPE = ARRAY GFTIndex OF GFTItem;
public final class GlobalFrameTable {
    public static final String NAME      = "GlobalFrameTable";
    public static final int    WORD_SIZE =              65536;
    public static final int    BIT_SIZE  =            1048576;

    public static final int INDEX_MIN_VALUE   =     0;
    public static final int INDEX_MAX_VALUE   = 16383;
    public static final int ELEMENT_WORD_SIZE =     4;

    public static final ContextSubrange context = new ContextSubrange("GlobalFrameTable#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
}

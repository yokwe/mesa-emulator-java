package yokwe.majuro.type;

// SystemData: TYPE = ARRAY SDIndex OF ControlLink;
public final class SystemData extends MemoryBase {
    public static final String NAME      = "SystemData";
    public static final int    WORD_SIZE =          512;
    public static final int    BIT_SIZE  =         8192;

    public static final int INDEX_MIN_VALUE   =     (int)SDIndex.MIN_VALUE;
    public static final int INDEX_MAX_VALUE   =     (int)SDIndex.MAX_VALUE;
    public static final int ELEMENT_WORD_SIZE = LONG_UNSPECIFIED.WORD_SIZE;

    public static final ContextSubrange context = new ContextSubrange("SystemData#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public SystemData(int base) {
        super(base);
    }

    //
    // Access to Element of Array
    //
    public LONG_UNSPECIFIED element(int index, MemoryAccess memoryAccess) {
        return new LONG_UNSPECIFIED(base + (ELEMENT_WORD_SIZE * index), memoryAccess);
    }
}

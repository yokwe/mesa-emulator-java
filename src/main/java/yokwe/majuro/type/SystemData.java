package yokwe.majuro.type;

// SystemData: TYPE = ARRAY SDIndex OF ControlLink;
public final class SystemData extends MemoryBase {
    public static final String NAME      = "SystemData";
    public static final int    WORD_SIZE =          512;
    public static final int    BIT_SIZE  =         8192;

    public static final int INDEX_MIN_VALUE   =   0;
    public static final int INDEX_MAX_VALUE   = 255;
    public static final int ELEMENT_WORD_SIZE =   2;

    public static final ContextSubrange context = new ContextSubrange("SystemData#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public SystemData(int base) {
        super(base);
    }
    public SystemData(int base, int index) {
        super(base + (ELEMENT_WORD_SIZE * index));
    }

}

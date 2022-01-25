package yokwe.majuro.type;

// EscTrapTable: TYPE = ARRAY BYTE OF ControlLink;
public final class EscTrapTable {
    public static final String NAME      = "EscTrapTable";
    public static final int    WORD_SIZE =            512;
    public static final int    BIT_SIZE  =           8192;

    public static final int INDEX_MIN_VALUE   =   0;
    public static final int INDEX_MAX_VALUE   = 255;
    public static final int ELEMENT_WORD_SIZE =   2;

    public static final ContextSubrange context = new ContextSubrange("EscTrapTable#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
}

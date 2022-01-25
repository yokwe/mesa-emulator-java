package yokwe.majuro.type;

// BLOCK: TYPE = ARRAY [0..0) OF UNSPECIFIED;
public final class BLOCK {
    public static final String NAME      = "BLOCK";
    public static final int    WORD_SIZE =       0;
    public static final int    BIT_SIZE  =       0;

    public static final int INDEX_MIN_VALUE   =  0;
    public static final int INDEX_MAX_VALUE   = -1;
    public static final int ELEMENT_WORD_SIZE =  1;

    public static final ContextSubrange context = new ContextSubrange("BLOCK#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
}

package yokwe.majuro.type;

// BLOCK: TYPE = ARRAY [0..0) OF UNSPECIFIED;
public final class BLOCK extends MemoryBase {
    public static final String NAME      = "BLOCK";
    public static final int    WORD_SIZE =       0;
    public static final int    BIT_SIZE  =       0;

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
        return new UNSPECIFIED(base + (UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
}

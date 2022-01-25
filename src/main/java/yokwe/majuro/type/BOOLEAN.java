package yokwe.majuro.type;

// BOOLEAN: TYPE = BOOLEAN;
public final class BOOLEAN extends MemoryData16 {
    public static final String NAME      = "BOOLEAN";
    public static final int    WORD_SIZE =         1;
    public static final int    BIT_SIZE  =         1;

    //
    // Constructor
    //
    public BOOLEAN(char value) {
        super(value);
    }
    public BOOLEAN(int base, MemoryAccess access) {
        super(base, access);
    }
    public BOOLEAN(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }
}

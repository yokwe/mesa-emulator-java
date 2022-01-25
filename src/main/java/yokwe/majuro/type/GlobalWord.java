package yokwe.majuro.type;

// GlobalWord: TYPE = RECORD[gfi (0:0..13): GFTIndex, trapxfers (0:14..14): BOOLEAN, codelinks (0:15..15): BOOLEAN];
public final class GlobalWord extends MemoryData16 {
    public static final String NAME     = "GlobalWord";
    public static final int    SIZE     =            1;
    public static final int    BIT_SIZE =           16;

    //
    // Constructor
    //
    public GlobalWord(char value) {
        super(value);
    }
    public GlobalWord(int base, MemoryAccess access) {
        super(base, access);
    }

    //
    // Bit Field
    //

    // gfi       (0:0..13):  GFTIndex
    // trapxfers (0:14..14): BOOLEAN
    // codelinks (0:15..15): BOOLEAN

    private static final int GFI_MASK        = 0b1111_1111_1111_1100;
    private static final int GFI_SHIFT       =                     2;
    private static final int TRAPXFERS_MASK  = 0b0000_0000_0000_0010;
    private static final int TRAPXFERS_SHIFT =                     1;
    private static final int CODELINKS_MASK  = 0b0000_0000_0000_0001;
    private static final int CODELINKS_SHIFT =                     0;

    //
    // Bit Field Access Methods
    //
    public int gfi() {
        return (value & GFI_MASK) >> GFI_SHIFT;
    }
    public void gfi(int newValue) {
        value = (value & ~GFI_MASK) | ((newValue << GFI_SHIFT) & GFI_MASK);
    }

    public int trapxfers() {
        return (value & TRAPXFERS_MASK) >> TRAPXFERS_SHIFT;
    }
    public void trapxfers(int newValue) {
        value = (value & ~TRAPXFERS_MASK) | ((newValue << TRAPXFERS_SHIFT) & TRAPXFERS_MASK);
    }

    public int codelinks() {
        return (value & CODELINKS_MASK) >> CODELINKS_SHIFT;
    }
    public void codelinks(int newValue) {
        value = (value & ~CODELINKS_MASK) | ((newValue << CODELINKS_SHIFT) & CODELINKS_MASK);
    }

}

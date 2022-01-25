package yokwe.majuro.type;

// GrayParm: TYPE = RECORD[reserved (0:0..3): NIBBLE, yOffset (0:4..7): NIBBLE, widthMinusOne (0:8..11): NIBBLE, heightMinusOne (0:12..15): NIBBLE];
public final class GrayParm extends MemoryData16 {
    public static final String NAME      = "GrayParm";
    public static final int    WORD_SIZE =          1;
    public static final int    BIT_SIZE  =         16;

    //
    // Constructor
    //
    public GrayParm(char value) {
        super(value);
    }
    public GrayParm(int base, MemoryAccess access) {
        super(base, access);
    }
    public GrayParm(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }

    //
    // Bit Field
    //

    // reserved       (0:0..3):   NIBBLE
    // yOffset        (0:4..7):   NIBBLE
    // widthMinusOne  (0:8..11):  NIBBLE
    // heightMinusOne (0:12..15): NIBBLE

    private static final int RESERVED_MASK          = 0b1111_0000_0000_0000;
    private static final int RESERVED_SHIFT         =                    12;
    private static final int Y_OFFSET_MASK          = 0b0000_1111_0000_0000;
    private static final int Y_OFFSET_SHIFT         =                     8;
    private static final int WIDTH_MINUS_ONE_MASK   = 0b0000_0000_1111_0000;
    private static final int WIDTH_MINUS_ONE_SHIFT  =                     4;
    private static final int HEIGHT_MINUS_ONE_MASK  = 0b0000_0000_0000_1111;
    private static final int HEIGHT_MINUS_ONE_SHIFT =                     0;

    //
    // Bit Field Access Methods
    //
    public int reserved() {
        return (value & RESERVED_MASK) >> RESERVED_SHIFT;
    }
    public void reserved(int newValue) {
        value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
    }

    public int yOffset() {
        return (value & Y_OFFSET_MASK) >> Y_OFFSET_SHIFT;
    }
    public void yOffset(int newValue) {
        value = (value & ~Y_OFFSET_MASK) | ((newValue << Y_OFFSET_SHIFT) & Y_OFFSET_MASK);
    }

    public int widthMinusOne() {
        return (value & WIDTH_MINUS_ONE_MASK) >> WIDTH_MINUS_ONE_SHIFT;
    }
    public void widthMinusOne(int newValue) {
        value = (value & ~WIDTH_MINUS_ONE_MASK) | ((newValue << WIDTH_MINUS_ONE_SHIFT) & WIDTH_MINUS_ONE_MASK);
    }

    public int heightMinusOne() {
        return (value & HEIGHT_MINUS_ONE_MASK) >> HEIGHT_MINUS_ONE_SHIFT;
    }
    public void heightMinusOne(int newValue) {
        value = (value & ~HEIGHT_MINUS_ONE_MASK) | ((newValue << HEIGHT_MINUS_ONE_SHIFT) & HEIGHT_MINUS_ONE_MASK);
    }

}

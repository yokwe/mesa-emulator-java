package yokwe.majuro.type;

// TaggedControlLink: TYPE = RECORD32[data (0:0..13): UNSPECIFIED, tag (0:14..15): LinkType, fill (1:0..15): UNSPECIFIED];
public final class TaggedControlLink extends MemoryData32 {
    public static final String NAME      = "TaggedControlLink";
    public static final int    WORD_SIZE =                   2;
    public static final int    BIT_SIZE  =                  32;

    //
    // Constructor
    //
    public TaggedControlLink(int value) {
        super(value);
    }
    public TaggedControlLink(int base, MemoryAccess access) {
        super(base, access);
    }
    public TaggedControlLink(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }

    //
    // Bit Field
    //

    // data (0:0..13):  UNSPECIFIED
    // tag  (0:14..15): LinkType
    // fill (1:0..15):  UNSPECIFIED

    private static final int DATA_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1100;
    private static final int DATA_SHIFT =                                         2;
    private static final int TAG_MASK   = 0b0000_0000_0000_0000_0000_0000_0000_0011;
    private static final int TAG_SHIFT  =                                         0;
    private static final int FILL_MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    private static final int FILL_SHIFT =                                        16;

    //
    // Bit Field Access Methods
    //
    public int data() {
        return (value & DATA_MASK) >> DATA_SHIFT;
    }
    public void data(int newValue) {
        value = (value & ~DATA_MASK) | ((newValue << DATA_SHIFT) & DATA_MASK);
    }

    public int tag() {
        return (value & TAG_MASK) >> TAG_SHIFT;
    }
    public void tag(int newValue) {
        value = (value & ~TAG_MASK) | ((newValue << TAG_SHIFT) & TAG_MASK);
    }

    public int fill() {
        return (value & FILL_MASK) >> FILL_SHIFT;
    }
    public void fill(int newValue) {
        value = (value & ~FILL_MASK) | ((newValue << FILL_SHIFT) & FILL_MASK);
    }

}

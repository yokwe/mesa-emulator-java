package yokwe.majuro.type;

// NibblePair: TYPE = RECORD[left (0:0..3): NIBBLE, right (0:4..7): NIBBLE];
public final class NibblePair extends MemoryData16 {
    public static final String NAME      = "NibblePair";
    public static final int    WORD_SIZE =            1;
    public static final int    BIT_SIZE  =            8;

    //
    // Constructor
    //
    public NibblePair(char value) {
        super(value);
    }
    public NibblePair(int base, MemoryAccess access) {
        super(base, access);
    }
    public NibblePair(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }

    //
    // Bit Field
    //

    // left  (0:0..3): NIBBLE
    // right (0:4..7): NIBBLE

    private static final int LEFT_MASK   = 0b1111_0000;
    private static final int LEFT_SHIFT  =           4;
    private static final int RIGHT_MASK  = 0b0000_1111;
    private static final int RIGHT_SHIFT =           0;

    //
    // Bit Field Access Methods
    //
    public int left() {
        return (value & LEFT_MASK) >> LEFT_SHIFT;
    }
    public void left(int newValue) {
        value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
    }

    public int right() {
        return (value & RIGHT_MASK) >> RIGHT_SHIFT;
    }
    public void right(int newValue) {
        value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
    }

}

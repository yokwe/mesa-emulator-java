package yokwe.majuro.type;

// AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType];
public final class AVItem extends MemoryData16 {
    public static final String NAME     = "AVItem";
    public static final int    SIZE     =        1;
    public static final int    BIT_SIZE =       16;

    public static final int DATA_MASK  = 0b1111_1111_1111_1100;
    public static final int DATA_SHIFT =                     2;
    public static final int TAG_MASK   = 0b0000_0000_0000_0011;
    public static final int TAG_SHIFT  =                     0;

    public AVItem(char value) {
        super(value);
    }
    public AVItem(int base, MemoryAccess access) {
        super(base, access);
    }


    // field access
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

}

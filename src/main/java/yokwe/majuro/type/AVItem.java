package yokwe.majuro.type;

// AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType];
public class AVItem extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();

    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;

    //
    // Constructor
    //
    public AVItem(char value) {
        super(value);
    }
    public AVItem(int base, MemoryAccess access) {
        super(base, access);
    }

    //
    // Bit Field
    //

    // data (0:0..13):  UNSPECIFIED
    // tag  (0:14..15): AVItemType

    private static final int DATA_MASK  = 0b1111_1111_1111_1100;
    private static final int DATA_SHIFT =                     2;
    private static final int TAG_MASK   = 0b0000_0000_0000_0011;
    private static final int TAG_SHIFT  =                     0;

    //
    // Bit Field Access Methods
    //
    public final int data() {
        return (value & DATA_MASK) >> DATA_SHIFT;
    }
    public final void data(int newValue) {
        value = (value & ~DATA_MASK) | ((newValue << DATA_SHIFT) & DATA_MASK);
    }

    public final int tag() {
        return (value & TAG_MASK) >> TAG_SHIFT;
    }
    public final void tag(int newValue) {
        value = (value & ~TAG_MASK) | ((newValue << TAG_SHIFT) & TAG_MASK);
    }

}

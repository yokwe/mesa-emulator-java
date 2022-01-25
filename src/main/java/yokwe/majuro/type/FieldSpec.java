package yokwe.majuro.type;

// FieldSpec: TYPE = RECORD[pos (0:0..3): NIBBLE, size (0:4..7): NIBBLE];
public final class FieldSpec extends MemoryData16 {
    public static final String NAME     = "FieldSpec";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =           8;

    public static final int POS_MASK   = 0b1111_0000;
    public static final int POS_SHIFT  =           4;
    public static final int SIZE_MASK  = 0b0000_1111;
    public static final int SIZE_SHIFT =           0;

    public FieldSpec(char value) {
        super(value);
    }
    public FieldSpec(int base, MemoryAccess access) {
        super(base, access);
    }


    // field access
    public int pos() {
        return (value & POS_MASK) >> POS_SHIFT;
    }
    public void pos(int newValue) {
        value = (value & ~POS_MASK) | ((newValue << POS_SHIFT) & POS_MASK);
    }

    public int size() {
        return (value & SIZE_MASK) >> SIZE_SHIFT;
    }
    public void size(int newValue) {
        value = (value & ~SIZE_MASK) | ((newValue << SIZE_SHIFT) & SIZE_MASK);
    }

}

package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType];
public final class AVItem {
    public static final String NAME     = "AVItem";
    public static final int    SIZE     =        1;
    public static final int    BIT_SIZE =       16;

    public static final int DATA_MASK  = 0b1111_1111_1111_1100;
    public static final int DATA_SHIFT =                     2;
    public static final int TAG_MASK   = 0b0000_0000_0000_0011;
    public static final int TAG_SHIFT  =                     0;

    public static final int NO_VALUE = -1;

    public static AVItem value(int value) {
        return new AVItem(NO_VALUE, value, false);
    }
    public static AVItem fetch(int base) {
        int ra = Mesa.fetch(base);
        return new AVItem(ra, Mesa.readReal(ra), false);
    }
    public static AVItem store(int base) {
        int ra = Mesa.store(base);
        return new AVItem(ra, Mesa.readReal(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private AVItem(int ra, int value, boolean canWrite) {
        this.ra       = ra;
        this.canWrite = canWrite;
    }

    public char get() {
        return (char)value;
    }
    public void set(char newValue) {
        value = newValue;
    }
    public void write() {
        if (ra == NO_VALUE || !canWrite) throw new UnexpectedException("Unexpected");
        Mesa.writeReal(ra, (char)value);
    }

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

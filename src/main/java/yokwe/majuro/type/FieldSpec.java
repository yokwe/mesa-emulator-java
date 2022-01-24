package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// FieldSpec: TYPE = RECORD[pos (0:0..3): NIBBLE, size (0:4..7): NIBBLE];
public final class FieldSpec {
    public static final String NAME     = "FieldSpec";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =           8;

    public static final int POS_MASK   = 0b1111_0000;
    public static final int POS_SHIFT  =           4;
    public static final int SIZE_MASK  = 0b0000_1111;
    public static final int SIZE_SHIFT =           0;

    public static final int NO_VALUE = -1;

    public static FieldSpec value(int value) {
        return new FieldSpec(NO_VALUE, value, false);
    }
    public static FieldSpec fetch(int base) {
        int ra = Mesa.fetch(base);
        return new FieldSpec(ra, Mesa.readReal(ra), false);
    }
    public static FieldSpec store(int base) {
        int ra = Mesa.store(base);
        return new FieldSpec(ra, Mesa.readReal(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private FieldSpec(int ra, int value, boolean canWrite) {
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

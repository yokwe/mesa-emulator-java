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

    private final int     ra;
    private final boolean canWrite;

    public int value;

    public FieldSpec(char value) {
        this.ra       = NO_VALUE;
        this.canWrite = false;
        this.value    = value;
    }
    public FieldSpec(int base, boolean canWrite) {
        if (canWrite) {
            this.ra       = Mesa.store(base);
            this.canWrite = true;
        } else {
            this.ra       = Mesa.fetch(base);
            this.canWrite = false;
        }
        this.value = Mesa.readReal16(ra);
    }

    public char get() {
        return (char)value;
    }
    public void set(char newValue) {
        value = newValue;
    }
    public void write() {
        if (ra == NO_VALUE || !canWrite) throw new UnexpectedException("Unexpected");
        Mesa.writeReal16(ra, (char)value);
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

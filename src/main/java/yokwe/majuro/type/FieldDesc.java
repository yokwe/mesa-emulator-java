package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// FieldDesc: TYPE = RECORD[offset (0:0..7): BYTE, field (0:8..15): FieldSpec];
public final class FieldDesc {
    public static final String NAME     = "FieldDesc";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =          16;

    public static final int OFFSET_MASK  = 0b1111_1111_0000_0000;
    public static final int OFFSET_SHIFT =                     8;
    public static final int FIELD_MASK   = 0b0000_0000_1111_1111;
    public static final int FIELD_SHIFT  =                     0;

    public static final int NO_VALUE = -1;

    public static FieldDesc value(int value) {
        return new FieldDesc(NO_VALUE, value, false);
    }
    public static FieldDesc fetch(int base) {
        int ra = Mesa.fetch(base);
        return new FieldDesc(ra, Mesa.readReal(ra), false);
    }
    public static FieldDesc store(int base) {
        int ra = Mesa.store(base);
        return new FieldDesc(ra, Mesa.readReal(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private FieldDesc(int ra, int value, boolean canWrite) {
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

    public int offset() {
        return (value & OFFSET_MASK) >> OFFSET_SHIFT;
    }
    public void offset(int newValue) {
        value = (value & ~OFFSET_MASK) | ((newValue << OFFSET_SHIFT) & OFFSET_MASK);
    }

    public int field() {
        return (value & FIELD_MASK) >> FIELD_SHIFT;
    }
    public void field(int newValue) {
        value = (value & ~FIELD_MASK) | ((newValue << FIELD_SHIFT) & FIELD_MASK);
    }

}

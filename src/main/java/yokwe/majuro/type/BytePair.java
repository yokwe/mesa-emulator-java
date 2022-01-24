package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// BytePair: TYPE = RECORD[left (0:0..7): BYTE, right (0:8..15): BYTE];
public final class BytePair {
    public static final String NAME     = "BytePair";
    public static final int    SIZE     =          1;
    public static final int    BIT_SIZE =         16;

    public static final int LEFT_MASK   = 0b1111_1111_0000_0000;
    public static final int LEFT_SHIFT  =                     8;
    public static final int RIGHT_MASK  = 0b0000_0000_1111_1111;
    public static final int RIGHT_SHIFT =                     0;

    public static final int NO_VALUE = -1;

    public static BytePair value(int value) {
        return new BytePair(NO_VALUE, value, false);
    }
    public static BytePair fetch(int base) {
        int ra = Mesa.fetch(base);
        return new BytePair(ra, Mesa.readReal(ra), false);
    }
    public static BytePair store(int base) {
        int ra = Mesa.store(base);
        return new BytePair(ra, Mesa.readReal(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private BytePair(int ra, int value, boolean canWrite) {
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

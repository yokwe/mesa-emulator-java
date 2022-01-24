package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// NibblePair: TYPE = RECORD[left (0:0..3): NIBBLE, right (0:4..7): NIBBLE];
public final class NibblePair {
    public static final String NAME     = "NibblePair";
    public static final int    SIZE     =            1;
    public static final int    BIT_SIZE =            8;

    public static final int LEFT_MASK   = 0b1111_0000;
    public static final int LEFT_SHIFT  =           4;
    public static final int RIGHT_MASK  = 0b0000_1111;
    public static final int RIGHT_SHIFT =           0;

    public static final int NO_VALUE = -1;

    public static NibblePair value(int value) {
        return new NibblePair(NO_VALUE, value, false);
    }
    public static NibblePair fetch(int base) {
        int ra = Mesa.fetch(base);
        return new NibblePair(ra, Mesa.readReal16(ra), false);
    }
    public static NibblePair store(int base) {
        int ra = Mesa.store(base);
        return new NibblePair(ra, Mesa.readReal16(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private NibblePair(int ra, int value, boolean canWrite) {
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
        Mesa.writeReal16(ra, (char)value);
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

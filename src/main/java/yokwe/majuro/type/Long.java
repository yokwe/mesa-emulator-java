package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// Long: TYPE = RECORD32[low (0:0..15): UNSPECIFIED, high (1:0..15): UNSPECIFIED];
public final class Long {
    public static final String NAME     = "Long";
    public static final int    SIZE     =      1;
    public static final int    BIT_SIZE =     32;

    public static final int LOW_MASK   = 0b0000_0000_0000_0000_1111_1111_1111_1111;
    public static final int LOW_SHIFT  =                                         0;
    public static final int HIGH_MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    public static final int HIGH_SHIFT =                                        16;

    public static final int NO_VALUE = -1;

    public static Long value(int value) {
        return new Long(NO_VALUE, NO_VALUE, value, false);
    }
    public static Long fetch(int base) {
        int ra0 = Mesa.fetch(base + 0);
        int ra1 = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.fetch(base + 1);
        return new Long(ra0, ra1, Mesa.readReal32(ra0, ra1), false);
    }
    public static Long store(int base) {
        int ra0 = Mesa.store(base + 0);
        int ra1 = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.store(base + 1);
        return new Long(ra0, ra1, Mesa.readReal32(ra0, ra1), true);
    }
    private final int     ra0;
    private final int     ra1;
    private final boolean canWrite;

    public int value;

    private Long(int ra0, int ra1, int value, boolean canWrite) {
        this.ra0      = ra0;
        this.ra1      = ra1;
        this.canWrite = canWrite;
    }

    public char get() {
        return (char)value;
    }
    public void set(char newValue) {
        value = newValue;
    }
    public void write() {
        if (ra0 == NO_VALUE || !canWrite) throw new UnexpectedException("Unexpected");
        Mesa.writeReal32(ra0, ra1, value);
    }

    public int low() {
        return (value & LOW_MASK) >> LOW_SHIFT;
    }
    public void low(int newValue) {
        value = (value & ~LOW_MASK) | ((newValue << LOW_SHIFT) & LOW_MASK);
    }

    public int high() {
        return (value & HIGH_MASK) >> HIGH_SHIFT;
    }
    public void high(int newValue) {
        value = (value & ~HIGH_MASK) | ((newValue << HIGH_SHIFT) & HIGH_MASK);
    }

}

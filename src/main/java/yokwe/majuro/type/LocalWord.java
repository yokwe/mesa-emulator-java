package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// LocalWord: TYPE = RECORD[available (0:0..7): BYTE, fsi (0:8..15): FSIndex];
public final class LocalWord {
    public static final String NAME     = "LocalWord";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =          16;

    public static final int AVAILABLE_MASK  = 0b1111_1111_0000_0000;
    public static final int AVAILABLE_SHIFT =                     8;
    public static final int FSI_MASK        = 0b0000_0000_1111_1111;
    public static final int FSI_SHIFT       =                     0;

    public static final int NO_VALUE = -1;

    public static LocalWord value(int value) {
        return new LocalWord(NO_VALUE, value, false);
    }
    public static LocalWord fetch(int base) {
        int ra = Mesa.fetch(base);
        return new LocalWord(ra, Mesa.readReal(ra), false);
    }
    public static LocalWord store(int base) {
        int ra = Mesa.store(base);
        return new LocalWord(ra, Mesa.readReal(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private LocalWord(int ra, int value, boolean canWrite) {
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

    public int available() {
        return (value & AVAILABLE_MASK) >> AVAILABLE_SHIFT;
    }
    public void available(int newValue) {
        value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
    }

    public int fsi() {
        return (value & FSI_MASK) >> FSI_SHIFT;
    }
    public void fsi(int newValue) {
        value = (value & ~FSI_MASK) | ((newValue << FSI_SHIFT) & FSI_MASK);
    }

}

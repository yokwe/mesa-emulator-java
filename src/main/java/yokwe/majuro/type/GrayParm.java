package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// GrayParm: TYPE = RECORD[reserved (0:0..3): NIBBLE, yOffset (0:4..7): NIBBLE, widthMinusOne (0:8..11): NIBBLE, heightMinusOne (0:12..15): NIBBLE];
public final class GrayParm {
    public static final String NAME     = "GrayParm";
    public static final int    SIZE     =          1;
    public static final int    BIT_SIZE =         16;

    public static final int RESERVED_MASK          = 0b1111_0000_0000_0000;
    public static final int RESERVED_SHIFT         =                    12;
    public static final int Y_OFFSET_MASK          = 0b0000_1111_0000_0000;
    public static final int Y_OFFSET_SHIFT         =                     8;
    public static final int WIDTH_MINUS_ONE_MASK   = 0b0000_0000_1111_0000;
    public static final int WIDTH_MINUS_ONE_SHIFT  =                     4;
    public static final int HEIGHT_MINUS_ONE_MASK  = 0b0000_0000_0000_1111;
    public static final int HEIGHT_MINUS_ONE_SHIFT =                     0;

    public static final int NO_VALUE = -1;

    public static GrayParm value(int value) {
        return new GrayParm(NO_VALUE, value, false);
    }
    public static GrayParm fetch(int base) {
        int ra = Mesa.fetch(base);
        return new GrayParm(ra, Mesa.readReal(ra), false);
    }
    public static GrayParm store(int base) {
        int ra = Mesa.store(base);
        return new GrayParm(ra, Mesa.readReal(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private GrayParm(int ra, int value, boolean canWrite) {
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

    public int reserved() {
        return (value & RESERVED_MASK) >> RESERVED_SHIFT;
    }
    public void reserved(int newValue) {
        value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
    }

    public int yOffset() {
        return (value & Y_OFFSET_MASK) >> Y_OFFSET_SHIFT;
    }
    public void yOffset(int newValue) {
        value = (value & ~Y_OFFSET_MASK) | ((newValue << Y_OFFSET_SHIFT) & Y_OFFSET_MASK);
    }

    public int widthMinusOne() {
        return (value & WIDTH_MINUS_ONE_MASK) >> WIDTH_MINUS_ONE_SHIFT;
    }
    public void widthMinusOne(int newValue) {
        value = (value & ~WIDTH_MINUS_ONE_MASK) | ((newValue << WIDTH_MINUS_ONE_SHIFT) & WIDTH_MINUS_ONE_MASK);
    }

    public int heightMinusOne() {
        return (value & HEIGHT_MINUS_ONE_MASK) >> HEIGHT_MINUS_ONE_SHIFT;
    }
    public void heightMinusOne(int newValue) {
        value = (value & ~HEIGHT_MINUS_ONE_MASK) | ((newValue << HEIGHT_MINUS_ONE_SHIFT) & HEIGHT_MINUS_ONE_MASK);
    }

}

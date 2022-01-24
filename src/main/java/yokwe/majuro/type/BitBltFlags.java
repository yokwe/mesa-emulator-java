package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// BitBltFlags: TYPE = RECORD[direction (0:0..0): Direction, disjoint (0:1..1): BOOLEAN, disjointItems (0:2..2): BOOLEAN, gray (0:3..3): BOOLEAN, srcFunc (0:4..4): SrcFunc, dstFunc (0:5..6): DstFunc, reserved (0:7..15): UNSPECIFIED];
public final class BitBltFlags {
    public static final String NAME     = "BitBltFlags";
    public static final int    SIZE     =             1;
    public static final int    BIT_SIZE =            16;

    public static final int DIRECTION_MASK       = 0b1000_0000_0000_0000;
    public static final int DIRECTION_SHIFT      =                    15;
    public static final int DISJOINT_MASK        = 0b0100_0000_0000_0000;
    public static final int DISJOINT_SHIFT       =                    14;
    public static final int DISJOINT_ITEMS_MASK  = 0b0010_0000_0000_0000;
    public static final int DISJOINT_ITEMS_SHIFT =                    13;
    public static final int GRAY_MASK            = 0b0001_0000_0000_0000;
    public static final int GRAY_SHIFT           =                    12;
    public static final int SRC_FUNC_MASK        = 0b0000_1000_0000_0000;
    public static final int SRC_FUNC_SHIFT       =                    11;
    public static final int DST_FUNC_MASK        = 0b0000_0110_0000_0000;
    public static final int DST_FUNC_SHIFT       =                     9;
    public static final int RESERVED_MASK        = 0b0000_0001_1111_1111;
    public static final int RESERVED_SHIFT       =                     0;

    public static final int NO_VALUE = -1;

    public static BitBltFlags value(int value) {
        return new BitBltFlags(NO_VALUE, value, false);
    }
    public static BitBltFlags fetch(int base) {
        int ra = Mesa.fetch(base);
        return new BitBltFlags(ra, Mesa.readReal(ra), false);
    }
    public static BitBltFlags store(int base) {
        int ra = Mesa.store(base);
        return new BitBltFlags(ra, Mesa.readReal(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private BitBltFlags(int ra, int value, boolean canWrite) {
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

    public int direction() {
        return (value & DIRECTION_MASK) >> DIRECTION_SHIFT;
    }
    public void direction(int newValue) {
        value = (value & ~DIRECTION_MASK) | ((newValue << DIRECTION_SHIFT) & DIRECTION_MASK);
    }

    public int disjoint() {
        return (value & DISJOINT_MASK) >> DISJOINT_SHIFT;
    }
    public void disjoint(int newValue) {
        value = (value & ~DISJOINT_MASK) | ((newValue << DISJOINT_SHIFT) & DISJOINT_MASK);
    }

    public int disjointItems() {
        return (value & DISJOINT_ITEMS_MASK) >> DISJOINT_ITEMS_SHIFT;
    }
    public void disjointItems(int newValue) {
        value = (value & ~DISJOINT_ITEMS_MASK) | ((newValue << DISJOINT_ITEMS_SHIFT) & DISJOINT_ITEMS_MASK);
    }

    public int gray() {
        return (value & GRAY_MASK) >> GRAY_SHIFT;
    }
    public void gray(int newValue) {
        value = (value & ~GRAY_MASK) | ((newValue << GRAY_SHIFT) & GRAY_MASK);
    }

    public int srcFunc() {
        return (value & SRC_FUNC_MASK) >> SRC_FUNC_SHIFT;
    }
    public void srcFunc(int newValue) {
        value = (value & ~SRC_FUNC_MASK) | ((newValue << SRC_FUNC_SHIFT) & SRC_FUNC_MASK);
    }

    public int dstFunc() {
        return (value & DST_FUNC_MASK) >> DST_FUNC_SHIFT;
    }
    public void dstFunc(int newValue) {
        value = (value & ~DST_FUNC_MASK) | ((newValue << DST_FUNC_SHIFT) & DST_FUNC_MASK);
    }

    public int reserved() {
        return (value & RESERVED_MASK) >> RESERVED_SHIFT;
    }
    public void reserved(int newValue) {
        value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
    }

}

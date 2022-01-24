package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// TaggedControlLink: TYPE = RECORD32[data (0:0..13): UNSPECIFIED, tag (0:14..15): LinkType, fill (1:0..15): UNSPECIFIED];
public final class TaggedControlLink {
    public static final String NAME     = "TaggedControlLink";
    public static final int    SIZE     =                   1;
    public static final int    BIT_SIZE =                  32;

    public static final int DATA_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1100;
    public static final int DATA_SHIFT =                                         2;
    public static final int TAG_MASK   = 0b0000_0000_0000_0000_0000_0000_0000_0011;
    public static final int TAG_SHIFT  =                                         0;
    public static final int FILL_MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    public static final int FILL_SHIFT =                                        16;

    public static final int NO_VALUE = -1;

    public static TaggedControlLink value(int value) {
        return new TaggedControlLink(NO_VALUE, NO_VALUE, value, false);
    }
    public static TaggedControlLink fetch(int base) {
        int ra0 = Mesa.fetch(base + 0);
        int ra1 = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.fetch(base + 1);
        return new TaggedControlLink(ra0, ra1, Mesa.readReal32(ra0, ra1), false);
    }
    public static TaggedControlLink store(int base) {
        int ra0 = Mesa.store(base + 0);
        int ra1 = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.store(base + 1);
        return new TaggedControlLink(ra0, ra1, Mesa.readReal32(ra0, ra1), true);
    }
    private final int     ra0;
    private final int     ra1;
    private final boolean canWrite;

    public int value;

    private TaggedControlLink(int ra0, int ra1, int value, boolean canWrite) {
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

    public int fill() {
        return (value & FILL_MASK) >> FILL_SHIFT;
    }
    public void fill(int newValue) {
        value = (value & ~FILL_MASK) | ((newValue << FILL_SHIFT) & FILL_MASK);
    }

}

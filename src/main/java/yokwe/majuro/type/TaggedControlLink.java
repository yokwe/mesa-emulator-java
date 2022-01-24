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

    private final int     ra0;
    private final int     ra1;
    private final boolean canWrite;

    public int value;

    public TaggedControlLink(int value) {
        this.ra0      = NO_VALUE;
        this.ra1      = NO_VALUE;
        this.canWrite = false;
        this.value    = value;
    }
    public TaggedControlLink(int base, boolean canWrite) {
        if (canWrite) {
            this.ra0      = Mesa.store(base + 0);
            this.ra1      = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.store(base + 1);
            this.canWrite = true;
        } else {
            this.ra0      = Mesa.fetch(base + 0);
            this.ra1      = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.fetch(base + 1);
            this.canWrite = false;
        }
        this.value = Mesa.readReal32(ra0,  ra1);
    }

    public int get() {
        return value;
    }
    public void set(int newValue) {
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

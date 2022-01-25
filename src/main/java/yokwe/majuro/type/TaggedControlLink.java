package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// TaggedControlLink: TYPE = RECORD32[data (0:0..13): UNSPECIFIED, tag (0:14..15): LinkType, fill (1:0..15): UNSPECIFIED];
public final class TaggedControlLink extends MemoryData32 {
    public static final String NAME     = "TaggedControlLink";
    public static final int    SIZE     =                   2;
    public static final int    BIT_SIZE =                  32;

    public static final int DATA_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1100;
    public static final int DATA_SHIFT =                                         2;
    public static final int TAG_MASK   = 0b0000_0000_0000_0000_0000_0000_0000_0011;
    public static final int TAG_SHIFT  =                                         0;
    public static final int FILL_MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    public static final int FILL_SHIFT =                                        16;

    public TaggedControlLink(int value) {
        super(value);
    }
    public TaggedControlLink(int base, MemoryAccess access) {
        super(base, access);
    }


    // field access
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

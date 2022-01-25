package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType];
public final class AVItem {
    public static final String NAME     = "AVItem";
    public static final int    SIZE     =        1;
    public static final int    BIT_SIZE =       16;

    public static final int DATA_MASK  = 0b1111_1111_1111_1100;
    public static final int DATA_SHIFT =                     2;
    public static final int TAG_MASK   = 0b0000_0000_0000_0011;
    public static final int TAG_SHIFT  =                     0;

    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, use int for value
    public int value;

    public AVItem(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public AVItem(int base, MemoryAccess access) {
        this.access = access;
        switch(access) {
        case NONE:
            this.ra    = 0;
            this.value = 0;
            break;
        case READ:
            this.ra    = Mesa.fetch(base);
            this.value = Mesa.readReal16(ra);
            break;
        case READ_WRITE:
            this.ra    = Mesa.store(base);
            this.value = Mesa.readReal16(ra);
            break;
        case WRITE:
            this.ra    = Mesa.store(base);
            this.value = 0;
            break;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }

    public void write() {
        switch(access) {
        case READ_WRITE:
        case WRITE:
            Mesa.writeReal16(ra, (char)value);
            break;
        default:
            throw new UnexpectedException("Unexpected");
        }
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

}

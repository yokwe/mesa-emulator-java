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

    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, use int for value
    public int value;

    public NibblePair(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public NibblePair(int base, MemoryAccess access) {
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

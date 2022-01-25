package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// BytePair: TYPE = RECORD[left (0:0..7): BYTE, right (0:8..15): BYTE];
public final class BytePair {
    public static final String NAME     = "BytePair";
    public static final int    SIZE     =          1;
    public static final int    BIT_SIZE =         16;

    public static final int LEFT_MASK   = 0b1111_1111_0000_0000;
    public static final int LEFT_SHIFT  =                     8;
    public static final int RIGHT_MASK  = 0b0000_0000_1111_1111;
    public static final int RIGHT_SHIFT =                     0;

    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, use int for value
    public int value;

    public BytePair(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public BytePair(int base, MemoryAccess access) {
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

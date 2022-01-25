package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// FieldSpec: TYPE = RECORD[pos (0:0..3): NIBBLE, size (0:4..7): NIBBLE];
public final class FieldSpec {
    public static final String NAME     = "FieldSpec";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =           8;

    public static final int POS_MASK   = 0b1111_0000;
    public static final int POS_SHIFT  =           4;
    public static final int SIZE_MASK  = 0b0000_1111;
    public static final int SIZE_SHIFT =           0;

    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, use int for value
    public int value;

    public FieldSpec(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public FieldSpec(int base, MemoryAccess access) {
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


    public int pos() {
        return (value & POS_MASK) >> POS_SHIFT;
    }
    public void pos(int newValue) {
        value = (value & ~POS_MASK) | ((newValue << POS_SHIFT) & POS_MASK);
    }

    public int size() {
        return (value & SIZE_MASK) >> SIZE_SHIFT;
    }
    public void size(int newValue) {
        value = (value & ~SIZE_MASK) | ((newValue << SIZE_SHIFT) & SIZE_MASK);
    }

}

package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// FieldDesc: TYPE = RECORD[offset (0:0..7): BYTE, field (0:8..15): FieldSpec];
public final class FieldDesc {
    public static final String NAME     = "FieldDesc";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =          16;

    public static final int OFFSET_MASK  = 0b1111_1111_0000_0000;
    public static final int OFFSET_SHIFT =                     8;
    public static final int FIELD_MASK   = 0b0000_0000_1111_1111;
    public static final int FIELD_SHIFT  =                     0;

    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, use int for value
    public int value;

    public FieldDesc(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public FieldDesc(int base, MemoryAccess access) {
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


    public int offset() {
        return (value & OFFSET_MASK) >> OFFSET_SHIFT;
    }
    public void offset(int newValue) {
        value = (value & ~OFFSET_MASK) | ((newValue << OFFSET_SHIFT) & OFFSET_MASK);
    }

    public int field() {
        return (value & FIELD_MASK) >> FIELD_SHIFT;
    }
    public void field(int newValue) {
        value = (value & ~FIELD_MASK) | ((newValue << FIELD_SHIFT) & FIELD_MASK);
    }

}

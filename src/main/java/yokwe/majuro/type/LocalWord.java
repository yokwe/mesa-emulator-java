package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// LocalWord: TYPE = RECORD[available (0:0..7): BYTE, fsi (0:8..15): FSIndex];
public final class LocalWord {
    public static final String NAME     = "LocalWord";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =          16;

    public static final int AVAILABLE_MASK  = 0b1111_1111_0000_0000;
    public static final int AVAILABLE_SHIFT =                     8;
    public static final int FSI_MASK        = 0b0000_0000_1111_1111;
    public static final int FSI_SHIFT       =                     0;

    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, use int for value
    public int value;

    public LocalWord(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public LocalWord(int base, MemoryAccess access) {
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


    public int available() {
        return (value & AVAILABLE_MASK) >> AVAILABLE_SHIFT;
    }
    public void available(int newValue) {
        value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
    }

    public int fsi() {
        return (value & FSI_MASK) >> FSI_SHIFT;
    }
    public void fsi(int newValue) {
        value = (value & ~FSI_MASK) | ((newValue << FSI_SHIFT) & FSI_MASK);
    }

}

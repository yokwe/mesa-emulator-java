package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// Monitor: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..14): UNSPECIFIED, locked (0:15..15): BOOLEAN];
public final class Monitor {
    public static final String NAME     = "Monitor";
    public static final int    SIZE     =         1;
    public static final int    BIT_SIZE =        16;

    public static final int RESERVED_MASK   = 0b1110_0000_0000_0000;
    public static final int RESERVED_SHIFT  =                    13;
    public static final int TAIL_MASK       = 0b0001_1111_1111_1000;
    public static final int TAIL_SHIFT      =                     3;
    public static final int AVAILABLE_MASK  = 0b0000_0000_0000_0110;
    public static final int AVAILABLE_SHIFT =                     1;
    public static final int LOCKED_MASK     = 0b0000_0000_0000_0001;
    public static final int LOCKED_SHIFT    =                     0;

    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, use int for value
    public int value;

    public Monitor(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public Monitor(int base, MemoryAccess access) {
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


    public int reserved() {
        return (value & RESERVED_MASK) >> RESERVED_SHIFT;
    }
    public void reserved(int newValue) {
        value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
    }

    public int tail() {
        return (value & TAIL_MASK) >> TAIL_SHIFT;
    }
    public void tail(int newValue) {
        value = (value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK);
    }

    public int available() {
        return (value & AVAILABLE_MASK) >> AVAILABLE_SHIFT;
    }
    public void available(int newValue) {
        value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
    }

    public int locked() {
        return (value & LOCKED_MASK) >> LOCKED_SHIFT;
    }
    public void locked(int newValue) {
        value = (value & ~LOCKED_MASK) | ((newValue << LOCKED_SHIFT) & LOCKED_MASK);
    }

}

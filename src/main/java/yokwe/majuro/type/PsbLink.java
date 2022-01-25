package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// PsbLink: TYPE = RECORD[priority (0:0..2): Priority, next (0:3..12): PsbIndex, failed (0:13..13): BOOLEAN, permanent (0:14..14): BOOLEAN, preempted (0:15..15): BOOLEAN];
public final class PsbLink {
    public static final String NAME     = "PsbLink";
    public static final int    SIZE     =         1;
    public static final int    BIT_SIZE =        16;

    public static final int PRIORITY_MASK   = 0b1110_0000_0000_0000;
    public static final int PRIORITY_SHIFT  =                    13;
    public static final int NEXT_MASK       = 0b0001_1111_1111_1000;
    public static final int NEXT_SHIFT      =                     3;
    public static final int FAILED_MASK     = 0b0000_0000_0000_0100;
    public static final int FAILED_SHIFT    =                     2;
    public static final int PERMANENT_MASK  = 0b0000_0000_0000_0010;
    public static final int PERMANENT_SHIFT =                     1;
    public static final int PREEMPTED_MASK  = 0b0000_0000_0000_0001;
    public static final int PREEMPTED_SHIFT =                     0;

    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, use int for value
    public int value;

    public PsbLink(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public PsbLink(int base, MemoryAccess access) {
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


    public int priority() {
        return (value & PRIORITY_MASK) >> PRIORITY_SHIFT;
    }
    public void priority(int newValue) {
        value = (value & ~PRIORITY_MASK) | ((newValue << PRIORITY_SHIFT) & PRIORITY_MASK);
    }

    public int next() {
        return (value & NEXT_MASK) >> NEXT_SHIFT;
    }
    public void next(int newValue) {
        value = (value & ~NEXT_MASK) | ((newValue << NEXT_SHIFT) & NEXT_MASK);
    }

    public int failed() {
        return (value & FAILED_MASK) >> FAILED_SHIFT;
    }
    public void failed(int newValue) {
        value = (value & ~FAILED_MASK) | ((newValue << FAILED_SHIFT) & FAILED_MASK);
    }

    public int permanent() {
        return (value & PERMANENT_MASK) >> PERMANENT_SHIFT;
    }
    public void permanent(int newValue) {
        value = (value & ~PERMANENT_MASK) | ((newValue << PERMANENT_SHIFT) & PERMANENT_MASK);
    }

    public int preempted() {
        return (value & PREEMPTED_MASK) >> PREEMPTED_SHIFT;
    }
    public void preempted(int newValue) {
        value = (value & ~PREEMPTED_MASK) | ((newValue << PREEMPTED_SHIFT) & PREEMPTED_MASK);
    }

}

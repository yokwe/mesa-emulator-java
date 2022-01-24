package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// Condition: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..13): UNSPECIFIED, abortable (0:14..14): BOOLEAN, wakeup (0:15..15): BOOLEAN];
public final class Condition {
    public static final String NAME     = "Condition";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =          16;

    public static final int RESERVED_MASK   = 0b1110_0000_0000_0000;
    public static final int RESERVED_SHIFT  =                    13;
    public static final int TAIL_MASK       = 0b0001_1111_1111_1000;
    public static final int TAIL_SHIFT      =                     3;
    public static final int AVAILABLE_MASK  = 0b0000_0000_0000_0100;
    public static final int AVAILABLE_SHIFT =                     2;
    public static final int ABORTABLE_MASK  = 0b0000_0000_0000_0010;
    public static final int ABORTABLE_SHIFT =                     1;
    public static final int WAKEUP_MASK     = 0b0000_0000_0000_0001;
    public static final int WAKEUP_SHIFT    =                     0;

    public static final int NO_VALUE = -1;

    public static Condition value(int value) {
        return new Condition(NO_VALUE, value, false);
    }
    public static Condition fetch(int base) {
        int ra = Mesa.fetch(base);
        return new Condition(ra, Mesa.readReal16(ra), false);
    }
    public static Condition store(int base) {
        int ra = Mesa.store(base);
        return new Condition(ra, Mesa.readReal16(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private Condition(int ra, int value, boolean canWrite) {
        this.ra       = ra;
        this.canWrite = canWrite;
    }

    public char get() {
        return (char)value;
    }
    public void set(char newValue) {
        value = newValue;
    }
    public void write() {
        if (ra == NO_VALUE || !canWrite) throw new UnexpectedException("Unexpected");
        Mesa.writeReal16(ra, (char)value);
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

    public int abortable() {
        return (value & ABORTABLE_MASK) >> ABORTABLE_SHIFT;
    }
    public void abortable(int newValue) {
        value = (value & ~ABORTABLE_MASK) | ((newValue << ABORTABLE_SHIFT) & ABORTABLE_MASK);
    }

    public int wakeup() {
        return (value & WAKEUP_MASK) >> WAKEUP_SHIFT;
    }
    public void wakeup(int newValue) {
        value = (value & ~WAKEUP_MASK) | ((newValue << WAKEUP_SHIFT) & WAKEUP_MASK);
    }

}

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

    public static final int NO_VALUE = -1;

    public static PsbLink value(int value) {
        return new PsbLink(NO_VALUE, value, false);
    }
    public static PsbLink fetch(int base) {
        int ra = Mesa.fetch(base);
        return new PsbLink(ra, Mesa.readReal16(ra), false);
    }
    public static PsbLink store(int base) {
        int ra = Mesa.store(base);
        return new PsbLink(ra, Mesa.readReal16(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private PsbLink(int ra, int value, boolean canWrite) {
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

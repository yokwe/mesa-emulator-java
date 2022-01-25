package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// StateWord: TYPE = RECORD[instByte (0:0..7): BYTE, stkPtr (0:8..15): BYTE];
public final class StateWord {
    public static final String NAME     = "StateWord";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =          16;

    public static final int INST_BYTE_MASK  = 0b1111_1111_0000_0000;
    public static final int INST_BYTE_SHIFT =                     8;
    public static final int STK_PTR_MASK    = 0b0000_0000_1111_1111;
    public static final int STK_PTR_SHIFT   =                     0;

    public static final int NO_VALUE = -1;

    private final int     ra;
    private final boolean canWrite;

    public int value;

    public StateWord(char value) {
        this.ra       = NO_VALUE;
        this.canWrite = false;
        this.value    = value;
    }
    public StateWord(int base, boolean canWrite) {
        if (canWrite) {
            this.ra       = Mesa.store(base);
            this.canWrite = true;
        } else {
            this.ra       = Mesa.fetch(base);
            this.canWrite = false;
        }
        this.value = Mesa.readReal16(ra);
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

    public int instByte() {
        return (value & INST_BYTE_MASK) >> INST_BYTE_SHIFT;
    }
    public void instByte(int newValue) {
        value = (value & ~INST_BYTE_MASK) | ((newValue << INST_BYTE_SHIFT) & INST_BYTE_MASK);
    }

    public int stkPtr() {
        return (value & STK_PTR_MASK) >> STK_PTR_SHIFT;
    }
    public void stkPtr(int newValue) {
        value = (value & ~STK_PTR_MASK) | ((newValue << STK_PTR_SHIFT) & STK_PTR_MASK);
    }

}
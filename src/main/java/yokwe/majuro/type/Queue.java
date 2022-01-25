package yokwe.majuro.type;

// Queue: TYPE = RECORD[reserved1 (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, reserved2 (0:13..15): UNSPECIFIED];
public final class Queue extends MemoryData16 {
    public static final String NAME     = "Queue";
    public static final int    SIZE     =       1;
    public static final int    BIT_SIZE =      16;

    public static final int RESERVED_1_MASK  = 0b1110_0000_0000_0000;
    public static final int RESERVED_1_SHIFT =                    13;
    public static final int TAIL_MASK        = 0b0001_1111_1111_1000;
    public static final int TAIL_SHIFT       =                     3;
    public static final int RESERVED_2_MASK  = 0b0000_0000_0000_0111;
    public static final int RESERVED_2_SHIFT =                     0;

    public Queue(char value) {
        super(value);
    }
    public Queue(int base, MemoryAccess access) {
        super(base, access);
    }


    // field access
    public int reserved1() {
        return (value & RESERVED_1_MASK) >> RESERVED_1_SHIFT;
    }
    public void reserved1(int newValue) {
        value = (value & ~RESERVED_1_MASK) | ((newValue << RESERVED_1_SHIFT) & RESERVED_1_MASK);
    }

    public int tail() {
        return (value & TAIL_MASK) >> TAIL_SHIFT;
    }
    public void tail(int newValue) {
        value = (value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK);
    }

    public int reserved2() {
        return (value & RESERVED_2_MASK) >> RESERVED_2_SHIFT;
    }
    public void reserved2(int newValue) {
        value = (value & ~RESERVED_2_MASK) | ((newValue << RESERVED_2_SHIFT) & RESERVED_2_MASK);
    }

}

package yokwe.majuro.type;

// Queue: TYPE = RECORD[reserved1 (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, reserved2 (0:13..15): UNSPECIFIED];
public class Queue extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public Queue(char value) {
        super(value);
    }
    public Queue(int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // reserved1 (0:0..2):   UNSPECIFIED
    // tail      (0:3..12):  PsbIndex
    // reserved2 (0:13..15): UNSPECIFIED
    
    private static final int RESERVED_1_MASK  = 0b1110_0000_0000_0000;
    private static final int RESERVED_1_SHIFT =                    13;
    private static final int TAIL_MASK        = 0b0001_1111_1111_1000;
    private static final int TAIL_SHIFT       =                     3;
    private static final int RESERVED_2_MASK  = 0b0000_0000_0000_0111;
    private static final int RESERVED_2_SHIFT =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final int reserved1() {
        return (value & RESERVED_1_MASK) >> RESERVED_1_SHIFT;
    }
    public final void reserved1(int newValue) {
        value = (value & ~RESERVED_1_MASK) | ((newValue << RESERVED_1_SHIFT) & RESERVED_1_MASK);
    }
    
    public final int tail() {
        return (value & TAIL_MASK) >> TAIL_SHIFT;
    }
    public final void tail(int newValue) {
        value = (value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK);
    }
    
    public final int reserved2() {
        return (value & RESERVED_2_MASK) >> RESERVED_2_SHIFT;
    }
    public final void reserved2(int newValue) {
        value = (value & ~RESERVED_2_MASK) | ((newValue << RESERVED_2_SHIFT) & RESERVED_2_MASK);
    }
    
}

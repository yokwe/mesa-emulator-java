package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// Queue: TYPE = RECORD[reserved1 (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, reserved2 (0:13..15): UNSPECIFIED];
public final class Queue extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final Queue value(char value) {
        return new Queue(value);
    }
    public static final Queue longPointer(int base, MemoryAccess access) {
        return new Queue(base, access);
    }
    public static final Queue pointer(char base, MemoryAccess access) {
        return new Queue(Memory.instance.lengthenMDS(base), access);
    }
    
    private Queue(char value) {
        super(value);
    }
    private Queue(int base, MemoryAccess access) {
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
    public final char reserved1() {
        return (char)((value & RESERVED_1_MASK) >>> RESERVED_1_SHIFT);
    }
    public final void reserved1(char newValue) {
        value = (value & ~RESERVED_1_MASK) | ((newValue << RESERVED_1_SHIFT) & RESERVED_1_MASK);
    }
    
    public final char tail() {
        return (char)((value & TAIL_MASK) >>> TAIL_SHIFT);
    }
    public final void tail(char newValue) {
        value = (value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK);
    }
    
    public final char reserved2() {
        return (char)((value & RESERVED_2_MASK) >>> RESERVED_2_SHIFT);
    }
    public final void reserved2(char newValue) {
        value = (value & ~RESERVED_2_MASK) | ((newValue << RESERVED_2_SHIFT) & RESERVED_2_MASK);
    }
    
}

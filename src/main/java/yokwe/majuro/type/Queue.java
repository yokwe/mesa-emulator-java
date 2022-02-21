package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// Queue: TYPE = RECORD[reserved1 (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, reserved2 (0:13..15): UNSPECIFIED];
public final class Queue extends MemoryData16 {
    public static final String NAME = "Queue";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final Queue value(@Mesa.CARD16 int value) {
        return new Queue(value);
    }
    public static final Queue value() {
        return new Queue(0);
    }
    public static final Queue longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new Queue(base, access);
    }
    public static final Queue pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Queue(Memory.lengthenMDS(base), access);
    }
    
    private Queue(@Mesa.CARD16 int value) {
        super(value);
    }
    private Queue(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    public final @Mesa.CARD16 int reserved1() {
        return Types.toCARD16((value & RESERVED_1_MASK) >>> RESERVED_1_SHIFT);
    }
    public final Queue reserved1(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~RESERVED_1_MASK) | ((newValue << RESERVED_1_SHIFT) & RESERVED_1_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int tail() {
        return Types.toCARD16((value & TAIL_MASK) >>> TAIL_SHIFT);
    }
    public final Queue tail(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int reserved2() {
        return Types.toCARD16((value & RESERVED_2_MASK) >>> RESERVED_2_SHIFT);
    }
    public final Queue reserved2(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~RESERVED_2_MASK) | ((newValue << RESERVED_2_SHIFT) & RESERVED_2_MASK));
        return this;
    }
    
}

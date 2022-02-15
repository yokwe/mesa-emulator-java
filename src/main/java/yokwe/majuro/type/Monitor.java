package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// Monitor: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..14): UNSPECIFIED, locked (0:15..15): BOOLEAN];
public final class Monitor extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final Monitor value(char value) {
        return new Monitor(value);
    }
    public static final Monitor longPointer(int base, MemoryAccess access) {
        return new Monitor(base, access);
    }
    public static final Monitor pointer(char base, MemoryAccess access) {
        return new Monitor(Memory.instance.lengthenMDS(base), access);
    }
    
    private Monitor(char value) {
        super(value);
    }
    private Monitor(int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // reserved  (0:0..2):   UNSPECIFIED
    // tail      (0:3..12):  PsbIndex
    // available (0:13..14): UNSPECIFIED
    // locked    (0:15..15): BOOLEAN
    
    private static final int RESERVED_MASK   = 0b1110_0000_0000_0000;
    private static final int RESERVED_SHIFT  =                    13;
    private static final int TAIL_MASK       = 0b0001_1111_1111_1000;
    private static final int TAIL_SHIFT      =                     3;
    private static final int AVAILABLE_MASK  = 0b0000_0000_0000_0110;
    private static final int AVAILABLE_SHIFT =                     1;
    private static final int LOCKED_MASK     = 0b0000_0000_0000_0001;
    private static final int LOCKED_SHIFT    =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final char reserved() {
        return (char)((value & RESERVED_MASK) >>> RESERVED_SHIFT);
    }
    public final void reserved(char newValue) {
        value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
    }
    
    public final char tail() {
        return (char)((value & TAIL_MASK) >>> TAIL_SHIFT);
    }
    public final void tail(char newValue) {
        value = (value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK);
    }
    
    public final char available() {
        return (char)((value & AVAILABLE_MASK) >>> AVAILABLE_SHIFT);
    }
    public final void available(char newValue) {
        value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
    }
    
    public final char locked() {
        return (char)((value & LOCKED_MASK) >>> LOCKED_SHIFT);
    }
    public final void locked(char newValue) {
        value = (value & ~LOCKED_MASK) | ((newValue << LOCKED_SHIFT) & LOCKED_MASK);
    }
    
}

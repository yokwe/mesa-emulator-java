package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// Monitor: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..14): UNSPECIFIED, locked (0:15..15): BOOLEAN];
public final class Monitor extends MemoryData16 {
    public static final String NAME = "Monitor";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final Monitor value(@Mesa.CARD16 int value) {
        return new Monitor(value);
    }
    public static final Monitor longPointer(@Mesa.POINTER int base, MemoryAccess access) {
        return new Monitor(base, access);
    }
    public static final Monitor pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Monitor(Memory.lengthenMDS(base), access);
    }
    
    private Monitor(@Mesa.CARD16 int value) {
        super(value);
    }
    private Monitor(@Mesa.POINTER int base, MemoryAccess access) {
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
    public final @Mesa.CARD16 int reserved() {
        return Types.toCARD16((value & RESERVED_MASK) >>> RESERVED_SHIFT);
    }
    public final void reserved(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK));
    }
    
    public final @Mesa.CARD16 int tail() {
        return Types.toCARD16((value & TAIL_MASK) >>> TAIL_SHIFT);
    }
    public final void tail(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK));
    }
    
    public final @Mesa.CARD16 int available() {
        return Types.toCARD16((value & AVAILABLE_MASK) >>> AVAILABLE_SHIFT);
    }
    public final void available(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK));
    }
    
    public final @Mesa.CARD16 int locked() {
        return Types.toCARD16((value & LOCKED_MASK) >>> LOCKED_SHIFT);
    }
    public final void locked(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~LOCKED_MASK) | ((newValue << LOCKED_SHIFT) & LOCKED_MASK));
    }
    
}

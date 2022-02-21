package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// Condition: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..13): UNSPECIFIED, abortable (0:14..14): BOOLEAN, wakeup (0:15..15): BOOLEAN];
public final class Condition extends MemoryData16 {
    public static final String NAME = "Condition";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final Condition value(@Mesa.CARD16 int value) {
        return new Condition(value);
    }
    public static final Condition value() {
        return new Condition(0);
    }
    public static final Condition longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new Condition(base, access);
    }
    public static final Condition pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Condition(Memory.lengthenMDS(base), access);
    }
    
    private Condition(@Mesa.CARD16 int value) {
        super(value);
    }
    private Condition(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // reserved  (0:0..2):   UNSPECIFIED
    // tail      (0:3..12):  PsbIndex
    // available (0:13..13): UNSPECIFIED
    // abortable (0:14..14): BOOLEAN
    // wakeup    (0:15..15): BOOLEAN
    
    private static final int RESERVED_MASK   = 0b1110_0000_0000_0000;
    private static final int RESERVED_SHIFT  =                    13;
    private static final int TAIL_MASK       = 0b0001_1111_1111_1000;
    private static final int TAIL_SHIFT      =                     3;
    private static final int AVAILABLE_MASK  = 0b0000_0000_0000_0100;
    private static final int AVAILABLE_SHIFT =                     2;
    private static final int ABORTABLE_MASK  = 0b0000_0000_0000_0010;
    private static final int ABORTABLE_SHIFT =                     1;
    private static final int WAKEUP_MASK     = 0b0000_0000_0000_0001;
    private static final int WAKEUP_SHIFT    =                     0;
    
    //
    // Bit Field Access Methods
    //
    // @Mesa.CARD16 is UNSPECIFIED
    public final @Mesa.CARD16 int reserved() {
        return Types.toCARD16((value & RESERVED_MASK) >>> RESERVED_SHIFT);
    }
    public final Condition reserved(@Mesa.CARD16 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
        value = Types.toCARD16((value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK));
        return this;
    }
    
    // @Mesa.CARD16 is PsbIndex
    public final @Mesa.CARD16 int tail() {
        return Types.toCARD16((value & TAIL_MASK) >>> TAIL_SHIFT);
    }
    public final Condition tail(@Mesa.CARD16 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) PsbIndex.checkValue(newValue);
        value = Types.toCARD16((value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK));
        return this;
    }
    
    // @Mesa.CARD16 is UNSPECIFIED
    public final @Mesa.CARD16 int available() {
        return Types.toCARD16((value & AVAILABLE_MASK) >>> AVAILABLE_SHIFT);
    }
    public final Condition available(@Mesa.CARD16 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
        value = Types.toCARD16((value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK));
        return this;
    }
    
    public final boolean abortable() {
        return ((value & ABORTABLE_MASK) >>> ABORTABLE_SHIFT) != 0;
    }
    public final Condition abortable(boolean newValue) {
        value = Types.toCARD16((value & ~ABORTABLE_MASK) | (((newValue ? 1 : 0) << ABORTABLE_SHIFT) & ABORTABLE_MASK));
        return this;
    }
    
    public final boolean wakeup() {
        return ((value & WAKEUP_MASK) >>> WAKEUP_SHIFT) != 0;
    }
    public final Condition wakeup(boolean newValue) {
        value = Types.toCARD16((value & ~WAKEUP_MASK) | (((newValue ? 1 : 0) << WAKEUP_SHIFT) & WAKEUP_MASK));
        return this;
    }
    
}

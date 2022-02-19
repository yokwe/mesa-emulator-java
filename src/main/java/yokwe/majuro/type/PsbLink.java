package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// PsbLink: TYPE = RECORD[priority (0:0..2): Priority, next (0:3..12): PsbIndex, failed (0:13..13): BOOLEAN, permanent (0:14..14): BOOLEAN, preempted (0:15..15): BOOLEAN];
public final class PsbLink extends MemoryData16 {
    public static final String NAME = "PsbLink";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final PsbLink value(@Mesa.CARD16 int value) {
        return new PsbLink(value);
    }
    public static final PsbLink longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new PsbLink(base, access);
    }
    public static final PsbLink pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new PsbLink(Memory.lengthenMDS(base), access);
    }
    
    private PsbLink(@Mesa.CARD16 int value) {
        super(value);
    }
    private PsbLink(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // priority  (0:0..2):   Priority
    // next      (0:3..12):  PsbIndex
    // failed    (0:13..13): BOOLEAN
    // permanent (0:14..14): BOOLEAN
    // preempted (0:15..15): BOOLEAN
    
    private static final int PRIORITY_MASK   = 0b1110_0000_0000_0000;
    private static final int PRIORITY_SHIFT  =                    13;
    private static final int NEXT_MASK       = 0b0001_1111_1111_1000;
    private static final int NEXT_SHIFT      =                     3;
    private static final int FAILED_MASK     = 0b0000_0000_0000_0100;
    private static final int FAILED_SHIFT    =                     2;
    private static final int PERMANENT_MASK  = 0b0000_0000_0000_0010;
    private static final int PERMANENT_SHIFT =                     1;
    private static final int PREEMPTED_MASK  = 0b0000_0000_0000_0001;
    private static final int PREEMPTED_SHIFT =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final @Mesa.CARD16 int priority() {
        return Types.toCARD16((value & PRIORITY_MASK) >>> PRIORITY_SHIFT);
    }
    public final void priority(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~PRIORITY_MASK) | ((newValue << PRIORITY_SHIFT) & PRIORITY_MASK));
    }
    
    public final @Mesa.CARD16 int next() {
        return Types.toCARD16((value & NEXT_MASK) >>> NEXT_SHIFT);
    }
    public final void next(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~NEXT_MASK) | ((newValue << NEXT_SHIFT) & NEXT_MASK));
    }
    
    public final @Mesa.CARD16 int failed() {
        return Types.toCARD16((value & FAILED_MASK) >>> FAILED_SHIFT);
    }
    public final void failed(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~FAILED_MASK) | ((newValue << FAILED_SHIFT) & FAILED_MASK));
    }
    
    public final @Mesa.CARD16 int permanent() {
        return Types.toCARD16((value & PERMANENT_MASK) >>> PERMANENT_SHIFT);
    }
    public final void permanent(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~PERMANENT_MASK) | ((newValue << PERMANENT_SHIFT) & PERMANENT_MASK));
    }
    
    public final @Mesa.CARD16 int preempted() {
        return Types.toCARD16((value & PREEMPTED_MASK) >>> PREEMPTED_SHIFT);
    }
    public final void preempted(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~PREEMPTED_MASK) | ((newValue << PREEMPTED_SHIFT) & PREEMPTED_MASK));
    }
    
}

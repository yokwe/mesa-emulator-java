package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// PsbFlags: TYPE = RECORD[available (0:0..2): UNSPECIFIED, cleanup (0:3..12): PsbIndex, reserved (0:13..13): UNSPECIFIED, waiting (0:14..14): BOOLEAN, abort (0:15..15): BOOLEAN];
public final class PsbFlags extends MemoryData16 {
    public static final String NAME = "PsbFlags";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final PsbFlags value(@Mesa.CARD16 int value) {
        return new PsbFlags(value);
    }
    public static final PsbFlags value() {
        return new PsbFlags(0);
    }
    public static final PsbFlags longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new PsbFlags(base, access);
    }
    public static final PsbFlags pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new PsbFlags(Memory.lengthenMDS(base), access);
    }
    
    private PsbFlags(@Mesa.CARD16 int value) {
        super(value);
    }
    private PsbFlags(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // available (0:0..2):   UNSPECIFIED
    // cleanup   (0:3..12):  PsbIndex
    // reserved  (0:13..13): UNSPECIFIED
    // waiting   (0:14..14): BOOLEAN
    // abort     (0:15..15): BOOLEAN
    
    private static final int AVAILABLE_MASK  = 0b1110_0000_0000_0000;
    private static final int AVAILABLE_SHIFT =                    13;
    private static final int CLEANUP_MASK    = 0b0001_1111_1111_1000;
    private static final int CLEANUP_SHIFT   =                     3;
    private static final int RESERVED_MASK   = 0b0000_0000_0000_0100;
    private static final int RESERVED_SHIFT  =                     2;
    private static final int WAITING_MASK    = 0b0000_0000_0000_0010;
    private static final int WAITING_SHIFT   =                     1;
    private static final int ABORT_MASK      = 0b0000_0000_0000_0001;
    private static final int ABORT_SHIFT     =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final @Mesa.CARD16 int available() {
        return Types.toCARD16((value & AVAILABLE_MASK) >>> AVAILABLE_SHIFT);
    }
    public final PsbFlags available(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int cleanup() {
        return Types.toCARD16((value & CLEANUP_MASK) >>> CLEANUP_SHIFT);
    }
    public final PsbFlags cleanup(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~CLEANUP_MASK) | ((newValue << CLEANUP_SHIFT) & CLEANUP_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int reserved() {
        return Types.toCARD16((value & RESERVED_MASK) >>> RESERVED_SHIFT);
    }
    public final PsbFlags reserved(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int waiting() {
        return Types.toCARD16((value & WAITING_MASK) >>> WAITING_SHIFT);
    }
    public final PsbFlags waiting(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~WAITING_MASK) | ((newValue << WAITING_SHIFT) & WAITING_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int abort() {
        return Types.toCARD16((value & ABORT_MASK) >>> ABORT_SHIFT);
    }
    public final PsbFlags abort(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~ABORT_MASK) | ((newValue << ABORT_SHIFT) & ABORT_MASK));
        return this;
    }
    
}

package yokwe.majuro.type;

// PsbFlags: TYPE = RECORD[available (0:0..2): UNSPECIFIED, cleanup (0:3..12): PsbIndex, reserved (0:13..13): UNSPECIFIED, waiting (0:14..14): BOOLEAN, abort (0:15..15): BOOLEAN];
public final class PsbFlags extends MemoryData16 {
    public static final String NAME      = "PsbFlags";
    public static final int    WORD_SIZE =          1;
    public static final int    BIT_SIZE  =         16;

    //
    // Constructor
    //
    public PsbFlags(char value) {
        super(value);
    }
    public PsbFlags(int base, MemoryAccess access) {
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
    public int available() {
        return (value & AVAILABLE_MASK) >> AVAILABLE_SHIFT;
    }
    public void available(int newValue) {
        value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
    }

    public int cleanup() {
        return (value & CLEANUP_MASK) >> CLEANUP_SHIFT;
    }
    public void cleanup(int newValue) {
        value = (value & ~CLEANUP_MASK) | ((newValue << CLEANUP_SHIFT) & CLEANUP_MASK);
    }

    public int reserved() {
        return (value & RESERVED_MASK) >> RESERVED_SHIFT;
    }
    public void reserved(int newValue) {
        value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
    }

    public int waiting() {
        return (value & WAITING_MASK) >> WAITING_SHIFT;
    }
    public void waiting(int newValue) {
        value = (value & ~WAITING_MASK) | ((newValue << WAITING_SHIFT) & WAITING_MASK);
    }

    public int abort() {
        return (value & ABORT_MASK) >> ABORT_SHIFT;
    }
    public void abort(int newValue) {
        value = (value & ~ABORT_MASK) | ((newValue << ABORT_SHIFT) & ABORT_MASK);
    }

}

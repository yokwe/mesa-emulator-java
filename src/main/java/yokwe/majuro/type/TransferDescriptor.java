package yokwe.majuro.type;

// TransferDescriptor: TYPE = RECORD[src (0:0..15): ShortControlLink, reserved (1:0..15): UNSPECIFIED, dst (2:0..31): ControlLink];
public final class TransferDescriptor extends MemoryBase {
    public static final String NAME      = "TransferDescriptor";
    public static final int    WORD_SIZE =                    4;
    public static final int    BIT_SIZE  =                   64;

    //
    // Constructor
    //
    public TransferDescriptor(int base) {
        super(base);
    }
    public TransferDescriptor(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Constants for field access
    //
    public static final int OFFSET_SRC      = 0; // src      (0:0..15): ShortControlLink
    public static final int OFFSET_RESERVED = 1; // reserved (1:0..15): UNSPECIFIED
    public static final int OFFSET_DST      = 2; // dst      (2:0..31): ControlLink

    public UNSPECIFIED src(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_SRC, memoryAccess);
    }
    public UNSPECIFIED reserved(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_RESERVED, memoryAccess);
    }
    public LONG_UNSPECIFIED dst(MemoryAccess memoryAccess) {
        return new LONG_UNSPECIFIED(base + OFFSET_DST, memoryAccess);
    }
}

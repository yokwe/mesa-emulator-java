package yokwe.majuro.type;

// Port: TYPE = RECORD[inport (0:0..15): FrameLink, unused (1:0..15): UNSPECIFIED, outport (2:0..31): ControlLink];
public final class Port extends MemoryBase {
    public static final String NAME      = "Port";
    public static final int    WORD_SIZE =      4;
    public static final int    BIT_SIZE  =     64;

    //
    // Constructor
    //
    public Port(int base) {
        super(base);
    }
    public Port(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Access to Field of Record
    //
    // inport (0:0..15): FrameLink
    private static final int OFFSET_INPORT = 0;
    public BLOCK inport() {
        return new BLOCK(base + OFFSET_INPORT);
    }
    // unused (1:0..15): UNSPECIFIED
    private static final int OFFSET_UNUSED = 1;
    public UNSPECIFIED unused(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_UNUSED, memoryAccess);
    }
    // outport (2:0..31): ControlLink
    private static final int OFFSET_OUTPORT = 2;
    public LONG_UNSPECIFIED outport(MemoryAccess memoryAccess) {
        return new LONG_UNSPECIFIED(base + OFFSET_OUTPORT, memoryAccess);
    }
}

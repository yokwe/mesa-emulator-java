package yokwe.majuro.type;

// Port: TYPE = RECORD[inport (0:0..15): FrameLink, unused (1:0..15): UNSPECIFIED, outport (2:0..31): ControlLink];
public final class Port {
    public static final String NAME      = "Port";
    public static final int    WORD_SIZE =      4;
    public static final int    BIT_SIZE  =     64;

    //
    // Constants for field access
    //
    public static final int OFFSET_INPORT  = 0; // inport  (0:0..15): FrameLink
    public static final int OFFSET_UNUSED  = 1; // unused  (1:0..15): UNSPECIFIED
    public static final int OFFSET_OUTPORT = 2; // outport (2:0..31): ControlLink

    //
    // Constructor
    //
    public final int base;

    public Port(int base) {
        this.base = base;
    }
    public Port(int base, int index) {
        this.base = base + (WORD_SIZE * index);
    }

}

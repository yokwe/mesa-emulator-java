package yokwe.majuro.type;

// TransferDescriptor: TYPE = RECORD[src (0:0..15): ShortControlLink, reserved (1:0..15): UNSPECIFIED, dst (2:0..31): ControlLink];
public final class TransferDescriptor {
    public static final String NAME     = "TransferDescriptor";
    public static final int    SIZE     =                    4;
    public static final int    BIT_SIZE =                   64;

    public final int base;

    public TransferDescriptor(int value) {
        this.base = value;
    }

    public static final int OFFSET_SRC      = 0; // src      (0:0..15): ShortControlLink
    public static final int OFFSET_RESERVED = 1; // reserved (1:0..15): UNSPECIFIED
    public static final int OFFSET_DST      = 2; // dst      (2:0..31): ControlLink

}

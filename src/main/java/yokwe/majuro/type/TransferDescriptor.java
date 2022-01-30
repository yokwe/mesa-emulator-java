package yokwe.majuro.type;

// TransferDescriptor: TYPE = RECORD[src (0:0..15): ShortControlLink, reserved (1:0..15): UNSPECIFIED, dst (2:0..31): ControlLink];
public class TransferDescriptor extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();

    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;

    //
    // Constructor
    //
    public TransferDescriptor(int base) {
        super(base);
    }

    //
    // Access to Field of Record
    //
    // src (0:0..15): ShortControlLink
    private static final int OFFSET_SRC = 0;
    public UNSPECIFIED src(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_SRC, memoryAccess);
    }
    // reserved (1:0..15): UNSPECIFIED
    private static final int OFFSET_RESERVED = 1;
    public UNSPECIFIED reserved(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_RESERVED, memoryAccess);
    }
    // dst (2:0..31): ControlLink
    private static final int OFFSET_DST = 2;
    public LONG_UNSPECIFIED dst(MemoryAccess memoryAccess) {
        return new LONG_UNSPECIFIED(base + OFFSET_DST, memoryAccess);
    }
}

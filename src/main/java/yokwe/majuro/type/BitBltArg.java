package yokwe.majuro.type;

// BitBltArg: TYPE = RECORD[dst (0:0..47): BitAddress, dstBpl (3:0..15): INTEGER, src (4:0..47): BitAddress, srcBpl (7:0..15): INTEGER, width (8:0..15): CARDINAL, height (9:0..15): CARDINAL, flags (10:0..15): BitBltFlags, reserved (11:0..15): UNSPECIFIED];
public class BitBltArg extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();

    public static final int WORD_SIZE =  12;
    public static final int BIT_SIZE  = 192;

    //
    // Constructor
    //
    public BitBltArg(int base) {
        super(base);
    }

    //
    // Access to Field of Record
    //
    // dst (0:0..47): BitAddress
    private static final int OFFSET_DST = 0;
    public BitAddress dst() {
        return new BitAddress(base + OFFSET_DST);
    }
    // dstBpl (3:0..15): INTEGER
    private static final int OFFSET_DST_BPL = 3;
    public INTEGER dstBpl(MemoryAccess memoryAccess) {
        return new INTEGER(base + OFFSET_DST_BPL, memoryAccess);
    }
    // src (4:0..47): BitAddress
    private static final int OFFSET_SRC = 4;
    public BitAddress src() {
        return new BitAddress(base + OFFSET_SRC);
    }
    // srcBpl (7:0..15): INTEGER
    private static final int OFFSET_SRC_BPL = 7;
    public INTEGER srcBpl(MemoryAccess memoryAccess) {
        return new INTEGER(base + OFFSET_SRC_BPL, memoryAccess);
    }
    // width (8:0..15): CARDINAL
    private static final int OFFSET_WIDTH = 8;
    public CARDINAL width(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_WIDTH, memoryAccess);
    }
    // height (9:0..15): CARDINAL
    private static final int OFFSET_HEIGHT = 9;
    public CARDINAL height(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_HEIGHT, memoryAccess);
    }
    // flags (10:0..15): BitBltFlags
    private static final int OFFSET_FLAGS = 10;
    public BitBltFlags flags(MemoryAccess memoryAccess) {
        return new BitBltFlags(base + OFFSET_FLAGS, memoryAccess);
    }
    // reserved (11:0..15): UNSPECIFIED
    private static final int OFFSET_RESERVED = 11;
    public UNSPECIFIED reserved(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_RESERVED, memoryAccess);
    }
}

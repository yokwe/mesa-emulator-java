package yokwe.majuro.type;

// BitBltArg: TYPE = RECORD[dst (0:0..47): BitAddress, dstBpl (3:0..15): INTEGER, src (4:0..47): BitAddress, srcBpl (7:0..15): INTEGER, width (8:0..15): CARDINAL, height (9:0..15): CARDINAL, flags (10:0..15): BitBltFlags, reserved (11:0..15): UNSPECIFIED];
public final class BitBltArg extends MemoryBase {
    public static final String NAME      = "BitBltArg";
    public static final int    WORD_SIZE =          12;
    public static final int    BIT_SIZE  =         192;

    //
    // Constructor
    //
    public BitBltArg(int base) {
        super(base);
    }
    public BitBltArg(int base, int index) {
        super(base + (WORD_SIZE * index));
    }

    //
    // Constants for field access
    //
    public static final int OFFSET_DST      =  0; // dst      (0:0..47):  BitAddress
    public static final int OFFSET_DST_BPL  =  3; // dstBpl   (3:0..15):  INTEGER
    public static final int OFFSET_SRC      =  4; // src      (4:0..47):  BitAddress
    public static final int OFFSET_SRC_BPL  =  7; // srcBpl   (7:0..15):  INTEGER
    public static final int OFFSET_WIDTH    =  8; // width    (8:0..15):  CARDINAL
    public static final int OFFSET_HEIGHT   =  9; // height   (9:0..15):  CARDINAL
    public static final int OFFSET_FLAGS    = 10; // flags    (10:0..15): BitBltFlags
    public static final int OFFSET_RESERVED = 11; // reserved (11:0..15): UNSPECIFIED

    public BitAddress dst() {
        return new BitAddress(base + OFFSET_DST);
    }
    public INTEGER dstBpl(MemoryAccess memoryAccess) {
        return new INTEGER(base + OFFSET_DST_BPL, memoryAccess);
    }
    public BitAddress src() {
        return new BitAddress(base + OFFSET_SRC);
    }
    public INTEGER srcBpl(MemoryAccess memoryAccess) {
        return new INTEGER(base + OFFSET_SRC_BPL, memoryAccess);
    }
    public CARDINAL width(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_WIDTH, memoryAccess);
    }
    public CARDINAL height(MemoryAccess memoryAccess) {
        return new CARDINAL(base + OFFSET_HEIGHT, memoryAccess);
    }
    public BitBltFlags flags(MemoryAccess memoryAccess) {
        return new BitBltFlags(base + OFFSET_FLAGS, memoryAccess);
    }
    public UNSPECIFIED reserved(MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + OFFSET_RESERVED, memoryAccess);
    }
}

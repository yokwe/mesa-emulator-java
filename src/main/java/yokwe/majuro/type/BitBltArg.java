package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// BitBltArg: TYPE = RECORD[dst (0:0..47): BitAddress, dstBpl (3:0..15): INTEGER, src (4:0..47): BitAddress, srcBpl (7:0..15): INTEGER, width (8:0..15): CARDINAL, height (9:0..15): CARDINAL, flags (10:0..15): BitBltFlags, reserved (11:0..15): UNSPECIFIED];
public final class BitBltArg extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  12;
    public static final int BIT_SIZE  = 192;
    
    //
    // Constructor
    //
    public static final BitBltArg longPointer(int base) {
        return new BitBltArg(base);
    }
    public static final BitBltArg pointer(char base) {
        return new BitBltArg(Memory.lengthenMDS(base));
    }
    
    private BitBltArg(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // dst (0:0..47): BitAddress
    private static final int OFFSET_DST = 0;
    public BitAddress dst() {
        int longPointer = base + OFFSET_DST;
        return BitAddress.longPointer(longPointer);
    }
    // dstBpl (3:0..15): INTEGER
    private static final int OFFSET_DST_BPL = 3;
    public INTEGER dstBpl(MemoryAccess access) {
        int longPointer = base + OFFSET_DST_BPL;
        return INTEGER.longPointer(longPointer, access);
    }
    // src (4:0..47): BitAddress
    private static final int OFFSET_SRC = 4;
    public BitAddress src() {
        int longPointer = base + OFFSET_SRC;
        return BitAddress.longPointer(longPointer);
    }
    // srcBpl (7:0..15): INTEGER
    private static final int OFFSET_SRC_BPL = 7;
    public INTEGER srcBpl(MemoryAccess access) {
        int longPointer = base + OFFSET_SRC_BPL;
        return INTEGER.longPointer(longPointer, access);
    }
    // width (8:0..15): CARDINAL
    private static final int OFFSET_WIDTH = 8;
    public CARDINAL width(MemoryAccess access) {
        int longPointer = base + OFFSET_WIDTH;
        return CARDINAL.longPointer(longPointer, access);
    }
    // height (9:0..15): CARDINAL
    private static final int OFFSET_HEIGHT = 9;
    public CARDINAL height(MemoryAccess access) {
        int longPointer = base + OFFSET_HEIGHT;
        return CARDINAL.longPointer(longPointer, access);
    }
    // flags (10:0..15): BitBltFlags
    private static final int OFFSET_FLAGS = 10;
    public BitBltFlags flags(MemoryAccess access) {
        int longPointer = base + OFFSET_FLAGS;
        return BitBltFlags.longPointer(longPointer, access);
    }
    // reserved (11:0..15): UNSPECIFIED
    private static final int OFFSET_RESERVED = 11;
    public UNSPECIFIED reserved(MemoryAccess access) {
        int longPointer = base + OFFSET_RESERVED;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

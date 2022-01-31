package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// ProcessStateBlock: TYPE = RECORD[link (0:0..15): PsbLink, flags (1:0..15): PsbFlags, context (2:0..15): POINTER, timeout (3:0..15): Ticks, mds (4:0..15): CARDINAL, available (5:0..15): UNSPECIFIED, sticky (6:0..31): LONG UNSPECIFIED];
public final class ProcessStateBlock extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
    public static final ProcessStateBlock longPointer(int base) {
        return new ProcessStateBlock(base);
    }
    public static final ProcessStateBlock pointer(char base) {
        return new ProcessStateBlock(Mesa.lengthenMDS(base));
    }
    
    private ProcessStateBlock(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // link (0:0..15): PsbLink
    private static final int OFFSET_LINK = 0;
    public PsbLink link(MemoryAccess access) {
        return PsbLink.longPointer(base + OFFSET_LINK, access);
    }
    // flags (1:0..15): PsbFlags
    private static final int OFFSET_FLAGS = 1;
    public PsbFlags flags(MemoryAccess access) {
        return PsbFlags.longPointer(base + OFFSET_FLAGS, access);
    }
    // context (2:0..15): POINTER
    private static final int OFFSET_CONTEXT = 2;
    public POINTER context() {
        return POINTER.longPointer(base + OFFSET_CONTEXT);
    }
    // timeout (3:0..15): Ticks
    private static final int OFFSET_TIMEOUT = 3;
    public CARDINAL timeout(MemoryAccess access) {
        return CARDINAL.longPointer(base + OFFSET_TIMEOUT, access);
    }
    // mds (4:0..15): CARDINAL
    private static final int OFFSET_MDS = 4;
    public CARDINAL mds(MemoryAccess access) {
        return CARDINAL.longPointer(base + OFFSET_MDS, access);
    }
    // available (5:0..15): UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 5;
    public UNSPECIFIED available(MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_AVAILABLE, access);
    }
    // sticky (6:0..31): LONG UNSPECIFIED
    private static final int OFFSET_STICKY = 6;
    public LONG_UNSPECIFIED sticky(MemoryAccess access) {
        return LONG_UNSPECIFIED.longPointer(base + OFFSET_STICKY, access);
    }
}

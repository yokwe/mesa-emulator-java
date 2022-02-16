package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// TransferDescriptor: TYPE = RECORD[src (0:0..15): ShortControlLink, reserved (1:0..15): UNSPECIFIED, dst (2:0..31): ControlLink];
public final class TransferDescriptor extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final TransferDescriptor longPointer(int base) {
        return new TransferDescriptor(base);
    }
    public static final TransferDescriptor pointer(char base) {
        return new TransferDescriptor(Memory.lengthenMDS(base));
    }
    
    private TransferDescriptor(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // src (0:0..15): ShortControlLink
    private static final int OFFSET_SRC = 0;
    public UNSPECIFIED src(MemoryAccess access) {
        int longPointer = base + OFFSET_SRC;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // reserved (1:0..15): UNSPECIFIED
    private static final int OFFSET_RESERVED = 1;
    public UNSPECIFIED reserved(MemoryAccess access) {
        int longPointer = base + OFFSET_RESERVED;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // dst (2:0..31): ControlLink
    private static final int OFFSET_DST = 2;
    public LONG_UNSPECIFIED dst(MemoryAccess access) {
        int longPointer = base + OFFSET_DST;
        return LONG_UNSPECIFIED.longPointer(longPointer, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// Port: TYPE = RECORD[inport (0:0..15): FrameLink, unused (1:0..15): UNSPECIFIED, outport (2:0..31): ControlLink];
public final class Port extends MemoryBase {
    public static final String NAME = "Port";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final Port longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new Port(base, access);
    }
    public static final Port pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Port(Memory.lengthenMDS(base), access);
    }
    
    private Port(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Access to Field of Record
    //
    // inport (0:0..15): FrameLink
    private static final int OFFSET_INPORT = 0;
    public BLOCK inport() {
        int pointer = Memory.read16(base + OFFSET_INPORT);
        return BLOCK.pointer(pointer, access);
    }
    // unused (1:0..15): UNSPECIFIED
    private static final int OFFSET_UNUSED = 1;
    public UNSPECIFIED unused() {
        int longPointer = base + OFFSET_UNUSED;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // outport (2:0..31): ControlLink
    private static final int OFFSET_OUTPORT = 2;
    public LONG_UNSPECIFIED outport() {
        int longPointer = base + OFFSET_OUTPORT;
        return LONG_UNSPECIFIED.longPointer(longPointer, access);
    }
}

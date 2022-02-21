package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ProcDesc: TYPE = RECORD32[taggedGF (0:0..15): UNSPECIFIED, pc (1:0..15): CARDINAL];
public final class ProcDesc extends MemoryData32 {
    public static final String NAME = "ProcDesc";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final ProcDesc value(@Mesa.CARD32 int value) {
        return new ProcDesc(value);
    }
    public static final ProcDesc value() {
        return new ProcDesc(0);
    }
    public static final ProcDesc longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new ProcDesc(base, access);
    }
    public static final ProcDesc pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new ProcDesc(Memory.lengthenMDS(base), access);
    }
    
    private ProcDesc(@Mesa.CARD32 int value) {
        super(value);
    }
    private ProcDesc(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // taggedGF (0:0..15): UNSPECIFIED
    // pc       (1:0..15): CARDINAL
    
    private static final int TAGGED_GF_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1111;
    private static final int TAGGED_GF_SHIFT =                                         0;
    private static final int PC_MASK         = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    private static final int PC_SHIFT        =                                        16;
    
    //
    // Bit Field Access Methods
    //
    public final int taggedGF() {
        return (value & TAGGED_GF_MASK) >>> TAGGED_GF_SHIFT;
    }
    public final ProcDesc taggedGF(int newValue) {
        value = (value & ~TAGGED_GF_MASK) | ((newValue << TAGGED_GF_SHIFT) & TAGGED_GF_MASK);
        return this;
    }
    
    public final int pc() {
        return (value & PC_MASK) >>> PC_SHIFT;
    }
    public final ProcDesc pc(int newValue) {
        value = (value & ~PC_MASK) | ((newValue << PC_SHIFT) & PC_MASK);
        return this;
    }
    
}

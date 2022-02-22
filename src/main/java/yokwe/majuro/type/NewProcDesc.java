package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// NewProcDesc: TYPE = RECORD32[taggedGFI (0:0..15): UNSPECIFIED, pc (1:0..15): CARDINAL];
public final class NewProcDesc extends MemoryData32 {
    public static final String NAME = "NewProcDesc";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final NewProcDesc value(@Mesa.CARD32 int value) {
        return new NewProcDesc(value);
    }
    public static final NewProcDesc value() {
        return new NewProcDesc(0);
    }
    public static final NewProcDesc longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new NewProcDesc(base, access);
    }
    public static final NewProcDesc pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new NewProcDesc(Memory.lengthenMDS(base), access);
    }
    
    private NewProcDesc(@Mesa.CARD32 int value) {
        super(value);
    }
    private NewProcDesc(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // taggedGFI (0:0..15): UNSPECIFIED
    // pc        (1:0..15): CARDINAL
    
    private static final int TAGGED_GFI_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1111;
    private static final int TAGGED_GFI_SHIFT =                                         0;
    private static final int PC_MASK          = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    private static final int PC_SHIFT         =                                        16;
    
    //
    // Bit Field Access Methods
    //
    // @Mesa.CARD16 is UNSPECIFIED
    public final @Mesa.CARD16 int taggedGFI() {
        return Types.toCARD16((value & TAGGED_GFI_MASK) >>> TAGGED_GFI_SHIFT);
    }
    public final NewProcDesc taggedGFI(@Mesa.CARD16 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
        value = Types.toCARD16((value & ~TAGGED_GFI_MASK) | ((newValue << TAGGED_GFI_SHIFT) & TAGGED_GFI_MASK));
        return this;
    }
    
    // @Mesa.CARD16 is CARDINAL
    public final @Mesa.CARD16 int pc() {
        return Types.toCARD16((value & PC_MASK) >>> PC_SHIFT);
    }
    public final NewProcDesc pc(@Mesa.CARD16 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) CARDINAL.checkValue(newValue);
        value = Types.toCARD16((value & ~PC_MASK) | ((newValue << PC_SHIFT) & PC_MASK));
        return this;
    }
    
}

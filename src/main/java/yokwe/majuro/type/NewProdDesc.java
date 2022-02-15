package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// NewProdDesc: TYPE = RECORD32[taggedGFI (0:0..15): UNSPECIFIED, pc (1:0..15): CARDINAL];
public final class NewProdDesc extends MemoryData32 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final NewProdDesc value(int value) {
        return new NewProdDesc(value);
    }
    public static final NewProdDesc longPointer(int base, MemoryAccess access) {
        return new NewProdDesc(base, access);
    }
    public static final NewProdDesc pointer(char base, MemoryAccess access) {
        return new NewProdDesc(Mesa.lengthenMDS(base), access);
    }
    
    private NewProdDesc(int value) {
        super(value);
    }
    private NewProdDesc(int base, MemoryAccess access) {
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
    public final int taggedGFI() {
        return (value & TAGGED_GFI_MASK) >>> TAGGED_GFI_SHIFT;
    }
    public final void taggedGFI(int newValue) {
        value = (value & ~TAGGED_GFI_MASK) | ((newValue << TAGGED_GFI_SHIFT) & TAGGED_GFI_MASK);
    }
    
    public final int pc() {
        return (value & PC_MASK) >>> PC_SHIFT;
    }
    public final void pc(int newValue) {
        value = (value & ~PC_MASK) | ((newValue << PC_SHIFT) & PC_MASK);
    }
    
}

package yokwe.majuro.type;

// NewProdDesc: TYPE = RECORD32[taggedGFI (0:0..15): UNSPECIFIED, pc (1:0..15): CARDINAL];
public final class NewProdDesc extends MemoryData32 {
    public static final String NAME     = "NewProdDesc";
    public static final int    SIZE     =             2;
    public static final int    BIT_SIZE =            32;

    public static final int TAGGED_GFI_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1111;
    public static final int TAGGED_GFI_SHIFT =                                         0;
    public static final int PC_MASK          = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    public static final int PC_SHIFT         =                                        16;

    public NewProdDesc(int value) {
        super(value);
    }
    public NewProdDesc(int base, MemoryAccess access) {
        super(base, access);
    }


    // field access
    public int taggedGFI() {
        return (value & TAGGED_GFI_MASK) >> TAGGED_GFI_SHIFT;
    }
    public void taggedGFI(int newValue) {
        value = (value & ~TAGGED_GFI_MASK) | ((newValue << TAGGED_GFI_SHIFT) & TAGGED_GFI_MASK);
    }

    public int pc() {
        return (value & PC_MASK) >> PC_SHIFT;
    }
    public void pc(int newValue) {
        value = (value & ~PC_MASK) | ((newValue << PC_SHIFT) & PC_MASK);
    }

}

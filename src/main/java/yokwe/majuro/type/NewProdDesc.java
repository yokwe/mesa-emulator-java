package yokwe.majuro.type;

// NewProdDesc: TYPE = RECORD32[taggedGFI (0:0..15): UNSPECIFIED, pc (1:0..15): CARDINAL];
public final class NewProdDesc extends MemoryData32 {
    public static final String NAME      = "NewProdDesc";
    public static final int    WORD_SIZE =             2;
    public static final int    BIT_SIZE  =            32;

    //
    // Constructor
    //
    public NewProdDesc(int value) {
        super(value);
    }
    public NewProdDesc(int base, MemoryAccess access) {
        super(base, access);
    }
    public NewProdDesc(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
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

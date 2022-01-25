package yokwe.majuro.type;

// ProcDesc: TYPE = RECORD32[taggedGF (0:0..15): UNSPECIFIED, pc (1:0..15): CARDINAL];
public final class ProcDesc extends MemoryData32 {
    public static final String NAME     = "ProcDesc";
    public static final int    SIZE     =          2;
    public static final int    BIT_SIZE =         32;

    public static final int TAGGED_GF_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1111;
    public static final int TAGGED_GF_SHIFT =                                         0;
    public static final int PC_MASK         = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    public static final int PC_SHIFT        =                                        16;

    public ProcDesc(int value) {
        super(value);
    }
    public ProcDesc(int base, MemoryAccess access) {
        super(base, access);
    }


    // field access
    public int taggedGF() {
        return (value & TAGGED_GF_MASK) >> TAGGED_GF_SHIFT;
    }
    public void taggedGF(int newValue) {
        value = (value & ~TAGGED_GF_MASK) | ((newValue << TAGGED_GF_SHIFT) & TAGGED_GF_MASK);
    }

    public int pc() {
        return (value & PC_MASK) >> PC_SHIFT;
    }
    public void pc(int newValue) {
        value = (value & ~PC_MASK) | ((newValue << PC_SHIFT) & PC_MASK);
    }

}

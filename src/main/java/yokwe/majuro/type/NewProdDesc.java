package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// NewProdDesc: TYPE = RECORD32[taggedGFI (0:0..15): UNSPECIFIED, pc (1:0..15): CARDINAL];
public final class NewProdDesc {
    public static final String NAME     = "NewProdDesc";
    public static final int    SIZE     =             1;
    public static final int    BIT_SIZE =            32;

    public static final int TAGGED_GFI_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1111;
    public static final int TAGGED_GFI_SHIFT =                                         0;
    public static final int PC_MASK          = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    public static final int PC_SHIFT         =                                        16;

    public static final int NO_VALUE = -1;

    private final int     ra0;
    private final int     ra1;
    private final boolean canWrite;

    public int value;

    public NewProdDesc(int value) {
        this.ra0      = NO_VALUE;
        this.ra1      = NO_VALUE;
        this.canWrite = false;
        this.value    = value;
    }
    public NewProdDesc(int base, boolean canWrite) {
        if (canWrite) {
            this.ra0      = Mesa.store(base + 0);
            this.ra1      = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.store(base + 1);
            this.canWrite = true;
        } else {
            this.ra0      = Mesa.fetch(base + 0);
            this.ra1      = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.fetch(base + 1);
            this.canWrite = false;
        }
        this.value = Mesa.readReal32(ra0,  ra1);
    }

    public int get() {
        return value;
    }
    public void set(int newValue) {
        value = newValue;
    }
    public void write() {
        if (ra0 == NO_VALUE || !canWrite) throw new UnexpectedException("Unexpected");
        Mesa.writeReal32(ra0, ra1, value);
    }

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

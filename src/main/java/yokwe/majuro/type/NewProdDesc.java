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

    public static NewProdDesc value(int value) {
        return new NewProdDesc(NO_VALUE, NO_VALUE, value, false);
    }
    public static NewProdDesc fetch(int base) {
        int ra0 = Mesa.fetch(base + 0);
        int ra1 = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.fetch(base + 1);
        return new NewProdDesc(ra0, ra1, Mesa.readReal32(ra0, ra1), false);
    }
    public static NewProdDesc store(int base) {
        int ra0 = Mesa.store(base + 0);
        int ra1 = Memory.isSamePage(base,  base + 1) ? ra0 + 1 : Mesa.store(base + 1);
        return new NewProdDesc(ra0, ra1, Mesa.readReal32(ra0, ra1), true);
    }
    private final int     ra0;
    private final int     ra1;
    private final boolean canWrite;

    public int value;

    private NewProdDesc(int ra0, int ra1, int value, boolean canWrite) {
        this.ra0      = ra0;
        this.ra1      = ra1;
        this.canWrite = canWrite;
    }

    public char get() {
        return (char)value;
    }
    public void set(char newValue) {
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

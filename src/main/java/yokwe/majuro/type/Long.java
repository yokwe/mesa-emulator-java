package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// Long: TYPE = RECORD32[low (0:0..15): UNSPECIFIED, high (1:0..15): UNSPECIFIED];
public final class Long {
    public static final String NAME     = "Long";
    public static final int    SIZE     =      1;
    public static final int    BIT_SIZE =     32;

    public static final int LOW_MASK   = 0b0000_0000_0000_0000_1111_1111_1111_1111;
    public static final int LOW_SHIFT  =                                         0;
    public static final int HIGH_MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
    public static final int HIGH_SHIFT =                                        16;

    private final MemoryAccess access;
    private final int          ra0;
    private final int          ra1;

    public int value;

    public Long(int value) {
        this.access = MemoryAccess.NONE;
        this.ra0    = 0;
        this.ra1    = 0;
        this.value  = value;
    }
    public Long(int base, MemoryAccess access) {
        this.access = access;
        switch(access) {
        case NONE:
            this.ra0   = 0;
            this.ra1   = 0;
            this.value = 0;
            break;
        case READ:
            this.ra0   = Mesa.fetch(base);
            this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Mesa.fetch(base + 1);
            this.value = Mesa.readReal32(ra0, ra1);
            break;
        case READ_WRITE:
            this.ra0   = Mesa.store(base);
            this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Mesa.store(base + 1);
            this.value = Mesa.readReal32(ra0, ra1);
            break;
        case WRITE:
            this.ra0   = Mesa.store(base);
            this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Mesa.store(base + 1);
            this.value = 0;
            break;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }

    public void write() {
        switch(access) {
        case READ_WRITE:
        case WRITE:
            Mesa.writeReal32(ra0, ra1, value);
            break;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }


    public int low() {
        return (value & LOW_MASK) >> LOW_SHIFT;
    }
    public void low(int newValue) {
        value = (value & ~LOW_MASK) | ((newValue << LOW_SHIFT) & LOW_MASK);
    }

    public int high() {
        return (value & HIGH_MASK) >> HIGH_SHIFT;
    }
    public void high(int newValue) {
        value = (value & ~HIGH_MASK) | ((newValue << HIGH_SHIFT) & HIGH_MASK);
    }

}

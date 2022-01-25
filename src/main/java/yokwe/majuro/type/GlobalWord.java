package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Mesa;

// GlobalWord: TYPE = RECORD[gfi (0:0..13): GFTIndex, trapxfers (0:14..14): BOOLEAN, codelinks (0:15..15): BOOLEAN];
public final class GlobalWord {
    public static final String NAME     = "GlobalWord";
    public static final int    SIZE     =            1;
    public static final int    BIT_SIZE =           16;

    public static final int GFI_MASK        = 0b1111_1111_1111_1100;
    public static final int GFI_SHIFT       =                     2;
    public static final int TRAPXFERS_MASK  = 0b0000_0000_0000_0010;
    public static final int TRAPXFERS_SHIFT =                     1;
    public static final int CODELINKS_MASK  = 0b0000_0000_0000_0001;
    public static final int CODELINKS_SHIFT =                     0;

    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, use int for value
    public int value;

    public GlobalWord(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public GlobalWord(int base, MemoryAccess access) {
        this.access = access;
        switch(access) {
        case NONE:
            this.ra    = 0;
            this.value = 0;
            break;
        case READ:
            this.ra    = Mesa.fetch(base);
            this.value = Mesa.readReal16(ra);
            break;
        case READ_WRITE:
            this.ra    = Mesa.store(base);
            this.value = Mesa.readReal16(ra);
            break;
        case WRITE:
            this.ra    = Mesa.store(base);
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
            Mesa.writeReal16(ra, (char)value);
            break;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }


    public int gfi() {
        return (value & GFI_MASK) >> GFI_SHIFT;
    }
    public void gfi(int newValue) {
        value = (value & ~GFI_MASK) | ((newValue << GFI_SHIFT) & GFI_MASK);
    }

    public int trapxfers() {
        return (value & TRAPXFERS_MASK) >> TRAPXFERS_SHIFT;
    }
    public void trapxfers(int newValue) {
        value = (value & ~TRAPXFERS_MASK) | ((newValue << TRAPXFERS_SHIFT) & TRAPXFERS_MASK);
    }

    public int codelinks() {
        return (value & CODELINKS_MASK) >> CODELINKS_SHIFT;
    }
    public void codelinks(int newValue) {
        value = (value & ~CODELINKS_MASK) | ((newValue << CODELINKS_SHIFT) & CODELINKS_MASK);
    }

}

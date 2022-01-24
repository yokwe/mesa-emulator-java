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

    public static final int NO_VALUE = -1;

    public static GlobalWord value(int value) {
        return new GlobalWord(NO_VALUE, value, false);
    }
    public static GlobalWord fetch(int base) {
        int ra = Mesa.fetch(base);
        return new GlobalWord(ra, Mesa.readReal(ra), false);
    }
    public static GlobalWord store(int base) {
        int ra = Mesa.store(base);
        return new GlobalWord(ra, Mesa.readReal(ra), true);
    }
    private final int     ra;
    private final boolean canWrite;

    public int value;

    private GlobalWord(int ra, int value, boolean canWrite) {
        this.ra       = ra;
        this.canWrite = canWrite;
    }

    public char get() {
        return (char)value;
    }
    public void set(char newValue) {
        value = newValue;
    }
    public void write() {
        if (ra == NO_VALUE || !canWrite) throw new UnexpectedException("Unexpected");
        Mesa.writeReal(ra, (char)value);
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

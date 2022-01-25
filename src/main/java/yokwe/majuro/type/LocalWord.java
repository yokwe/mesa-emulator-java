package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// LocalWord: TYPE = RECORD[available (0:0..7): BYTE, fsi (0:8..15): FSIndex];
public final class LocalWord extends MemoryData16 {
    public static final String NAME     = "LocalWord";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =          16;

    public static final int AVAILABLE_MASK  = 0b1111_1111_0000_0000;
    public static final int AVAILABLE_SHIFT =                     8;
    public static final int FSI_MASK        = 0b0000_0000_1111_1111;
    public static final int FSI_SHIFT       =                     0;

    public LocalWord(char value) {
        super(value);
    }
    public LocalWord(int base, MemoryAccess access) {
        super(base, access);
    }


    // field access
    public int available() {
        return (value & AVAILABLE_MASK) >> AVAILABLE_SHIFT;
    }
    public void available(int newValue) {
        value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
    }

    public int fsi() {
        return (value & FSI_MASK) >> FSI_SHIFT;
    }
    public void fsi(int newValue) {
        value = (value & ~FSI_MASK) | ((newValue << FSI_SHIFT) & FSI_MASK);
    }

}

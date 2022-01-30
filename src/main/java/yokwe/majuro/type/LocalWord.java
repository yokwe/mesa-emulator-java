package yokwe.majuro.type;

// LocalWord: TYPE = RECORD[available (0:0..7): BYTE, fsi (0:8..15): FSIndex];
public class LocalWord extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();

    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;

    //
    // Constructor
    //
    public LocalWord(char value) {
        super(value);
    }
    public LocalWord(int base, MemoryAccess access) {
        super(base, access);
    }

    //
    // Bit Field
    //

    // available (0:0..7):  BYTE
    // fsi       (0:8..15): FSIndex

    private static final int AVAILABLE_MASK  = 0b1111_1111_0000_0000;
    private static final int AVAILABLE_SHIFT =                     8;
    private static final int FSI_MASK        = 0b0000_0000_1111_1111;
    private static final int FSI_SHIFT       =                     0;

    //
    // Bit Field Access Methods
    //
    public final int available() {
        return (value & AVAILABLE_MASK) >> AVAILABLE_SHIFT;
    }
    public final void available(int newValue) {
        value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
    }

    public final int fsi() {
        return (value & FSI_MASK) >> FSI_SHIFT;
    }
    public final void fsi(int newValue) {
        value = (value & ~FSI_MASK) | ((newValue << FSI_SHIFT) & FSI_MASK);
    }

}

package yokwe.majuro.type;

// StateWord: TYPE = RECORD[instByte (0:0..7): BYTE, stkPtr (0:8..15): BYTE];
public final class StateWord extends MemoryData16 {
    public static final String NAME     = "StateWord";
    public static final int    SIZE     =           1;
    public static final int    BIT_SIZE =          16;

    //
    // Constructor
    //
    public StateWord(char value) {
        super(value);
    }
    public StateWord(int base, MemoryAccess access) {
        super(base, access);
    }

    //
    // Bit Field
    //

    // instByte (0:0..7):  BYTE
    // stkPtr   (0:8..15): BYTE

    private static final int INST_BYTE_MASK  = 0b1111_1111_0000_0000;
    private static final int INST_BYTE_SHIFT =                     8;
    private static final int STK_PTR_MASK    = 0b0000_0000_1111_1111;
    private static final int STK_PTR_SHIFT   =                     0;

    //
    // Bit Field Access Methods
    //
    public int instByte() {
        return (value & INST_BYTE_MASK) >> INST_BYTE_SHIFT;
    }
    public void instByte(int newValue) {
        value = (value & ~INST_BYTE_MASK) | ((newValue << INST_BYTE_SHIFT) & INST_BYTE_MASK);
    }

    public int stkPtr() {
        return (value & STK_PTR_MASK) >> STK_PTR_SHIFT;
    }
    public void stkPtr(int newValue) {
        value = (value & ~STK_PTR_MASK) | ((newValue << STK_PTR_SHIFT) & STK_PTR_MASK);
    }

}

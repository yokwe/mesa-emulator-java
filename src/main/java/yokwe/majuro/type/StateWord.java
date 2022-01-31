package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// StateWord: TYPE = RECORD[instByte (0:0..7): BYTE, stkPtr (0:8..15): BYTE];
public final class StateWord extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final StateWord value(char value) {
        return new StateWord(value);
    }
    public static final StateWord longPointer(int base, MemoryAccess access) {
        return new StateWord(base, access);
    }
    public static final StateWord pointer(char base, MemoryAccess access) {
        return new StateWord(Mesa.lengthenMDS(base), access);
    }
    
    private StateWord(char value) {
        super(value);
    }
    private StateWord(int base, MemoryAccess access) {
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
    public final char instByte() {
        return (char)((value & INST_BYTE_MASK) >> INST_BYTE_SHIFT);
    }
    public final void instByte(char newValue) {
        value = (value & ~INST_BYTE_MASK) | ((newValue << INST_BYTE_SHIFT) & INST_BYTE_MASK);
    }
    
    public final char stkPtr() {
        return (char)((value & STK_PTR_MASK) >> STK_PTR_SHIFT);
    }
    public final void stkPtr(char newValue) {
        value = (value & ~STK_PTR_MASK) | ((newValue << STK_PTR_SHIFT) & STK_PTR_MASK);
    }
    
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// StateWord: TYPE = RECORD[instByte (0:0..7): BYTE, stkPtr (0:8..15): BYTE];
public final class StateWord extends MemoryData16 {
    public static final String NAME = "StateWord";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final StateWord value(@Mesa.CARD16 int value) {
        return new StateWord(value);
    }
    public static final StateWord value() {
        return new StateWord(0);
    }
    public static final StateWord longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new StateWord(base, access);
    }
    public static final StateWord pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new StateWord(Memory.lengthenMDS(base), access);
    }
    
    private StateWord(@Mesa.CARD16 int value) {
        super(value);
    }
    private StateWord(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // @Mesa.CARD8 is BYTE
    public final @Mesa.CARD8 int instByte() {
        return (value & INST_BYTE_MASK) >>> INST_BYTE_SHIFT;
    }
    public final StateWord instByte(@Mesa.CARD8 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
        value = (value & ~INST_BYTE_MASK) | ((newValue << INST_BYTE_SHIFT) & INST_BYTE_MASK);
        return this;
    }
    
    // @Mesa.CARD8 is BYTE
    public final @Mesa.CARD8 int stkPtr() {
        return (value & STK_PTR_MASK) >>> STK_PTR_SHIFT;
    }
    public final StateWord stkPtr(@Mesa.CARD8 int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
        value = (value & ~STK_PTR_MASK) | ((newValue << STK_PTR_SHIFT) & STK_PTR_MASK);
        return this;
    }
    
}

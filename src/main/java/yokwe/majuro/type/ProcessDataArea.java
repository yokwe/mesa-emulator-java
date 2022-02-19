package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ProcessDataArea: TYPE = RECORD[ready (0:0..15): Queue, count (1:0..15): CARDINAL, unused (2:0..15): UNSPECIFIED, available (3:0..79): ARRAY [0..4] OF UNSPECIFIED, state (8:0..127): StateAllocationTable, interrupt (16:0..511): InterruptVector, fault (48:0..255): FaultVector, block (0): ARRAY PsbIndex OF ProcessStateBlock];
public final class ProcessDataArea extends MemoryBase {
    public static final String NAME = "ProcessDataArea";
    
    public static final int WORD_SIZE =   64;
    public static final int BIT_SIZE  = 1024;
    
    //
    // Constructor
    //
    public static final ProcessDataArea longPointer(@Mesa.LONG_POINTER int base) {
        return new ProcessDataArea(base);
    }
    public static final ProcessDataArea pointer(@Mesa.SHORT_POINTER int base) {
        return new ProcessDataArea(Memory.lengthenMDS(base));
    }
    
    private ProcessDataArea(@Mesa.LONG_POINTER int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // ready (0:0..15): Queue
    private static final int OFFSET_READY = 0;
    public Queue ready(MemoryAccess access) {
        int longPointer = base + OFFSET_READY;
        return Queue.longPointer(longPointer, access);
    }
    // count (1:0..15): CARDINAL
    private static final int OFFSET_COUNT = 1;
    public CARDINAL count(MemoryAccess access) {
        int longPointer = base + OFFSET_COUNT;
        return CARDINAL.longPointer(longPointer, access);
    }
    // unused (2:0..15): UNSPECIFIED
    private static final int OFFSET_UNUSED = 2;
    public UNSPECIFIED unused(MemoryAccess access) {
        int longPointer = base + OFFSET_UNUSED;
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // available (3:0..79): ARRAY [0..4] OF UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 3;
    private static final class AvailableIndex {
        private static final ContextSubrange context = new ContextSubrange("ProcessDataArea", 0, 4);
        private static final void checkValue(int value) {
            context.check(value);
        }
    }
    public final UNSPECIFIED available(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) AvailableIndex.checkValue(index);
        int longPointer = base + OFFSET_AVAILABLE + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // state (8:0..127): StateAllocationTable
    private static final int OFFSET_STATE = 8;
    public StateAllocationTable state() {
        int longPointer = base + OFFSET_STATE;
        return StateAllocationTable.longPointer(longPointer);
    }
    // interrupt (16:0..511): InterruptVector
    private static final int OFFSET_INTERRUPT = 16;
    public InterruptVector interrupt() {
        int longPointer = base + OFFSET_INTERRUPT;
        return InterruptVector.longPointer(longPointer);
    }
    // fault (48:0..255): FaultVector
    private static final int OFFSET_FAULT = 48;
    public FaultVector fault() {
        int longPointer = base + OFFSET_FAULT;
        return FaultVector.longPointer(longPointer);
    }
    // block (0): ARRAY PsbIndex OF ProcessStateBlock
    private static final int OFFSET_BLOCK = 0;
    public final ProcessStateBlock block(int index) {
        if (Debug.ENABLE_CHECK_VALUE) PsbIndex.checkValue(index);
        int longPointer = base + OFFSET_BLOCK + (ProcessStateBlock.WORD_SIZE * index);
        return ProcessStateBlock.longPointer(longPointer);
    }
}

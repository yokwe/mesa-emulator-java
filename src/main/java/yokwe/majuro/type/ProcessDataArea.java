package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// ProcessDataArea: TYPE = RECORD[ready (0:0..15): Queue, count (1:0..15): CARDINAL, unused (2:0..15): UNSPECIFIED, available (3:0..79): ARRAY [0..4] OF UNSPECIFIED, state (8:0..127): StateAllocationTable, interrupt (16:0..511): InterruptVector, fault (48:0..255): FaultVector, block (0): ARRAY PsbIndex OF ProcessStateBlock];
public final class ProcessDataArea extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   64;
    public static final int BIT_SIZE  = 1024;
    
    //
    // Constructor
    //
    public static final ProcessDataArea longPointer(int base) {
        return new ProcessDataArea(base);
    }
    public static final ProcessDataArea pointer(char base) {
        return new ProcessDataArea(Mesa.lengthenMDS(base));
    }
    
    private ProcessDataArea(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // ready (0:0..15): Queue
    private static final int OFFSET_READY = 0;
    public Queue ready(MemoryAccess access) {
        return Queue.longPointer(base + OFFSET_READY, access);
    }
    // count (1:0..15): CARDINAL
    private static final int OFFSET_COUNT = 1;
    public CARDINAL count(MemoryAccess access) {
        return CARDINAL.longPointer(base + OFFSET_COUNT, access);
    }
    // unused (2:0..15): UNSPECIFIED
    private static final int OFFSET_UNUSED = 2;
    public UNSPECIFIED unused(MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_UNUSED, access);
    }
    // available (3:0..79): ARRAY [0..4] OF UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 3;
    public UNSPECIFIED available(int index, MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_AVAILABLE + (UNSPECIFIED.WORD_SIZE * index), access);
    }
    // state (8:0..127): StateAllocationTable
    private static final int OFFSET_STATE = 8;
    public StateAllocationTable state() {
        return StateAllocationTable.longPointer(base + OFFSET_STATE);
    }
    // interrupt (16:0..511): InterruptVector
    private static final int OFFSET_INTERRUPT = 16;
    public InterruptVector interrupt() {
        return InterruptVector.longPointer(base + OFFSET_INTERRUPT);
    }
    // fault (48:0..255): FaultVector
    private static final int OFFSET_FAULT = 48;
    public FaultVector fault() {
        return FaultVector.longPointer(base + OFFSET_FAULT);
    }
    // block (0): ARRAY PsbIndex OF ProcessStateBlock
    private static final int OFFSET_BLOCK = 0;
    public ProcessStateBlock block(int index) {
        return ProcessStateBlock.longPointer(base + OFFSET_BLOCK + (ProcessStateBlock.WORD_SIZE * index));
    }
}

package yokwe.majuro.type;

// ProcessDataArea: TYPE = RECORD[ready (0:0..15): Queue, count (1:0..15): CARDINAL, unused (2:0..15): UNSPECIFIED, available (3:0..79): ARRAY [0..4] OF UNSPECIFIED, state (8:0..127): StateAllocationTable, interrupt (16:0..511): InterruptVector, fault (48:0..255): FaultVector, block (0): ARRAY PsbIndex OF ProcessStateBlock];
public final class ProcessDataArea {
    public static final String NAME     = "ProcessDataArea";
    public static final int    SIZE     =                64;
    public static final int    BIT_SIZE =              1024;

    //
    // Constants for field access
    //
    public static final int OFFSET_READY     =  0; // ready     (0:0..15):   Queue
    public static final int OFFSET_COUNT     =  1; // count     (1:0..15):   CARDINAL
    public static final int OFFSET_UNUSED    =  2; // unused    (2:0..15):   UNSPECIFIED
    public static final int OFFSET_AVAILABLE =  3; // available (3:0..79):   ARRAY [0..4] OF UNSPECIFIED
    public static final int OFFSET_STATE     =  8; // state     (8:0..127):  StateAllocationTable
    public static final int OFFSET_INTERRUPT = 16; // interrupt (16:0..511): InterruptVector
    public static final int OFFSET_FAULT     = 48; // fault     (48:0..255): FaultVector
    public static final int OFFSET_BLOCK     =  0; // block     (0):         ARRAY PsbIndex OF ProcessStateBlock

    public final int base;

    //
    // Constructor
    //
    public ProcessDataArea(int value) {
        this.base = value;
    }

}

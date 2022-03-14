package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.type.PrincOps.UNSPECIFIED;

public final class VMDataInternal {
    
    //
    // Run: TYPE = RECORD[interval (0:0..63): VM.Interval, usage (4:0..10): Space.Usage, class (4:11..15): Space.Class, transferProcID (5:0..7): TransferProcID, startOfMapUnit (5:8..8): BOOLEAN, maybePartlyIn (5:9..9): BOOLEAN, endOfMapUnit (5:10..15): BOOLEAN, backingData (6:0..111): BackingStore.Data, swapUnitData (13:0..15): UNSPECIFIED];
    //
    public static final class Run extends MemoryBase {
        public static final String NAME = "Run";
        
        public static final int WORD_SIZE =  14;
        public static final int BIT_SIZE  = 224;
        
        // Constructor
        public static final Run longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Run(base, access);
        }
        public static final Run pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Run(Memory.lengthenMDS(base), access);
        }
        
        private Run(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // interval (0:0..63): VM.Interval
        private static final int OFFSET_INTERVAL = 0;
        public VM.Interval interval() {
            int longPointer = base + OFFSET_INTERVAL;
            return VM.Interval.longPointer(longPointer, access);
        }
        // usage (4:0..10): Space.Usage
        // FIXME  Field is not aligned
        // class (4:11..15): Space.Class
        // FIXME  Field is not aligned
        // transferProcID (5:0..7): TransferProcID
        // FIXME  Field is not aligned
        // startOfMapUnit (5:8..8): BOOLEAN
        // FIXME  Field is not aligned
        // maybePartlyIn (5:9..9): BOOLEAN
        // FIXME  Field is not aligned
        // endOfMapUnit (5:10..15): BOOLEAN
        // FIXME  Field is not aligned
        // backingData (6:0..111): BackingStore.Data
        private static final int OFFSET_BACKING_DATA = 6;
        public BackingStore.Data backingData() {
            int longPointer = base + OFFSET_BACKING_DATA;
            return BackingStore.Data.longPointer(longPointer, access);
        }
        // swapUnitData (13:0..15): UNSPECIFIED
        private static final int OFFSET_SWAP_UNIT_DATA = 13;
        public UNSPECIFIED swapUnitData() {
            int longPointer = base + OFFSET_SWAP_UNIT_DATA;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // TransferProcID: TYPE = [0..256);
    //
    public static final class TransferProcID extends MemoryData16 {
        public static final String NAME = "TransferProcID";
        
        public static final int WORD_SIZE =    1;
        public static final int BIT_SIZE  =    8;
                                                 
        public static final int MIN_VALUE =    0;
        public static final int MAX_VALUE = 0xFF;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final TransferProcID value(@Mesa.CARD16 int value) {
            return new TransferProcID(value);
        }
        public static final TransferProcID value() {
            return new TransferProcID(0);
        }
        public static final TransferProcID longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new TransferProcID(base, access);
        }
        public static final TransferProcID pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new TransferProcID(Memory.lengthenMDS(base), access);
        }
        
        private TransferProcID(@Mesa.CARD16 int value) {
            super(value);
        }
        private TransferProcID(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
}

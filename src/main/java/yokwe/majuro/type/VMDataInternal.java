package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;
import yokwe.majuro.type.PrincOps.BOOLEAN;
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
        // BIT FIELD 16 in MULTI WORD
        private static final int OFFSET_U4 = 4;
        public MemoryData16 u4 = null;
        // usage (4:0..10):  Space.Usage
        // class (4:11..15): Space.Class
        
        private static final int USAGE_MASK  = 0b1111_1111_1110_0000;
        private static final int USAGE_SHIFT =                     5;
        private static final int CLASS_MASK  = 0b0000_0000_0001_1111;
        private static final int CLASS_SHIFT =                     0;
        
        //
        // Bit Field Access Methods
        //
        // usage is BIT FIELD 16
        public final Space.Usage usage() {
            if (u4 == null) u4 = new MemoryData16(base + OFFSET_U4, access);
            return Space.Usage.value(Types.toCARD16((u4.value & USAGE_MASK) >>> USAGE_SHIFT));
        }
        public final Run usage(Space.Usage newValue) {
            if (u4 == null) u4 = new MemoryData16(base + OFFSET_U4, access);
            u4.value = Types.toCARD16((u4.value & ~USAGE_MASK) | ((newValue.value << USAGE_SHIFT) & USAGE_MASK));
            if (u4.writable()) u4.write();
            return this;
        }
        
        // clazz is BIT FIELD 16
        public final Space.Class clazz() {
            if (u4 == null) u4 = new MemoryData16(base + OFFSET_U4, access);
            return Space.Class.value(Types.toCARD16((u4.value & CLASS_MASK) >>> CLASS_SHIFT));
        }
        public final Run clazz(Space.Class newValue) {
            if (u4 == null) u4 = new MemoryData16(base + OFFSET_U4, access);
            u4.value = Types.toCARD16((u4.value & ~CLASS_MASK) | ((newValue.value << CLASS_SHIFT) & CLASS_MASK));
            if (u4.writable()) u4.write();
            return this;
        }
        
        // BIT FIELD 16 in MULTI WORD
        private static final int OFFSET_U5 = 5;
        public MemoryData16 u5 = null;
        // transferProcID (5:0..7):   TransferProcID
        // startOfMapUnit (5:8..8):   BOOLEAN
        // maybePartlyIn  (5:9..9):   BOOLEAN
        // endOfMapUnit   (5:10..15): BOOLEAN
        
        private static final int TRANSFER_PROC_ID_MASK   = 0b1111_1111_0000_0000;
        private static final int TRANSFER_PROC_ID_SHIFT  =                     8;
        private static final int START_OF_MAP_UNIT_MASK  = 0b0000_0000_1000_0000;
        private static final int START_OF_MAP_UNIT_SHIFT =                     7;
        private static final int MAYBE_PARTLY_IN_MASK    = 0b0000_0000_0100_0000;
        private static final int MAYBE_PARTLY_IN_SHIFT   =                     6;
        private static final int END_OF_MAP_UNIT_MASK    = 0b0000_0000_0011_1111;
        private static final int END_OF_MAP_UNIT_SHIFT   =                     0;
        
        //
        // Bit Field Access Methods
        //
        // transferProcID is BIT FIELD 16
        public final TransferProcID transferProcID() {
            if (u5 == null) u5 = new MemoryData16(base + OFFSET_U5, access);
            return TransferProcID.value(Types.toCARD16((u5.value & TRANSFER_PROC_ID_MASK) >>> TRANSFER_PROC_ID_SHIFT));
        }
        public final Run transferProcID(TransferProcID newValue) {
            if (u5 == null) u5 = new MemoryData16(base + OFFSET_U5, access);
            u5.value = Types.toCARD16((u5.value & ~TRANSFER_PROC_ID_MASK) | ((newValue.value << TRANSFER_PROC_ID_SHIFT) & TRANSFER_PROC_ID_MASK));
            if (u5.writable()) u5.write();
            return this;
        }
        
        // startOfMapUnit is BIT FIELD 16
        public final BOOLEAN startOfMapUnit() {
            if (u5 == null) u5 = new MemoryData16(base + OFFSET_U5, access);
            return BOOLEAN.value(Types.toCARD16((u5.value & START_OF_MAP_UNIT_MASK) >>> START_OF_MAP_UNIT_SHIFT));
        }
        public final Run startOfMapUnit(BOOLEAN newValue) {
            if (u5 == null) u5 = new MemoryData16(base + OFFSET_U5, access);
            u5.value = Types.toCARD16((u5.value & ~START_OF_MAP_UNIT_MASK) | ((newValue.value << START_OF_MAP_UNIT_SHIFT) & START_OF_MAP_UNIT_MASK));
            if (u5.writable()) u5.write();
            return this;
        }
        
        // maybePartlyIn is BIT FIELD 16
        public final BOOLEAN maybePartlyIn() {
            if (u5 == null) u5 = new MemoryData16(base + OFFSET_U5, access);
            return BOOLEAN.value(Types.toCARD16((u5.value & MAYBE_PARTLY_IN_MASK) >>> MAYBE_PARTLY_IN_SHIFT));
        }
        public final Run maybePartlyIn(BOOLEAN newValue) {
            if (u5 == null) u5 = new MemoryData16(base + OFFSET_U5, access);
            u5.value = Types.toCARD16((u5.value & ~MAYBE_PARTLY_IN_MASK) | ((newValue.value << MAYBE_PARTLY_IN_SHIFT) & MAYBE_PARTLY_IN_MASK));
            if (u5.writable()) u5.write();
            return this;
        }
        
        // endOfMapUnit is BIT FIELD 16
        public final BOOLEAN endOfMapUnit() {
            if (u5 == null) u5 = new MemoryData16(base + OFFSET_U5, access);
            return BOOLEAN.value(Types.toCARD16((u5.value & END_OF_MAP_UNIT_MASK) >>> END_OF_MAP_UNIT_SHIFT));
        }
        public final Run endOfMapUnit(BOOLEAN newValue) {
            if (u5 == null) u5 = new MemoryData16(base + OFFSET_U5, access);
            u5.value = Types.toCARD16((u5.value & ~END_OF_MAP_UNIT_MASK) | ((newValue.value << END_OF_MAP_UNIT_SHIFT) & END_OF_MAP_UNIT_MASK));
            if (u5.writable()) u5.write();
            return this;
        }
        
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

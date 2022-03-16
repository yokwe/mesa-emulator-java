package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;
import yokwe.majuro.type.PrincOps.BOOLEAN;
import yokwe.majuro.type.PrincOps.CARDINAL;

public final class ProcessorFaceExtras {
    
    //
    // MachineType: TYPE = {unused(0), altoI(1), altoII(2), altoIIXM(3), dolphin(4), dorado(5), dandelion(6), dicentra(7), daybreak(8), daisy(9), kiku(10), daylight(11), tridlion(12), dahlia(13)};
    //
    public static final class MachineType extends MemoryData16 {
        public static final String NAME = "MachineType";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 4;
        
        //
        // Enum Value Constants
        //
        public static final @Mesa.ENUM int UNUSED     = 0;
        public static final @Mesa.ENUM int ALTO_I     = 1;
        public static final @Mesa.ENUM int ALTO_II    = 2;
        public static final @Mesa.ENUM int ALTO_IIXM  = 3;
        public static final @Mesa.ENUM int DOLPHIN    = 4;
        public static final @Mesa.ENUM int DORADO     = 5;
        public static final @Mesa.ENUM int DANDELION  = 6;
        public static final @Mesa.ENUM int DICENTRA   = 7;
        public static final @Mesa.ENUM int DAYBREAK   = 8;
        public static final @Mesa.ENUM int DAISY      = 9;
        public static final @Mesa.ENUM int KIKU      = 10;
        public static final @Mesa.ENUM int DAYLIGHT  = 11;
        public static final @Mesa.ENUM int TRIDLION  = 12;
        public static final @Mesa.ENUM int DAHLIA    = 13;
        
        private static final int[] values = {
            UNUSED, ALTO_I, ALTO_II, ALTO_IIXM, DOLPHIN, DORADO, DANDELION, DICENTRA, DAYBREAK, DAISY, KIKU, DAYLIGHT, TRIDLION, DAHLIA
        };
        private static final String[] names = {
            "UNUSED", "ALTO_I", "ALTO_II", "ALTO_IIXM", "DOLPHIN", "DORADO", "DANDELION", "DICENTRA", "DAYBREAK", "DAISY", "KIKU", "DAYLIGHT", "TRIDLION", "DAHLIA"
        };
        private static final ContextEnum context = new ContextEnum(NAME, values, names);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final MachineType value(@Mesa.CARD16 int value) {
            return new MachineType(value);
        }
        public static final MachineType value() {
            return new MachineType(0);
        }
        public static final MachineType longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new MachineType(base, access);
        }
        public static final MachineType pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new MachineType(Memory.lengthenMDS(base), access);
        }
        
        private MachineType(@Mesa.CARD16 int value) {
            super(value);
        }
        private MachineType(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        public final String toString(int value) {
            return context.toString(value);
        }
    }
    
    //
    // VersionResult: TYPE = RECORD[machineType (0:0..3): MachineType, majorVersion (0:4..7): CARDINAL, unused (0:8..13): CARDINAL, floatingPoint (0:14..14): BOOLEAN, cedar (0:15..15): BOOLEAN, releaseDate (1:0..15): CARDINAL];
    //
    public static final class VersionResult extends MemoryBase {
        public static final String NAME = "VersionResult";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final VersionResult longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new VersionResult(base, access);
        }
        public static final VersionResult pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new VersionResult(Memory.lengthenMDS(base), access);
        }
        
        private VersionResult(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // BIT FIELD 16 in MULTI WORD
        private static final int OFFSET_U0 = 0;
        public MemoryData16 u0 = null;
        // machineType   (0:0..3):   MachineType
        // majorVersion  (0:4..7):   CARDINAL
        // unused        (0:8..13):  CARDINAL
        // floatingPoint (0:14..14): BOOLEAN
        // cedar         (0:15..15): BOOLEAN
        
        private static final int MACHINE_TYPE_MASK    = 0b1111_0000_0000_0000;
        private static final int MACHINE_TYPE_SHIFT   =                    12;
        private static final int MAJOR_VERSION_MASK   = 0b0000_1111_0000_0000;
        private static final int MAJOR_VERSION_SHIFT  =                     8;
        private static final int UNUSED_MASK          = 0b0000_0000_1111_1100;
        private static final int UNUSED_SHIFT         =                     2;
        private static final int FLOATING_POINT_MASK  = 0b0000_0000_0000_0010;
        private static final int FLOATING_POINT_SHIFT =                     1;
        private static final int CEDAR_MASK           = 0b0000_0000_0000_0001;
        private static final int CEDAR_SHIFT          =                     0;
        
        //
        // Bit Field Access Methods
        //
        // machineType is BIT FIELD 16
        public final MachineType machineType() {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            return MachineType.value(Types.toCARD16((u0.value & MACHINE_TYPE_MASK) >>> MACHINE_TYPE_SHIFT));
        }
        public final VersionResult machineType(MachineType newValue) {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            u0.value = Types.toCARD16((u0.value & ~MACHINE_TYPE_MASK) | ((newValue.value << MACHINE_TYPE_SHIFT) & MACHINE_TYPE_MASK));
            if (u0.writable()) u0.write();
            return this;
        }
        
        // majorVersion is BIT FIELD 16
        public final CARDINAL majorVersion() {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            return CARDINAL.value(Types.toCARD16((u0.value & MAJOR_VERSION_MASK) >>> MAJOR_VERSION_SHIFT));
        }
        public final VersionResult majorVersion(CARDINAL newValue) {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            u0.value = Types.toCARD16((u0.value & ~MAJOR_VERSION_MASK) | ((newValue.value << MAJOR_VERSION_SHIFT) & MAJOR_VERSION_MASK));
            if (u0.writable()) u0.write();
            return this;
        }
        
        // unused is BIT FIELD 16
        public final CARDINAL unused() {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            return CARDINAL.value(Types.toCARD16((u0.value & UNUSED_MASK) >>> UNUSED_SHIFT));
        }
        public final VersionResult unused(CARDINAL newValue) {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            u0.value = Types.toCARD16((u0.value & ~UNUSED_MASK) | ((newValue.value << UNUSED_SHIFT) & UNUSED_MASK));
            if (u0.writable()) u0.write();
            return this;
        }
        
        // floatingPoint is BIT FIELD 16
        public final BOOLEAN floatingPoint() {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            return BOOLEAN.value(Types.toCARD16((u0.value & FLOATING_POINT_MASK) >>> FLOATING_POINT_SHIFT));
        }
        public final VersionResult floatingPoint(BOOLEAN newValue) {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            u0.value = Types.toCARD16((u0.value & ~FLOATING_POINT_MASK) | ((newValue.value << FLOATING_POINT_SHIFT) & FLOATING_POINT_MASK));
            if (u0.writable()) u0.write();
            return this;
        }
        
        // cedar is BIT FIELD 16
        public final BOOLEAN cedar() {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            return BOOLEAN.value(Types.toCARD16((u0.value & CEDAR_MASK) >>> CEDAR_SHIFT));
        }
        public final VersionResult cedar(BOOLEAN newValue) {
            if (u0 == null) u0 = new MemoryData16(base + OFFSET_U0, access);
            u0.value = Types.toCARD16((u0.value & ~CEDAR_MASK) | ((newValue.value << CEDAR_SHIFT) & CEDAR_MASK));
            if (u0.writable()) u0.write();
            return this;
        }
        
        // releaseDate (1:0..15): CARDINAL
        private static final int OFFSET_RELEASE_DATE = 1;
        public CARDINAL releaseDate() {
            int longPointer = base + OFFSET_RELEASE_DATE;
            return CARDINAL.longPointer(longPointer, access);
        }
    }
    
    //
    // VersionWord: TYPE = RECORD[machineType (0:0..3): MachineType, majorVersion (0:4..7): CARDINAL, unused (0:8..13): CARDINAL, floatingPoint (0:14..14): BOOLEAN, cedar (0:15..15): BOOLEAN];
    //
    public static final class VersionWord extends MemoryData16 {
        public static final String NAME = "VersionWord";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final VersionWord value(@Mesa.CARD16 int value) {
            return new VersionWord(value);
        }
        public static final VersionWord value() {
            return new VersionWord(0);
        }
        public static final VersionWord longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new VersionWord(base, access);
        }
        public static final VersionWord pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new VersionWord(Memory.lengthenMDS(base), access);
        }
        
        private VersionWord(@Mesa.CARD16 int value) {
            super(value);
        }
        private VersionWord(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // machineType   (0:0..3):   MachineType
        // majorVersion  (0:4..7):   CARDINAL
        // unused        (0:8..13):  CARDINAL
        // floatingPoint (0:14..14): BOOLEAN
        // cedar         (0:15..15): BOOLEAN
        
        private static final int MACHINE_TYPE_MASK    = 0b1111_0000_0000_0000;
        private static final int MACHINE_TYPE_SHIFT   =                    12;
        private static final int MAJOR_VERSION_MASK   = 0b0000_1111_0000_0000;
        private static final int MAJOR_VERSION_SHIFT  =                     8;
        private static final int UNUSED_MASK          = 0b0000_0000_1111_1100;
        private static final int UNUSED_SHIFT         =                     2;
        private static final int FLOATING_POINT_MASK  = 0b0000_0000_0000_0010;
        private static final int FLOATING_POINT_SHIFT =                     1;
        private static final int CEDAR_MASK           = 0b0000_0000_0000_0001;
        private static final int CEDAR_SHIFT          =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.ENUM is MachineType
        public final @Mesa.ENUM int machineType() {
            return Types.toCARD16((value & MACHINE_TYPE_MASK) >>> MACHINE_TYPE_SHIFT);
        }
        public final VersionWord machineType(@Mesa.ENUM int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) MachineType.checkValue(newValue);
            value = Types.toCARD16((value & ~MACHINE_TYPE_MASK) | ((newValue << MACHINE_TYPE_SHIFT) & MACHINE_TYPE_MASK));
            return this;
        }
        
        // @Mesa.CARD16 is CARDINAL
        public final @Mesa.CARD16 int majorVersion() {
            return (value & MAJOR_VERSION_MASK) >>> MAJOR_VERSION_SHIFT;
        }
        public final VersionWord majorVersion(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) CARDINAL.checkValue(newValue);
            value = (value & ~MAJOR_VERSION_MASK) | ((newValue << MAJOR_VERSION_SHIFT) & MAJOR_VERSION_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is CARDINAL
        public final @Mesa.CARD16 int unused() {
            return (value & UNUSED_MASK) >>> UNUSED_SHIFT;
        }
        public final VersionWord unused(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) CARDINAL.checkValue(newValue);
            value = (value & ~UNUSED_MASK) | ((newValue << UNUSED_SHIFT) & UNUSED_MASK);
            return this;
        }
        
        public final boolean floatingPoint() {
            return ((value & FLOATING_POINT_MASK) >>> FLOATING_POINT_SHIFT) != 0;
        }
        public final VersionWord floatingPoint(boolean newValue) {
            value = Types.toCARD16((value & ~FLOATING_POINT_MASK) | (((newValue ? 1 : 0) << FLOATING_POINT_SHIFT) & FLOATING_POINT_MASK));
            return this;
        }
        
        public final boolean cedar() {
            return ((value & CEDAR_MASK) >>> CEDAR_SHIFT) != 0;
        }
        public final VersionWord cedar(boolean newValue) {
            value = Types.toCARD16((value & ~CEDAR_MASK) | (((newValue ? 1 : 0) << CEDAR_SHIFT) & CEDAR_MASK));
            return this;
        }
        
    }
}

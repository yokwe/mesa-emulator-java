package yokwe.majuro.mesa.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Type.*;

public final class TaggedControlLink {
    public static final int SIZE = 2;

    // offset    0  size    2  type CARD32    name data
    //   bit startBit  0  stopBit 13
    // offset    0  size    2  type CARD32    name tag
    //   bit startBit 14  stopBit 15
    // offset    0  size    2  type CARD32    name fill
    //   bit startBit 16  stopBit 31

    public static final class data {
        public static final         int SIZE       =  2;
        public static final         int OFFSET     =  0;
        public static final @CARD32 int MASK       = 0b1111_1111_1111_1100_0000_0000_0000_0000;
        public static final         int SHIFT      = 18;

        public static @CARD32 int getBit(@CARD32 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD32 int setBit(@CARD32 int value, @CARD32 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }
        public static @CARD32 int get(@LONG_POINTER int base) {
            return getBit(Memory.readDbl(base + OFFSET));
        }
        public static void set(@LONG_POINTER int base, @CARD32 int newValue) {
            Memory.modifyDbl(base + OFFSET, TaggedControlLink.data::setBit, newValue);
        }
    }
    public static final class tag {
        public static final         int SIZE       =  2;
        public static final         int OFFSET     =  0;
        public static final @CARD32 int MASK       = 0b0000_0000_0000_0011_0000_0000_0000_0000;
        public static final         int SHIFT      = 16;

        public static @CARD32 int getBit(@CARD32 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD32 int setBit(@CARD32 int value, @CARD32 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }
        public static @CARD32 int get(@LONG_POINTER int base) {
            return getBit(Memory.readDbl(base + OFFSET));
        }
        public static void set(@LONG_POINTER int base, @CARD32 int newValue) {
            Memory.modifyDbl(base + OFFSET, TaggedControlLink.tag::setBit, newValue);
        }
    }
    public static final class fill {
        public static final         int SIZE       =  2;
        public static final         int OFFSET     =  0;
        public static final @CARD32 int MASK       = 0b0000_0000_0000_0000_1111_1111_1111_1111;
        public static final         int SHIFT      = 0;

        public static @CARD32 int getBit(@CARD32 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD32 int setBit(@CARD32 int value, @CARD32 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }
        public static @CARD32 int get(@LONG_POINTER int base) {
            return getBit(Memory.readDbl(base + OFFSET));
        }
        public static void set(@LONG_POINTER int base, @CARD32 int newValue) {
            Memory.modifyDbl(base + OFFSET, TaggedControlLink.fill::setBit, newValue);
        }
    }
}

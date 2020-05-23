package yokwe.majuro.mesa.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Type.*;

public final class PsbFlags {
    public static final int SIZE = 1;

    // offset    0  size    1  type           name available
    //   bit startBit  0  stopBit  2
    // offset    0  size    1  type PsbIndex  name cleanup
    //   bit startBit  3  stopBit 12
    // offset    0  size    1  type           name reserved
    //   bit startBit 13  stopBit 13
    // offset    0  size    1  type boolean   name waiting
    //   bit startBit 14  stopBit 14
    // offset    0  size    1  type boolean   name abort
    //   bit startBit 15  stopBit 15

    public static final class cleanup {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;
        public static final         int SHIFT      =  3;
        public static final @CARD16 int MASK       = 0b0001_1111_1111_1000;

        public static @CARD16 int getBit(@CARD16 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD16 int setBit(@CARD16 int value, @CARD16 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }
        public static @CARD16 int get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(base + OFFSET));
        }
        public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
            Memory.modify(base + OFFSET, PsbFlags.cleanup::setBit, newValue);
        }
    }
    public static final class waiting {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;
        public static final         int SHIFT      =  1;
        public static final @CARD16 int MASK       = 0b0000_0000_0000_0010;

        public static @CARD16 int getBit(@CARD16 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD16 int setBit(@CARD16 int value, @CARD16 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }
        public static boolean get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(base + OFFSET)) != 0;
        }
        public static void set(@LONG_POINTER int base, boolean newValue) {
            Memory.modify(base + OFFSET, PsbFlags.waiting::setBit, (newValue ? 1 : 0));
        }
    }
    public static final class abort {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;
        public static final         int SHIFT      =  0;
        public static final @CARD16 int MASK       = 0b0000_0000_0000_0001;

        public static @CARD16 int getBit(@CARD16 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD16 int setBit(@CARD16 int value, @CARD16 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }
        public static boolean get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(base + OFFSET)) != 0;
        }
        public static void set(@LONG_POINTER int base, boolean newValue) {
            Memory.modify(base + OFFSET, PsbFlags.abort::setBit, (newValue ? 1 : 0));
        }
    }
}

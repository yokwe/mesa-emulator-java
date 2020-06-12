/*******************************************************************************
 * Copyright (c) 2020, Yasuhiro Hasegawa
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. The name of the author may not be used to endorse or promote products derived
 *      from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *******************************************************************************/
package yokwe.majuro.mesa.type;

import yokwe.majuro.mesa.Memory;

//
// BitBltArg: TYPE = RECORD[dst (0:0..31): BitAddress, dstBpl (3:0..15): INTEGER, src (4:0..47): BitAddress, srcBpl (7:0..15): INTEGER, width (8:0..15): CARDINAL, height (9:0..15): CARDINAL, flags (10:0..15): BitBltFlags, reserved (11:0..15): UNSPECIFIED];
//

public final class BitBltArg {
    public static final int SIZE = 12;

    // dst (0:0..31): BitAddress
    public static final class dst {
        public static final int SIZE = 3;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // Expand BitAddress: TYPE = RECORD[word (0:0..31): LONG_POINTER, reserved (2:0..11): UNSPECIFIED, bit (2:12..15): CARDINAL];
        //   word (0:0..31): LONG_POINTER
        public static final class word {
            public static final int SIZE = 2;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return BitBltArg.dst.getAddress(base) + OFFSET;
            }
            public static int get(int base) {
                return LONG_POINTER.get(getAddress(base));
            }
            public static void set(int base, int newValue) {
                LONG_POINTER.set(getAddress(base), newValue);
            }
        }
        //   reserved (2:0..11): UNSPECIFIED
        public static final class reserved {
            public static final int SIZE = 1;

            private static final int OFFSET = 2;
            public static int getAddress(int base) {
                return BitBltArg.dst.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b1111_1111_1111_0000;
            private static final int SHIFT = 4;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return UNSPECIFIED.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BitBltArg.dst.reserved::setBit, newValue);
            }
        }
        //   bit (2:12..15): CARDINAL
        public static final class bit {
            public static final int SIZE = 1;

            private static final int OFFSET = 2;
            public static int getAddress(int base) {
                return BitBltArg.dst.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0000_0000_0000_1111;
            private static final int SHIFT = 0;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return CARDINAL.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BitBltArg.dst.bit::setBit, newValue);
            }
        }
    }
    // dstBpl (3:0..15): INTEGER
    public static final class dstBpl {
        public static final int SIZE = 1;

        private static final int OFFSET = 3;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return INTEGER.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            INTEGER.set(getAddress(base), newValue);
        }
    }
    // src (4:0..47): BitAddress
    public static final class src {
        public static final int SIZE = 3;

        private static final int OFFSET = 4;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // Expand BitAddress: TYPE = RECORD[word (0:0..31): LONG_POINTER, reserved (2:0..11): UNSPECIFIED, bit (2:12..15): CARDINAL];
        //   word (0:0..31): LONG_POINTER
        public static final class word {
            public static final int SIZE = 2;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return BitBltArg.src.getAddress(base) + OFFSET;
            }
            public static int get(int base) {
                return LONG_POINTER.get(getAddress(base));
            }
            public static void set(int base, int newValue) {
                LONG_POINTER.set(getAddress(base), newValue);
            }
        }
        //   reserved (2:0..11): UNSPECIFIED
        public static final class reserved {
            public static final int SIZE = 1;

            private static final int OFFSET = 2;
            public static int getAddress(int base) {
                return BitBltArg.src.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b1111_1111_1111_0000;
            private static final int SHIFT = 4;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return UNSPECIFIED.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BitBltArg.src.reserved::setBit, newValue);
            }
        }
        //   bit (2:12..15): CARDINAL
        public static final class bit {
            public static final int SIZE = 1;

            private static final int OFFSET = 2;
            public static int getAddress(int base) {
                return BitBltArg.src.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0000_0000_0000_1111;
            private static final int SHIFT = 0;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return CARDINAL.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BitBltArg.src.bit::setBit, newValue);
            }
        }
    }
    // srcBpl (7:0..15): INTEGER
    public static final class srcBpl {
        public static final int SIZE = 1;

        private static final int OFFSET = 7;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return INTEGER.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            INTEGER.set(getAddress(base), newValue);
        }
    }
    // width (8:0..15): CARDINAL
    public static final class width {
        public static final int SIZE = 1;

        private static final int OFFSET = 8;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return CARDINAL.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            CARDINAL.set(getAddress(base), newValue);
        }
    }
    // height (9:0..15): CARDINAL
    public static final class height {
        public static final int SIZE = 1;

        private static final int OFFSET = 9;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return CARDINAL.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            CARDINAL.set(getAddress(base), newValue);
        }
    }
    // flags (10:0..15): BitBltFlags
    public static final class flags {
        public static final int SIZE = 1;

        private static final int OFFSET = 10;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // Expand BitBltFlags: TYPE = RECORD[direction (0:0..0): Direction, disjoint (0:1..1): BOOL, disjointItems (0:2..2): BOOL, gray (0:3..3): BOOL, srcFunc (0:4..4): SrcFunc, dstFunc (0:5..6): DstFunc, reserved (0:7..15): UNSPECIFIED];
        //   direction (0:0..0): Direction
        public static final class direction {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return BitBltArg.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b1000_0000_0000_0000;
            private static final int SHIFT = 15;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return Direction.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BitBltArg.flags.direction::setBit, newValue);
            }
        }
        //   disjoint (0:1..1): BOOL
        public static final class disjoint {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return BitBltArg.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0100_0000_0000_0000;
            private static final int SHIFT = 14;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return value;
            }
            public static boolean get(int base) {
                return getBit(Memory.fetch(getAddress(base))) != 0;
            }
            public static void set(int base, boolean newValue) {
                Memory.modify(getAddress(base), BitBltArg.flags.disjoint::setBit, (newValue ? 1 : 0));
            }
        }
        //   disjointItems (0:2..2): BOOL
        public static final class disjointItems {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return BitBltArg.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0010_0000_0000_0000;
            private static final int SHIFT = 13;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return value;
            }
            public static boolean get(int base) {
                return getBit(Memory.fetch(getAddress(base))) != 0;
            }
            public static void set(int base, boolean newValue) {
                Memory.modify(getAddress(base), BitBltArg.flags.disjointItems::setBit, (newValue ? 1 : 0));
            }
        }
        //   gray (0:3..3): BOOL
        public static final class gray {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return BitBltArg.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0001_0000_0000_0000;
            private static final int SHIFT = 12;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return value;
            }
            public static boolean get(int base) {
                return getBit(Memory.fetch(getAddress(base))) != 0;
            }
            public static void set(int base, boolean newValue) {
                Memory.modify(getAddress(base), BitBltArg.flags.gray::setBit, (newValue ? 1 : 0));
            }
        }
        //   srcFunc (0:4..4): SrcFunc
        public static final class srcFunc {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return BitBltArg.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0000_1000_0000_0000;
            private static final int SHIFT = 11;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return SrcFunc.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BitBltArg.flags.srcFunc::setBit, newValue);
            }
        }
        //   dstFunc (0:5..6): DstFunc
        public static final class dstFunc {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return BitBltArg.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0000_0110_0000_0000;
            private static final int SHIFT = 9;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return DstFunc.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BitBltArg.flags.dstFunc::setBit, newValue);
            }
        }
        //   reserved (0:7..15): UNSPECIFIED
        public static final class reserved {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return BitBltArg.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0000_0001_1111_1111;
            private static final int SHIFT = 0;

            private static int getBit(int value) {
                return (checkValue(value) & MASK) >>> SHIFT;
            }
            private static int setBit(int value, int newValue) {
                return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
            }

            private static final int MAX = MASK >>> SHIFT;
            private static final Subrange SUBRANGE = new Subrange(0, MAX);

            public static int checkValue(int value) {
                SUBRANGE.check(value);
                return UNSPECIFIED.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BitBltArg.flags.reserved::setBit, newValue);
            }
        }
    }
    // reserved (11:0..15): UNSPECIFIED
    public static final class reserved {
        public static final int SIZE = 1;

        private static final int OFFSET = 11;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return UNSPECIFIED.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            UNSPECIFIED.set(getAddress(base), newValue);
        }
    }
}

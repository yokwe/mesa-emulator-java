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

import yokwe.majuro.mesa.Debug;

//
// BitBltFlags: TYPE = RECORD[direction (0:0..0): Direction, disjoint (0:1..1): BOOL, disjointItems (0:2..2): BOOL, gray (0:3..3): BOOL, srcFunc (0:4..4): SrcFunc, dstFunc (0:5..6): DstFunc, reserved (0:7..15): UNSPECIFIED];
//

public final class BitBltFlags {
    public static final int SIZE = 1;

    // direction (0:0..0): Direction
    public static final class direction {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int SHIFT = 15;
        private static final int MASK  = 0b1000_0000_0000_0000;
        private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
        public static int checkValue(int value) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return Direction.checkValue(BITFIELD, value);
            } else {
                return value;
            }
        }
        public static int get(int base) {
            return Direction.get(BITFIELD, getAddress(base));
        }
        public static void set(int base, int newValue) {
            Direction.set(BITFIELD, getAddress(base), newValue);
        }
    }
    // disjoint (0:1..1): BOOL
    public static final class disjoint {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int SHIFT = 14;
        private static final int MASK  = 0b0100_0000_0000_0000;
        private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
        public static int checkValue(int value) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return BOOL.checkValue(BITFIELD, value);
            } else {
                return value;
            }
        }
        public static boolean get(int base) {
            return BOOL.get(BITFIELD, getAddress(base));
        }
        public static void set(int base, boolean newValue) {
            BOOL.set(BITFIELD, getAddress(base), newValue);
        }
    }
    // disjointItems (0:2..2): BOOL
    public static final class disjointItems {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int SHIFT = 13;
        private static final int MASK  = 0b0010_0000_0000_0000;
        private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
        public static int checkValue(int value) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return BOOL.checkValue(BITFIELD, value);
            } else {
                return value;
            }
        }
        public static boolean get(int base) {
            return BOOL.get(BITFIELD, getAddress(base));
        }
        public static void set(int base, boolean newValue) {
            BOOL.set(BITFIELD, getAddress(base), newValue);
        }
    }
    // gray (0:3..3): BOOL
    public static final class gray {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int SHIFT = 12;
        private static final int MASK  = 0b0001_0000_0000_0000;
        private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
        public static int checkValue(int value) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return BOOL.checkValue(BITFIELD, value);
            } else {
                return value;
            }
        }
        public static boolean get(int base) {
            return BOOL.get(BITFIELD, getAddress(base));
        }
        public static void set(int base, boolean newValue) {
            BOOL.set(BITFIELD, getAddress(base), newValue);
        }
    }
    // srcFunc (0:4..4): SrcFunc
    public static final class srcFunc {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int SHIFT = 11;
        private static final int MASK  = 0b0000_1000_0000_0000;
        private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
        public static int checkValue(int value) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return SrcFunc.checkValue(BITFIELD, value);
            } else {
                return value;
            }
        }
        public static int get(int base) {
            return SrcFunc.get(BITFIELD, getAddress(base));
        }
        public static void set(int base, int newValue) {
            SrcFunc.set(BITFIELD, getAddress(base), newValue);
        }
    }
    // dstFunc (0:5..6): DstFunc
    public static final class dstFunc {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int SHIFT = 9;
        private static final int MASK  = 0b0000_0110_0000_0000;
        private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
        public static int checkValue(int value) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return DstFunc.checkValue(BITFIELD, value);
            } else {
                return value;
            }
        }
        public static int get(int base) {
            return DstFunc.get(BITFIELD, getAddress(base));
        }
        public static void set(int base, int newValue) {
            DstFunc.set(BITFIELD, getAddress(base), newValue);
        }
    }
    // reserved (0:7..15): UNSPECIFIED
    public static final class reserved {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int SHIFT = 0;
        private static final int MASK  = 0b0000_0001_1111_1111;
        private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
        public static int checkValue(int value) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return UNSPECIFIED.checkValue(BITFIELD, value);
            } else {
                return value;
            }
        }
        public static int get(int base) {
            return UNSPECIFIED.get(BITFIELD, getAddress(base));
        }
        public static void set(int base, int newValue) {
            UNSPECIFIED.set(BITFIELD, getAddress(base), newValue);
        }
    }
}

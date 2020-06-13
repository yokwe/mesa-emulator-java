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
// LocalOverhead: TYPE = RECORD[word (0:0..15): LocalWord, returnlink (1:0..15): UNSPECIFIED, globallink (2:0..15): POINTER, pc (3:0..15): CARDINAL, local (4): BLOCK];
//

public final class LocalOverhead {
    public static final int SIZE = 4;

    // word (0:0..15): LocalWord
    public static final class word {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // Expand Record in Record
        //   LocalWord: TYPE = RECORD[available (0:0..7): BYTE, fsi (0:8..15): FSIndex];
        //     available (0:0..7): BYTE
        public static final class available {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return LocalOverhead.word.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b1111_1111_0000_0000;
            private static final int SHIFT = 8;

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
                return BYTE.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), LocalOverhead.word.available::setBit, newValue);
            }
        }
        //     fsi (0:8..15): FSIndex
        public static final class fsi {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return LocalOverhead.word.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0000_0000_1111_1111;
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
                return FSIndex.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), LocalOverhead.word.fsi::setBit, newValue);
            }
        }
    }
    // returnlink (1:0..15): UNSPECIFIED
    public static final class returnlink {
        public static final int SIZE = 1;

        private static final int OFFSET = 1;
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
    // globallink (2:0..15): POINTER
    public static final class globallink {
        public static final int SIZE = 1;

        private static final int OFFSET = 2;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return POINTER.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            POINTER.set(getAddress(base), newValue);
        }
    }
    // pc (3:0..15): CARDINAL
    public static final class pc {
        public static final int SIZE = 1;

        private static final int OFFSET = 3;
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
    // local (4): BLOCK
    public static final class local {
        public static final int SIZE = 0;

        private static final int OFFSET = 4;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // BLOCK: TYPE = ARRAY CARDINAL [0..0) OF UNSPECIFIED;
        //   CARDINAL: TYPE = CARDINAL [0..65536)
        //   UNSPECIFIED: TYPE = UNSPECIFIED [0..65536)
        private static final int ELEMENT_SIZE = 1;
        public static int getAddress(int base, int index) {
            return LocalOverhead.local.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
        }

        private static int checkIndex(int index) {
            return CARDINAL.checkValue(index);
        }

        public static int get(int base, int index) {
            return UNSPECIFIED.get(LocalOverhead.local.getAddress(base, checkIndex(index)));
        }
        public static void set(int base, int index, int newValue) {
            UNSPECIFIED.set(LocalOverhead.local.getAddress(base, checkIndex(index)), newValue);
        }
    }
}

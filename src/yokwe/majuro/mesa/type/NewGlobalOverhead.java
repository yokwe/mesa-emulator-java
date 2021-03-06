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
// NewGlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, global (2): BLOCK];
//

public final class NewGlobalOverhead {
    public static final int SIZE = 2;

    // available (0:0..15): UNSPECIFIED
    public static final class available {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
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
    // word (1:0..15): GlobalWord
    public static final class word {
        public static final int SIZE = 1;

        private static final int OFFSET = 1;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // Expand Record in Record
        //   GlobalWord: TYPE = RECORD[gfi (0:0..13): GFTIndex, trapxfers (0:14..14): BOOL, codelinks (0:15..15): BOOL];
        //     gfi (0:0..13): GFTIndex
        public static final class gfi {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return NewGlobalOverhead.word.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 2;
            private static final int MASK  = 0b1111_1111_1111_1100;
            private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
            public static int checkValue(int value) {
                if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                    return GFTIndex.checkValue(BITFIELD, value);
                } else {
                    return value;
                }
            }
            public static int get(int base) {
                return GFTIndex.get(BITFIELD, getAddress(base));
            }
            public static void set(int base, int newValue) {
                GFTIndex.set(BITFIELD, getAddress(base), newValue);
            }
        }
        //     trapxfers (0:14..14): BOOL
        public static final class trapxfers {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return NewGlobalOverhead.word.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 1;
            private static final int MASK  = 0b0000_0000_0000_0010;
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
        //     codelinks (0:15..15): BOOL
        public static final class codelinks {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return NewGlobalOverhead.word.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 0;
            private static final int MASK  = 0b0000_0000_0000_0001;
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
    }
    // global (2): BLOCK
    public static final class global {
        public static final int SIZE = 0;

        private static final int OFFSET = 2;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // BLOCK: TYPE = ARRAY CARDINAL [0..0) OF UNSPECIFIED;
        //   CARDINAL: TYPE = CARDINAL [0..65536)
        //   UNSPECIFIED: TYPE = UNSPECIFIED [0..65536)
        private static final int ELEMENT_SIZE = 1;
        public static int getAddress(int base, int index) {
            return NewGlobalOverhead.global.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
        }

        private static int checkIndex(int index) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return CARDINAL.checkValue(index);
            } else {
                return index;
            }
        }

        public static int get(int base, int index) {
            return UNSPECIFIED.get(NewGlobalOverhead.global.getAddress(base, checkIndex(index)));
        }
        public static void set(int base, int index, int newValue) {
            UNSPECIFIED.set(NewGlobalOverhead.global.getAddress(base, checkIndex(index)), newValue);
        }
    }
}

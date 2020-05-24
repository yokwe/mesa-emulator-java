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
import yokwe.majuro.mesa.Type.*;

public final class StateVector {
    public static final int SIZE = 18;

    // offset    0  size   14  type           name stack
    //   array  index CARD8             size    1  length  14  element CARD16
    // offset   14  size    1  type StateWord  name word
    // offset   15  size    1  type POINTER   name frame
    // offset   16  size    2  type           name data
    //   array  index CARD8             size    1  length   2  element CARD16

    public static final class stack {
        public static final         int SIZE       = 14;
        public static final         int OFFSET     =  0;

        public static final         int ARRAY_SIZE =  1;
        public static final         int ARRAY_LEN  = 14;

        public static int getAddress(@LONG_POINTER int base, int index) {
            return base + OFFSET + (ARRAY_SIZE * index);
        }
        public static @CARD16 int get(@LONG_POINTER int base, int index) {
            return Memory.fetch(getAddress(base, index));
        }
        public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
            Memory.store(getAddress(base, index), newValue);
        }
    }
    public static final class word {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     = 14;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }

        //   StateWord  breakByte
        public static final class breakByte {
            public static final int OFFSET = word.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static @CARD8 int get(@LONG_POINTER int base) {
                return StateWord.breakByte.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, @CARD8 int newValue) {
                StateWord.breakByte.set(getAddress(base), newValue);
            }
        }
        //   StateWord  stkptr
        public static final class stkptr {
            public static final int OFFSET = word.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static @CARD8 int get(@LONG_POINTER int base) {
                return StateWord.stkptr.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, @CARD8 int newValue) {
                StateWord.stkptr.set(getAddress(base), newValue);
            }
        }
    }
    public static final class frame {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     = 15;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }

        public static @POINTER int get(@LONG_POINTER int base) {
            return Memory.fetch(getAddress(base));
        }
        public static void set(@LONG_POINTER int base, @POINTER int newValue) {
            Memory.store(getAddress(base), newValue);
        }
    }
    public static final class data {
        public static final         int SIZE       =  2;
        public static final         int OFFSET     = 16;

        public static final         int ARRAY_SIZE =  1;
        public static final         int ARRAY_LEN  =  2;

        public static int getAddress(@LONG_POINTER int base, int index) {
            return base + OFFSET + (ARRAY_SIZE * index);
        }
        public static @CARD16 int get(@LONG_POINTER int base, int index) {
            return Memory.fetch(getAddress(base, index));
        }
        public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
            Memory.store(getAddress(base, index), newValue);
        }
    }
}
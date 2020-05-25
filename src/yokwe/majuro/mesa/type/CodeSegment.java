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

public final class CodeSegment {
    public static final int SIZE = 4;

    // offset    0  size    1  type CARD8     name globalFsi
    //   bit startBit  0  stopBit  7
    // offset    0  size    1  type CARD8     name nlinks
    //   bit startBit  8  stopBit 15
    // offset    1  size    1  type boolean   name stops
    //   bit startBit  0  stopBit  0
    // offset    1  size    1  type           name available
    //   bit startBit  1  stopBit 15
    // offset    2  size    1  type CARD16    name mainBody
    // offset    3  size    1  type CARD16    name catchCode

    public static final class globalFsi {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;
        public static final         int SHIFT      =  8;
        public static final @CARD16 int MASK       = 0b1111_1111_0000_0000;

        public static @CARD16 int getBit(@CARD16 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD16 int setBit(@CARD16 int value, @CARD16 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        public static @CARD8 int get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(@LONG_POINTER int base, @CARD8 int newValue) {
            Memory.modify(getAddress(base), CodeSegment.globalFsi::setBit, newValue);
        }
    }
    public static final class nlinks {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;
        public static final         int SHIFT      =  0;
        public static final @CARD16 int MASK       = 0b0000_0000_1111_1111;

        public static @CARD16 int getBit(@CARD16 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD16 int setBit(@CARD16 int value, @CARD16 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        public static @CARD8 int get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(@LONG_POINTER int base, @CARD8 int newValue) {
            Memory.modify(getAddress(base), CodeSegment.nlinks::setBit, newValue);
        }
    }
    public static final class stops {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  1;
        public static final         int SHIFT      = 15;
        public static final @CARD16 int MASK       = 0b1000_0000_0000_0000;

        public static @CARD16 int getBit(@CARD16 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD16 int setBit(@CARD16 int value, @CARD16 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        public static boolean get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(getAddress(base))) != 0;
        }
        public static void set(@LONG_POINTER int base, boolean newValue) {
            Memory.modify(getAddress(base), CodeSegment.stops::setBit, (newValue ? 1 : 0));
        }
    }
    public static final class mainBody {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  2;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        public static @CARD16 int get(@LONG_POINTER int base) {
            return Memory.fetch(getAddress(base));
        }
        public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
            Memory.store(getAddress(base), newValue);
        }
    }
    public static final class catchCode {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  3;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        public static @CARD16 int get(@LONG_POINTER int base) {
            return Memory.fetch(getAddress(base));
        }
        public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
            Memory.store(getAddress(base), newValue);
        }
    }
}

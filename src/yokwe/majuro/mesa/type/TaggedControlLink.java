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
        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }

        public static @CARD32 int get(@LONG_POINTER int base) {
            return getBit(Memory.readDbl(getAddress(base)));
        }
        public static void set(@LONG_POINTER int base, @CARD32 int newValue) {
            Memory.modifyDbl(getAddress(base), TaggedControlLink.data::setBit, newValue);
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
        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }

        public static @CARD32 int get(@LONG_POINTER int base) {
            return getBit(Memory.readDbl(getAddress(base)));
        }
        public static void set(@LONG_POINTER int base, @CARD32 int newValue) {
            Memory.modifyDbl(getAddress(base), TaggedControlLink.tag::setBit, newValue);
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
        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }

        public static @CARD32 int get(@LONG_POINTER int base) {
            return getBit(Memory.readDbl(getAddress(base)));
        }
        public static void set(@LONG_POINTER int base, @CARD32 int newValue) {
            Memory.modifyDbl(getAddress(base), TaggedControlLink.fill::setBit, newValue);
        }
    }
}

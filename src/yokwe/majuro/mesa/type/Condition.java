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

public final class Condition {
    public static final int SIZE = 1;

    // offset    0  size    1  type           name reserved
    //   bit startBit  0  stopBit  2
    // offset    0  size    1  type PsbIndex  name tail
    //   bit startBit  3  stopBit 12
    // offset    0  size    1  type           name available
    //   bit startBit 13  stopBit 13
    // offset    0  size    1  type boolean   name abortable
    //   bit startBit 14  stopBit 14
    // offset    0  size    1  type boolean   name wakeup
    //   bit startBit 15  stopBit 15

    public static final class tail {
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

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        public static @CARD16 int get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
            Memory.modify(getAddress(base), Condition.tail::setBit, newValue);
        }
    }
    public static final class abortable {
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

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        public static boolean get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(getAddress(base))) != 0;
        }
        public static void set(@LONG_POINTER int base, boolean newValue) {
            Memory.modify(getAddress(base), Condition.abortable::setBit, (newValue ? 1 : 0));
        }
    }
    public static final class wakeup {
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

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        public static boolean get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(getAddress(base))) != 0;
        }
        public static void set(@LONG_POINTER int base, boolean newValue) {
            Memory.modify(getAddress(base), Condition.wakeup::setBit, (newValue ? 1 : 0));
        }
    }
}

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

public final class ConditionVariable {
    public static final int SIZE = 2;

    // offset    0  size    1  type Condition  name condition
    // offset    1  size    1  type CARD16    name timeout

    public static final class condition {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }

        //   Condition  tail
        public static final class tail {
            public static final int OFFSET = condition.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static @CARD16 int get(@LONG_POINTER int base) {
                return Condition.tail.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
                Condition.tail.set(getAddress(base), newValue);
            }
        }
        //   Condition  abortable
        public static final class abortable {
            public static final int OFFSET = condition.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static boolean get(@LONG_POINTER int base) {
                return Condition.abortable.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, boolean newValue) {
                Condition.abortable.set(getAddress(base), newValue);
            }
        }
        //   Condition  wakeup
        public static final class wakeup {
            public static final int OFFSET = condition.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static boolean get(@LONG_POINTER int base) {
                return Condition.wakeup.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, boolean newValue) {
                Condition.wakeup.set(getAddress(base), newValue);
            }
        }
    }
    public static final class timeout {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  1;

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

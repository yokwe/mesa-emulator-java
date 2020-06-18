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
// InterruptVector: TYPE = ARRAY InterruptLevel OF InterruptItem;
//   InterruptLevel: TYPE = CARDINAL [0..16)
//   InterruptItem: TYPE = RECORD[condition (0:0..15): Condition, available (1:0..15): UNSPECIFIED]
//

public final class InterruptVector {
    public static final int SIZE = 32;

    private static final int ELEMENT_SIZE = 2;
    public static int getAddress(int base, int index) {
        return base + (checkIndex(index) * ELEMENT_SIZE);
    }

    private static int checkIndex(int index) {
        if (Debug.ENABLE_TYPE_CHECK_VALUE) {
            return InterruptLevel.checkValue(index);
        } else {
            return index;
        }
    }

    // Expand Record in Array
    //   InterruptItem: TYPE = RECORD[condition (0:0..15): Condition, available (1:0..15): UNSPECIFIED];
    //     condition (0:0..15): Condition
    public static final class condition {
        public static int getAddress(int base, int index) {
            return InterruptItem.condition.getAddress(InterruptVector.getAddress(base, index));
        }
        // Expand Record in Array
        //   Condition: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..13): UNSPECIFIED, abortable (0:14..14): BOOL, wakeup (0:15..15): BOOL];
        //     reserved (0:0..2): UNSPECIFIED
        public static final class reserved {
            public static int get(int base, int index) {
                return Condition.reserved.get(InterruptVector.getAddress(base, index));
            }
            public static void set(int base, int index, int newValue) {
                Condition.reserved.set(InterruptVector.getAddress(base, index), newValue);
            }
        }
        //     tail (0:3..12): PsbIndex
        public static final class tail {
            public static int get(int base, int index) {
                return Condition.tail.get(InterruptVector.getAddress(base, index));
            }
            public static void set(int base, int index, int newValue) {
                Condition.tail.set(InterruptVector.getAddress(base, index), newValue);
            }
        }
        //     available (0:13..13): UNSPECIFIED
        public static final class available {
            public static int get(int base, int index) {
                return Condition.available.get(InterruptVector.getAddress(base, index));
            }
            public static void set(int base, int index, int newValue) {
                Condition.available.set(InterruptVector.getAddress(base, index), newValue);
            }
        }
        //     abortable (0:14..14): BOOL
        public static final class abortable {
            public static boolean get(int base, int index) {
                return Condition.abortable.get(InterruptVector.getAddress(base, index));
            }
            public static void set(int base, int index, boolean newValue) {
                Condition.abortable.set(InterruptVector.getAddress(base, index), newValue);
            }
        }
        //     wakeup (0:15..15): BOOL
        public static final class wakeup {
            public static boolean get(int base, int index) {
                return Condition.wakeup.get(InterruptVector.getAddress(base, index));
            }
            public static void set(int base, int index, boolean newValue) {
                Condition.wakeup.set(InterruptVector.getAddress(base, index), newValue);
            }
        }
    }
    //     available (1:0..15): UNSPECIFIED
    public static final class available {
        public static int getAddress(int base, int index) {
            return InterruptItem.available.getAddress(InterruptVector.getAddress(base, index));
        }
        public static int get(int base, int index) {
            return InterruptItem.available.get(InterruptVector.getAddress(base, index));
        }
        public static void set(int base, int index, int newValue) {
            InterruptItem.available.set(InterruptVector.getAddress(base, index), newValue);
        }
    }
}

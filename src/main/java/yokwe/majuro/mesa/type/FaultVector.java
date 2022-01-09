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
// FaultVector: TYPE = ARRAY FaultIndex OF FaultQueue;
//   FaultIndex: TYPE = CARDINAL [0..8)
//   FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition]
//

public final class FaultVector {
    public static final int SIZE = 16;

    private static final int ELEMENT_SIZE = 2;
    public static int getAddress(int base, int index) {
        return base + (checkIndex(index) * ELEMENT_SIZE);
    }

    private static int checkIndex(int index) {
        if (Debug.ENABLE_TYPE_CHECK_VALUE) {
            return FaultIndex.checkValue(index);
        } else {
            return index;
        }
    }

    // Expand Record in Array
    //   FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition];
    //     queue (0:0..15): Queue
    public static final class queue {
        public static int getAddress(int base, int index) {
            return FaultQueue.queue.getAddress(FaultVector.getAddress(base, index));
        }
        // Expand Record in Array
        //   Queue: TYPE = RECORD[reserved1 (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, reserved2 (0:13..15): UNSPECIFIED];
        //     reserved1 (0:0..2): UNSPECIFIED
        public static final class reserved1 {
            public static int get(int base, int index) {
                return Queue.reserved1.get(FaultVector.getAddress(base, index));
            }
            public static void set(int base, int index, int newValue) {
                Queue.reserved1.set(FaultVector.getAddress(base, index), newValue);
            }
        }
        //     tail (0:3..12): PsbIndex
        public static final class tail {
            public static int get(int base, int index) {
                return Queue.tail.get(FaultVector.getAddress(base, index));
            }
            public static void set(int base, int index, int newValue) {
                Queue.tail.set(FaultVector.getAddress(base, index), newValue);
            }
        }
        //     reserved2 (0:13..15): UNSPECIFIED
        public static final class reserved2 {
            public static int get(int base, int index) {
                return Queue.reserved2.get(FaultVector.getAddress(base, index));
            }
            public static void set(int base, int index, int newValue) {
                Queue.reserved2.set(FaultVector.getAddress(base, index), newValue);
            }
        }
    }
    //     condition (1:0..15): Condition
    public static final class condition {
        public static int getAddress(int base, int index) {
            return FaultQueue.condition.getAddress(FaultVector.getAddress(base, index));
        }
        // Expand Record in Array
        //   Condition: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..13): UNSPECIFIED, abortable (0:14..14): BOOL, wakeup (0:15..15): BOOL];
        //     reserved (0:0..2): UNSPECIFIED
        public static final class reserved {
            public static int get(int base, int index) {
                return Condition.reserved.get(FaultVector.getAddress(base, index));
            }
            public static void set(int base, int index, int newValue) {
                Condition.reserved.set(FaultVector.getAddress(base, index), newValue);
            }
        }
        //     tail (0:3..12): PsbIndex
        public static final class tail {
            public static int get(int base, int index) {
                return Condition.tail.get(FaultVector.getAddress(base, index));
            }
            public static void set(int base, int index, int newValue) {
                Condition.tail.set(FaultVector.getAddress(base, index), newValue);
            }
        }
        //     available (0:13..13): UNSPECIFIED
        public static final class available {
            public static int get(int base, int index) {
                return Condition.available.get(FaultVector.getAddress(base, index));
            }
            public static void set(int base, int index, int newValue) {
                Condition.available.set(FaultVector.getAddress(base, index), newValue);
            }
        }
        //     abortable (0:14..14): BOOL
        public static final class abortable {
            public static boolean get(int base, int index) {
                return Condition.abortable.get(FaultVector.getAddress(base, index));
            }
            public static void set(int base, int index, boolean newValue) {
                Condition.abortable.set(FaultVector.getAddress(base, index), newValue);
            }
        }
        //     wakeup (0:15..15): BOOL
        public static final class wakeup {
            public static boolean get(int base, int index) {
                return Condition.wakeup.get(FaultVector.getAddress(base, index));
            }
            public static void set(int base, int index, boolean newValue) {
                Condition.wakeup.set(FaultVector.getAddress(base, index), newValue);
            }
        }
    }
}

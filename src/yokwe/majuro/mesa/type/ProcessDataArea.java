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
import yokwe.majuro.mesa.Memory;

//
// ProcessDataArea: TYPE = RECORD[vp (0:0..1023): SELECT OVERLAID * FROM header(0) => [ready (0:0..15): Queue, count (1:0..15): CARDINAL, unused (2:0..15): UNSPECIFIED, available (3:0..79): ARRAY CARDINAL [0..4] OF UNSPECIFIED, state (8:0..127): StateAllocationTable, interrupt (16:0..511): InterruptVector, fault (48:0..255): FaultVector], blocks(1) => [block (0): ARRAY PsbIndex OF ProcessStateBlock] ENDCASE];
//

public final class ProcessDataArea {
    public static final int SIZE = 8192;

    // vp (0:0..1023): SELECT OVERLAID * FROM header(0) => [ready (0:0..15): Queue, count (1:0..15): CARDINAL, unused (2:0..15): UNSPECIFIED, available (3:0..79): ARRAY CARDINAL [0..4] OF UNSPECIFIED, state (8:0..127): StateAllocationTable, interrupt (16:0..511): InterruptVector, fault (48:0..255): FaultVector], blocks(1) => [block (0): ARRAY PsbIndex OF ProcessStateBlock] ENDCASE
    public static final class vp {
        public static final int SIZE = 8192;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // SELECT OVERLAID * FROM header(0) => [ready (0:0..15): Queue, count (1:0..15): CARDINAL, unused (2:0..15): UNSPECIFIED, available (3:0..79): ARRAY CARDINAL [0..4] OF UNSPECIFIED, state (8:0..127): StateAllocationTable, interrupt (16:0..511): InterruptVector, fault (48:0..255): FaultVector], blocks(1) => [block (0): ARRAY PsbIndex OF ProcessStateBlock] ENDCASE
        // OVERLAID_ANON
        public static class TagType {
            public static final int SIZE = 1;

            // enum value
            public static final int HEADER =  0;
            public static final int BLOCKS =  1;

            private static final Enum ENUM = Enum.builder().add(HEADER, "HEADER").add(BLOCKS, "BLOCKS").build();
            public static String toString(int value) {
                return ENUM.toString(value);
            }
            public static int checkValue(int value) {
                if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                    return ENUM.checkValue(value);
                } else {
                    return value;
                }
            }
            public static int get(int base) {
                return checkValue(Memory.fetch(base));
            }
            public static void set(int base, int newValue) {
                Memory.store(base, checkValue(newValue));
            }
        }
        // header(0) => [ready (0:0..15): Queue, count (1:0..15): CARDINAL, unused (2:0..15): UNSPECIFIED, available (3:0..79): ARRAY CARDINAL [0..4] OF UNSPECIFIED, state (8:0..127): StateAllocationTable, interrupt (16:0..511): InterruptVector, fault (48:0..255): FaultVector]
        public static final class header {
            public static final int TAG  = ProcessDataArea.vp.TagType.HEADER;
            public static final int SIZE = 64;
            public static int getAddress(int base) {
                return ProcessDataArea.vp.getAddress(base);
            }

            // ready (0:0..15): Queue
            public static final class ready {
                public static final int SIZE = 1;

                private static final int OFFSET = 0;
                public static int getAddress(int base) {
                    return ProcessDataArea.vp.header.getAddress(base) + OFFSET;
                }
                // Expand Record in Record
                //   Queue: TYPE = RECORD[reserved1 (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, reserved2 (0:13..15): UNSPECIFIED];
                //     reserved1 (0:0..2): UNSPECIFIED
                public static final class reserved1 {
                    public static final int SIZE = 1;

                    private static final int OFFSET = 0;
                    public static int getAddress(int base) {
                        return ProcessDataArea.vp.header.ready.getAddress(base) + OFFSET;
                    }
                    private static final int SHIFT = 13;
                    private static final int MASK  = 0b1110_0000_0000_0000;
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
                //     tail (0:3..12): PsbIndex
                public static final class tail {
                    public static final int SIZE = 1;

                    private static final int OFFSET = 0;
                    public static int getAddress(int base) {
                        return ProcessDataArea.vp.header.ready.getAddress(base) + OFFSET;
                    }
                    private static final int SHIFT = 3;
                    private static final int MASK  = 0b0001_1111_1111_1000;
                    private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
                    public static int checkValue(int value) {
                        if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                            return PsbIndex.checkValue(BITFIELD, value);
                        } else {
                            return value;
                        }
                    }
                    public static int get(int base) {
                        return PsbIndex.get(BITFIELD, getAddress(base));
                    }
                    public static void set(int base, int newValue) {
                        PsbIndex.set(BITFIELD, getAddress(base), newValue);
                    }
                }
                //     reserved2 (0:13..15): UNSPECIFIED
                public static final class reserved2 {
                    public static final int SIZE = 1;

                    private static final int OFFSET = 0;
                    public static int getAddress(int base) {
                        return ProcessDataArea.vp.header.ready.getAddress(base) + OFFSET;
                    }
                    private static final int SHIFT = 0;
                    private static final int MASK  = 0b0000_0000_0000_0111;
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

            // count (1:0..15): CARDINAL
            public static final class count {
                public static final int SIZE = 1;

                private static final int OFFSET = 1;
                public static int getAddress(int base) {
                    return ProcessDataArea.vp.header.getAddress(base) + OFFSET;
                }
                public static int get(int base) {
                    return CARDINAL.get(getAddress(base));
                }
                public static void set(int base, int newValue) {
                    CARDINAL.set(getAddress(base), newValue);
                }
            }

            // unused (2:0..15): UNSPECIFIED
            public static final class unused {
                public static final int SIZE = 1;

                private static final int OFFSET = 2;
                public static int getAddress(int base) {
                    return ProcessDataArea.vp.header.getAddress(base) + OFFSET;
                }
                public static int get(int base) {
                    return UNSPECIFIED.get(getAddress(base));
                }
                public static void set(int base, int newValue) {
                    UNSPECIFIED.set(getAddress(base), newValue);
                }
            }

            // available (3:0..79): ARRAY CARDINAL [0..4] OF UNSPECIFIED
            public static final class available {
                public static final int SIZE = 5;

                private static final int OFFSET = 3;
                public static int getAddress(int base) {
                    return ProcessDataArea.vp.header.getAddress(base) + OFFSET;
                }
                // ProcessDataArea#header#available: TYPE = ARRAY CARDINAL [0..4] OF UNSPECIFIED;
                //   CARDINAL: TYPE = CARDINAL [0..65536)
                //   UNSPECIFIED: TYPE = UNSPECIFIED [0..65536)
                private static final int ELEMENT_SIZE = 1;
                public static int getAddress(int base, int index) {
                    return ProcessDataArea.vp.header.available.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
                }

                private static final int INDEX_MIN    = 0;
                private static final int INDEX_MAX    = 4;
                private static final Subrange INDEX_SUBRANGE = new Subrange(INDEX_MIN, INDEX_MAX);
                private static int checkIndex(int index) {
                    if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                        return INDEX_SUBRANGE.checkValue(index);
                    } else {
                        return index;
                    }
                }

                public static int get(int base, int index) {
                    return UNSPECIFIED.get(ProcessDataArea.vp.header.available.getAddress(base, checkIndex(index)));
                }
                public static void set(int base, int index, int newValue) {
                    UNSPECIFIED.set(ProcessDataArea.vp.header.available.getAddress(base, checkIndex(index)), newValue);
                }
            }

            // state (8:0..127): StateAllocationTable
            public static final class state {
                public static final int SIZE = 8;

                private static final int OFFSET = 8;
                public static int getAddress(int base) {
                    return ProcessDataArea.vp.header.getAddress(base) + OFFSET;
                }
                // StateAllocationTable: TYPE = ARRAY Priority OF POINTER;
                //   Priority: TYPE = CARDINAL [0..7]
                //   POINTER: TYPE = POINTER [0..65536)
                private static final int ELEMENT_SIZE = 1;
                public static int getAddress(int base, int index) {
                    return ProcessDataArea.vp.header.state.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
                }

                private static int checkIndex(int index) {
                    if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                        return Priority.checkValue(index);
                    } else {
                        return index;
                    }
                }

                public static int get(int base, int index) {
                    return POINTER.get(ProcessDataArea.vp.header.state.getAddress(base, checkIndex(index)));
                }
                public static void set(int base, int index, int newValue) {
                    POINTER.set(ProcessDataArea.vp.header.state.getAddress(base, checkIndex(index)), newValue);
                }
            }

            // interrupt (16:0..511): InterruptVector
            public static final class interrupt {
                public static final int SIZE = 32;

                private static final int OFFSET = 16;
                public static int getAddress(int base) {
                    return ProcessDataArea.vp.header.getAddress(base) + OFFSET;
                }
                // InterruptVector: TYPE = ARRAY InterruptLevel OF InterruptItem;
                //   InterruptLevel: TYPE = CARDINAL [0..16)
                //   InterruptItem: TYPE = RECORD[condition (0:0..15): Condition, available (1:0..15): UNSPECIFIED]
                private static final int ELEMENT_SIZE = 2;
                public static int getAddress(int base, int index) {
                    return ProcessDataArea.vp.header.interrupt.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
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
                        return InterruptItem.condition.getAddress(ProcessDataArea.vp.header.interrupt.getAddress(base, index));
                    }
                    // Expand Record in Array
                    //   Condition: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..13): UNSPECIFIED, abortable (0:14..14): BOOL, wakeup (0:15..15): BOOL];
                    //     reserved (0:0..2): UNSPECIFIED
                    public static final class reserved {
                        public static int get(int base, int index) {
                            return Condition.reserved.get(ProcessDataArea.vp.header.interrupt.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            Condition.reserved.set(ProcessDataArea.vp.header.interrupt.getAddress(base, index), newValue);
                        }
                    }
                    //     tail (0:3..12): PsbIndex
                    public static final class tail {
                        public static int get(int base, int index) {
                            return Condition.tail.get(ProcessDataArea.vp.header.interrupt.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            Condition.tail.set(ProcessDataArea.vp.header.interrupt.getAddress(base, index), newValue);
                        }
                    }
                    //     available (0:13..13): UNSPECIFIED
                    public static final class available {
                        public static int get(int base, int index) {
                            return Condition.available.get(ProcessDataArea.vp.header.interrupt.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            Condition.available.set(ProcessDataArea.vp.header.interrupt.getAddress(base, index), newValue);
                        }
                    }
                    //     abortable (0:14..14): BOOL
                    public static final class abortable {
                        public static boolean get(int base, int index) {
                            return Condition.abortable.get(ProcessDataArea.vp.header.interrupt.getAddress(base, index));
                        }
                        public static void set(int base, int index, boolean newValue) {
                            Condition.abortable.set(ProcessDataArea.vp.header.interrupt.getAddress(base, index), newValue);
                        }
                    }
                    //     wakeup (0:15..15): BOOL
                    public static final class wakeup {
                        public static boolean get(int base, int index) {
                            return Condition.wakeup.get(ProcessDataArea.vp.header.interrupt.getAddress(base, index));
                        }
                        public static void set(int base, int index, boolean newValue) {
                            Condition.wakeup.set(ProcessDataArea.vp.header.interrupt.getAddress(base, index), newValue);
                        }
                    }
                }
                //     available (1:0..15): UNSPECIFIED
                public static final class available {
                    public static int getAddress(int base, int index) {
                        return InterruptItem.available.getAddress(ProcessDataArea.vp.header.interrupt.getAddress(base, index));
                    }
                    public static int get(int base, int index) {
                        return InterruptItem.available.get(ProcessDataArea.vp.header.interrupt.getAddress(base, index));
                    }
                    public static void set(int base, int index, int newValue) {
                        InterruptItem.available.set(ProcessDataArea.vp.header.interrupt.getAddress(base, index), newValue);
                    }
                }
            }

            // fault (48:0..255): FaultVector
            public static final class fault {
                public static final int SIZE = 16;

                private static final int OFFSET = 48;
                public static int getAddress(int base) {
                    return ProcessDataArea.vp.header.getAddress(base) + OFFSET;
                }
                // FaultVector: TYPE = ARRAY FaultIndex OF FaultQueue;
                //   FaultIndex: TYPE = CARDINAL [0..8)
                //   FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition]
                private static final int ELEMENT_SIZE = 2;
                public static int getAddress(int base, int index) {
                    return ProcessDataArea.vp.header.fault.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
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
                        return FaultQueue.queue.getAddress(ProcessDataArea.vp.header.fault.getAddress(base, index));
                    }
                    // Expand Record in Array
                    //   Queue: TYPE = RECORD[reserved1 (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, reserved2 (0:13..15): UNSPECIFIED];
                    //     reserved1 (0:0..2): UNSPECIFIED
                    public static final class reserved1 {
                        public static int get(int base, int index) {
                            return Queue.reserved1.get(ProcessDataArea.vp.header.fault.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            Queue.reserved1.set(ProcessDataArea.vp.header.fault.getAddress(base, index), newValue);
                        }
                    }
                    //     tail (0:3..12): PsbIndex
                    public static final class tail {
                        public static int get(int base, int index) {
                            return Queue.tail.get(ProcessDataArea.vp.header.fault.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            Queue.tail.set(ProcessDataArea.vp.header.fault.getAddress(base, index), newValue);
                        }
                    }
                    //     reserved2 (0:13..15): UNSPECIFIED
                    public static final class reserved2 {
                        public static int get(int base, int index) {
                            return Queue.reserved2.get(ProcessDataArea.vp.header.fault.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            Queue.reserved2.set(ProcessDataArea.vp.header.fault.getAddress(base, index), newValue);
                        }
                    }
                }
                //     condition (1:0..15): Condition
                public static final class condition {
                    public static int getAddress(int base, int index) {
                        return FaultQueue.condition.getAddress(ProcessDataArea.vp.header.fault.getAddress(base, index));
                    }
                    // Expand Record in Array
                    //   Condition: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..13): UNSPECIFIED, abortable (0:14..14): BOOL, wakeup (0:15..15): BOOL];
                    //     reserved (0:0..2): UNSPECIFIED
                    public static final class reserved {
                        public static int get(int base, int index) {
                            return Condition.reserved.get(ProcessDataArea.vp.header.fault.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            Condition.reserved.set(ProcessDataArea.vp.header.fault.getAddress(base, index), newValue);
                        }
                    }
                    //     tail (0:3..12): PsbIndex
                    public static final class tail {
                        public static int get(int base, int index) {
                            return Condition.tail.get(ProcessDataArea.vp.header.fault.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            Condition.tail.set(ProcessDataArea.vp.header.fault.getAddress(base, index), newValue);
                        }
                    }
                    //     available (0:13..13): UNSPECIFIED
                    public static final class available {
                        public static int get(int base, int index) {
                            return Condition.available.get(ProcessDataArea.vp.header.fault.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            Condition.available.set(ProcessDataArea.vp.header.fault.getAddress(base, index), newValue);
                        }
                    }
                    //     abortable (0:14..14): BOOL
                    public static final class abortable {
                        public static boolean get(int base, int index) {
                            return Condition.abortable.get(ProcessDataArea.vp.header.fault.getAddress(base, index));
                        }
                        public static void set(int base, int index, boolean newValue) {
                            Condition.abortable.set(ProcessDataArea.vp.header.fault.getAddress(base, index), newValue);
                        }
                    }
                    //     wakeup (0:15..15): BOOL
                    public static final class wakeup {
                        public static boolean get(int base, int index) {
                            return Condition.wakeup.get(ProcessDataArea.vp.header.fault.getAddress(base, index));
                        }
                        public static void set(int base, int index, boolean newValue) {
                            Condition.wakeup.set(ProcessDataArea.vp.header.fault.getAddress(base, index), newValue);
                        }
                    }
                }
            }
        }
        // blocks(1) => [block (0): ARRAY PsbIndex OF ProcessStateBlock]
        public static final class blocks {
            public static final int TAG  = ProcessDataArea.vp.TagType.BLOCKS;
            public static final int SIZE = 8192;
            public static int getAddress(int base) {
                return ProcessDataArea.vp.getAddress(base);
            }

            // block (0): ARRAY PsbIndex OF ProcessStateBlock
            public static final class block {
                public static final int SIZE = 8192;

                private static final int OFFSET = 0;
                public static int getAddress(int base) {
                    return ProcessDataArea.vp.blocks.getAddress(base) + OFFSET;
                }
                // ProcessDataArea#blocks#block: TYPE = ARRAY PsbIndex OF ProcessStateBlock;
                //   PsbIndex: TYPE = CARDINAL [0..1024)
                //   ProcessStateBlock: TYPE = RECORD[link (0:0..15): PsbLink, flags (1:0..15): PsbFlags, context (2:0..15): POINTER, timeout (3:0..15): CARDINAL, mds (4:0..15): CARDINAL, available (5:0..15): UNSPECIFIED, stickty (6:0..31): LONG_UNSPECIFIED]
                private static final int ELEMENT_SIZE = 8;
                public static int getAddress(int base, int index) {
                    return ProcessDataArea.vp.blocks.block.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
                }

                private static int checkIndex(int index) {
                    if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                        return PsbIndex.checkValue(index);
                    } else {
                        return index;
                    }
                }

                // Expand Record in Array
                //   ProcessStateBlock: TYPE = RECORD[link (0:0..15): PsbLink, flags (1:0..15): PsbFlags, context (2:0..15): POINTER, timeout (3:0..15): CARDINAL, mds (4:0..15): CARDINAL, available (5:0..15): UNSPECIFIED, stickty (6:0..31): LONG_UNSPECIFIED];
                //     link (0:0..15): PsbLink
                public static final class link {
                    public static int getAddress(int base, int index) {
                        return ProcessStateBlock.link.getAddress(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    // Expand Record in Array
                    //   PsbLink: TYPE = RECORD[priority (0:0..2): Priority, next (0:3..12): PsbIndex, failed (0:13..13): BOOL, permanent (0:14..14): BOOL, preempted (0:15..15): BOOL];
                    //     priority (0:0..2): Priority
                    public static final class priority {
                        public static int get(int base, int index) {
                            return PsbLink.priority.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            PsbLink.priority.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                    //     next (0:3..12): PsbIndex
                    public static final class next {
                        public static int get(int base, int index) {
                            return PsbLink.next.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            PsbLink.next.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                    //     failed (0:13..13): BOOL
                    public static final class failed {
                        public static boolean get(int base, int index) {
                            return PsbLink.failed.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, boolean newValue) {
                            PsbLink.failed.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                    //     permanent (0:14..14): BOOL
                    public static final class permanent {
                        public static boolean get(int base, int index) {
                            return PsbLink.permanent.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, boolean newValue) {
                            PsbLink.permanent.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                    //     preempted (0:15..15): BOOL
                    public static final class preempted {
                        public static boolean get(int base, int index) {
                            return PsbLink.preempted.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, boolean newValue) {
                            PsbLink.preempted.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                }
                //     flags (1:0..15): PsbFlags
                public static final class flags {
                    public static int getAddress(int base, int index) {
                        return ProcessStateBlock.flags.getAddress(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    // Expand Record in Array
                    //   PsbFlags: TYPE = RECORD[available (0:0..2): UNSPECIFIED, cleanup (0:3..12): PsbIndex, reserved (0:13..13): UNSPECIFIED, waiting (0:14..14): BOOL, abort (0:15..15): BOOL];
                    //     available (0:0..2): UNSPECIFIED
                    public static final class available {
                        public static int get(int base, int index) {
                            return PsbFlags.available.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            PsbFlags.available.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                    //     cleanup (0:3..12): PsbIndex
                    public static final class cleanup {
                        public static int get(int base, int index) {
                            return PsbFlags.cleanup.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            PsbFlags.cleanup.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                    //     reserved (0:13..13): UNSPECIFIED
                    public static final class reserved {
                        public static int get(int base, int index) {
                            return PsbFlags.reserved.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, int newValue) {
                            PsbFlags.reserved.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                    //     waiting (0:14..14): BOOL
                    public static final class waiting {
                        public static boolean get(int base, int index) {
                            return PsbFlags.waiting.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, boolean newValue) {
                            PsbFlags.waiting.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                    //     abort (0:15..15): BOOL
                    public static final class abort {
                        public static boolean get(int base, int index) {
                            return PsbFlags.abort.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                        }
                        public static void set(int base, int index, boolean newValue) {
                            PsbFlags.abort.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                        }
                    }
                }
                //     context (2:0..15): POINTER
                public static final class context {
                    public static int getAddress(int base, int index) {
                        return ProcessStateBlock.context.getAddress(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static int get(int base, int index) {
                        return ProcessStateBlock.context.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static void set(int base, int index, int newValue) {
                        ProcessStateBlock.context.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                    }
                }
                //     timeout (3:0..15): CARDINAL
                public static final class timeout {
                    public static int getAddress(int base, int index) {
                        return ProcessStateBlock.timeout.getAddress(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static int get(int base, int index) {
                        return ProcessStateBlock.timeout.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static void set(int base, int index, int newValue) {
                        ProcessStateBlock.timeout.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                    }
                }
                //     mds (4:0..15): CARDINAL
                public static final class mds {
                    public static int getAddress(int base, int index) {
                        return ProcessStateBlock.mds.getAddress(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static int get(int base, int index) {
                        return ProcessStateBlock.mds.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static void set(int base, int index, int newValue) {
                        ProcessStateBlock.mds.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                    }
                }
                //     available (5:0..15): UNSPECIFIED
                public static final class available {
                    public static int getAddress(int base, int index) {
                        return ProcessStateBlock.available.getAddress(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static int get(int base, int index) {
                        return ProcessStateBlock.available.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static void set(int base, int index, int newValue) {
                        ProcessStateBlock.available.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                    }
                }
                //     stickty (6:0..31): LONG_UNSPECIFIED
                public static final class stickty {
                    public static int getAddress(int base, int index) {
                        return ProcessStateBlock.stickty.getAddress(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static int get(int base, int index) {
                        return ProcessStateBlock.stickty.get(ProcessDataArea.vp.blocks.block.getAddress(base, index));
                    }
                    public static void set(int base, int index, int newValue) {
                        ProcessStateBlock.stickty.set(ProcessDataArea.vp.blocks.block.getAddress(base, index), newValue);
                    }
                }
            }
        }
    }
}

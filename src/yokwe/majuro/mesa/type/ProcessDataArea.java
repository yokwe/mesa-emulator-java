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

public final class ProcessDataArea {
    public static final int SIZE = 8192;

    // offset    0  size    1  type Queue     name ready
    // offset    1  size    1  type CARD16    name count
    // offset    2  size    1  type           name unused
    // offset    3  size    5  type           name available
    // offset    8  size    8  type           name state
    //   array  index CARD16            size    1  length   8  element CARD16
    // offset   16  size   32  type           name interrupt
    //   array  index InterruptLevle    size    2  length  16  element InterruptItem
    // offset   48  size   16  type           name fault
    //   array  index FaultIndex        size    2  length   8  element FaultQueue
    // offset    0  size 8192  type           name block
    //   array  index PsbIndex          size    8  length 1024  element ProcessStateBlock

    public static final class ready {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }

        //   Queue  tail
        public static final class tail {
            public static final int OFFSET = ready.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static @CARD16 int get(@LONG_POINTER int base) {
                return Queue.tail.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
                Queue.tail.set(getAddress(base), newValue);
            }
        }
    }
    public static final class count {
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
    public static final class state {
        public static final         int SIZE       =  8;
        public static final         int OFFSET     =  8;

        public static final         int ARRAY_SIZE =  1;
        public static final         int ARRAY_LEN  =  8;

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
    public static final class interrupt {
        public static final         int SIZE       = 32;
        public static final         int OFFSET     = 16;

        public static final         int ARRAY_SIZE =  2;
        public static final         int ARRAY_LEN  = 16;

        public static int getAddress(@LONG_POINTER int base, int index) {
            return base + OFFSET + (ARRAY_SIZE * index);
        }
        // InterruptItem  condition  Condition
        public static final class condition {
            public static final int OFFSET = interrupt.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            // Condition  tail  CARD16
            public static final class tail {
                public static final int OFFSET = interrupt.condition.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static @CARD16 int get(@LONG_POINTER int base, int index) {
                    return Condition.tail.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                    Condition.tail.set(getAddress(base, index), newValue);
                }
            }
            // Condition  abortable  boolean
            public static final class abortable {
                public static final int OFFSET = interrupt.condition.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static boolean get(@LONG_POINTER int base, int index) {
                    return Condition.abortable.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, boolean newValue) {
                    Condition.abortable.set(getAddress(base, index), newValue);
                }
            }
            // Condition  wakeup  boolean
            public static final class wakeup {
                public static final int OFFSET = interrupt.condition.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static boolean get(@LONG_POINTER int base, int index) {
                    return Condition.wakeup.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, boolean newValue) {
                    Condition.wakeup.set(getAddress(base, index), newValue);
                }
            }
        }
    }
    public static final class fault {
        public static final         int SIZE       = 16;
        public static final         int OFFSET     = 48;

        public static final         int ARRAY_SIZE =  2;
        public static final         int ARRAY_LEN  =  8;

        public static int getAddress(@LONG_POINTER int base, int index) {
            return base + OFFSET + (ARRAY_SIZE * index);
        }
        // FaultQueue  queue  Queue
        public static final class queue {
            public static final int OFFSET = fault.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            // Queue  tail  CARD16
            public static final class tail {
                public static final int OFFSET = fault.queue.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static @CARD16 int get(@LONG_POINTER int base, int index) {
                    return Queue.tail.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                    Queue.tail.set(getAddress(base, index), newValue);
                }
            }
        }
        // FaultQueue  condition  Condition
        public static final class condition {
            public static final int OFFSET = fault.OFFSET +  1;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            // Condition  tail  CARD16
            public static final class tail {
                public static final int OFFSET = fault.condition.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static @CARD16 int get(@LONG_POINTER int base, int index) {
                    return Condition.tail.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                    Condition.tail.set(getAddress(base, index), newValue);
                }
            }
            // Condition  abortable  boolean
            public static final class abortable {
                public static final int OFFSET = fault.condition.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static boolean get(@LONG_POINTER int base, int index) {
                    return Condition.abortable.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, boolean newValue) {
                    Condition.abortable.set(getAddress(base, index), newValue);
                }
            }
            // Condition  wakeup  boolean
            public static final class wakeup {
                public static final int OFFSET = fault.condition.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static boolean get(@LONG_POINTER int base, int index) {
                    return Condition.wakeup.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, boolean newValue) {
                    Condition.wakeup.set(getAddress(base, index), newValue);
                }
            }
        }
    }
    public static final class block {
        public static final         int SIZE       = 8192;
        public static final         int OFFSET     =  0;

        public static final         int ARRAY_SIZE =  8;
        public static final         int ARRAY_LEN  = 1024;

        public static int getAddress(@LONG_POINTER int base, int index) {
            return base + OFFSET + (ARRAY_SIZE * index);
        }
        // ProcessStateBlock  link  PsbLink
        public static final class link {
            public static final int OFFSET = block.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            // PsbLink  priority  CARD16
            public static final class priority {
                public static final int OFFSET = block.link.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static @CARD16 int get(@LONG_POINTER int base, int index) {
                    return PsbLink.priority.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                    PsbLink.priority.set(getAddress(base, index), newValue);
                }
            }
            // PsbLink  next  CARD16
            public static final class next {
                public static final int OFFSET = block.link.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static @CARD16 int get(@LONG_POINTER int base, int index) {
                    return PsbLink.next.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                    PsbLink.next.set(getAddress(base, index), newValue);
                }
            }
            // PsbLink  failed  boolean
            public static final class failed {
                public static final int OFFSET = block.link.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static boolean get(@LONG_POINTER int base, int index) {
                    return PsbLink.failed.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, boolean newValue) {
                    PsbLink.failed.set(getAddress(base, index), newValue);
                }
            }
            // PsbLink  permanent  boolean
            public static final class permanent {
                public static final int OFFSET = block.link.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static boolean get(@LONG_POINTER int base, int index) {
                    return PsbLink.permanent.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, boolean newValue) {
                    PsbLink.permanent.set(getAddress(base, index), newValue);
                }
            }
            // PsbLink  preempted  boolean
            public static final class preempted {
                public static final int OFFSET = block.link.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static boolean get(@LONG_POINTER int base, int index) {
                    return PsbLink.preempted.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, boolean newValue) {
                    PsbLink.preempted.set(getAddress(base, index), newValue);
                }
            }
        }
        // ProcessStateBlock  flags  PsbFlags
        public static final class flags {
            public static final int OFFSET = block.OFFSET +  1;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            // PsbFlags  cleanup  CARD16
            public static final class cleanup {
                public static final int OFFSET = block.flags.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static @CARD16 int get(@LONG_POINTER int base, int index) {
                    return PsbFlags.cleanup.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                    PsbFlags.cleanup.set(getAddress(base, index), newValue);
                }
            }
            // PsbFlags  waiting  boolean
            public static final class waiting {
                public static final int OFFSET = block.flags.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static boolean get(@LONG_POINTER int base, int index) {
                    return PsbFlags.waiting.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, boolean newValue) {
                    PsbFlags.waiting.set(getAddress(base, index), newValue);
                }
            }
            // PsbFlags  abort  boolean
            public static final class abort {
                public static final int OFFSET = block.flags.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static boolean get(@LONG_POINTER int base, int index) {
                    return PsbFlags.abort.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, boolean newValue) {
                    PsbFlags.abort.set(getAddress(base, index), newValue);
                }
            }
        }
        // ProcessStateBlock  conext  Context
        public static final class conext {
            public static final int OFFSET = block.OFFSET +  2;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            // Context  frame  CARD16
            public static final class frame {
                public static final int OFFSET = block.conext.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static @CARD16 int get(@LONG_POINTER int base, int index) {
                    return Context.frame.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                    Context.frame.set(getAddress(base, index), newValue);
                }
            }
            // Context  state  CARD16
            public static final class state {
                public static final int OFFSET = block.conext.OFFSET +  0;

                public static int getAddress(@LONG_POINTER int base, int index) {
                    return base + OFFSET + (ARRAY_SIZE * index);
                }
                public static @CARD16 int get(@LONG_POINTER int base, int index) {
                    return Context.state.get(getAddress(base, index));
                }
                public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                    Context.state.set(getAddress(base, index), newValue);
                }
            }
        }
        // ProcessStateBlock  timeout  CARD16
        public static final class timeout {
            public static final int OFFSET = block.OFFSET +  3;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            public static @CARD16 int get(@LONG_POINTER int base, int index) {
                return ProcessStateBlock.timeout.get(getAddress(base, index));
            }
            public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                ProcessStateBlock.timeout.set(getAddress(base, index), newValue);
            }
        }
        // ProcessStateBlock  mds  CARD16
        public static final class mds {
            public static final int OFFSET = block.OFFSET +  4;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            public static @CARD16 int get(@LONG_POINTER int base, int index) {
                return ProcessStateBlock.mds.get(getAddress(base, index));
            }
            public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                ProcessStateBlock.mds.set(getAddress(base, index), newValue);
            }
        }
        // ProcessStateBlock  data  CARD16
        public static final class data {
            public static final int OFFSET = block.OFFSET +  5;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            public static @CARD16 int get(@LONG_POINTER int base, int index) {
                return ProcessStateBlock.data.get(getAddress(base, index));
            }
            public static void set(@LONG_POINTER int base, int index, @CARD16 int newValue) {
                ProcessStateBlock.data.set(getAddress(base, index), newValue);
            }
        }
        // ProcessStateBlock  sticky  CARD32
        public static final class sticky {
            public static final int OFFSET = block.OFFSET +  6;

            public static int getAddress(@LONG_POINTER int base, int index) {
                return base + OFFSET + (ARRAY_SIZE * index);
            }
            public static @CARD32 int get(@LONG_POINTER int base, int index) {
                return ProcessStateBlock.sticky.get(getAddress(base, index));
            }
            public static void set(@LONG_POINTER int base, int index, @CARD32 int newValue) {
                ProcessStateBlock.sticky.set(getAddress(base, index), newValue);
            }
        }
    }
}

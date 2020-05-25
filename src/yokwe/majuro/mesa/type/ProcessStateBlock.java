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

public final class ProcessStateBlock {
    public static final int SIZE = 8;

    // offset    0  size    1  type PsbLink   name link
    // offset    1  size    1  type PsbFlags  name flags
    // offset    2  size    1  type Context   name conext
    // offset    3  size    1  type CARD16    name timeout
    // offset    4  size    1  type CARD16    name mds
    // offset    5  size    1  type CARD16    name data
    // offset    6  size    2  type CARD32    name sticky

    public static final class link {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        //   PsbLink  priority
        public static final class priority {
            public static final int OFFSET = link.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static @CARD16 int get(@LONG_POINTER int base) {
                return PsbLink.priority.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
                PsbLink.priority.set(getAddress(base), newValue);
            }
        }
        //   PsbLink  next
        public static final class next {
            public static final int OFFSET = link.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static @CARD16 int get(@LONG_POINTER int base) {
                return PsbLink.next.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
                PsbLink.next.set(getAddress(base), newValue);
            }
        }
        //   PsbLink  failed
        public static final class failed {
            public static final int OFFSET = link.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static boolean get(@LONG_POINTER int base) {
                return PsbLink.failed.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, boolean newValue) {
                PsbLink.failed.set(getAddress(base), newValue);
            }
        }
        //   PsbLink  permanent
        public static final class permanent {
            public static final int OFFSET = link.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static boolean get(@LONG_POINTER int base) {
                return PsbLink.permanent.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, boolean newValue) {
                PsbLink.permanent.set(getAddress(base), newValue);
            }
        }
        //   PsbLink  preempted
        public static final class preempted {
            public static final int OFFSET = link.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static boolean get(@LONG_POINTER int base) {
                return PsbLink.preempted.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, boolean newValue) {
                PsbLink.preempted.set(getAddress(base), newValue);
            }
        }
    }
    public static final class flags {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  1;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        //   PsbFlags  cleanup
        public static final class cleanup {
            public static final int OFFSET = flags.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static @CARD16 int get(@LONG_POINTER int base) {
                return PsbFlags.cleanup.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
                PsbFlags.cleanup.set(getAddress(base), newValue);
            }
        }
        //   PsbFlags  waiting
        public static final class waiting {
            public static final int OFFSET = flags.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static boolean get(@LONG_POINTER int base) {
                return PsbFlags.waiting.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, boolean newValue) {
                PsbFlags.waiting.set(getAddress(base), newValue);
            }
        }
        //   PsbFlags  abort
        public static final class abort {
            public static final int OFFSET = flags.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static boolean get(@LONG_POINTER int base) {
                return PsbFlags.abort.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, boolean newValue) {
                PsbFlags.abort.set(getAddress(base), newValue);
            }
        }
    }
    public static final class conext {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  2;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        //   Context  frame
        public static final class frame {
            public static final int OFFSET = conext.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static @CARD16 int get(@LONG_POINTER int base) {
                return Context.frame.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
                Context.frame.set(getAddress(base), newValue);
            }
        }
        //   Context  state
        public static final class state {
            public static final int OFFSET = conext.OFFSET +  0;

            public static int getAddress(@LONG_POINTER int base) {
                return base + OFFSET;
            }
            public static @CARD16 int get(@LONG_POINTER int base) {
                return Context.state.get(getAddress(base));
            }
            public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
                Context.state.set(getAddress(base), newValue);
            }
        }
    }
    public static final class timeout {
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
    public static final class mds {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  4;

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
    public static final class data {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  5;

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
    public static final class sticky {
        public static final         int SIZE       =  2;
        public static final         int OFFSET     =  6;

        public static int getAddress(@LONG_POINTER int base) {
            return base + OFFSET;
        }
        public static @CARD32 int get(@LONG_POINTER int base) {
            return Memory.readDbl(getAddress(base));
        }
        public static void set(@LONG_POINTER int base, @CARD32 int newValue) {
            Memory.writeDbl(getAddress(base), newValue);
        }
    }
}

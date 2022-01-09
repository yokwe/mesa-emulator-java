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
package yokwe.majuro.mesa;

import yokwe.util.UnexpectedException;
import yokwe.majuro.mesa.Type.LongNumber;

public final class Processor {
	public static final int StackDepth = Constant.cSS;
	
	// 3.3.2 Evaluation Stack
	public static final /* CARD16 */ int[] stack = new int[StackDepth];
	public static       /* CARD16 */ int   SP; // [0..StackDepth)
	
	// 3.3.2 Evaluation Stack
	public static int stackCount() {
		return SP;
	}
	public static void push(/* CARD16 */ int data) {
		if (SP == StackDepth) ControlTransfers.stackError();
		stack[SP++] = data;
	}
	public static /* CARD16 */ int pop() {
		if (SP == 0) ControlTransfers.stackError();
		return stack[--SP];
	}
	// Note that double-word quantities are placed on the stack so that
	// the least significant word is at the lower-numbered stack index.
	public static void pushLong(/* CARD32 */ int data) {
//		Long t = {data};
//		Push(t.low);
//		Push(t.high);
		push(LongNumber.lowHalf(data));
		push(LongNumber.highHalf(data));
	}
	public static /* CARD32 */ int popLong() {
//		Long t;
//		t.high = Pop();
//		t.low = Pop();
//		return t.u;
		int t0 = pop();
		int t1 = pop();
		return LongNumber.make(t0, t1);
	}
	public static void minimalStack() {
		if (SP != 0) ControlTransfers.stackError();
	}

	public static void recover() {
		if (SP == StackDepth) ControlTransfers.stackError();
		SP++;
	}
	public static void discard() {
		if (SP == 0) ControlTransfers.stackError();
		SP--;
	}

	
	// 3.3.3 Data and Status Registers
	// Processor ID
	public static final short[] PID = new short[4];
	// Maintenance Panel
	public static /* CARD16 */ int MP;
	// Xfer trap status - 9.5.5
	public static /* CARD16 */ int XTS;
	
	// 3.3.1 Control Registers
	// PsbIndex - 10.1.1
	public static /* CARD16 */       int PSB;
	public static /* LONG_POINTER */ int GF;  // LONG POINTER TO GlobalVarables
	public static /* CARD16 */       int GFI;

	// 4.5 Instruction Execution
	public static /* CARD8 */  int breakByte;
	public static /* CARD16 */ int savedPC;
	public static /* CARD16 */ int savedSP;
	
	
	//
	//
	//
	@SuppressWarnings("serial")
	public static final class AbortRuntimeException extends RuntimeException {
		AbortRuntimeException() {
			//    message
			//          cause
			//                enableSuppression
			//                       writableStackTrace
			super(null, null, true, Debug.ENABLE_ABORT_STACK_TRACE);
		}
	};
	
	public static void abort() {
		throw new AbortRuntimeException();
	}
	
	public static void error() {
		throw new UnexpectedException("ERROR");
	}
}

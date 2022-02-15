package yokwe.majuro.mesa;

import yokwe.majuro.UnexpectedException;

public class Processor {
	public static final int StackDepth = Constants.cSS;
	
	
	public static char PC;
	
	// Main Data Space
	public static int MDS;

	
	// 3.3.2 Evaluation Stack
	public static final int[] stack = new int[StackDepth];
	public static       int    SP; // [0..StackDepth)
	
	// 3.3.2 Evaluation Stack
	public static int stackCount() {
		return SP;
	}
	public static void push(char data) {
		if (SP == StackDepth) ControlTransfers.stackError();
		stack[SP++] = data;
	}
	public static char pop() {
		if (SP == 0) ControlTransfers.stackError();
		return (char)(stack[--SP]);
	}

	public static void pushLong(int data) {
//		Long t = {data};
//		Push(t.low);
//		Push(t.high);
		push(Types.lowHalf(data));
		push(Types.highHalf(data));
	}
	public static int popLong() {
//		Long t;
//		t.high = Pop();
//		t.low = Pop();
//		return t.u;
		char high = pop();
		char low  = pop();
		return Types.makeLong(high, low);
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
	public static final char[] PID = new char[4];
	// Maintenance Panel
	public static char MP;
	// Xfer trap status - 9.5.5
	public static char XTS;
	
	// 3.3.1 Control Registers
	// PsbIndex - 10.1.1
	public static char PSB;
	public static int  GF;  // LONG POINTER TO GlobalVarables
	public static char GFI;

	// 4.5 Instruction Execution
	public static int breakByte;
	public static int savedPC;
	public static int savedSP;


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

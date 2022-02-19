package yokwe.majuro.mesa;

import yokwe.majuro.UnexpectedException;

public final class Processor {
	//
	// Variables
	//
	
	// 3.3.1 Control Registers
	public static char PSB;
	public static int  MDS;
	public static char LF;
	public static int  GF;  // LONG POINTER TO GlobalVarables
	public static char GFI;
	
	// public static int  CB;
	// public static char PC

	// 3.3.2 Evaluation Stack
	public static final int   StackDepth = Constants.cSS;
	public static final int[] stack      = new int[StackDepth];
	public static       int   SP; // [0..StackDepth)

	// 3.3.3 Data and Status Registers
	public static final char[] PID = new char[4];
	public static char MP;
	public static int  IT;
	//public static char WM;
	public static char WP;
	public static char WDC;
	public static char PTC;
	public static char XTS;
	
	// 4.5 Instruction Execution
	public static int     breakByte;
	public static int     savedPC;
	public static int     savedSP;
	public static boolean running;
	
	
	//
	// Methods
	//
	
	public static void clear() {
		// 3.3.1 Control Registers
		PSB = 0;
		MDS = 0;
		LF  = 0;
		GF  = 0;
		GFI = 0;
		Memory.CB(0);
		Memory.PC(0);
		// 3.3.2 Evaluation Stack
		for(int i = 0; i < stack.length; i++) stack[i] = 0;
		SP = 0;
		// 3.3.3 Data and Status Registers
		for(int i = 0; i < PID.length; i++) PID[i] = 0;
		MP  = 0;
		IT  = 0;
		WP  = 0;
		WDC = 0;
		PTC = 0;
		XTS = 0;
		// 4.5 Instruction Execution
		breakByte = 0;
		savedPC   = 0;
		savedSP   = 0;
		running   = false;
	}
	
	// 3.3.2 Evaluation Stack
	public static int  stackCount() {
		return SP;
	}
	public static void push(int data) {
		if (SP == StackDepth) ControlTransfers.stackError();
		stack[SP++] = Types.toCARD16(data);
	}
	public static int pop() {
		if (SP == 0) ControlTransfers.stackError();
		return Types.toCARD16(stack[--SP]);
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
		int high = pop();
		int low  = pop();
		return Types.toCARD32(high, low);
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

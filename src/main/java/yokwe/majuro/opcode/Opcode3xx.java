package yokwe.majuro.opcode;

import yokwe.majuro.mesa.CodeCache;
import yokwe.majuro.opcode.Opcode.Register;

public class Opcode3xx {
//	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Opcode0xx.class);

	// 0370 ESC
	@Register(Opcode.ESC)
	public static final void OP_ESC () {
		Interpreter.dispatchEsc(CodeCache.getCodeByte());
	};
	
	// 0371 ESCL
	@Register(Opcode.ESCL)
	public static final void OP_ESCL () {
		Interpreter.dispatchEsc(CodeCache.getCodeByte());
	};

}

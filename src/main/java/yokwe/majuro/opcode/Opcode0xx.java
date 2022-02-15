package yokwe.majuro.opcode;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.opcode.Opcode.Register;

public class Opcode0xx {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Opcode0xx.class);

	// 00 NOOP
	@Register(Opcode.NOOP)
	public static final void OP_NOOP() {
		logger.error(String.format("TRACE %6o  NOOP", Processor.savedPC));
		throw new UnexpectedException();
	};

}

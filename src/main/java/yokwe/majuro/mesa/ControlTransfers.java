package yokwe.majuro.mesa;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.type.XferType;

public final class ControlTransfers {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ControlTransfers.class);

	// 9.5.3 Trap Handlers
	public static void SaveStack(int state) {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void LoadStack(int state) {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}

	public static void XFER(int dst, char src, XferType type, boolean freeFlag) {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}

	// 9.5.1 Trap Routines
	public static void boundsTrap() {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void breakTrap() {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void codeTrap(int gfi) {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void controlTrap(int src) {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void divCheckTrap() {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void divZeroTrap() {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void escOpcodeTrap(int opcode) {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void interruptError() {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void opcodeTrap(int opcode) {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void pointerTrap() {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void processTrap() {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void rescheduleError() {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void stackError() {
		logger.error("stackError");
		throw new UnexpectedException();
	}
	public static void unboundTrap(int dst) {
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void hardwareError() {
		logger.error("hardwareError");
		throw new UnexpectedException();
	}
}

package yokwe.majuro.mesa;

import yokwe.majuro.UnexpectedException;

public class Processes {
	// 10.4.3 Faults
	public static void frameFault(char fsi) {
		if (Perf.ENABLED) Perf.frameFault++;
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void pageFault(int ptr) {
		if (Perf.ENABLED) Perf.pageFault++;
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
	public static void writeProtectFault(int ptr) {
		if (Perf.ENABLED) Perf.writeProtectFault++;
		throw new UnexpectedException("NO IMPL"); // FIXME
	}
}

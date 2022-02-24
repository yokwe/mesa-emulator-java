package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Processor.*;

public class Processes {
	// 10.4.3 Faults
	public static void frameFault(char fsi) {
		if (Perf.ENABLED) Perf.frameFault++;
		abort(); // FIXME
	}
	public static void pageFault(int ptr) {
		if (Perf.ENABLED) Perf.pageFault++;
		abort(); // FIXME
	}
	public static void writeProtectFault(int ptr) {
		if (Perf.ENABLED) Perf.writeProtectFault++;
		abort(); // FIXME
	}
}

package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constant.PAGE_MASK;
import static yokwe.majuro.mesa.Constant.WORD_MASK;
import static yokwe.majuro.mesa.Constant.WORD_SIZE;

public final class Mesa {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Mesa.class);

	private static Memory memory;
	
	public static void init(int vmbits, int rmbits, int ioRegionPage) {
		memory = new Memory(vmbits, rmbits, ioRegionPage);
	}
	
	public static void pageFault(int va) {
		if (Perf.ENABLED) Perf.pageFault++;
		// FIXME
		logger.info("pageFault {}", Integer.toHexString(va).toUpperCase());
	}
	public static void writeProtectFault(int va) {
		if (Perf.ENABLED) Perf.writeProtectFault++;
		// FIXME
		logger.info("writeProtectFault {}", Integer.toHexString(va).toUpperCase());
	}
	
	public static int fetch(int va) {
		if (Perf.ENABLED) Perf.fetch++;
		return memory.fetch(va);
	}
	public static int store(int va) {
		if (Perf.ENABLED) Perf.store++;
		return memory.store(va);
	}
	
	public static int readDbl(int va) {
		if (Perf.ENABLED) Perf.readDbl++;
		int p0 = memory.fetch(va);
		int p1 = p0 + 1;
		if ((va & PAGE_MASK) == PAGE_MASK) p1 = memory.fetch(va + 1);
		
		return (memory.readRealMemory(p1) << WORD_SIZE) | (memory.readRealMemory(p0) & WORD_MASK);
	}

}

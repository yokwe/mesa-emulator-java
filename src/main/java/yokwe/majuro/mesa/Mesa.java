package yokwe.majuro.mesa;

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
	
	//
	// memory read and write
	//
	public static char read16(int va) {
		if (Perf.ENABLED) Perf.read16++;
		return memory.read16(va);
	}
	public static void write16(int va, char newValue) {
		if (Perf.ENABLED) Perf.write16++;
		memory.write16(va, newValue);
	}
	public static int read32(int va) {
		if (Perf.ENABLED) Perf.read32++;
		return memory.read32(va);
	}
	public static void write32(int va, int newValue) {
		if (Perf.ENABLED) Perf.write32++;
		memory.write32(va, newValue);
	}

}

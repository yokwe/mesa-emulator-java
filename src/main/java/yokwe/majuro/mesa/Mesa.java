package yokwe.majuro.mesa;

import yokwe.majuro.UnexpectedException;

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
	
	//
	// low level memory access
	//
	
	public static int fetch(int va) {
		if (Perf.ENABLED) Perf.fetch++;
		return memory.fetch(va);
	}
	public static int store(int va) {
		if (Perf.ENABLED) Perf.store++;
		return memory.store(va);
	}
	public static MapFlag mapFlag(int va) {
		if (Perf.ENABLED) Perf.mapFlag++;
		return memory.mapFlag(va);
	}
	public static char readReal(int ra) {
		if (Perf.ENABLED) Perf.readReal++;
		return memory.readReal(ra);
	}
	public static void writeReal(int ra, char newValue) {
		if (Perf.ENABLED) Perf.writeReal++;
		memory.writeReal(ra, newValue);
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
	//
	// MDS
	//
	public static char read16MDS(char va) {
		return memory.read16MDS(va);
	}
	public static void write16MDS(char va, char newValue) {
		memory.write16MDS(va, newValue);
	}
	public static int read32MDS(char va) {
		return memory.read32MDS(va);
	}
	public static void write32MDS(char va, int newValue) {
		memory.write32MDS(va, newValue);
	}
	// capture wrong parameter calls
	public static char read16MDS(int va) {
		throw new UnexpectedException("Unexpected");
	}
	public static void write16MDS(int va, char newValue) {
		throw new UnexpectedException("Unexpected");
	}
	public static int read32MDS(int va) {
		throw new UnexpectedException("Unexpected");
	}
	public static void write32MDS(int va, int newValue) {
		throw new UnexpectedException("Unexpected");
	}

}

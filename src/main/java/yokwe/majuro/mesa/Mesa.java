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
	public static Map map(int va) {
		if (Perf.ENABLED) Perf.map++;
		return memory.map(va);
	}
	public static char readReal16(int ra) {
		if (Perf.ENABLED) Perf.readReal16++;
		return memory.readReal16(ra);
	}
	public static void writeReal16(int ra, char newValue) {
		if (Perf.ENABLED) Perf.writeReal16++;
		memory.writeReal16(ra, newValue);
	}
	public static int readReal32(int ra0, int ra1) {
		if (Perf.ENABLED) Perf.readReal32++;
		return memory.readReal32(ra0, ra1);
	}
	public static void writeReal32(int ra0, int ra1, int newValue) {
		if (Perf.ENABLED) Perf.writeReal32++;
		memory.writeReal32(ra0, ra1, newValue);
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
	public static int lengthenMDS(char va) {
		if (Perf.ENABLED) Perf.lengthenMDS++;
		return memory.lengthenMDS(va);
	}
	public static char read16MDS(char va) {
		if (Perf.ENABLED) Perf.read16MDS++;
		return memory.read16MDS(va);
	}
	public static void write16MDS(char va, char newValue) {
		if (Perf.ENABLED) Perf.write16MDS++;
		memory.write16MDS(va, newValue);
	}
	public static int read32MDS(char va) {
		if (Perf.ENABLED) Perf.read32MDS++;
		return memory.read32MDS(va);
	}
	public static void write32MDS(char va, int newValue) {
		if (Perf.ENABLED) Perf.write32MDS++;
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

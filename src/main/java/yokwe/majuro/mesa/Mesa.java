package yokwe.majuro.mesa;

public final class Mesa {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Mesa.class);

	public static final int PAGE_BITS = 8;
	public static final int PAGE_SIZE = 1 << PAGE_BITS;
	public static final int PAGE_MASK = PAGE_SIZE - 1;
	
	public static final int WORD_BITS = 16;
	public static final int WORD_SIZE = 1 << WORD_BITS;
	public static final int WORD_MASK = WORD_SIZE - 1;
	
	public static final int MAX_REALMEMORY_PAGE_SIZE = /* RealMemoryImplGuam::largestArraySize */ 4086 * WORD_BITS;

	private static Memory    memory;
	private static PageCache pageCache;
	
	public static void init(int vmbits, int rmbits, int ioRegionPage) {
		memory    = new Memory(vmbits, rmbits, ioRegionPage);
		pageCache = new PageCache(memory);
	}
	
	public static void pageFault(int va) {
		// FIXME
		logger.info("pageFault {}", Integer.toHexString(va).toUpperCase());
	}
	public static void writeProtectFault(int va) {
		// FIXME
		logger.info("writeProtectFault {}", Integer.toHexString(va).toUpperCase());
	}
	
	public static int fetch(int va) {
		if (Perf.ENABLED) Perf.fetch++;
		return pageCache.fetch(va);
	}
	public static int store(int va) {
		if (Perf.ENABLED) Perf.store++;
		return pageCache.store(va);
	}
	
	public static int readWord(int va) {
		return memory.readMemory(va);
	}
	public static void writeWord(int va, int newValue) {
		memory.writeMemory(va, newValue);
	}
	public static int readDbl(int va) {
		if (Perf.ENABLED) Perf.readDbl++;
		int p0 = fetch(va);
		int p1 = p0 + 1;
		if ((va & PAGE_MASK) == PAGE_MASK) p1 = fetch(va + 1);
		
		return (readWord(p1) << WORD_SIZE) | (readWord(p0) & WORD_MASK);
	}

}

package yokwe.majuro.mesa;

public final class Mesa {
	public static final int PAGE_BITS = 8;
	public static final int PAGE_SIZE = 1 << PAGE_BITS;
	public static final int PAGE_MASK = PAGE_SIZE - 1;
	
	public static final int WORD_BITS = 16;
	public static final int WORD_SIZE = 1 << WORD_BITS;
	public static final int WORD_MASK = WORD_SIZE - 1;
	
	public static final int MAX_REALMEMORY_PAGE_SIZE = /* RealMemoryImplGuam::largestArraySize */ 4086 * WORD_BITS;


	public Memory    memory;
	public PageCache pageCache;
	
	public Mesa(int vmbits, int rmbits, int ioRegionPage) {
		memory    = new Memory(vmbits, rmbits, ioRegionPage);
		pageCache = new PageCache(memory);
	}
	
	public int fetch(int va) {
		if (Perf.ENABLED) Perf.fetch++;
		return pageCache.fetch(va);
	}
	public int store(int va) {
		if (Perf.ENABLED) Perf.store++;
		return pageCache.store(va);
	}
	
	public int readWord(int va) {
		return memory.readMemory(va);
	}
	public void writeWord(int va, int newValue) {
		memory.writeMemory(va, newValue);
	}
	public int readDbl(int va) {
		if (Perf.ENABLED) Perf.readDbl++;
		int p0 = fetch(va);
		int p1 = p0 + 1;
		if ((va & PAGE_MASK) == PAGE_MASK) p1 = fetch(va + 1);
		
		return (readWord(p1) << WORD_SIZE) | (readWord(p0) & WORD_MASK);
	}

}

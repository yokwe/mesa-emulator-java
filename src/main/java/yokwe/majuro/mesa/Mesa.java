package yokwe.majuro.mesa;

public final class Mesa {
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
		if ((va & Memory.PAGE_MASK) == Memory.PAGE_MASK) p1 = fetch(va + 1);
		
		return (readWord(p1) << Memory.WORD_SIZE) | (readWord(p0) & Memory.WORD_MASK);
	}

}

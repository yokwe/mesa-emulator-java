package yokwe.majuro.mesa;

public final class PageCache {
	private static final int N_BIT   = 14;
	private static final int N_ENTRY = 1 << N_BIT;
	private static final int MASK    = (1 << N_BIT) - 1;
	
	private static final int hash(int vp_) {
		return ((vp_ >> N_BIT) ^ vp_) & MASK;
		// NOTE above expression calculate better hash value than "vp_ & MASK"
	}

	private static final class Entry {
		public boolean flagFetch;
		public boolean flagStore;
		public int     vp;
		public int     ra; // real address
		
		Entry() {
			clear();
		}
		
		void clear() {
			flagFetch = false;
			flagStore = false;
			vp = 0;
			ra = 0;
		}
	}
	
	private final Memory memory;
	private final PageCache.Entry entries[];
	
	PageCache(Memory memory_) {
		entries = new PageCache.Entry[N_ENTRY];
		
		this.memory = memory_;
		for(int i = 0; i < entries.length; i++) {
			entries[i] = new Entry();
		}
	}
	
	private PageCache.Entry getEntry(int vp) {
		return entries[hash(vp)];
	}
	
	public void invalidate(int vp) {
		PageCache.Entry entry = getEntry(vp);
		if (entry.vp == vp) entry.clear();
	}
	public int fetch(int va) {
		int vp = va >>> Memory.PAGE_BITS;
		int of = va & Memory.PAGE_MASK;

		PageCache.Entry entry = getEntry(vp);
		if (entry.vp == vp) {
			if (Perf.ENABLED) Perf.pageCacheHit++;
			if (!entry.flagFetch) {
				memory.setReferenced(vp);
				entry.flagFetch = true;
			}
		} else {
			// setup entry
			if (Perf.ENABLED) {
				if (entry.vp == 0) Perf.pageCacheMissEmpty++;
				else Perf.pageCacheMissConflict++;
			}
			// Overwrite content of entry
			entry.flagFetch = true;
			entry.flagStore = false;
			entry.vp = vp;
			entry.ra = memory.fetch(vp << Memory.PAGE_BITS);
		}
		return entry.ra + of;
	}
	public int store(int va) {
		int vp = va >>> Memory.PAGE_BITS;
		int of = va & Memory.PAGE_MASK;

		PageCache.Entry entry = getEntry(vp);
		if (entry.vp == vp) {
			if (Perf.ENABLED) Perf.pageCacheHit++;
			if (!entry.flagStore) {
				memory.setReferencedDirty(vp);
				entry.flagStore = true;
			}
		} else {
			// setup entry
			if (Perf.ENABLED) {
				if (entry.vp == 0) Perf.pageCacheMissEmpty++;
				else Perf.pageCacheMissConflict++;
			}
			// Overwrite content of entry
			entry.flagFetch = true;
			entry.flagStore = true;
			entry.vp = vp;
			entry.ra = memory.store(vp << Memory.PAGE_BITS);
		}
		return entry.ra + of;
	}
	
}
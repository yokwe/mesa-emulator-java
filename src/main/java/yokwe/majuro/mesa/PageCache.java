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
		private static final int MASK_FETCH = 0x00000002;
		private static final int MASK_STORE = 0x00000001;
		
		public static final int FLAG_FETCH       = MASK_FETCH;
		public static final int FLAG_FETCH_STORE = MASK_FETCH | MASK_STORE;
		
		
		public int flags; // fetch and store
		public int vp;    // virtual page
		public int ra;    // real address
		
		Entry() {
			clear();
		}
		
		void clear() {
			flags = 0;
			vp    = 0;
			ra    = 0;
		}
		
		private boolean isNotFetch() {
			return (flags & MASK_FETCH) == 0;
		}
		private boolean isNotStore() {
			return (flags & MASK_FETCH) == 0;
		}
		private void setFetch() {
			flags |= MASK_FETCH;
		}
		private void setStore() {
			flags |= MASK_STORE;
		}
	}
	
	private final Memory memory;
	private final Entry  entries[];
	
	PageCache(Memory memory_) {
		memory  = memory_;
		entries = new Entry[N_ENTRY];
		
		for(int i = 0; i < entries.length; i++) {
			entries[i] = new Entry();
		}
	}
	
	private Entry getEntry(int vp) {
		return entries[hash(vp)];
	}
	
	public void invalidate(int vp) {
		Entry entry = getEntry(vp);
		if (entry.vp == vp) entry.clear();
	}
	public int fetch(int va) {
		int vp = va >>> Mesa.PAGE_BITS;

		Entry entry = getEntry(vp);
		if (entry.vp == vp) {
			if (Perf.ENABLED) Perf.pageCacheHit++;
			if (entry.isNotFetch()) {
				memory.setReferenced(vp);
				entry.setFetch();
			}
		} else {
			// setup entry
			if (Perf.ENABLED) {
				if (entry.vp == 0) Perf.pageCacheMissEmpty++;
				else Perf.pageCacheMissConflict++;
			}
			// generate PageFault before update entry
			int ra = memory.fetch(va & ~Mesa.PAGE_MASK);
			
			// Overwrite content of entry
			entry.flags = Entry.FLAG_FETCH;
			entry.vp    = vp;
			entry.ra    = ra;
		}
		return entry.ra | (va & Mesa.PAGE_MASK);
	}
	public int store(int va) {
		int vp = va >>> Mesa.PAGE_BITS;

		Entry entry = getEntry(vp);
		if (entry.vp == vp) {
			if (Perf.ENABLED) Perf.pageCacheHit++;
			if (entry.isNotStore()) {
				memory.setReferencedDirty(vp);
				entry.setStore();
			}
		} else {
			// setup entry
			if (Perf.ENABLED) {
				if (entry.vp == 0) Perf.pageCacheMissEmpty++;
				else Perf.pageCacheMissConflict++;
			}
			// generate PageFault before update entry
			int ra = memory.store(va & ~Mesa.PAGE_MASK);

			// Overwrite content of entry
			entry.flags = Entry.FLAG_FETCH_STORE;
			entry.vp    = vp;
			entry.ra    = ra;
		}
		return entry.ra | (va & Mesa.PAGE_MASK);
	}
	
}
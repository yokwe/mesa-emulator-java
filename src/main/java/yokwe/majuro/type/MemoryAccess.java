package yokwe.majuro.type;

public enum MemoryAccess {
	NONE      (false, false),
	READ      (true,  false),
	READ_WRITE(true,  true),
	WRITE     (false, true),
	WRITE_READ(true,  true);
	
	public final boolean readable;
	public final boolean writable;
	
	MemoryAccess(boolean readable, boolean writable) {
		this.readable = readable;
		this.writable = writable;
	}
	
}

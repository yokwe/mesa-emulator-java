package yokwe.majuro.type;

public enum MemoryAccess {
	NONE,
	READ,
	READ_WRITE,
	WRITE,
	WRITE_READ;
	
	public final boolean readable() {
		switch(this) {
		case READ:
		case READ_WRITE:
		case WRITE_READ:
			return true;
		default:
			return false;
		}
	}
	public final boolean writable() {
		switch(this) {
		case READ_WRITE:
		case WRITE:
		case WRITE_READ:
			return true;
		default:
			return false;
		}
	}
	
}

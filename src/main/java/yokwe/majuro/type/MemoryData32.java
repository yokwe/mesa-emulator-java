package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

public class MemoryData32 {
    private final MemoryAccess access;
    private final @Mesa.REAL_POINTER int ra0;
    private final @Mesa.REAL_POINTER int ra1;

    public @Mesa.CARD32 int value;

    public MemoryData32(@Mesa.CARD32 int value) {
        this.access = MemoryAccess.NONE;
        this.ra0    = 0;
        this.ra1    = 0;
        this.value  = value;
    }
    public MemoryData32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        this.access = access;
        switch(access) {
        case NONE:
            this.ra0   = 0;
            this.ra1   = 0;
            this.value = 0;
            break;
        case READ:
            this.ra0   = Memory.fetch(base);
            this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Memory.fetch(base + 1);
            this.value = Memory.readReal32(ra0, ra1);
            break;
        case READ_WRITE:
            this.ra0   = Memory.store(base);
            this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Memory.store(base + 1);
            this.value = Memory.readReal32(ra0, ra1);
            break;
        case WRITE:
        case WRITE_READ:
            this.ra0   = Memory.store(base);
            this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Memory.store(base + 1);
            this.value = 0;
            break;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }

    public final MemoryAccess access() {
    	return access;
    }
    public final boolean writable() {
    	return access.writable();
    }
    public final boolean readable() {
    	return access.readable();
    }

    public final void write() {
    	if (writable()) {
        	Memory.writeReal32(ra0, ra1, value);
    	} else {
            throw new UnexpectedException("Unexpected");
    	}
    }
    public final void write(@Mesa.CARD32 int newValue) {
    	value = newValue;
    	write();
    }
    public final void write(MemoryData32 newValue) {
    	value = newValue.value;
    	write();
    }
    
    public final @Mesa.CARD32 int read() {
    	if (readable()) {
        	value = Memory.readReal32(ra0, ra1);
    		return value;
    	} else {
            throw new UnexpectedException("Unexpected");
    	}
    }
    
}

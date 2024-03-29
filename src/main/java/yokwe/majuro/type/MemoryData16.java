package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

public class MemoryData16 {
    private final MemoryAccess access;
    private final @Mesa.REAL_POINTER int ra;

    // NOTE To reduce type conversion, using int for value
    public @Mesa.CARD16 int value;

    public MemoryData16(@Mesa.CARD16 int value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = Types.toCARD16(value);
    }
    public MemoryData16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        this.access = access;
        switch(access) {
        case NONE:
            this.ra    = 0;
            this.value = 0;
            break;
        case READ:
            this.ra    = Memory.fetch(base);
            this.value = Memory.readReal16(ra);
            break;
        case READ_WRITE:
            this.ra    = Memory.store(base);
            this.value = Memory.readReal16(ra);
            break;
        case WRITE:
        case WRITE_READ:
            this.ra    = Memory.store(base);
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
    	return access.writable;
    }
    public final boolean readable() {
    	return access.readable;
    }

    public final void write() {
    	if (writable()) {
    		Memory.writeReal16(ra, value);
    	} else {
            throw new UnexpectedException("Unexpected");
    	}
    }
    public final void write(@Mesa.CARD16 int newValue) {
    	value = Types.toCARD16(newValue);
    	write();
    }
    public final void write(MemoryData16 newValue) {
    	value = newValue.value;
    	write();
    }
    
    public final @Mesa.CARD16 int read() {
    	if (readable()) {
    		value = Memory.readReal16(ra);
    		return value;
    	} else {
            throw new UnexpectedException("Unexpected");
    	}
    }

}

package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;

public class MemoryData32 {
    private final MemoryAccess access;
    private final int          ra0;
    private final int          ra1;

    public int value;

    public MemoryData32(int value) {
        this.access = MemoryAccess.NONE;
        this.ra0    = 0;
        this.ra1    = 0;
        this.value  = value;
    }
    public MemoryData32(int base, MemoryAccess access) {
        this.access = access;
        switch(access) {
        case NONE:
            this.ra0   = 0;
            this.ra1   = 0;
            this.value = 0;
            break;
        case READ:
            this.ra0   = Memory.instance.fetch(base);
            this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Memory.instance.fetch(base + 1);
            this.value = Memory.instance.readReal32(ra0, ra1);
            break;
        case READ_WRITE:
            this.ra0   = Memory.instance.store(base);
            this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Memory.instance.store(base + 1);
            this.value = Memory.instance.readReal32(ra0, ra1);
            break;
        case WRITE:
            this.ra0   = Memory.instance.store(base);
            this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Memory.instance.store(base + 1);
            this.value = 0;
            break;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }

    public void write() {
        switch(access) {
        case READ_WRITE:
        case WRITE:
        	Memory.instance.writeReal32(ra0, ra1, value);
            break;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }
    public void write(int newValue) {
    	value = newValue;
    	write();
    }
    
    public int read() {
        switch(access) {
        case READ:
        case READ_WRITE:
        	value = Memory.instance.readReal32(ra0, ra1);
            return value;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }
    
}

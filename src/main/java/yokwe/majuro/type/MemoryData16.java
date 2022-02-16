package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;

public class MemoryData16 {
    private final MemoryAccess access;
    private final int          ra;

    // NOTE To reduce type conversion, using int for value
    public int value;

    public MemoryData16(char value) {
        this.access = MemoryAccess.NONE;
        this.ra     = 0;
        this.value  = value;
    }
    public MemoryData16(int base, MemoryAccess access) {
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
            this.ra    = Memory.store(base);
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
        	Memory.writeReal16(ra, (char)value);
            break;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }
    public void write(char newValue) {
    	value = newValue;
    	write();
    }
    
    public char read() {
        switch(access) {
        case READ:
        case READ_WRITE:
        	value = Memory.read16(ra);
            return (char)value;
        default:
            throw new UnexpectedException("Unexpected");
        }
    }

}

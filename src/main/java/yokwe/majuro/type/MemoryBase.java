package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

public class MemoryBase {
	public final @Mesa.LONG_POINTER int base;
	public final MemoryAccess access;
	
    public MemoryBase(@Mesa.LONG_POINTER int base, MemoryAccess access) {
    	this.base   = base;
    	this.access = access;
    }
}

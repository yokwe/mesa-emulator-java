package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

public class MemoryBase {
	public final @Mesa.LONG_POINTER int base;
 
    public MemoryBase(@Mesa.LONG_POINTER int base) {
    	this.base = base;
    }
}

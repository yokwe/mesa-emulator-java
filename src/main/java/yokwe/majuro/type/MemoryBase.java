package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

public class MemoryBase {
	public final @Mesa.POINTER int base;
 
    public MemoryBase(@Mesa.POINTER int base) {
    	this.base = base;
    }
}

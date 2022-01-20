package yokwe.majuro.symbol.model;

import yokwe.majuro.util.StringUtil;

public class TypeReference extends Type {
	public final String typeString;
	
	public Type realType;
	
	public TypeReference(String name, String typeString) {
		super(name, Kind.REFERENCE);
		
		this.typeString = typeString;
		this.realType   = null;
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}

	@Override
	public void fix() {
		if (needsFix) {
			if (realType == null) {
				realType = Type.getRealType(typeString);
			}
			if (realType != null) {
				realType.fix();
				
				if (!realType.needsFix) {
					needsFix = false;
					
					bitSize = realType.bitSize();
				}
			}
		}
	}

}
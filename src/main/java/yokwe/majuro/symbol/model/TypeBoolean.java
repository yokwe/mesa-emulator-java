package yokwe.majuro.symbol.model;

import yokwe.majuro.util.StringUtil;

public class TypeBoolean extends Type {
	public TypeBoolean(String name) {
		super(name, Kind.BOOLEAN);
		fix();
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}

	@Override
	public void fix() {
		needsFix = false;
	}

}

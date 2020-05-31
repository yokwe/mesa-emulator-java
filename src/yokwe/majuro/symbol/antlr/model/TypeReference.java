package yokwe.majuro.symbol.antlr.model;

public class TypeReference extends Type {
	public final String baseName;
	
	public Type baseType;
	
	public TypeReference(String name, String baseName) {
		super(name, Kind.REFERENCE);
		
		this.baseName = baseName;
		this.baseType = null;
		
		fix();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %d %s %s %s}", name, size, kind, baseName, baseType);
	}
	
	@Override
	protected void fix() {
		if (needsFix) {
			if (map.containsKey(baseName)) {
				Type nextType = map.get(baseName);
				if (needsFix) nextType.fix();
				if (!nextType.needsFix) {
					size     = nextType.size;
					baseType = nextType.isReference() ? ((TypeReference)nextType).baseType : nextType;
					needsFix = false;
				}
			}
		}
	}
}
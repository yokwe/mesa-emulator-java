package yokwe.majuro.symbol.antlr.model;

public class TypeSubrange extends Type {
	public final TypeReference baseType;
	public final long          valueMin;
	public final long          valueMax;
	
	public TypeSubrange(String name, int size, String baseName, long valueMin, long valueMax) {
		super(name, Kind.SUBRANGE, size);
		
		this.baseType = new TypeReference(name + "#base", baseName);
		this.valueMin = valueMin;
		this.valueMax = valueMax;
		
		fix();
	}
	public TypeSubrange(String name, String baseName, long valueMin, long valueMax) {
		super(name, Kind.SUBRANGE);
		
		this.baseType = new TypeReference(name + "#base", baseName);
		this.valueMin = valueMin;
		this.valueMax = valueMax;
		
		fix();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s %d %d}", name, size, kind, baseType, valueMin, valueMax);
	}
	
	@Override
	protected void fix() {
		if (needsFix) {
			baseType.fix();
			if (!baseType.needsFix) {
				size     = baseType.size;
				needsFix = false;
			}
		}
	}
}

package yokwe.majuro.symbol.antlr.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public class TypeSubrange extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeSubrange.class);

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
				switch(baseType.kind) {
				case SUBRANGE:
					break;
				default:
					logger.error("Unexpected baseType");
					logger.error("  baseType {}", baseType);
					throw new UnexpectedException("Unexpected baseType");
				}
				
				size     = baseType.size;
				needsFix = false;
			}
		}
	}
}

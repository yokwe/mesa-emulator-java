package yokwe.majuro.symbol.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public abstract class TypeArray extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeArray.class);
	
	public enum RecordKind {
		OPEN, SUBRANGE, FULL,
	}
	
	public final RecordKind recordKind;

	public final TypeReference elementType;
	public final TypeReference indexType;
	
	public long rangeMin;
	public long rangeMax;
	
	protected TypeArray(String name, int size, RecordKind recordKind, String elementName, String indexName, long rangeMin, long rangeMax) {
		super(name, Kind.SUBRANGE, size);
		
		this.recordKind   = recordKind;
		this.elementType  = new TypeReference(name + "#element", elementName);
		this.indexType    = new TypeReference(name + "#index", indexName);
		this.rangeMin     = rangeMin;
		this.rangeMax     = rangeMax;
		
		fix();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s %d %s %s %d %d}", name, kind, size, elementType, indexType, rangeMin, rangeMax);
	}

	@Override
	protected void fix() {
		if (needsFix) {
			if (indexType.needsFix)   indexType.fix();
			if (elementType.needsFix) elementType.fix();
			
			if (!indexType.needsFix && !elementType.needsFix) {
				int length;
				
				// sanity check
				switch(indexType.baseType.kind) {
				case ENUM:
				{
					TypeEnum baseType = (TypeEnum)indexType.baseType;
					baseType.checkValue(rangeMax, rangeMin);
					length = baseType.length;
				}
					break;
				case SUBRANGE:
				{
					TypeSubrange baseType = (TypeSubrange)indexType.baseType;
					baseType.checkValue(rangeMax, rangeMin);
					length = baseType.length;
				}
					break;
				default:
					logger.error("Unexpected indexType");
					logger.error("  indexType {}", indexType);
					throw new UnexpectedException("Unexpected indexType");
				}

				this.size = elementType.size * length;
				this.needsFix = false;
			}
		}
	}
}

class TypeArrayOpen extends TypeArray {
	TypeArrayOpen(String name, String elementName, String indexName, long rangeMinMax) {
		super(name, 0, RecordKind.OPEN, elementName, indexName, rangeMinMax, rangeMinMax);
	}
	@Override
	public void fix() {
		needsFix = false;
	}
}
class TypeArrayFull extends TypeArray {
	private static final Logger logger = LoggerFactory.getLogger(TypeArrayFull.class);

	TypeArrayFull(String name, String elementName, String indexName) {
		super(name, Type.UNKNOWN_SIZE, RecordKind.FULL, elementName, indexName, 0, 0);
	}
	@Override
	public void fix() {
		{
			indexType.fix();
			if (!indexType.needsFix) {
				switch(indexType.baseType.kind) {
				case ENUM:
				{
					TypeEnum baseType = (TypeEnum)indexType.baseType;
					this.rangeMin = baseType.valueMin;
					this.rangeMax = baseType.valueMax;
				}
					break;
				case SUBRANGE:
				{
					TypeSubrange baseType = (TypeSubrange)indexType.baseType;
					this.rangeMin = baseType.valueMin;
					this.rangeMax = baseType.valueMax;
				}
					break;
				default:
					logger.error("Unexpected indexType");
					logger.error("  indexType {}", indexType);
					throw new UnexpectedException("Unexpected indexType");
				}
			}
		}
		super.fix();
	}
}
class TypeArraySubrange extends TypeArray {
	TypeArraySubrange(String name, String elementName, String indexName, long rangeMin, long rangeMax) {
		super(name, Type.UNKNOWN_SIZE, RecordKind.SUBRANGE, elementName, indexName, rangeMin, rangeMax);
	}
}


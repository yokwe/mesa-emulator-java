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
	
	public       Const         rangeMin;
	public       Const         rangeMax;
	public final boolean       rangeMaxInclusive;

	protected TypeArray(String name, int size, RecordKind recordKind, String elementName, String indexName, String rangeMin, String rangeMax, boolean rangeMaxInclusive) {
		super(name, Kind.SUBRANGE, size);
		
		this.recordKind   = recordKind;
		this.elementType  = new TypeReference(name + "#element", elementName);
		this.indexType    = new TypeReference(name + "#index", indexName);
		if (rangeMin.matches("^-?[0-9]+$")) {
			this.rangeMin = new Const(name + "#valueMin", Type.LONG_CARDINAL, Long.parseLong(rangeMin));
		} else {
			this.rangeMin = new Const(name + "#valueMin", Type.LONG_CARDINAL, rangeMin);
		}
		if (rangeMax.matches("^-?[0-9]+$")) {
			this.rangeMax = new Const(name + "#valueMax", Type.LONG_CARDINAL, Long.parseLong(rangeMax));
		} else {
			this.rangeMax = new Const(name + "#valueMax", Type.LONG_CARDINAL, rangeMax);
		}
		this.rangeMaxInclusive = rangeMaxInclusive;

		fix();
	}
	protected TypeArray(String name, int size, RecordKind recordKind, String elementName, String indexName, long rangeMin, long rangeMax, boolean rangeMaxInclusive) {
		super(name, Kind.SUBRANGE, size);
		
		this.recordKind   = recordKind;
		this.elementType  = new TypeReference(name + "#element", elementName);
		this.indexType    = new TypeReference(name + "#index", indexName);
		this.rangeMin     = new Const(name + "#valueMinLong", Type.LONG_CARDINAL, rangeMin);
		this.rangeMax     = new Const(name + "#valueMaxLong", Type.LONG_CARDINAL, rangeMax);
		this.rangeMaxInclusive = rangeMaxInclusive;

		fix();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s %d %s %s %s %s}", name, kind, size, elementType, indexType, rangeMin, rangeMax);
	}

	@Override
	protected void fix() {
		if (needsFix) {
			indexType.fix();
			elementType.fix();
			rangeMin.fix();
			rangeMax.fix();
			
			if (!indexType.needsFix && !elementType.needsFix && !rangeMin.needsFix && !rangeMax.needsFix) {
				int length;
				
				// sanity check
				switch(indexType.baseType.kind) {
				case ENUM:
				{
					TypeEnum baseType = (TypeEnum)indexType.baseType;
					baseType.checkValue(rangeMin.numericValue, rangeMax.numericValue);
					length = baseType.length;
				}
					break;
				case SUBRANGE:
				{
					TypeSubrange baseType = (TypeSubrange)indexType.baseType;
					baseType.checkValue(rangeMin.numericValue, rangeMax.numericValue);
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
	TypeArrayOpen(String name, String elementName, String indexName, String rangeMinMax) {
		super(name, 0, RecordKind.OPEN, elementName, indexName, rangeMinMax, rangeMinMax, false);
	}
	@Override
	public void fix() {
		needsFix = false;
	}
}
class TypeArrayFull extends TypeArray {
	private static final Logger logger = LoggerFactory.getLogger(TypeArrayFull.class);

	TypeArrayFull(String name, String elementName, String indexName) {
		super(name, Type.UNKNOWN_SIZE, RecordKind.FULL, elementName, indexName, 0, 0, true);
	}
	@Override
	public void fix() {
		{
			indexType.fix();
			rangeMin.fix();
			rangeMax.fix();
			
			if (!indexType.needsFix && !rangeMin.needsFix && !rangeMax.needsFix) {
				switch(indexType.baseType.kind) {
				case ENUM:
				{
					TypeEnum baseType = (TypeEnum)indexType.baseType;
					// FIXME
//					this.rangeMin = baseType.valueMin;
//					this.rangeMax = baseType.valueMax;
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
	TypeArraySubrange(String name, String elementName, String indexName, String rangeMin, String rangeMax, boolean rangeMaxInclusive) {
		super(name, Type.UNKNOWN_SIZE, RecordKind.SUBRANGE, elementName, indexName, rangeMin, rangeMax, rangeMaxInclusive);
	}
}


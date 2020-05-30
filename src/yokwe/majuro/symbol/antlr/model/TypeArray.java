package yokwe.majuro.symbol.antlr.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public class TypeArray extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeArray.class);

	public final Type    elementType;
	public final Type    indexType;
	public final boolean useIndexTypeRange;
	public final boolean isOpenArray;
	
	public long rangeMin;
	public long rangeMax;
	
	// for open array
	public TypeArray(String name, String elementName, String indexName, long rangeMinMax) {
		super(name, Kind.SUBRANGE, 0);
		
		this.elementType       = new TypeReference(name + "#element", elementName);
		this.indexType         = new TypeReference(name + "#index", indexName);
		this.useIndexTypeRange = false;
		this.isOpenArray       = true;
		this.rangeMin          = rangeMinMax;
		this.rangeMax          = rangeMinMax;
		
		fix();
	}
	public TypeArray(String name, String elementName, long rangeMinMax) {
		this(name, elementName, "CARDINAL", rangeMinMax);
	}

	// for subrange array
	public TypeArray(String name, String elementName, String indexName, long rangeMin, long rangeMax) {
		super(name, Kind.SUBRANGE);
		
		this.elementType       = new TypeReference(name + "#element", elementName);
		this.indexType         = new TypeReference(name + "#index", indexName);
		this.useIndexTypeRange = false;
		this.isOpenArray       = false;
		this.rangeMin          = rangeMin;
		this.rangeMax          = rangeMax;
		
		fix();
	}
	public TypeArray(String name, String elementName, long rangeMin, long rangeMax) {
		this(name, elementName, "CARDINAL", rangeMin, rangeMax);
	}
	
	// for full range array
	public TypeArray(String name, String indexName, String elementName) {
		super(name, Kind.SUBRANGE);
		
		this.elementType       = new TypeReference(name + "#element", elementName);
		this.indexType         = new TypeReference(name + "#index", indexName);
		this.useIndexTypeRange = true;
		this.isOpenArray       = false;
		this.rangeMin          = 0;
		this.rangeMax          = 0;
		
		fix();
	}
	
	@Override
	public String toString() {
		if (useIndexTypeRange) {
			return String.format("{%s %s %d %s %s}", name, kind, size, elementType, indexType);
		} else {
			return String.format("{%s %s %d %s %s %d %d}", name, kind, size, elementType, indexType, rangeMin, rangeMax);
		}
	}
	
	public void checkIndexType() {
		if (!indexType.needsFix) {
			switch(indexType.kind) {
			case ENUM:
			{
				TypeEnum typeEnum = (TypeEnum)indexType;
				
				if (useIndexTypeRange) {
					rangeMin = typeEnum.valueMin;
					rangeMax = typeEnum.valueMax;
				} else {
					if (rangeMin < typeEnum.valueMin) {
						logger.error("Unexpected rangeMin");
						logger.error("  rangeMin {}", rangeMin);
						logger.error("  valueMin {}", typeEnum.valueMin);
						throw new UnexpectedException("Unexpected rangeMin");
					}
					if (typeEnum.valueMax < rangeMax) {
						logger.error("Unexpected rangeMax");
						logger.error("  rangeMax {}", rangeMax);
						logger.error("  valueMax {}", typeEnum.valueMax);
						throw new UnexpectedException("Unexpected rangeMax");
					}
				}
			}
				break;
			case SUBRANGE:
			{
				TypeSubrange typeSubrange = (TypeSubrange)indexType;
				
				if (useIndexTypeRange) {
					rangeMin = typeSubrange.valueMin;
					rangeMax = typeSubrange.valueMax;
				} else {
					if (rangeMin < typeSubrange.valueMin) {
						logger.error("Unexpected rangeMin");
						logger.error("  rangeMin {}", rangeMin);
						logger.error("  valueMin {}", typeSubrange.valueMin);
						throw new UnexpectedException("Unexpected rangeMin");
					}
					if (typeSubrange.valueMax < rangeMax) {
						logger.error("Unexpected rangeMax");
						logger.error("  rangeMax {}", rangeMax);
						logger.error("  valueMax {}", typeSubrange.valueMax);
						throw new UnexpectedException("Unexpected rangeMax");
					}
				}
			}
				break;
			default:
				logger.error("Unexpected indexType");
				logger.error("  indexType {}", indexType);
				throw new UnexpectedException("Unexpected indexType");
			}
		}
	}

	@Override
	protected void fix() {
		if (needsFix) {
			if (indexType.needsFix)   indexType.fix();
			if (elementType.needsFix) elementType.fix();
			
			if (!indexType.needsFix) checkIndexType();
			
			if (!indexType.needsFix && !elementType.needsFix) {
				long indexLength = rangeMax - rangeMin;
				if (Integer.MAX_VALUE < size) {
					// FIXME
					logger.error("size is too big");
					logger.info("    rangeMin", rangeMin);
					logger.info("    rangeMax", rangeMax);
					throw new UnexpectedException("size is too big");
				}
				this.size = elementType.size * (int)indexLength;
				this.needsFix = false;
			}
		}
	}
}

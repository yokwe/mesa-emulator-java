package yokwe.majuro.symbol.model;

import static yokwe.majuro.mesa.Constants.WORD_BITS;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypeArrayRef extends TypeArray {
	public TypeReference typeReference;
	
	public TypeArrayRef(QName qName, TypeReference typeReference, Type arrayElement) {
		super(qName, arrayElement);
		this.typeReference = typeReference;
		
		fix();
	}
	
	@Override
	public void fix() {
		if (needsFix) {
			arrayElement.fix();
			typeReference.fix();
			
			if (!arrayElement.needsFix && !typeReference.needsFix) {
				needsFix = false;
				
				Type realType = typeReference.realType;
				if (realType instanceof TypeSubrange) {
					TypeSubrange typeSubrange = realType.toTypeSubrange();
					setValue(typeSubrange.minValue, typeSubrange.maxValue);
				} else if (realType instanceof TypeEnum) {
					TypeEnum typeEnum = realType.toTypeEnum();
					setValue(typeEnum.minValue, typeEnum.maxValue);
				} else {
					logger.error("Unexpected");
					logger.error("  this  %s", this);
					throw new UnexpectedException("Unexpected");
				}
				
				// To calculate bitSize, use arrayElemen.wordSize() * WORD_BITS
				// Because arrayElement is aligned to WORD.
				bitSize = arrayElement.wordSize() * WORD_BITS * (int)size;
			}
		}
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}

	@Override
	public String toMesaType() {
		return String.format("ARRAY %s OF %s", typeReference.toMesaType(), arrayElement.toMesaType());
	}
}
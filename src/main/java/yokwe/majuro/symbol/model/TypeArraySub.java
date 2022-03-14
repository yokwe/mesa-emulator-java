package yokwe.majuro.symbol.model;

import static yokwe.majuro.mesa.Constants.WORD_BITS;

import yokwe.majuro.util.StringUtil;

public class TypeArraySub extends TypeArray {
	public TypeSubrange typeSubrange;
	
	public TypeArraySub(QName qName, TypeSubrange typeSubrange, Type arrayElement) {
		super(qName, arrayElement);
		this.typeSubrange = typeSubrange;
		
		fix();
	}
	
	@Override
	public void fix() {
		if (needsFix) {
			arrayElement.fix();
			typeSubrange.fix();
			
			if (!arrayElement.needsFix && !typeSubrange.needsFix) {
				needsFix = false;
				setValue(typeSubrange.minValue, typeSubrange.maxValue);
				
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
		return String.format("ARRAY %s OF %s", typeSubrange.toMesaType(), arrayElement.toMesaType());
	}
}
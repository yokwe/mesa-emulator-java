package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;

public class FieldType extends Field {
	public final String        fieldName;
	public final TypeReference type;
	
	public FieldType(String prefix, String name, int offset, String typeName) {
		super(name, offset, TargetKind.TYPE);
		
		this.fieldName = name;
		this.type = new TypeReference(prefix + "#" + name + "#fieldType", typeName);
		
		fix();
	}
	public FieldType(String prefix, String name, int offset, int startPos, int stopPos, String typeName) {
		super(name, offset, startPos, stopPos, TargetKind.TYPE);
		
		this.fieldName = name;
		this.type      = new TypeReference(prefix + "#" + name + "#fieldType", typeName);
		
		fix();
	}

	@Override
	public String toString() {
		if (hasValue()) {
			switch (fieldKind) {
			case FIELD:
				return String.format("{%s(%d) %s %s %s %s}", fieldName, offset, targetKind, fieldKind, getSize(), type.baseName);
			case BIT_FIELD:
				return String.format("{%s(%d:%d..%d) %s %s %s %s}", fieldName, offset, startPos, stopPos, targetKind, fieldKind, getSize(), type);
			}
		} else {
			switch (fieldKind) {
			case FIELD:
				return String.format("{%s(%d) %s %s %s %s}", fieldName, offset, targetKind, fieldKind, "*UNKNOWN*", type.baseName);
			case BIT_FIELD:
				return String.format("{%s(%d:%d..%d) %s %s %s %s}", fieldName, offset, startPos, stopPos, targetKind, fieldKind, "*UNKNOWN*", type);
			}
		}
		logger.error("Unexpected");
		throw new UnexpectedException("Unexpected");
	}

	@Override
	public String toMesaType() {
		String fieldType;
		if (type.baseName.contains("#")) {
			// Expand type definition
			fieldType = type.baseType.toMesaType();
		} else {
			fieldType = type.baseType.name;
		}
		
		switch (fieldKind) {
		case FIELD:
			return String.format("%s(%d): %s", fieldName, offset, fieldType);
		case BIT_FIELD:
			return String.format("%s(%d:%d..%d): %s", fieldName, offset, startPos, stopPos, fieldType);
		default:
			throw new UnexpectedException();
		}
	}
	
	@Override
	public void fix() {
		if (needsFix()) {
			type.fix();
			if (type.hasValue()) {
				setSize(type.getSize());
			}
		}
	}
}
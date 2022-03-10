package yokwe.majuro.symbol.model;

import java.util.ArrayList;
import java.util.List;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.antlr.SymbolParser.ArrayElementTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ArrayTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstantTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstantTypeNumericCardinalContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstantTypeNumericContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstantTypeNumericLongCardinalContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstantValueContext;
import yokwe.majuro.symbol.antlr.SymbolParser.DeclConstantContext;
import yokwe.majuro.symbol.antlr.SymbolParser.DeclTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.EnumTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.EumElementContext;
import yokwe.majuro.symbol.antlr.SymbolParser.FieldNameContext;
import yokwe.majuro.symbol.antlr.SymbolParser.FieldTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.PointerRefTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.PointerTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.RecordFieldContext;
import yokwe.majuro.symbol.antlr.SymbolParser.RecordTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ReferenceTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.SimpleTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.SubrangeTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeArrayReferenceContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeArraySubrangeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeBooleanContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeCardinalContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeFieldNameBitContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeFieldNameContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeIntegerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongCardinalContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongPointerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongUnspecifiedContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypePointerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypePointerLongContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypePointerShortContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeRecord16Context;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeRecord32Context;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeUnspecifiedContext;

public class SymbolUtil {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	//
	// Type
	//
	static Type getType(DeclTypeContext declType) {
		String name = declType.name.getText();
		TypeTypeContext type = declType.typeType();
		
		if (type.simpleType() != null) {
			return SymbolUtil.getType(name, type.simpleType());
		}
		if (type.referenceType() != null) {
			return SymbolUtil.getType(name, type.referenceType());
		}
		if (type.pointerType() != null) {
			return SymbolUtil.getType(name, type.pointerType());
		}
		if (type.subrangeType() != null) {
			return SymbolUtil.getType(name, type.subrangeType());
		}
		if (type.enumType() != null) {
			return SymbolUtil.getType(name, type.enumType());
		}
		if (type.arrayType() != null) {
			return SymbolUtil.getType(name, type.arrayType());
		}
		if (type.recordType() != null) {
			return SymbolUtil.getType(name, type.recordType());
		}

		logger.error("Unexpected");
		logger.error("  declType  %s", declType.getText());
		throw new UnexpectedException("Unexpected");
	}
	//
	// Simple
	//
	static Type getType(String name, SimpleTypeContext type) {
		if (type instanceof TypeBooleanContext) {
			return new TypeReference(name, Type.BOOLEAN.name);
		}
		if (type instanceof TypeIntegerContext) {
			return new TypeReference(name, Type.INTEGER.name);
		}
		if (type instanceof TypeCardinalContext) {
			return new TypeReference(name, Type.CARDINAL.name);
		}
		if (type instanceof TypeLongCardinalContext) {
			return new TypeReference(name, Type.LONG_CARDINAL.name);
		}
		if (type instanceof TypeUnspecifiedContext) {
			return new TypeReference(name, Type.UNSPECIFIED.name);
		}
		if (type instanceof TypeLongUnspecifiedContext) {
			return new TypeReference(name, Type.LONG_UNSPECIFIED.name);
		}
		if (type instanceof TypePointerContext) {
			return new TypeReference(name, Type.POINTER.name);
		}
		if (type instanceof TypeLongPointerContext) {
			return new TypeReference(name, Type.LONG_POINTER.name);
		}
		
		logger.error("Unexpected");
		logger.error("  name  %s", name);
		logger.error("  type  %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	
	
	//
	// Reference
	//
	static TypeReference getType(String name, ReferenceTypeContext type) {
		return new TypeReference(name, type.name.getText());
	}
	
	
	//
	// Pointer
	//
	static Type getType(String name, PointerRefTypeContext type) {
		if (type.simpleType() != null){
			return getType(name, type.simpleType());
		}
		if (type.referenceType() != null) {
			return getType(name, type.referenceType());
		}
//		if (type.arrayType() != null){
//			return SymbolUtil.getType(name, type.arrayType());
//		}
		logger.error("Unexpected");
		logger.error("  name  %s", name);
		logger.error("  type  %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	static Type getType(String name, PointerTypeContext type) {
		if (type instanceof TypePointerShortContext) {
			TypePointerShortContext typePointerShort = (TypePointerShortContext)type;
			Type typeRef = getType(name + "#ref", typePointerShort.pointerRefType());
			return new TypePointerShort(name, typeRef);
		}
		if (type instanceof TypePointerLongContext) {
			TypePointerLongContext typePointerLong = (TypePointerLongContext)type;
			Type typeRef = getType(name + "#ref", typePointerLong.pointerRefType());
			return new TypePointerLong(name, typeRef);
		}
		
		logger.error("Unexpected");
		logger.error("  name  %s", name);
		logger.error("  type  %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	//
	// Subrange
	//
	static TypeSubrange getType(String name, SubrangeTypeContext context) {
		String minValue  = context.minValue.getText();
		String maxValue  = context.maxValue.getText();
		String closeChar = context.closeChar.getText();
		
		return new TypeSubrange(name, minValue, maxValue, closeChar);
	}

	//
	// Enum
	//
	static Type getType(String name, EnumTypeContext type) {
		List<TypeEnum.Item> list = new ArrayList<>();
		
		for(EumElementContext enumElement: type.enumElementList().elements) {
			String itemName  = enumElement.name.getText();
			String itemValue = enumElement.value.getText();
			
			list.add(new TypeEnum.Item(itemName, itemValue));
		}
		
		return new TypeEnum(name, list);
	}

	//
	// Array
	//
	static Type getType(String name, ArrayTypeContext type) {
		if (type instanceof TypeArrayReferenceContext) {
			return SymbolUtil.getType(name, (TypeArrayReferenceContext)type);
		}
		if (type instanceof TypeArraySubrangeContext) {
			return SymbolUtil.getType(name, (TypeArraySubrangeContext)type);
		}
		
		logger.error("Unexpected");
		logger.error("  name  %s", name);
		logger.error("  type  %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	private static Type getType(String name, TypeArrayReferenceContext type) {
		TypeReference typeReference = getType(name + "#index", type.referenceType());
		Type arrayElement = SymbolUtil.getType(name + "#element", type.arrayElementType());
	
		return new TypeArrayRef(name, typeReference, arrayElement);
	}

	private static Type getType(String name, TypeArraySubrangeContext type) {
		TypeSubrange typeSubrange = getType(name + "#index", type.subrangeType());
		Type arrayElement = SymbolUtil.getType(name + "#element", type.arrayElementType());
		
		return new TypeArraySub(name, typeSubrange, arrayElement);
	}

	private static Type getType(String name, ArrayElementTypeContext type) {
		if (type.simpleType() != null) {
			return getType(name, type.simpleType());
		}
		if (type.pointerType() != null) {
			return getType(name, type.pointerType());
		}
		if (type.referenceType() != null) {
			return getType(name, type.referenceType());
		}
		
		logger.error("Unexpected");
		logger.error("  name  %s", name);
		logger.error("  type  %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	
	//
	// Record
	//
	static Type getType(String name, RecordTypeContext type) {
		if (type instanceof TypeRecord16Context) {
			TypeRecord16Context typeRecord = (TypeRecord16Context)type;
			
			List<TypeRecord.Field> list = new ArrayList<>();
			for(var e: typeRecord.recordFieldList().elements) {
				list.add(getField(name, e));
			}
			
			boolean bitField16 = true;
			for(var e: list) {
				if (e.offset != 0)   bitField16 = false;
				if (16 <= e.stopBit) bitField16 = false;
				// FIXME if field is open array, record is not BitField16
				if (e.type instanceof TypeArraySub) {
					TypeArraySub typeArraySub = e.type.toTypeArraySub();
					if (typeArraySub.typeSubrange.openSubrange()) bitField16 = false;
				}
			}
			if (bitField16) {
				return new TypeBitField16(name, list);
			} else {
				return new TypeMultiWord(name, list);
			}
		}
		if (type instanceof TypeRecord32Context) {
			TypeRecord32Context typeRecord = (TypeRecord32Context)type;
			
			List<TypeRecord.Field> list = new ArrayList<>();
			for(var e: typeRecord.recordFieldList().elements) {
				list.add(getField(name, e));
			}
			
			return new TypeBitField32(name, list);
		}
		
		logger.error("Unexpected");
		logger.error("  name  %s", name);
		logger.error("  type  %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	private static Type getType(String name, FieldTypeContext type) {
		if (type.simpleType() != null) {
			return getType(name, type.simpleType());
		}
		if (type.referenceType() != null) {
			return getType(name, type.referenceType());
		}
		if (type.arrayType() != null) {
			return getType(name, type.arrayType());
		}
		if (type.pointerType() != null) {
			return getType(name, type.pointerType());
		}
		
		logger.error("Unexpected");
		logger.error("  name  %s", name);
		logger.error("  type  %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	private static TypeRecord.Field getField(String typeName, RecordFieldContext recordField) {
		FieldNameContext fieldName = recordField.fieldName();
		FieldTypeContext fieldType = recordField.fieldType();
		
		if (fieldName instanceof TypeFieldNameContext) {
			TypeFieldNameContext typeFieldName = (TypeFieldNameContext)recordField.fieldName();
			String name   = typeFieldName.name.getText();
			String offset = typeFieldName.offset.getText();
			Type   type   = getType(typeName + "#" + name, fieldType);
			return new TypeRecord.Field(name, offset, type);
			
		}
		if (fieldName instanceof TypeFieldNameBitContext) {
			TypeFieldNameBitContext typeFieldName = (TypeFieldNameBitContext)recordField.fieldName();
			String name     = typeFieldName.name.getText();
			String offset   = typeFieldName.offset.getText();
			String startBit = typeFieldName.startBit.getText();
			String stopBit  = typeFieldName.stopBit.getText();
			Type   type     = getType(typeName + "#" + name, fieldType);
			return new TypeRecord.Field(name, offset, startBit, stopBit, type);
		}
		
		logger.error("Unexpected");
		logger.error("  type  %s", recordField.getText());
		throw new UnexpectedException("Unexpected");
	}
	
	
	//
	// Constant
	//
	static Constant getConstant(DeclConstantContext declConstant) {
		String name = declConstant.name.getText();
// FIXME		String value = String.join(".", declConstant.constantValue().name.stream().map(o -> o.getText()).toArray(String[]::new));
		ConstantValueContext valueContext = declConstant.constantValue();
		String value;

		if (valueContext.constantValueQName() != null) {
			value = String.join(".", valueContext.constantValueQName().name.stream().map(o -> o.getText()).toArray(String[]::new));
		} else if (valueContext.constantValueConstant() != null) {
			value = valueContext.constantValueConstant().getText();
		} else {
			throw new UnexpectedException();
		}

		ConstantTypeContext type = declConstant.constantType();
		if (type.constantTypeNumeric() != null) {
			return getConstant(name, type.constantTypeNumeric(), value);
		}
		if (type.pointerType() != null) {
			return getConstant(name, type.pointerType(), value);			
		}
		if (type.referenceType() != null) {
			return getConstant(name, type.referenceType(), value);			
		}
		
		logger.error("Unexpected");
		logger.error("  declConstant  %s", declConstant.getText());
		throw new UnexpectedException("Unexpected");
	}
	private static Constant getConstant(String name, ConstantTypeNumericContext type, String value) {
		if (type instanceof ConstantTypeNumericCardinalContext) {
			return new Constant(name, Type.CARDINAL, value);
		}
		if (type instanceof ConstantTypeNumericLongCardinalContext) {
			return new Constant(name, Type.CARDINAL, value);
		}
		
		logger.error("Unexpected");
		logger.error("  name  %s", name);
		logger.error("  type  %s", type);
		logger.error("  value %s", value);
		throw new UnexpectedException("Unexpected");
	}
	private static Constant getConstant(String name, PointerTypeContext type, String value) {
		Type pointerType = SymbolUtil.getType(name + "#pointer", type);
		return new Constant(name, pointerType, value);
	}
	private static Constant getConstant(String name, ReferenceTypeContext type, String value) {
		Type referenceType = SymbolUtil.getType(name + "#reference", type);
		return new Constant(name, referenceType, value);
	}
}

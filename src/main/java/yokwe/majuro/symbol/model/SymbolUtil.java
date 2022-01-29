package yokwe.majuro.symbol.model;

import java.util.ArrayList;
import java.util.List;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.antlr.SymbolParser.ArrayElementTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ArrayTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstantTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstantTypeNumericCARDINALContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstantTypeNumericContext;
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
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SymbolUtil.class);

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
		logger.error("  declType  {}", declType.getText());
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
		
		Symbol.logger.error("Unexpected");
		Symbol.logger.error("  name  {}", name);
		Symbol.logger.error("  type  {}", type.getText());
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
		if (type.arrayType() != null){
			return SymbolUtil.getType(name, type.arrayType());
		}
		Symbol.logger.error("Unexpected");
		Symbol.logger.error("  name  {}", name);
		Symbol.logger.error("  type  {}", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	static Type getType(String name, PointerTypeContext type) {
		if (type instanceof TypePointerShortContext) {
			TypePointerShortContext typePointerShort = (TypePointerShortContext)type;
			Type typeRef = getType(name + "#ref", typePointerShort.pointerRefType());
			return new TypePointer(name, TypePointer.PointerSize.SHORT, typeRef);
		}
		if (type instanceof TypePointerLongContext) {
			TypePointerLongContext typePointerLong = (TypePointerLongContext)type;
			Type typeRef = getType(name + "#ref", typePointerLong.pointerRefType());
			return new TypePointer(name, TypePointer.PointerSize.LONG, typeRef);
		}
		
		Symbol.logger.error("Unexpected");
		Symbol.logger.error("  name  {}", name);
		Symbol.logger.error("  type  {}", type.getText());
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
		
		Symbol.logger.error("Unexpected");
		Symbol.logger.error("  name  {}", name);
		Symbol.logger.error("  type  {}", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	private static Type getType(String name, TypeArrayReferenceContext type) {
		TypeReference typeReference = getType(name + "#index", type.referenceType());
		Type arrayElement = SymbolUtil.getType(name + "#element", type.arrayElementType());
	
		return new TypeArray.Reference(name, typeReference, arrayElement);
	}

	private static Type getType(String name, TypeArraySubrangeContext type) {
		TypeSubrange typeSubrange = getType(name + "#index", type.subrangeType());
		Type arrayElement = SymbolUtil.getType(name + "#element", type.arrayElementType());
		
		return new TypeArray.Subrange(name, typeSubrange, arrayElement);
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
		
		Symbol.logger.error("Unexpected");
		Symbol.logger.error("  name  {}", name);
		Symbol.logger.error("  type  {}", type.getText());
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
			
			return new TypeRecord(name, TypeRecord.Align.BIT_16, list);
		}
		if (type instanceof TypeRecord32Context) {
			TypeRecord32Context typeRecord = (TypeRecord32Context)type;
			
			List<TypeRecord.Field> list = new ArrayList<>();
			for(var e: typeRecord.recordFieldList().elements) {
				list.add(getField(name, e));
			}
			
			return new TypeRecord(name, TypeRecord.Align.BIT_32, list);
		}
		
		Symbol.logger.error("Unexpected");
		Symbol.logger.error("  name  {}", name);
		Symbol.logger.error("  type  {}", type.getText());
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
		
		Symbol.logger.error("Unexpected");
		Symbol.logger.error("  name  {}", name);
		Symbol.logger.error("  type  {}", type.getText());
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
		
		Symbol.logger.error("Unexpected");
		Symbol.logger.error("  type  {}", recordField.getText());
		throw new UnexpectedException("Unexpected");
	}
	
	
	//
	// Constant
	//
	static Constant getConstant(DeclConstantContext declConstant) {
		String name = declConstant.name.getText();
		String value = String.join(".", declConstant.constantValue().name.stream().map(o -> o.getText()).toArray(String[]::new));

		ConstantTypeContext type = declConstant.constantType();
		if (type.constantTypeNumeric() != null) {
			return getConstant(name, type.constantTypeNumeric(), value);
		}
		if (type.pointerType() != null) {
			return getConstant(name, type.pointerType(), value);			
		}
		
		logger.error("Unexpected");
		logger.error("  declConstant  {}", declConstant.getText());
		throw new UnexpectedException("Unexpected");
	}
	private static Constant getConstant(String name, ConstantTypeNumericContext type, String value) {
		// FIXME
		if (type instanceof ConstantTypeNumericCARDINALContext) {
			return new Constant(name, Type.CARDINAL, value);
		}
		
		logger.error("Unexpected");
		logger.error("  name  {}", name);
		logger.error("  type  {}", type);
		logger.error("  value {}", value);
		throw new UnexpectedException("Unexpected");
	}
	private static Constant getConstant(String name, PointerTypeContext type, String value) {
		// FIXME
		Type pointerType = SymbolUtil.getType(name + "#pointer", type);
		return new Constant(name, pointerType, value);
	}
}

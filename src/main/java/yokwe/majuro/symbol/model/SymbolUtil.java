package yokwe.majuro.symbol.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import yokwe.majuro.symbol.antlr.SymbolParser.TypeReferenceNameContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeReferenceQNameContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeUnspecifiedContext;
import yokwe.majuro.symbol.model.TypeRecord.Field;
import yokwe.majuro.util.StringUtil;

public class SymbolUtil {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	//
	// Type
	//
	static Type getType(String module, DeclTypeContext declType) {
		QName qName = new QName(module, declType.name.getText());
		TypeTypeContext type = declType.typeType();
		
		if (type.simpleType() != null) {
			return getType(qName, type.simpleType());
		}
		if (type.referenceType() != null) {
			return getType(qName, type.referenceType());
		}
		if (type.pointerType() != null) {
			return getType(qName, type.pointerType());
		}
		if (type.subrangeType() != null) {
			return getType(qName, type.subrangeType());
		}
		if (type.enumType() != null) {
			return getType(qName, type.enumType());
		}
		if (type.arrayType() != null) {
			return getType(qName, type.arrayType());
		}
		if (type.recordType() != null) {
			return getType(qName, type.recordType());
		}

		logger.error("Unexpected");
		logger.error("  declType  %s", declType.getText());
		throw new UnexpectedException("Unexpected");
	}
	//
	// Simple
	//
	static Type getType(QName qName, SimpleTypeContext type) {
		if (type instanceof TypeBooleanContext) {
			return new TypeReference(qName, Type.BOOLEAN.qName.qName);
		}
		if (type instanceof TypeIntegerContext) {
			return new TypeReference(qName, Type.INTEGER.qName.qName);
		}
		if (type instanceof TypeCardinalContext) {
			return new TypeReference(qName, Type.CARDINAL.qName.qName);
		}
		if (type instanceof TypeLongCardinalContext) {
			return new TypeReference(qName, Type.LONG_CARDINAL.qName.qName);
		}
		if (type instanceof TypeUnspecifiedContext) {
			return new TypeReference(qName, Type.UNSPECIFIED.qName.qName);
		}
		if (type instanceof TypeLongUnspecifiedContext) {
			return new TypeReference(qName, Type.LONG_UNSPECIFIED.qName.qName);
		}
		if (type instanceof TypePointerContext) {
			return new TypeReference(qName, Type.POINTER.qName.qName);
		}
		if (type instanceof TypeLongPointerContext) {
			return new TypeReference(qName, Type.LONG_POINTER.qName.qName);
		}
		
		logger.error("Unexpected");
		logger.error("  name  %s", qName);
		logger.error("  type  %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	
	
	//
	// Reference
	//
	static TypeReference getType(QName qName, ReferenceTypeContext type) {
		if (type instanceof TypeReferenceNameContext) {
			TypeReferenceNameContext typeReference = (TypeReferenceNameContext)type;
			String typeString = typeReference.name.getText();
			return new TypeReference(qName, typeString);
		}
		if (type instanceof TypeReferenceQNameContext) {
			TypeReferenceQNameContext typeReference = (TypeReferenceQNameContext)type;
			String typeString = typeReference.module.getText() + "." + typeReference.name.getText();
			return new TypeReference(qName, typeString);
		}
		
		logger.error("Unexpected");
		logger.error("  qName  %s", qName);
		logger.error("  type   %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	
	
	//
	// Pointer
	//
	static Type getType(QName qName, PointerRefTypeContext type) {
		if (type.simpleType() != null){
			return getType(qName, type.simpleType());
		}
		if (type.referenceType() != null) {
			return getType(qName, type.referenceType());
		}
//		if (type.arrayType() != null){
//			return getType(name, type.arrayType());
//		}
		logger.error("Unexpected");
		logger.error("  qName  %s", qName);
		logger.error("  type   %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	static Type getType(QName qName, PointerTypeContext type) {
		if (type instanceof TypePointerShortContext) {
			TypePointerShortContext typePointerShort = (TypePointerShortContext)type;
			Type typeRef = getType(qName.appendSuffix("#ref"), typePointerShort.pointerRefType());
			
			
			return new TypePointerShort(qName, typeRef);
		}
		if (type instanceof TypePointerLongContext) {
			TypePointerLongContext typePointerLong = (TypePointerLongContext)type;
			Type typeRef = getType(qName.appendSuffix("#ref"), typePointerLong.pointerRefType());
			return new TypePointerLong(qName, typeRef);
		}
		
		logger.error("Unexpected");
		logger.error("  qName  %s", qName);
		logger.error("  type   %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	//
	// Subrange
	//
	static TypeSubrange getType(QName qName, SubrangeTypeContext context) {
		String minValue  = context.minValue.getText();
		String maxValue  = context.maxValue.getText();
		String closeChar = context.closeChar.getText();
		
		return new TypeSubrange(qName, minValue, maxValue, closeChar);
	}

	//
	// Enum
	//
	static Type getType(QName qName, EnumTypeContext type) {
		List<TypeEnum.Item> list = new ArrayList<>();
		
		for(EumElementContext enumElement: type.enumElementList().elements) {
			String itemName  = enumElement.name.getText();
			String itemValue = enumElement.value.getText();
			
			list.add(new TypeEnum.Item(itemName, itemValue));
		}
		
		return new TypeEnum(qName, list);
	}

	//
	// Array
	//
	static Type getType(QName qName, ArrayTypeContext type) {
		if (type instanceof TypeArrayReferenceContext) {
			return getType(qName, (TypeArrayReferenceContext)type);
		}
		if (type instanceof TypeArraySubrangeContext) {
			return getType(qName, (TypeArraySubrangeContext)type);
		}
		
		logger.error("Unexpected");
		logger.error("  qName  %s", qName);
		logger.error("  type   %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	private static Type getType(QName qName, TypeArrayReferenceContext type) {
		TypeReference typeReference = getType(qName.appendSuffix("#index"), type.referenceType());
		Type arrayElement = getType(qName.appendSuffix("#element"), type.arrayElementType());
	
		return new TypeArrayRef(qName, typeReference, arrayElement);
	}

	private static Type getType(QName qName, TypeArraySubrangeContext type) {
		TypeSubrange typeSubrange = getType(qName.appendSuffix("#index"), type.subrangeType());
		Type arrayElement = getType(qName.appendSuffix("#element"), type.arrayElementType());
		
		return new TypeArraySub(qName, typeSubrange, arrayElement);
	}

	private static Type getType(QName qName, ArrayElementTypeContext type) {
		if (type.simpleType() != null) {
			return getType(qName, type.simpleType());
		}
		if (type.pointerType() != null) {
			return getType(qName, type.pointerType());
		}
		if (type.referenceType() != null) {
			return getType(qName, type.referenceType());
		}
		
		logger.error("Unexpected");
		logger.error("  qName  %s", qName);
		logger.error("  type   %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}

	
	//
	// Record
	//
	static Type getType(QName qName, RecordTypeContext type) {
		List<Field> list = new ArrayList<>();
		for(var e: type.recordFieldList().elements) {
			list.add(getField(qName, e));
		}
		
		Map<Integer, List<Field>> fieldMap = TypeRecord.getFieldMap(list);
		if (fieldMap.size() == 1) {
			var fieldList = fieldMap.get(0);
			Field last = fieldList.get(fieldList.size() - 1);
			
			if (!last.hasStartBit()) {
				return new TypeMultiWord(qName, list);
			}
			if (last.stopBit <= 15) {
				return new TypeBitField(qName, TypeRecord.SubType.BIT_FIELD_16, list);
			}
			if (last.stopBit <= 31) {
				return new TypeBitField(qName, TypeRecord.SubType.BIT_FIELD_32, list);
			}
			
			logger.error("Unexpected");
			logger.error("  name  %s", qName);
			logger.error("  type  %s", type.getText());
			throw new UnexpectedException("Unexpected");
		} else {
			return new TypeMultiWord(qName, list);
		}
	}
	private static Type getType(QName qName, FieldTypeContext type) {
		if (type.simpleType() != null) {
			return getType(qName, type.simpleType());
		}
		if (type.referenceType() != null) {
			return getType(qName, type.referenceType());
		}
		if (type.arrayType() != null) {
			return getType(qName, type.arrayType());
		}
		if (type.pointerType() != null) {
			return getType(qName, type.pointerType());
		}
		
		logger.error("Unexpected");
		logger.error("  qName  %s", qName);
		logger.error("  type   %s", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	private static Field getField(QName typeName, RecordFieldContext recordField) {
		FieldNameContext fieldName = recordField.fieldName();
		FieldTypeContext fieldType = recordField.fieldType();
		
		if (fieldName instanceof TypeFieldNameContext) {
			TypeFieldNameContext typeFieldName = (TypeFieldNameContext)recordField.fieldName();
			String name   = typeFieldName.name.getText();
			String offset = typeFieldName.offset.getText();
			Type   type   = getType(typeName.appendSuffix("#" + name), fieldType);
			return new Field(name, offset, type);
			
		}
		if (fieldName instanceof TypeFieldNameBitContext) {
			TypeFieldNameBitContext typeFieldName = (TypeFieldNameBitContext)recordField.fieldName();
			String name     = typeFieldName.name.getText();
			String offset   = typeFieldName.offset.getText();
			String startBit = typeFieldName.startBit.getText();
			String stopBit  = typeFieldName.stopBit.getText();
			Type   type     = getType(typeName.appendSuffix("#" + name), fieldType);
			return new Field(name, offset, startBit, stopBit, type);
		}
		
		logger.error("Unexpected");
		logger.error("  type  %s", recordField.getText());
		throw new UnexpectedException("Unexpected");
	}
	
	
	//
	// Constant
	//
	static Constant getConstant(String module, DeclConstantContext declConstant) {
		QName qName = new QName(module, StringUtil.toJavaConstName(declConstant.name.getText()));
		
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
			return getConstant(qName, type.constantTypeNumeric(), value);
		}
		if (type.pointerType() != null) {
			return getConstant(qName, type.pointerType(), value);			
		}
		if (type.referenceType() != null) {
			return getConstant(qName, type.referenceType(), value);			
		}
		
		logger.error("Unexpected");
		logger.error("  declConstant  %s", declConstant.getText());
		throw new UnexpectedException("Unexpected");
	}
	private static Constant getConstant(QName qName, ConstantTypeNumericContext type, String value) {
		if (type instanceof ConstantTypeNumericCardinalContext) {
			return new Constant(qName, Type.CARDINAL, value);
		}
		if (type instanceof ConstantTypeNumericLongCardinalContext) {
			return new Constant(qName, Type.LONG_CARDINAL, value);
		}
		
		logger.error("Unexpected");
		logger.error("  qName  %s", qName);
		logger.error("  type   %s", type);
		logger.error("  value  %s", value);
		throw new UnexpectedException("Unexpected");
	}
	private static Constant getConstant(QName qName, PointerTypeContext type, String value) {
		Type pointerType = getType(qName.appendSuffix("#pointer"), type);
		return new Constant(qName, pointerType, value);
	}
	private static Constant getConstant(QName qName, ReferenceTypeContext type, String value) {
		Type referenceType = getType(qName.appendSuffix("#reference"), type);
		return new Constant(qName, referenceType, value);
	}
}

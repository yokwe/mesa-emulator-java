package yokwe.majuro.symbol.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.antlr.SymbolLexer;
import yokwe.majuro.symbol.antlr.SymbolParser;
import yokwe.majuro.symbol.antlr.SymbolParser.*;
import yokwe.majuro.util.StringUtil;

public class Symbol {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Symbol.class);
	
	public static final String  PATH_RULE_FILE_TYPE = "data/type/Type.symbol";
	public static final String  PATH_RULE_FILE_TEST = "data/type/Test.symbol";
	
	public static Symbol getInstance(String filePath) {
		try {
			CharStream input = CharStreams.fromFileName(filePath);
			
			SymbolLexer       lexer   = new SymbolLexer(input);
			CommonTokenStream tokens  = new CommonTokenStream(lexer);
			
			tokens.fill();
			
			SymbolParser parser = new SymbolParser(tokens);
			parser.setErrorHandler(new BailErrorStrategy());
			
			SymbolContext tree = parser.symbol();
			
			Symbol symbol = new Symbol(tree);
			symbol.build(tree);
			
			return symbol;
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}

	
	public abstract static class Decl {
		public final String name;
		
		public Decl(String name) {
			this.name = name;
		}
	}
	public static class DeclConstant extends Decl {
		public final Constant value;
		
		public DeclConstant(Constant value) {
			super(value.name);
			this.value = value;
		}
		@Override
		public String toString() {
			return value.toMesaType();
		}
	}
	public static class DeclType extends Decl {
		public final Type value;
		
		public DeclType(Type value) {
			super(value.name);
			this.value = value;
		}
		@Override
		public String toString() {
			return value.toMesaType();
		}
	}
	
	public final String name;
	public final List<Decl> declList;
	
	private Symbol(SymbolContext tree) {
		this.name = tree.header().name.getText();
		this.declList = new ArrayList<>();
	}

	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	public void build(SymbolContext tree) {
		logger.info("build START");
		
		// append builtin types
		declList.add(new DeclType(Type.BOOLELAN));
		declList.add(new DeclType(Type.INTEGER));
		declList.add(new DeclType(Type.CARDINAL));
		declList.add(new DeclType(Type.LONG_CARDINAL));
		declList.add(new DeclType(Type.UNSPECIFIED));
		declList.add(new DeclType(Type.LONG_UNSPECIFIED));
		declList.add(new DeclType(Type.POINTER));
		declList.add(new DeclType(Type.LONG_POINTER));
		
		logger.info("build constant and type");
		for(DeclContext e: tree.body().declList().elements) {
			if (e.declConstant() != null) {
				Constant value = getConstant(e.declConstant());
				declList.add(new DeclConstant(value));
			}
			if (e.declType() != null) {
				Type value = getType(e.declType());
				declList.add(new DeclType(value));
			}
		}
		
		logger.info("cons {}", Constant.map.size());
		logger.info("type {}", Type.map.keySet().stream().filter(o -> !o.contains("#")).count());
		
		{
			int needsFixCountCons = Constant.fixAll();
			int needsFixCountType = Type.fixAll();
			
			logger.info("needsFix cons {}", needsFixCountCons);
			logger.info("needsFix type {}", needsFixCountType);
			
			if (needsFixCountCons != 0) {
				for(var e: Constant.map.values()) {
					logger.info("needsFix cons {}", e);
				}
			}
			if (needsFixCountType != 0) {
				for(var e: Type.map.values()) {
					logger.info("needsFix type {}", e);
				}
			}
			
			if (needsFixCountCons != 0 || needsFixCountType != 0) {
				logger.error("Unexpected");
				throw new UnexpectedException("Unexpected");
			}
		}
		
		logger.info("build STOP");
	}
	
	
	//
	// Constant
	//
	private Constant getConstant(DeclConstantContext declConstant) {
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
	private Constant getConstant(String name, ConstantTypeNumericContext type, String value) {
		if (type instanceof ConstantTypeNumericCARDINALContext) {
			return new Constant(name, Type.CARDINAL, value);
		}
		
		logger.error("Unexpected");
		logger.error("  name  {}", name);
		logger.error("  type  {}", type);
		logger.error("  value {}", value);
		throw new UnexpectedException("Unexpected");
	}
	private Constant getConstant(String name, PointerTypeContext type, String value) {
		Type pointerType = getType(name + "#pointer", type);
		return new Constant(name, pointerType, value);
	}
	
	
	//
	// Type
	//
	private Type getType(DeclTypeContext declType) {
		String name = declType.name.getText();
		TypeTypeContext type = declType.typeType();
		
		if (type.simpleType() != null) {
			return getType(name, type.simpleType());
		}
		if (type.referenceType() != null) {
			return getType(name, type.referenceType());
		}
		if (type.pointerType() != null) {
			return getType(name, type.pointerType());
		}
		if (type.subrangeType() != null) {
			return getType(name, type.subrangeType());
		}
		if (type.enumType() != null) {
			return getType(name, type.enumType());
		}
		if (type.arrayType() != null) {
			return getType(name, type.arrayType());
		}
		if (type.recordType() != null) {
			return getType(name, type.recordType());
		}

		logger.error("Unexpected");
		logger.error("  declType  {}", declType.getText());
		throw new UnexpectedException("Unexpected");
	}
	//
	// Simple
	//
	private Type getType(String name, SimpleTypeContext type) {
		if (type instanceof TypeBooleanContext) {
			return new TypeReference(name, Type.BOOLELAN.name);
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
		logger.error("  name  {}", name);
		logger.error("  type  {}", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	//
	// Reference
	//
	private TypeReference getType(String name, ReferenceTypeContext type) {
		return new TypeReference(name, type.name.getText());
	}
	//
	// Pointer
	//
	private Type getType(String name, PointerTypeContext type) {
		if (type instanceof TypePointerShortContext) {
			TypePointerShortContext typePointerShort = (TypePointerShortContext)type;
			Type typeRef = getType(name + "#ref", typePointerShort.referenceType());
			return new TypePointer(name, TypePointer.PointerSize.SHORT, typeRef);
		}
		if (type instanceof TypePointerLongContext) {
			TypePointerLongContext typePointerLong = (TypePointerLongContext)type;
			Type typeRef = getType(name + "#ref", typePointerLong.referenceType());
			return new TypePointer(name, TypePointer.PointerSize.LONG, typeRef);
		}
		
		logger.error("Unexpected");
		logger.error("  name  {}", name);
		logger.error("  type  {}", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	//
	// Subrange
	//
	private TypeSubrange getType(String name, SubrangeTypeContext context) {
		String minValue  = context.minValue.getText();
		String maxValue  = context.maxValue.getText();
		String closeChar = context.closeChar.getText();
		
		return new TypeSubrange(name, minValue, maxValue, closeChar);
	}
	//
	// Enum
	//
	private Type getType(String name, EnumTypeContext type) {
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
	private Type getType(String name, ArrayTypeContext type) {
		if (type instanceof TypeArrayReferenceContext) {
			return getType(name, (TypeArrayReferenceContext)type);
		}
		if (type instanceof TypeArraySubrangeContext) {
			return getType(name, (TypeArraySubrangeContext)type);
		}
		
		logger.error("Unexpected");
		logger.error("  name  {}", name);
		logger.error("  type  {}", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	private Type getType(String name, TypeArrayReferenceContext type) {
		TypeReference typeReference = getType(name + "#index", type.referenceType());
		Type arrayElement = getType(name + "#element", type.arrayElementType());

		return new TypeArray.Reference(name, typeReference, arrayElement);
	}
	private Type getType(String name, TypeArraySubrangeContext type) {
		TypeSubrange typeSubrange = getType(name + "#index", type.subrangeType());
		Type arrayElement = getType(name + "#element", type.arrayElementType());
		
		return new TypeArray.Subrange(name, typeSubrange, arrayElement);
	}
	private Type getType(String name, ArrayElementTypeContext type) {
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
		logger.error("  name  {}", name);
		logger.error("  type  {}", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	//
	// Record
	//
	private Type getType(String name, FieldTypeContext type) {
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
		logger.error("  name  {}", name);
		logger.error("  type  {}", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	private TypeRecord.Field getField(String typeName, RecordFieldContext recordField) {
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
		logger.error("  type  {}", recordField.getText());
		throw new UnexpectedException("Unexpected");
	}
	private Type getType(String name, RecordTypeContext type) {
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
		
		logger.error("Unexpected");
		logger.error("  name  {}", name);
		logger.error("  type  {}", type.getText());
		throw new UnexpectedException("Unexpected");
	}
	
	public static void main(String[] args) {
		logger.info("START");
		
		String path = PATH_RULE_FILE_TYPE;
//		String path = PATH_RULE_FILE_TEST;
		logger.info("path {}", path);

		Symbol symbol = Symbol.getInstance(path);
		logger.info("symbol {}", symbol);
		
		for(var e: Constant.map.values()) {
			if (e.needsFix) logger.info("needsFix cons {}", e);
		}
		for(var e: Type.map.values()) {
			if (e.needsFix) logger.info("needsFix type {}", e);
		}
		
		logger.info("STOP");
	}
}

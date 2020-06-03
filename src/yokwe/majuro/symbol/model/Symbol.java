/*******************************************************************************
 * Copyright (c) 2020, Yasuhiro Hasegawa
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. The name of the author may not be used to endorse or promote products derived
 *      from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *******************************************************************************/
package yokwe.majuro.symbol.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.antlr.SymbolLexer;
import yokwe.majuro.symbol.antlr.SymbolParser;
import yokwe.majuro.symbol.antlr.SymbolParser.*;
import yokwe.majuro.symbol.model.Field.FieldKind;
import yokwe.majuro.symbol.model.Field.TargetKind;
import yokwe.majuro.symbol.model.TypeEnum.Element;

public class Symbol {
	private static final Logger logger = LoggerFactory.getLogger(Symbol.class);
	
	public static final String  PATH_RULE_FILE = "data/type/MesaType.symbol";

	public final String                name;
	public final Map<String, Constant> constMap;
	public final Map<String, Type>     typeMap;
	
	private Symbol(SymbolContext tree) {
		this.name     = tree.header().name.getText();
		this.constMap = new TreeMap<>();
		this.typeMap  = new TreeMap<>();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %d %d}", name, constMap.size(), typeMap.size());
	}
	
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
			logger.info("name {}", symbol.name);
			symbol.build(tree);
			
			return symbol;
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	
	private static String getTypeName(SimpleTypeContext context) {
		if (context instanceof TypeBooleanContext)         return Type.BOOL_VALUE.name;
		if (context instanceof TypeCardinalContext)        return Type.CARDINAL_VALUE.name;
		if (context instanceof TypeLongCardinalContext)    return Type.LONG_CARDINAL_VALUE.name;
		if (context instanceof TypeIntegerContext)         return Type.INTEGER_VALUE.name;
		if (context instanceof TypeLongIntegerContext)     return Type.LONG_INTEGER_VALUE.name;
		if (context instanceof TypeUnspecifiedContext)     return Type.UNSPECIFIED_VALUE.name;
		if (context instanceof TypeLongUnspecifiedContext) return Type.LONG_UNSPECIFIED_VALUE.name;
		if (context instanceof TypePointerContext)         return Type.POINTER_VALUE.name;
		if (context instanceof TypeLongPointerContext)     return Type.LONG_POINTER_VALUE.name;
		
		logger.error("Unexpected clazz");
		logger.error("  context {}", context.getClass().getName());
		throw new UnexpectedException("Unexpected clazz");
	}
	
	private static Type getType(String name, ArrayTypeTypeContext context) {
		// ARRAY rangeType  OF arrayTypeElement # TypeArrayType
		RangeTypeContext        rangeType        = context.rangeType();
		ArrayTypeElementContext arrayTypeElement = context.arrayTypeElement();
		
		if (rangeType == null) {
			logger.error("Unexpected rangeType is null");
			logger.error("  context {}", context.getText());
			throw new UnexpectedException("Unexpected rangeType is null");
		}
		if (arrayTypeElement == null) {
			logger.error("Unexpected arrayTypeElement is null");
			logger.error("  context {}", context.getText());
			throw new UnexpectedException("Unexpected arrayTypeElement is null");
		}
		String indexName = rangeType.name.getText();
		
		SimpleTypeContext    simpleType    = arrayTypeElement.simpleType();
		ReferenceTypeContext referenceType = arrayTypeElement.referenceType();

		if (simpleType != null) {
			return new TypeArrayFull(name, getTypeName(simpleType), indexName);
		} else if (referenceType != null) {
			return new TypeArrayFull(name, referenceType.name.getText(), indexName);
		} else {
			logger.error("Unexpected arrayTypeElement");
			logger.error("  arrayTypeElement {}", arrayTypeElement.getText());
			throw new UnexpectedException("Unexpected arrayTypeElement");
		}	
	}
	private static Type getType(String name, ArrayTypeRangeContext context) {
		// ARRAY rangeTypeRange OF arrayTypeElement # TypeArrayRange
		RangeTypeRangeContext   rangeTypeRange   = context.rangeTypeRange();
		ArrayTypeElementContext arrayTypeElement = context.arrayTypeElement();
		
		if (rangeTypeRange == null) {
			logger.error("Unexpected rangeTypeRange is null");
			logger.error("  context {}", context.getText());
			throw new UnexpectedException("Unexpected rangeTypeRange is null");
		}
		if (arrayTypeElement == null) {
			logger.error("Unexpected arrayTypeElement is null");
			logger.error("  context {}", context.getText());
			throw new UnexpectedException("Unexpected arrayTypeElement is null");
		}
		
		String indexName  = rangeTypeRange.name == null ? Type.CARDINAL : rangeTypeRange.name.getText();
		String startIndex = rangeTypeRange.startIndex.getText();
		String stopIndex  = rangeTypeRange.stopIndex.getText();
		String closeChar  = rangeTypeRange.closeChar.getText();

		SimpleTypeContext    simpleType    = arrayTypeElement.simpleType();
		ReferenceTypeContext referenceType = arrayTypeElement.referenceType();

		boolean rangeMaxInclusive = closeChar.equals("]");
		
		if (simpleType != null) {
			if (startIndex.equals(stopIndex) && !rangeMaxInclusive) {
				return new TypeArrayOpen(name, getTypeName(simpleType), indexName, startIndex);
			} else {
				return new TypeArraySubrange(name, getTypeName(simpleType), indexName, startIndex, stopIndex, rangeMaxInclusive);
			}
		} else if (referenceType != null) {
			if (startIndex.equals(stopIndex) && !rangeMaxInclusive) {
				return new TypeArrayOpen(name, referenceType.name.getText(), indexName, startIndex);
			} else {
				return new TypeArraySubrange(name, referenceType.name.getText(), indexName, startIndex, stopIndex, rangeMaxInclusive);
			}
		} else {
			logger.error("Unexpected arrayTypeElement");
			logger.error("  arrayTypeElement {}", arrayTypeElement.getText());
			throw new UnexpectedException("Unexpected arrayTypeElement");
		}
	}
	private static Type getType(String name, EnumTypeContext context) {
		EnumElementListContext enumElementList = context.enumElementList();
		if (enumElementList == null) {
			logger.error("Unexpected enumElementList");
			logger.error("  context {}", context.getText());
			throw new UnexpectedException("Unexpected enumElementList");
		}
		List<Element> elementList = new ArrayList<>();
		for(EumElementContext e: enumElementList.elements) {
			String elementName  = e.numberedName().name.getText();
			long   elementValue = Type.getNumericValue(e.numberedName().value.getText());
			if (Type.CARDINAL_MAX < elementValue) {
				logger.error("Unexpeced value");
				logger.error("  elementValue {}", elementValue);
				throw new UnexpectedException("Unexpeced value");
			}
			
			elementList.add(new TypeEnum.Element(elementName, (int)elementValue));
		}
		
		return new TypeEnum(name, elementList);
	}
	private static Type getType(String name, SubrangeTypeContext context) {
		RangeTypeRangeContext rangeTypeRange = context.rangeTypeRange();
		if (rangeTypeRange == null) {
			logger.error("Unexpected rangeTypeRangeContext");
			logger.error("  context {}", context.getText());
			throw new UnexpectedException("Unexpected rangeTypeRangeContext");
		}
		String baseName   = rangeTypeRange.name == null ? Type.CARDINAL : rangeTypeRange.name.getText();
		String startIndex = rangeTypeRange.startIndex.getText();
		String stopIndex  = rangeTypeRange.stopIndex.getText();
		String closeChar  = rangeTypeRange.closeChar.getText();
		
		return new TypeSubrangeRange(name, baseName, startIndex, stopIndex, closeChar.equals("]"));
	}
	private static Type getType(String name, RecordTypeContext context) {
		RecordFieldListContext recordFieldList = context.recordFieldList();
		if (recordFieldList == null) {
			logger.error("Unexpected recordFieldList");
			logger.error("  context {}", context.getText());
			throw new UnexpectedException("Unexpected recordFieldList");
		}
		
		// build field list
		List<Field> fieldList = new ArrayList<>();
		for(RecordFieldContext recordField: recordFieldList.elements) {
			FieldKind  fieldKind;
			TargetKind targetKind;
			
			String fieldName;
			int    offset;
			int    startPos;
			int    stopPos;
			Field  field;
			
			{
				FieldNameContext fieldNameContext = recordField.fieldName();
				if (fieldNameContext.numberedName() != null) {
					NumberedNameContext numberedName = fieldNameContext.numberedName();
					fieldKind = FieldKind.FEILD;
					
					fieldName = numberedName.name.getText();
					offset    = Type.getNumericValue(numberedName.value.getText()).intValue();
					startPos  = -1;
					stopPos   = -1;
				} else if (fieldNameContext.bitfieldName() != null) {
					BitfieldNameContext bitfieldName = fieldNameContext.bitfieldName();
					fieldKind = FieldKind.BIT_FIELD;
					
					fieldName = bitfieldName.name.getText();
					offset    = Type.getNumericValue(bitfieldName.offset.getText()).intValue();
					startPos  = Type.getNumericValue(bitfieldName.startPos.getText()).intValue();
					stopPos   = Type.getNumericValue(bitfieldName.stopPos.getText()).intValue();
				} else {
					logger.error("Unexpected fieldNameContext");
					logger.error("  context {}", context.getText());
					throw new UnexpectedException("Unexpected fieldNameContext");
				}
			};
			
			{
				FieldTypeContext fieldType = recordField.fieldType();
				if (fieldType.arrayType() != null) {
					// FIXME
				} else if (fieldType.simpleType() != null) {
					// FIXME
				} else if (fieldType.referenceType() != null) {
					// FIXME
				} else if (fieldType.select() != null) {
					// FIXME
				} else {
					logger.error("Unexpected fieldType");
					logger.error("  context {}", context.getText());
					throw new UnexpectedException("Unexpected fieldType");
				}
			}
		}
		return new TypeRecord(name, fieldList);
	}
	private static Type getType(String name, SimpleTypeContext context) {
		if (context instanceof TypeBooleanContext) {
			return new TypeReference(name, Type.BOOL);
		}
		if (context instanceof TypeCardinalContext) {
			return new TypeReference(name, Type.CARDINAL);
		}
		if (context instanceof TypeLongCardinalContext) {
			return new TypeReference(name, Type.LONG_CARDINAL);
		}
		if (context instanceof TypeIntegerContext) {
			return new TypeReference(name, Type.INTEGER);
		}
		if (context instanceof TypeLongIntegerContext) {
			return new TypeReference(name, Type.LONG_INTEGER);
		}
		if (context instanceof TypeUnspecifiedContext) {
			return new TypeReference(name, Type.UNSPECIFIED);
		}
		if (context instanceof TypeLongUnspecifiedContext) {
			return new TypeReference(name, Type.LONG_UNSPECIFIED);
		}
		if (context instanceof TypePointerContext) {
			return new TypeReference(name, Type.POINTER);
		}
		if (context instanceof TypeLongPointerContext) {
			return new TypeReference(name, Type.LONG_POINTER);
		}
		logger.error("Unexpected context");
		logger.error("  name    {}", name);
		logger.error("  context {}", context.getText());
		throw new UnexpectedException("Unexpected typeType");
	}
	private static Type getType(String name, ReferenceTypeContext context) {
		return new TypeReference(name, context.name.getText());
	}
	
	private static Type getType(String name, TypeTypeContext context) {
		if (context.arrayType() != null) {
			ArrayTypeContext arrayType = context.arrayType();
			if (arrayType.arrayTypeType() != null) {
				return getType(name, arrayType.arrayTypeType());
			}
			if (arrayType.arrayTypeRange() != null) {
				return getType(name, arrayType.arrayTypeRange());
			}
			logger.error("Unexpected arrayType");
			logger.error("  name     {}", name);
			logger.error("  typeType {}", arrayType.getText());
			throw new UnexpectedException("Unexpected arrayType");
		}
		if (context.enumType() != null) {
			return getType(name, context.enumType());
		}
		if (context.subrangeType() != null) {
			return getType(name, context.subrangeType());
		}
		if (context.recordType() != null) {
			return getType(name, context.recordType());
		}
		if (context.simpleType() != null) {
			return getType(name, context.simpleType());
		}
		if (context.referenceType() != null) {
			return getType(name, context.referenceType());
		}
		logger.error("Unexpected context");
		logger.error("  name    {}", name);
		logger.error("  context {}", context.getText());
		throw new UnexpectedException("Unexpected typeType");
	}

	private static Type getType(DeclContext context) {
		if (context.declConst() != null) return null;
		if (context.declType() != null) {
			DeclTypeContext declType = context.declType();
			
			String name = declType.name.getText();
			TypeTypeContext typeType = declType.typeType();
			
			logger.info("TYPE   {} == {}", name, typeType.getText());
			
			Type type = getType(name, typeType);
			logger.info("       {}", type);			
			return type;
		}
		
		logger.error("Unexpected context");
		logger.error("  context {}", context.getText());
		throw new UnexpectedException("Unexpected context");
	}

	private static Constant getConstant(DeclContext context) {
		if (context.declConst() != null) {
			DeclConstContext declConst = context.declConst();
			
			String name      = declConst.name.getText();
			String typeName  = declConst.constType().getText();
			String value     = declConst.constValue().getText();			
			return new Constant(name, typeName, value);
		}
		if (context.declType() != null) return null;
		
		logger.error("Unexpected context");
		logger.error("  context {}", context.getText());
		throw new UnexpectedException("Unexpected context");
	}
	

	void build(SymbolContext tree) {
		for(DeclContext e: tree.body().declList().elements) {
			Constant v = getConstant(e);
			if (v != null) this.constMap.put(v.name, v);
		}
		for(DeclContext e: tree.body().declList().elements) {
			Type v = getType(e);
			if (v != null) this.typeMap.put(v.name, v);
		}
	}
	
	public static void main(String[] args) {
		logger.info("START");
		
		Symbol symbol = Symbol.getInstance(PATH_RULE_FILE);
		logger.info("symbol {}", symbol);
		
		Type.fixAll();
		Type.stats();
		
		Constant.fixAll();
		Constant.stats();
		
		logger.info("STOP");
	}
}

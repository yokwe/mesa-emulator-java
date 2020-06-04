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
import yokwe.majuro.symbol.model.Select.CaseField;
import yokwe.majuro.symbol.model.Select.SelectCase;
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
	
	private static String getTypeName(PredefinedTypeContext context) {
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
	
	private static Type getType(String name, TypeArrayTypeContext context) {
		// ARRAY indexName=ID OF arrayTypeElement # TypeArrayType
		ArrayTypeElementContext arrayTypeElement = context.arrayTypeElement();
		
		String indexName = context.indexName.getText();
		PredefinedTypeContext predefinedType = arrayTypeElement.predefinedType();
		ReferenceTypeContext  referenceType  = arrayTypeElement.referenceType();

		if (predefinedType != null) {
			return new TypeArrayFull(name, getTypeName(predefinedType), indexName);
		} else if (referenceType != null) {
			return new TypeArrayFull(name, referenceType.name.getText(), indexName);
		} else {
			logger.error("Unexpected arrayTypeElement");
			logger.error("  arrayTypeElement {}", arrayTypeElement.getText());
			throw new UnexpectedException("Unexpected arrayTypeElement");
		}	
	}
	private static Type getType(String name, TypeArrayRangeContext context) {
		// ARRAY (indexName=ID)? '[' startIndex=constant '..' stopIndex=constant closeChar=(']' | ')') OF arrayTypeElement # TypeArrayRange
		ArrayTypeElementContext arrayTypeElement = context.arrayTypeElement();
		
		String  indexName         = context.indexName == null ? Type.INTEGER : context.indexName.getText();
		String  startIndex        = context.startIndex.getText();
		String  stopIndex         = context.stopIndex.getText();
		String  closeChar         = context.closeChar.getText();
		boolean rangeMaxInclusive = closeChar.equals("]");
		
		if (arrayTypeElement.predefinedType() != null) {
			PredefinedTypeContext    predefinedType    = arrayTypeElement.predefinedType();
			
			if (startIndex.equals(stopIndex) && !rangeMaxInclusive) {
				return new TypeArrayOpen(name, getTypeName(predefinedType), indexName, startIndex);
			} else {
				return new TypeArraySubrange(name, getTypeName(predefinedType), indexName, startIndex, stopIndex, rangeMaxInclusive);
			}
		} else if (arrayTypeElement.referenceType() != null) {
			ReferenceTypeContext referenceType = arrayTypeElement.referenceType();
			
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
		List<Element> elementList = new ArrayList<>();
		for(EumElementContext e: context.enumElementList().elements) {
			String elementName  = e.name.getText();
			long   elementValue = Type.getNumericValue(e.value.getText());
			// Sanity check
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
		String baseName   = context.baseName == null ? Type.CARDINAL : context.baseName.getText();
		String startIndex = context.startIndex.getText();
		String stopIndex  = context.stopIndex.getText();
		String closeChar  = context.closeChar.getText();
		
		return new TypeSubrangeRange(name, baseName, startIndex, stopIndex, closeChar.equals("]"));
	}
	private static Select getSelect(String name, SelectContext context) {
		// FIXME
		List<SelectCaseContext> elements;
		if (context instanceof TypeSelectOverlaidAnyContext) {
			TypeSelectOverlaidAnyContext selectOverlaidAny = (TypeSelectOverlaidAnyContext)context;
			
			elements = selectOverlaidAny.selectCaseList().elements;
		} else if (context instanceof TypeSelectOverlaidTypeContext) {
			TypeSelectOverlaidTypeContext selectOveraidType = (TypeSelectOverlaidTypeContext)context;
			
			elements = selectOveraidType.selectCaseList().elements;
		} else if (context instanceof TypeSelectTypeContext) {
			TypeSelectTypeContext selectType = (TypeSelectTypeContext)context;
			
			elements = selectType.selectCaseList().elements;
		} else if (context instanceof TypeSelectAnonContext) {
			TypeSelectAnonContext selectAnon = (TypeSelectAnonContext)context;
			
			elements = selectAnon.selectCaseList().elements;
		} else {
			logger.error("Unexpected context");
			logger.error("  context {}", context);
			throw new UnexpectedException("Unexpected context");
		}

		// FIXME
		List<SelectCase> selectCaseList = new ArrayList<>();
		for(SelectCaseContext selectCase: elements) {
			SelectCaseSelectorContext selectCaseSelector = selectCase.selectCaseSelector();
			
			String selectorName;
			int    selectorValue;
			
			if (selectCaseSelector instanceof TypeSelectCaseSelectorContext) {
				TypeSelectCaseSelectorContext typeSelectCaseSelector = (TypeSelectCaseSelectorContext)selectCaseSelector;
				selectorName = typeSelectCaseSelector.selectorName.getText();
				selectorValue = -1;
			} else if (selectCaseSelector instanceof TypeSelectCaseSelectorValueContext) {
				TypeSelectCaseSelectorValueContext typeSelectCaseSelectorValue = (TypeSelectCaseSelectorValueContext)selectCaseSelector;
				selectorName = typeSelectCaseSelectorValue.selectorName.getText();
				selectorValue = Type.getNumericValue(typeSelectCaseSelectorValue.selectorValue.getText()).intValue();
			} else {
				logger.error("Unexpected selectCaseSelector");
				logger.error("  selectCaseSelector {}", selectCaseSelector);
				throw new UnexpectedException("Unexpected selectCaseSelector");
			}
			
			List<CaseField> caseFieldList = new ArrayList<>();
			RecordFieldListContext recordFieldList = selectCase.recordFieldList();
			if (recordFieldList == null) {
				// FIXME
			} else {
				List<RecordFieldContext> a = recordFieldList.elements;
				for(RecordFieldContext e: recordFieldList.elements) {
					// FIXME
				}
			}
			if (selectorValue == -1) {
				selectCaseList.add(new SelectCase(selectorName, caseFieldList));
			} else {
				selectCaseList.add(new SelectCase(selectorName, selectorValue, caseFieldList));
			}
		}
		
		return new Select(selectCaseList);
	}
	private static Type getType(String name, RecordTypeContext context) {
		RecordFieldListContext recordFieldList = context.recordFieldList();
		
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
			
			// process field name
			{
				FieldNameContext fieldNameContext = recordField.fieldName();
				if (fieldNameContext instanceof TypeFieldNameContext) {
					TypeFieldNameContext typeFieldName = (TypeFieldNameContext)fieldNameContext;
					
					fieldKind = FieldKind.FIELD;
					fieldName = typeFieldName.name.getText();
					offset    = Type.getNumericValue(typeFieldName.offset.getText()).intValue();
					startPos  = -1;
					stopPos   = -1;
				} else if (fieldNameContext instanceof TypeFieldNameBitContext) {
					TypeFieldNameBitContext typeFieldNameBit = (TypeFieldNameBitContext)fieldNameContext;
					
					fieldKind = FieldKind.BIT_FIELD;
					fieldName = typeFieldNameBit.name.getText();
					offset    = Type.getNumericValue(typeFieldNameBit.offset.getText()).intValue();
					startPos  = Type.getNumericValue(typeFieldNameBit.startPos.getText()).intValue();
					stopPos   = Type.getNumericValue(typeFieldNameBit.stopPos.getText()).intValue();
				} else {
					logger.error("Unexpected fieldNameContext");
					logger.error("  context {}", context.getText());
					throw new UnexpectedException("Unexpected fieldNameContext");
				}
			}
			
			// process field type
			{
				FieldTypeContext fieldType = recordField.fieldType();
				if (fieldType.arrayType() != null) {
					// FIXME
				} else if (fieldType.predefinedType() != null) {
					switch(fieldKind) {
					case FIELD:
						fieldList.add(new FieldType(name, fieldName, offset, getTypeName(fieldType.predefinedType())));
						break;
					case BIT_FIELD:
						fieldList.add(new FieldType(name, fieldName, offset, startPos, stopPos, getTypeName(fieldType.predefinedType())));
						break;
					default:
						logger.error("Unexpected fieldKind");
						logger.error("  fieldKind {}", fieldKind);
						throw new UnexpectedException("Unexpected fieldKind");
					}
				} else if (fieldType.referenceType() != null) {
					ReferenceTypeContext referenceType = fieldType.referenceType();
					switch(fieldKind) {
					case FIELD:
						fieldList.add(new FieldType(name, fieldName, offset, referenceType.name.getText()));
						break;
					case BIT_FIELD:
						fieldList.add(new FieldType(name, fieldName, offset, startPos, stopPos, referenceType.name.getText()));
						break;
					default:
						logger.error("Unexpected fieldKind");
						logger.error("  fieldKind {}", fieldKind);
						throw new UnexpectedException("Unexpected fieldKind");
					}
				} else if (fieldType.select() != null) {
					SelectContext select = fieldType.select();
					
					Select selectObject = getSelect(name, fieldType.select());
					
					switch(fieldKind) {
					case FIELD:
						fieldList.add(new FieldSelect(name, fieldName, offset, selectObject));
						break;
					case BIT_FIELD:
						fieldList.add(new FieldSelect(name, fieldName, offset, startPos, stopPos, selectObject));
						break;
					default:
						logger.error("Unexpected fieldKind");
						logger.error("  fieldKind {}", fieldKind);
						throw new UnexpectedException("Unexpected fieldKind");
					}
				} else {
					logger.error("Unexpected fieldType");
					logger.error("  context {}", context.getText());
					throw new UnexpectedException("Unexpected fieldType");
				}
			}
		}
		return new TypeRecord(name, fieldList);
	}
	private static Type getType(String name, PredefinedTypeContext context) {
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
			if (arrayType instanceof TypeArrayTypeContext) {
				return getType(name, (TypeArrayTypeContext)arrayType);
			}
			if (arrayType instanceof TypeArrayRangeContext) {
				return getType(name, (TypeArrayRangeContext)arrayType);
			}
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
		if (context.predefinedType() != null) {
			return getType(name, context.predefinedType());
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
			
			String          name     = declType.name.getText();
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
			Constant c = getConstant(e);
			if (c != null) this.constMap.put(c.name, c);
			
			Type v = getType(e);
			if (v != null) this.typeMap.put(v.name, v);
		}
		Constant.fixAll();
		Type.fixAll();
	}
	
	public static void main(String[] args) {
		logger.info("START");
		
		Symbol symbol = Symbol.getInstance(PATH_RULE_FILE);
		logger.info("symbol {}", symbol);
		
		Type.stats();
		
		Constant.stats();
		
		logger.info("STOP");
	}
}

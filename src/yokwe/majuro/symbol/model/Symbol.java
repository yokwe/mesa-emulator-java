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
import yokwe.majuro.symbol.antlr.SymbolBaseVisitor;
import yokwe.majuro.symbol.antlr.SymbolLexer;
import yokwe.majuro.symbol.antlr.SymbolParser;
import yokwe.majuro.symbol.antlr.SymbolParser.ArrayTypeElementContext;
import yokwe.majuro.symbol.antlr.SymbolParser.DeclContext;
import yokwe.majuro.symbol.antlr.SymbolParser.EnumElementListContext;
import yokwe.majuro.symbol.antlr.SymbolParser.EumElementContext;
import yokwe.majuro.symbol.antlr.SymbolParser.RangeTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.RangeTypeRangeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ReferenceTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.SimpleTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.SymbolContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeBooleanContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeCardinalContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeIntegerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongCardinalContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongIntegerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongPointerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongUnspecifiedContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypePointerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeRefContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeUnspecifiedContext;
import yokwe.majuro.symbol.model.TypeEnum.Element;

public class Symbol {
	private static final Logger logger = LoggerFactory.getLogger(Symbol.class);
	
	public static final String  PATH_RULE_FILE = "data/type/MesaType.symbol";

	public final String             name;
	public final Map<String, Const> constMap;
	public final Map<String, Type>  typeMap;
	
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
	
	private static class TypeVisitors extends SymbolBaseVisitor<Type> {
		public final String name;
		
		public TypeVisitors(String name) {
			this.name = name;
		}
		
		// ARRAY
		@Override public Type visitTypeArrayType(SymbolParser.TypeArrayTypeContext context) {
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
				Type type = getType(simpleType);
				return new TypeArrayFull(name, type.name, indexName);
			} else if (referenceType != null) {
				TypeRefContext typeRef = (TypeRefContext)referenceType;
				return new TypeArrayFull(name, typeRef.name.getText(), indexName);
			} else {
				logger.error("Unexpected arrayTypeElement");
				logger.error("  arrayTypeElement {}", arrayTypeElement.getText());
				throw new UnexpectedException("Unexpected arrayTypeElement");
			}
			
		}
		@Override public Type visitTypeArrayRange(SymbolParser.TypeArrayRangeContext context) {
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

			if (simpleType != null) {
				Type type = getType(simpleType);
				return new TypeArraySubrange(name, type.name, indexName, startIndex, stopIndex, closeChar.equals("]"));
			} else if (referenceType != null) {
				TypeRefContext typeRef = (TypeRefContext)referenceType;
				return new TypeArraySubrange(name, typeRef.name.getText(), indexName, startIndex, stopIndex, closeChar.equals("]"));
			} else {
				logger.error("Unexpected arrayTypeElement");
				logger.error("  arrayTypeElement {}", arrayTypeElement.getText());
				throw new UnexpectedException("Unexpected arrayTypeElement");
			}
		}
		
		// ENUM
		@Override public Type visitTypeEnum(SymbolParser.TypeEnumContext context) {
			EnumElementListContext enumElementList = context.enumElementList();
			if (enumElementList == null) {
				logger.error("Unexpected enumElementList");
				logger.error("  context {}", context.getText());
				throw new UnexpectedException("Unexpected enumElementList");
			}
			List<Element> elementList = new ArrayList<>();
			for(EumElementContext e: enumElementList.elements) {
				String name  = e.numberedName().name.getText();
				long   value = Type.getNumericValue(e.numberedName().value.getText());
				if (Type.CARDINAL_MAX < value) {
					logger.error("Unexpeced value");
					logger.error("  value {}", value);
					throw new UnexpectedException("Unexpeced value");
				}
				
				elementList.add(new TypeEnum.Element(name, (int)value));
			}
			
			return new TypeEnum(name, elementList);
		}

		// SUBRANGE
		@Override public Type visitTypeSubrangeTypeRange(SymbolParser.TypeSubrangeTypeRangeContext context) {
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
		
		// RECORD
		@Override public Type visitTypeRecordField(SymbolParser.TypeRecordFieldContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeRecord(SymbolParser.TypeRecordContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		
		// REFERENCE
		@Override public Type visitTypeRef(SymbolParser.TypeRefContext context) {
			String baseName = context.name.getText();
			return new TypeReference(name, baseName);
		}
	}
	
	private static Type getType(SimpleTypeContext context) {
		if (context instanceof TypeBooleanContext)         return Type.BOOL_VALUE;
		if (context instanceof TypeCardinalContext)        return Type.CARDINAL_VALUE;
		if (context instanceof TypeLongCardinalContext)    return Type.LONG_CARDINAL_VALUE;
		if (context instanceof TypeIntegerContext)         return Type.INTEGER_VALUE;
		if (context instanceof TypeLongIntegerContext)     return Type.LONG_INTEGER_VALUE;
		if (context instanceof TypeUnspecifiedContext)     return Type.UNSPECIFIED_VALUE;
		if (context instanceof TypeLongUnspecifiedContext) return Type.LONG_UNSPECIFIED_VALUE;
		if (context instanceof TypePointerContext)         return Type.POINTER_VALUE;
		if (context instanceof TypeLongPointerContext)     return Type.LONG_POINTER_VALUE;
		
		logger.error("Unexpected clazz");
		logger.error("  context {}", context.getClass().getName());
		throw new UnexpectedException("Unexpected clazz");
	}

	private static final SymbolBaseVisitor<Type> declTypeVisitor = new SymbolBaseVisitor<>() {
		@Override
		public Type visitTypeDecl(SymbolParser.TypeDeclContext context) {
			String name = context.name.getText();
			TypeTypeContext typeType = context.typeType();
			
			logger.info("TYPE   {} == {}", name, typeType.getText());
			
			TypeVisitors typeVisitors = new TypeVisitors(name);
			
			Type type = typeVisitors.visit(typeType);
			logger.info("       {}", type);
			
			return type;
		}
	};

	private static final SymbolBaseVisitor<Const> declConstVisitor = new SymbolBaseVisitor<>() {
		@Override
		public Const visitConstDecl(SymbolParser.ConstDeclContext context) {
			String name      = context.name.getText();
			String typeName  = context.constType().getText();
			String value     = context.constValue().getText();			
			return new Const(name, typeName, value);
		}
	};


	void build(SymbolContext tree) {
		for(DeclContext e: tree.body().declList().elements) {
			Const v = declConstVisitor.visit(e);
			if (v != null) this.constMap.put(v.name, v);
		}
		for(DeclContext e: tree.body().declList().elements) {
			Type v = declTypeVisitor.visit(e);
			if (v != null) this.typeMap.put(v.name, v);
		}
	}
	
	public static void main(String[] args) {
		logger.info("START");
		
		Symbol symbol = Symbol.getInstance(PATH_RULE_FILE);
		logger.info("symbol {}", symbol);
		
		Type.fixAll();
		Type.stats();
		
		Const.fixAll();
		Const.stats();
		
		logger.info("STOP");
	}
}

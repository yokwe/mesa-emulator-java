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
// Generated from data/type/Symbol.g4 by ANTLR 4.8
package yokwe.majuro.symbol.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SymbolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SymbolVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Number8}
	 * labeled alternative in {@link SymbolParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber8(SymbolParser.Number8Context ctx);
	/**
	 * Visit a parse tree produced by the {@code Number10}
	 * labeled alternative in {@link SymbolParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber10(SymbolParser.Number10Context ctx);
	/**
	 * Visit a parse tree produced by the {@code Number16}
	 * labeled alternative in {@link SymbolParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber16(SymbolParser.Number16Context ctx);
	/**
	 * Visit a parse tree produced by the {@code PositiveNumber8}
	 * labeled alternative in {@link SymbolParser#positive_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositiveNumber8(SymbolParser.PositiveNumber8Context ctx);
	/**
	 * Visit a parse tree produced by the {@code PositiveNumber10}
	 * labeled alternative in {@link SymbolParser#positive_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositiveNumber10(SymbolParser.PositiveNumber10Context ctx);
	/**
	 * Visit a parse tree produced by the {@code PositiveNumber16}
	 * labeled alternative in {@link SymbolParser#positive_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositiveNumber16(SymbolParser.PositiveNumber16Context ctx);
	/**
	 * Visit a parse tree produced by the {@code ConstNumber}
	 * labeled alternative in {@link SymbolParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstNumber(SymbolParser.ConstNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConstRef}
	 * labeled alternative in {@link SymbolParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstRef(SymbolParser.ConstRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#rangeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeType(SymbolParser.RangeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#rangeTypeRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeTypeRange(SymbolParser.RangeTypeRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(SymbolParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#numberedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberedName(SymbolParser.NumberedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#symbol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbol(SymbolParser.SymbolContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(SymbolParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(SymbolParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#declList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclList(SymbolParser.DeclListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(SymbolParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDecl}
	 * labeled alternative in {@link SymbolParser#declType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDecl(SymbolParser.TypeDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#typeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeType(SymbolParser.TypeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(SymbolParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeArrayType}
	 * labeled alternative in {@link SymbolParser#arrayTypeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArrayType(SymbolParser.TypeArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeArrayRange}
	 * labeled alternative in {@link SymbolParser#arrayTypeRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArrayRange(SymbolParser.TypeArrayRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#arrayTypeElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayTypeElement(SymbolParser.ArrayTypeElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeEnum}
	 * labeled alternative in {@link SymbolParser#enumType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeEnum(SymbolParser.TypeEnumContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#enumElementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumElementList(SymbolParser.EnumElementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#eumElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEumElement(SymbolParser.EumElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeSubrangeTypeRange}
	 * labeled alternative in {@link SymbolParser#subrangeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSubrangeTypeRange(SymbolParser.TypeSubrangeTypeRangeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeRecord}
	 * labeled alternative in {@link SymbolParser#recordType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeRecord(SymbolParser.TypeRecordContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#recordFieldList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordFieldList(SymbolParser.RecordFieldListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeRecordField}
	 * labeled alternative in {@link SymbolParser#recordField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeRecordField(SymbolParser.TypeRecordFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#fieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldName(SymbolParser.FieldNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeBitfieldName}
	 * labeled alternative in {@link SymbolParser#bitfieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBitfieldName(SymbolParser.TypeBitfieldNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#fieldType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldType(SymbolParser.FieldTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeSelectAny}
	 * labeled alternative in {@link SymbolParser#select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSelectAny(SymbolParser.TypeSelectAnyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#selectCaseList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectCaseList(SymbolParser.SelectCaseListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeSelectCaseEmpty}
	 * labeled alternative in {@link SymbolParser#selectCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSelectCaseEmpty(SymbolParser.TypeSelectCaseEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeSelectCaseList}
	 * labeled alternative in {@link SymbolParser#selectCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSelectCaseList(SymbolParser.TypeSelectCaseListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#selectCaseSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectCaseSelector(SymbolParser.SelectCaseSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeRef}
	 * labeled alternative in {@link SymbolParser#referenceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeRef(SymbolParser.TypeRefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeBoolean}
	 * labeled alternative in {@link SymbolParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBoolean(SymbolParser.TypeBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeCardinal}
	 * labeled alternative in {@link SymbolParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeCardinal(SymbolParser.TypeCardinalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeLongCardinal}
	 * labeled alternative in {@link SymbolParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeLongCardinal(SymbolParser.TypeLongCardinalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeInteger}
	 * labeled alternative in {@link SymbolParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeInteger(SymbolParser.TypeIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeLongInteger}
	 * labeled alternative in {@link SymbolParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeLongInteger(SymbolParser.TypeLongIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeUnspecified}
	 * labeled alternative in {@link SymbolParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeUnspecified(SymbolParser.TypeUnspecifiedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeLongUnspecified}
	 * labeled alternative in {@link SymbolParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeLongUnspecified(SymbolParser.TypeLongUnspecifiedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypePointer}
	 * labeled alternative in {@link SymbolParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypePointer(SymbolParser.TypePointerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeLongPointer}
	 * labeled alternative in {@link SymbolParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeLongPointer(SymbolParser.TypeLongPointerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConstDecl}
	 * labeled alternative in {@link SymbolParser#declConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(SymbolParser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#constType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstType(SymbolParser.ConstTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SymbolParser#constValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstValue(SymbolParser.ConstValueContext ctx);
}
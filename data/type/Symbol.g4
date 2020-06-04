grammar Symbol;

//
// LEXER
//

ARRAY        : 'ARRAY';
BEGIN        : 'BEGIN';
BOOLEAN      : 'BOOLEAN';
CARDINAL     : 'CARDINAL';
END          : 'END';
ENDCASE      : 'ENDCASE';
FROM         : 'FROM';
INTEGER      : 'INTEGER';
LONG         : 'LONG';
OF           : 'OF';
OVERLAID     : 'OVERLAID';
PACKED       : 'PACKED';
POINTER      : 'POINTER';
RECORD       : 'RECORD';
SELECT       : 'SELECT';
SYMBOL       : 'SYMBOL';
TYPE         : 'TYPE';
UNSPECIFIED  : 'UNSPECIFIED';
WORD         : 'WORD';
TRUE         : 'TRUE';
FALSE        : 'FALSE';

fragment CHAR_ALPHA : [A-Z|a-z];
fragment CHAR_AF    : [A-F|a-f];
fragment CHAR_DEC   : [0-9];
fragment CHAR_OCT   : [0-7];

ID  : CHAR_ALPHA (CHAR_ALPHA | CHAR_DEC | '_')*;

NUMBER_8  : CHAR_OCT+[Bb];
NUMBER_10 : CHAR_DEC+;
NUMBER_16 : CHAR_DEC(CHAR_DEC|CHAR_AF)*[Xx];

COMMENT_PARTIAL
    : '--' ~[\n\r]*? '--'      ->skip;

COMMENT_LINE
    : '--' ~[\n\r]* '\r'? '\n' ->skip;

SPACE
    : [ \r\t\n]                ->skip;


//
// PARSER
//


//
// common rule
//

number
    :   NUMBER_8
    |   '-'? NUMBER_10
    |   NUMBER_16
    ;

positive_number
    :   NUMBER_8
    |   NUMBER_10
    |   NUMBER_16
    ;

constant
    : number # ConstNumber
    | ID     # ConstRef
    ;
    
//
// SYMBOL
//
symbol
    :    header '=' body '.'
    ;

header
    :    name=ID ':' SYMBOL
    ;

body
    :    BEGIN declList END
    ;

declList
    :    elements+=decl+
    ;

decl
    :   declType
    |   declConst
    ;

//
// TYPE
//
declType
    :   name=ID ':' TYPE      '=' typeType ';'
    ;


typeType
    :    arrayType
    |    enumType
    |    subrangeType
    |    recordType
    |    predefinedType
    |    referenceType
    ;


//
// ARRAY
//
arrayType
    :   ARRAY indexName=ID                                                                          OF arrayTypeElement # TypeArrayType
    |   ARRAY (indexName=ID)? '[' startIndex=constant '..' stopIndex=constant closeChar=(']' | ')') OF arrayTypeElement # TypeArrayRange
    ;


arrayTypeElement
    :   predefinedType
    |   referenceType
    ;


//
// ENUM
//
enumType
    :    '{' enumElementList '}'
    ;

enumElementList
    :    elements+=eumElement (',' elements+=eumElement)*
    ;

eumElement
    :   name=ID '(' value=positive_number ')'
    ;

//
// SUBRANGE
//
subrangeType
    :    (baseName=ID)? '[' startIndex=constant '..' stopIndex=constant closeChar=(']' | ')')
    ;


//
// RECORD
//
recordType
    :   RECORD '[' recordFieldList ']'
    ;

recordFieldList
    :   elements+=recordField (',' elements+=recordField)*
    ;

recordField
    :   fieldName ':' fieldType
    ;

fieldName
    :   name=ID '(' offset=positive_number ')'                                                           # TypeFieldName
    |   name=ID '(' offset=positive_number ':' startPos=positive_number '..' stopPos=positive_number ')' # TypeFieldNameBit
    ;
    
fieldType
    :   arrayType
    |   predefinedType
    |   referenceType
    |   select
    ;

select
    :   SELECT OVERLAID                   '*'             FROM selectCaseList ENDCASE # TypeSelectOverlaidAny
    |   SELECT OVERLAID                   selectorType=ID FROM selectCaseList ENDCASE # TypeSelectOverlaidType
    |   SELECT selectorName=fieldName ':' '*'             FROM selectCaseList ENDCASE # TypeSelectAnon
    |   SELECT selectorName=fieldName ':' selectorType=ID FROM selectCaseList ENDCASE # TypeSelectType
    ;

selectCaseList
    :   elements+=selectCase (',' elements+=selectCase ',')*
    ;

selectCase
    :   selectCaseSelector '=>' '[' (recordFieldList)? ']'
    ;

selectCaseSelector
    :   selectorName=ID                                       # TypeSelectCaseSelector
    |   selectorName=ID '(' selectorValue=positive_number ')' # TypeSelectCaseSelectorValue
    ;


//
// REFERENCE
//
referenceType
    :    name=ID
    ;


//
// PREDEFINED
//
predefinedType
    :    BOOLEAN          # TypeBoolean
    |    CARDINAL         # TypeCardinal
    |    LONG CARDINAL    # TypeLongCardinal
    |    INTEGER          # TypeInteger
    |    LONG INTEGER     # TypeLongInteger
    |    UNSPECIFIED      # TypeUnspecified
    |    LONG UNSPECIFIED # TypeLongUnspecified
    |    POINTER          # TypePointer
    |    LONG POINTER     # TypeLongPointer
    ;


//
// CONSTANT
//

declConst
    :   name=ID ':' constType '=' constValue ';'
    ;

constType
    :   CARDINAL
    |   POINTER
    |   LONG POINTER
    ;
    
constValue
    :   name+=ID ('.' name+=ID)* // refer to static field in Java class
    ;

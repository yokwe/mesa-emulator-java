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
RECORD32     : 'RECORD32';
SELECT       : 'SELECT';
SYMBOL       : 'SYMBOL';
TO           : 'TO';
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

COMMENT_LINE
    : '//' ~[\n\r]* '\r'? '\n' ->skip;

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
    : number  # ConstNumber // numeric literal
    | name=ID # ConstName   // name of constant
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
    |   declConstant
    ;

//
// TYPE
//
declType
    :   name=ID ':' TYPE '=' typeType ';'
    ;


typeType
    :    simpleType
    |    referenceType
    |    pointerType
    |    subrangeType
    |    enumType
    |    arrayType
    |    recordType
    ;


//
// SIMPLE
//
simpleType
    :    BOOLEAN          # TypeBoolean
    |    INTEGER          # TypeInteger
    |    CARDINAL         # TypeCardinal
    |    LONG CARDINAL    # TypeLongCardinal
    |    UNSPECIFIED      # TypeUnspecified
    |    LONG UNSPECIFIED # TypeLongUnspecified
    |    POINTER          # TypePointer
    |    LONG POINTER     # TypeLongPointer
    ;


//
// REFERENCE
//
referenceType
    :    name=ID  // name of other type
    ;


//
// POINTER
//
pointerType
    :   POINTER      TO pointerRefType # TypePointerShort
    |   LONG POINTER TO pointerRefType # TypePointerLong
    ;

pointerRefType
    :    simpleType
    |    referenceType
//  |    arrayType
    ;

//
// SUBRANGE
//
subrangeType
    :  '[' minValue=constant '..' maxValue=constant closeChar=(']' | ')')  // assume base type is CARDINAL
    ;


//
// ARRAY
//
arrayType
    :   ARRAY referenceType OF arrayElementType # TypeArrayReference  // referenceType must be ENUM
    |   ARRAY subrangeType  OF arrayElementType # TypeArraySubrange
    ;


arrayElementType
    :   simpleType
    |   pointerType
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
// RECORD
//
recordType
    :   RECORD   '[' recordFieldList ']' # TypeRecord16
    |   RECORD32 '[' recordFieldList ']' # TypeRecord32
    ;

recordFieldList
    :   elements+=recordField (',' elements+=recordField)*
    ;

recordField
    :   fieldName ':' fieldType
    ;

fieldName
    :   name=ID '(' offset=positive_number ')'                                                           # TypeFieldName
    |   name=ID '(' offset=positive_number ':' startBit=positive_number '..' stopBit=positive_number ')' # TypeFieldNameBit
    ;
    
fieldType
    :   simpleType
    |   referenceType
    |   arrayType
    |   pointerType
    ;


//
// CONSTANT
//

declConstant
    :   name=ID ':' constantType '=' constantValue ';'
    ;

constantType
    :   constantTypeNumeric
    |   pointerType
    |   referenceType
    ;

constantTypeNumeric
    :   CARDINAL      # ConstantTypeNumericCardinal
    |   LONG CARDINAL # ConstantTypeNumericLongCardinal
    ;

constantValue
    :   constantValueQName
    |   constantValueConstant
    ;
    
constantValueQName
    :   name+=ID ('.' name+=ID)* // name of qualified field name of public static final int or long
    ;

constantValueConstant
    :   constant
    ;

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
    
rangeType
    :    name=ID
    ;
   
rangeTypeRange
    :    (name=ID)? '[' startIndex=constant '..' stopIndex=constant closeChar=(']' | ')')
    ;

// qName qualified name
qualifiedName
    :   name+=ID ('.' name+=ID)*
    ;
    
// nameNumbe is used in enum, record field and select case selector
numberedName
    :   name=ID '(' value=positive_number ')'
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
    |    simpleType
    |    referenceType
    ;


//
// ARRAY
//
arrayType
    :   arrayTypeType
    |   arrayTypeRange
    ;
    
arrayTypeType    
    :    ARRAY rangeType  OF arrayTypeElement
    ;
    
arrayTypeRange
    :    ARRAY rangeTypeRange OF arrayTypeElement
    ;

arrayTypeElement
    :   simpleType
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
    :   numberedName
    ;

//
// SUBRANGE
//
subrangeType
    :   rangeTypeRange
    ;


//
// RECORD
//
recordType
    :    RECORD '[' recordFieldList ']'
    ;

recordFieldList
    :    elements+=recordField (',' elements+=recordField)*
    ;

recordField
    :    fieldName ':' fieldType
    ;

fieldName
    :    numberedName
    |    bitfieldName
    ;
    
bitfieldName
    :   name=ID '(' offset=positive_number ':' startPos=positive_number '..' stopPos=positive_number ')'
    ;

fieldType
    :   arrayType
    |   simpleType
    |   referenceType
    |   select
    ;

select
    :   SELECT OVERLAID '*' FROM selectCaseList ENDCASE
    ;

selectCaseList
    :   elements+=selectCase (',' elements+=selectCase ',')*
    ;

selectCase
    :   selectCaseSelector '=>' '[' ']'                 # TypeSelectCaseEmpty
    |   selectCaseSelector '=>' '[' recordFieldList ']' # TypeSelectCaseList
    ;

selectCaseSelector
    :   name=ID
    |   numberedName
    ;


//
// REFERENCE
//
referenceType
    :    name=ID
    ;


//
// SIMPLE
//
simpleType
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
    :   qualifiedName // refer to static field in Java class
    ;

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
// common rule
//
qName
    :   name+=ID ('.' name+=ID)*
    ;
    
number
    :   NUMBER_8       # Number8
    |   '-'? NUMBER_10 # Number10
    |   NUMBER_16      # Number16
    ;

positive_number
    :   NUMBER_8  # PositiveNumber8
    |   NUMBER_10 # PositiveNumber10
    |   NUMBER_16 # PositiveNumber16
    ;

constant
    : number # ConstNumber
    | ID     # ConstRef
    ;
    
correspondence
    :    name=ID '(' value=positive_number ')'
    ;

correspondenceList
    :    elements+=correspondence (',' elements+=correspondence)*
    ;


//
// PARSER
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
    :    ARRAY rangeType  OF arrayTypeElement # TypeArrayType
    ;
    
arrayTypeRange
    :    ARRAY rangeRange OF arrayTypeElement # TypeArrayRange
    ;

rangeType
    :    name=ID                                                                # TypeRangeType
    ;
   
rangeRange
    :    (name=ID)? '[' start=constant '..' stop=constant closeChar=(']' | ')') # TypeRangeRange
    ;

arrayTypeElement
    :   simpleType
    |   recordType
    |   referenceType
    ;


//
// ENUM
//
enumType
    :    '{' correspondenceList '}' # TypeEnum
    ;


//
// SUBRANGE
//
subrangeType
    :   rangeType  # TypeSubrangeType
    |   rangeRange # TypeSubrangeInclusive
    ;


//
// RECORD
//
recordType
    :    RECORD '[' recordFieldList ']' # TypeRecord
    ;

recordFieldList
    :    elements+=recordField (',' elements+=recordField)*
    ;

recordField
    :    fieldName      ':' fieldType                        # TypeRecordField
    |    correspondence ':' (referenceType | arrayTypeRange) # TypeRecoddFieldBlock
    ;

fieldName
    :    name=ID '(' offset=positive_number ':' bitStart=positive_number '..' bitStop=positive_number ')'
    ;

fieldType
    :   typeType
    |   select
    ;

select
    :   SELECT OVERLAID '*' FROM selectCaseList ENDCASE # TypeSelectAny
    ;

selectCaseList
    :   elements+=selectCase (',' elements+=selectCase ',')*
    ;

selectCase
    :   correspondence '=>' '[' ']'                 # TypeSelectCaseEmpty
    |   correspondence '=>' '[' recordFieldList ']' # TypeSelectCaseList
    ;


//
// REFERENCE
//
referenceType
    :    name=ID # TypeRef
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
    :   qName // value is taken from yokwe.majuro.mesa.Constant
    ;

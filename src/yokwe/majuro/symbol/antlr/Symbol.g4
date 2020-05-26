grammar Symbol;

//
// LEXER
//

ARRAY        : 'ARRAY';
BEGIN        : 'BEGIN';
BOOLEAN      : 'BOOLEAN';
BYTE         : 'BYTE';
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

NUMBER
    : CHAR_DEC+
    ;

ANY_NUMBER
    : NUMBER
    | '-' NUMBER
    ;

COMMENT_PARTIAL
    : '--' ~[\n\r]*? '--'      ->skip;

COMMENT_LINE
    : '--' ~[\n\r]* '\r'? '\n' ->skip;

SPACE
    : [ \r\t\n]                ->skip;


//
// PARSER
//

symbol
    :    header '=' body '.'
    ;

header
    :    name=ID
    ;

body
    :    BEGIN declList END
    ;


declList
    :    elements+=decl+
    ;

decl
    :    name=ID ':' TYPE '=' type ';'                  # DeclType
//    |    name=ID ':' constantType '=' constantValue ';' # DeclConst
    ;

//
// TYPE
//
type
    :    arrayType
    |    enumType
    |    pointerType
    |    subrangeType
    |    recordType
    |    referencedType
    |    simpleType
    ;


//
// ARRAY
//
arrayType
    :    ARRAY arrayTypeIndex OF arrayTypeElement # TypeArray
    ;

arrayTypeIndex
    :    ID         // ID must be enum or range type
    |    rangeType
    ;

arrayTypeElement
    :   simpleType
    |   recordType
    ;

rangeType
    :    rangeTypeInclusive
    |    rangeTypeExclusive
    ;

rangeTypeInclusive
    :             '[' start=ANY_NUMBER ',' stop=ANY_NUMBER ']' # TypeRangeInclusive
    ;

rangeTypeExclusive
    :             '[' start=ANY_NUMBER ',' stop=ANY_NUMBER ')' # TypeRangeExclusive
    ;


//
// ENUM
//
enumType
    :    '{' correspondenceList '}' # TypeEnum
    ;

correspondenceList
    :    elements+=correspondence (',' elements+=correspondence)*
    ;

correspondence
    :    name=ID '(' value=NUMBER ')'
    ;

//
// POINTER
//
pointerType
    :    POINTER                              # TypePointer
    |    LONG POINTER                         # TypeLongPointer
//  |    POINTER TO      pointerTypeReference # TypePointerTo
//  |    LONG POINTER TO pointerTypeReference # TypeLongPointerTo
    ;


//
// SUBRANGE
//
subrangeType
    :   (INTEGER | CARDINAL) rangeType   # TypeSubrange
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
    :    fieldNameList ':' fieldType
    ;

fieldNameList
    :    elements+= fieldName (',' elements+=fieldName)*
    ;

fieldName
    :    name=ID '(' offset=NUMBER ':' bitStart=NUMBER '..' bitStop=NUMBER ')'
    ;

fieldType
    :   type
    |   select
    ;

select
    :   SELECT fieldName ':' name=ID FROM selectCaseList ENDCASE # TypeSelectTyped
    |   SELECT fieldName ':' '*'     FROM selectCaseList ENDCASE # TypeSelectAnon
    |   SELECT OVERLAID      '*'     FROM selectCaseList ENDCASE # TypeSelectAny
    ;

selectCaseList
    :   elements+= selectCase (',' selectCase ',')*
    ;

selectCase
    :   correspondence '=>' '[' ']'                 # TypeSelectCaseEmpty
    |   correspondence '=>' '[' recordFieldList ']' # TypeSelectCaseList
    ;

//
// SIMPLE
//
simpleType
    :    BOOLEAN          # TypeBoolean
    |    BYTE             # TypeByte
    |    CARDINAL         # TypeCardinal
    |    LONG CARDINAL    # TypeLongCardinal
    |    INTEGER          # TypeInteger
//  |    LONG INTEGER     # TypeLongInteger
    |    UNSPECIFIED      # TypeUnspecified
    |    LONG UNSPECIFIED # TypeLongUnspecified
    ;


//
// REFERENCE
//
referencedType
    :    name=ID # TypeRef
    ;


//
// CONSTANT
//
/*
constantType
    :
    ;
    
constantValue
    :    ANY_NMBER # ConstantNumber
    |    name=ID   # ConstRef
    |    '[' constList ']' # ConstArray
    ;

constructedConstant
    :    '[' constantList  ']' # ConstArray
    |    '[' componentList ']' # ConstRecord
    |    '[' ']'               # ConstEmpty
    |    ID constant           # ConstChoice
    ;

constantList
    :    elements+=constant (',' elements+=constant)*
    ;

componentList
    :    elements+=component (',' elements+=component)*
    ;

component
    :    name=ID ':' value=constant
    ;
 *''

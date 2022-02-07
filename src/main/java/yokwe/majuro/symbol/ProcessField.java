package yokwe.majuro.symbol;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeArrayRef;
import yokwe.majuro.symbol.model.TypeArraySub;
import yokwe.majuro.symbol.model.TypeBitField16;
import yokwe.majuro.symbol.model.TypeBitField32;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypeMultiWord;
import yokwe.majuro.symbol.model.TypePointerLong;
import yokwe.majuro.symbol.model.TypeRecord.Field;
import yokwe.majuro.symbol.model.TypePointerShort;
import yokwe.majuro.symbol.model.TypeSubrange;

public abstract class ProcessField {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProcessField.class);

	public static void unexpetected(Field field) {
		logger.error("field {}", field.toMesaType());
		throw new UnexpectedException("Unexpected");
	}
	
	protected final JavaFile javaFile;
	
	protected ProcessField(JavaFile javaFile) {
		this.javaFile = javaFile;
	}

	//
	// Entry point
	//
	public abstract void process();

	//
	// call processTypeXXX depends on type of argument
	//
	public void accept(Field field) {
		Type fieldType = field.type.realType();
		
		if (fieldType instanceof TypeBoolean) {
			processTypeBoolean(field, fieldType.toTypeBoolean());
		} else if (fieldType instanceof TypeEnum) {
			processTypeEnum(field, fieldType.toTypeEnum());
		} else if (fieldType instanceof TypeSubrange) {
			processTypeSubrange(field, fieldType.toTypeSubrange());
		} else if (fieldType instanceof TypeArrayRef) {
			processTypeArrayReference(field, fieldType.toTypeArrayRef());
		} else if (fieldType instanceof TypeArraySub) {
			processTypeArraySubrange(field, fieldType.toTypeArraySub());
		} else if (fieldType instanceof TypePointerShort) {
			processTypePointeShort(field, fieldType.toTypePointerShort());
		} else if (fieldType instanceof TypePointerLong) {
			processTypePointeLong(field, fieldType.toTypePointerLong());
		} else if (fieldType instanceof TypeBitField16) {
			processTypeBitField16(field, fieldType.toTypeBitField16());
		} else if (fieldType instanceof TypeBitField32) {
			processTypeBitField32(field, fieldType.toTypeBitField32());
		} else if (fieldType instanceof TypeMultiWord){
			processTypeMultiWord(field, fieldType.toTypeMultiWord());
		} else {
			unexpetected(field);
		}
	}
	
	// simple type
	protected abstract void processTypeBoolean       (Field field, TypeBoolean  fieldType);
	protected abstract void processTypeEnum          (Field field, TypeEnum     fieldType);
	protected abstract void processTypeSubrange      (Field field, TypeSubrange fieldType);
	
	// complex type
	protected abstract void processTypeArrayReference(Field field, TypeArrayRef fieldType);
	protected abstract void processTypeArraySubrange (Field field, TypeArraySub fieldType);
	
	protected abstract void processTypePointeShort   (Field field, TypePointerShort fieldType);
	protected abstract void processTypePointeLong    (Field field, TypePointerLong  fieldType);
	
	protected abstract void processTypeBitField16    (Field field, TypeBitField16 fieldType);
	protected abstract void processTypeBitField32    (Field field, TypeBitField32 fieldType);
	protected abstract void processTypeMultiWord     (Field field, TypeMultiWord  fieldType);
}

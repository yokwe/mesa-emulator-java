package yokwe.majuro.symbol;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Field;
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
		} else if (fieldType instanceof TypeArray.Reference) {
			processTypeArrayReference(field, fieldType.toTypeArrayReference());
		} else if (fieldType instanceof TypeArray.Subrange) {
			processTypeArraySubrange(field, fieldType.toTypeArraySubrange());
		} else if (fieldType instanceof TypePointer.Short) {
			processTypePointeShort(field, fieldType.toTypePointerShort());
		} else if (fieldType instanceof TypePointer.Long) {
			processTypePointeLong(field, fieldType.toTypePointerLong());
		} else if (fieldType instanceof TypeRecord) {
			if (fieldType.bitField16()) {
				processTypeBitField16(field, fieldType.toTypeRecord());
			} else if (fieldType.bitField32()) {
				processTypeBitField32(field, fieldType.toTypeRecord());
			} else {
				processTypeMultiWord(field, fieldType.toTypeRecord());
			}
		} else {
			unexpetected(field);
		}
	}
	
	// simple type
	protected abstract void processTypeBoolean       (Field field, TypeBoolean  fieldType);
	protected abstract void processTypeEnum          (Field field, TypeEnum     fieldType);
	protected abstract void processTypeSubrange      (Field field, TypeSubrange fieldType);
	
	// complex type
	protected abstract void processTypeArrayReference(Field field, TypeArray.Reference fieldType);
	protected abstract void processTypeArraySubrange (Field field, TypeArray.Subrange  fieldType);
	
	protected abstract void processTypePointeShort   (Field field, TypePointer fieldType);
	protected abstract void processTypePointeLong    (Field field, TypePointer fieldType);
	
	protected abstract void processTypeBitField16    (Field field, TypeRecord fieldType);
	protected abstract void processTypeBitField32    (Field field, TypeRecord fieldType);
	protected abstract void processTypeMultiWord     (Field field, TypeRecord fieldType);
}

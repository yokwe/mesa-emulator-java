package yokwe.majuro.symbol;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Field;
import yokwe.majuro.symbol.model.TypeReference;
import yokwe.majuro.symbol.model.TypeSubrange;

public abstract class ProcessField {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProcessField.class);

	public static void unexpetected(Field field) {
		logger.error("field ", field.toMesaType());
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
		Type type = field.type.getRealType();
		
		if (type instanceof TypeBoolean) {
			processTypeBoolean(field);
		} else if (type instanceof TypeEnum) {
			processTypeEnum(field);
		} else if (type instanceof TypeSubrange) {
			processTypeSubrange(field);
		} else if (type instanceof TypeArray) {
			if (type instanceof TypeArray.Reference) {
				processTypeArrayReference(field);
			} else if (type instanceof TypeArray.Subrange) {
				processTypeArraySubrange(field);
			} else {
				unexpetected(field);
			}
		} else if (type instanceof TypePointer) {
			TypePointer typePointer = type.toTypePointer();
			if (typePointer.pointerSize == TypePointer.PointerSize.SHORT) {
				processTypePointeShort(field);
			} else if (typePointer.pointerSize == TypePointer.PointerSize.LONG) {
				processTypePointeLong(field);
			} else {
				unexpetected(field);
			}
		} else if (type instanceof TypeRecord) {
			if (type.bitField16()) {
				processTypeBitField16(field);
			} else if (type.bitField32()) {
				processTypeBitField32(field);
			} else {
				processTypeMultiWord(field);
			}
		} else if (type instanceof TypeReference) {
			unexpetected(field);
		} else {
			unexpetected(field);
		}
	}
	
	// simple type
	protected abstract void processTypeBoolean       (Field field);
	protected abstract void processTypeEnum          (Field field);
	protected abstract void processTypeSubrange      (Field field);
	
	// complex type
	protected abstract void processTypeArrayReference(Field field);
	protected abstract void processTypeArraySubrange (Field field);
	
	protected abstract void processTypePointeShort   (Field field);
	protected abstract void processTypePointeLong    (Field field);
	
	protected abstract void processTypeBitField16    (Field field);
	protected abstract void processTypeBitField32    (Field field);
	protected abstract void processTypeMultiWord     (Field field);
}

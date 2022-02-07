package yokwe.majuro.symbol;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeReference;
import yokwe.majuro.symbol.model.TypeSubrange;

public abstract class ProcessType {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProcessType.class);

	public static void unexpected(Type type) {
		logger.error("type {}", type.toMesaDecl());
		throw new UnexpectedException("Unexpected");
	}
	
	protected final JavaFile javaFile;
	
	protected ProcessType(JavaFile javaFile) {
		this.javaFile = javaFile;
	}
	
	//
	// Entry point
	//
	public abstract void process();

	//
	// call processTypeXXX depends on type of argument
	//
	public void accept(Type type) {
		if (type instanceof TypeBoolean) {
			processTypeBoolean(type.toTypeBoolean());
		} else if (type instanceof TypeEnum) {
			processTypeEnum(type.toTypeEnum());
		} else if (type instanceof TypeSubrange) {
			processTypeSubrange(type.toTypeSubrange());
		} else if (type instanceof TypeArray.Reference) {
			processTypeArrayReference(type.toTypeArray().toReference());
		} else if (type instanceof TypeArray.Subrange) {
			processTypeArraySubrange(type.toTypeArray().toSubrange());
		} else if (type instanceof TypePointer.Short) {
			processTypePointeShort(type.toTypePointerShort());
		} else if (type instanceof TypePointer.Long) {
			processTypePointeLong(type.toTypePointerLong());
		} else if (type instanceof TypeRecord) {
			TypeRecord typeRecord = type.toTypeRecord();
			if (type.bitField16()) {
				processTypeBitField16(typeRecord);
			} else if (type.bitField32()) {
				processTypeBitField32(typeRecord);
			} else {
				processTypeMultiWord(typeRecord);
			}
		} else if (type instanceof TypeReference) {
			processTypeReference(type.toTypeReference());
		} else {
			unexpected(type);
		}
	}
	
	// simple type
	protected abstract void processTypeBoolean       (TypeBoolean  type);
	protected abstract void processTypeEnum          (TypeEnum     type);
	protected abstract void processTypeSubrange      (TypeSubrange type);
	
	// complex type
	protected abstract void processTypeArrayReference(TypeArray.Reference type);
	protected abstract void processTypeArraySubrange (TypeArray.Subrange  type);
	
	protected abstract void processTypePointeShort   (TypePointer type);
	protected abstract void processTypePointeLong    (TypePointer type);
	
	protected abstract void processTypeBitField16    (TypeRecord type);
	protected abstract void processTypeBitField32    (TypeRecord type);
	protected abstract void processTypeMultiWord     (TypeRecord type);

	// misc type
	protected abstract void processTypeReference     (TypeReference type);
}

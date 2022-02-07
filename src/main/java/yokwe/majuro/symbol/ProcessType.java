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
			processTypeArrayReference(type.toTypeArrayReference());
		} else if (type instanceof TypeArray.Subrange) {
			processTypeArraySubrange(type.toTypeArraySubrange());
		} else if (type instanceof TypePointer.Short) {
			processTypePointeShort(type.toTypePointerShort());
		} else if (type instanceof TypePointer.Long) {
			processTypePointeLong(type.toTypePointerLong());
		} else if (type instanceof TypeRecord.BitField16) {
			processTypeBitField16(type.toTypeRecordBitField16());
		} else if (type instanceof TypeRecord.BitField32) {
			processTypeBitField32(type.toTypeRecordBitField32());
		} else if (type instanceof TypeRecord.MultiWord) {
			processTypeMultiWord(type.toTypeRecordMultiWord());
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
	
	protected abstract void processTypePointeShort   (TypePointer.Short type);
	protected abstract void processTypePointeLong    (TypePointer.Long  type);
	
	protected abstract void processTypeBitField16    (TypeRecord.BitField16 type);
	protected abstract void processTypeBitField32    (TypeRecord.BitField32 type);
	protected abstract void processTypeMultiWord     (TypeRecord.MultiWord  type);

	// misc type
	protected abstract void processTypeReference     (TypeReference type);
}

package yokwe.majuro.symbol;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeArrayRef;
import yokwe.majuro.symbol.model.TypeArraySub;
import yokwe.majuro.symbol.model.TypeBitField16;
import yokwe.majuro.symbol.model.TypeBitField32;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypePointerLong;
import yokwe.majuro.symbol.model.TypeMultiWord;
import yokwe.majuro.symbol.model.TypeReference;
import yokwe.majuro.symbol.model.TypePointerShort;
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
		} else if (type instanceof TypeArrayRef) {
			processTypeArrayReference(type.toTypeArrayRef());
		} else if (type instanceof TypeArraySub) {
			processTypeArraySubrange(type.toTypeArraySub());
		} else if (type instanceof TypePointerShort) {
			processTypePointeShort(type.toTypePointerShort());
		} else if (type instanceof TypePointerLong) {
			processTypePointeLong(type.toTypePointerLong());
		} else if (type instanceof TypeBitField16) {
			processTypeBitField16(type.toTypeBitField16());
		} else if (type instanceof TypeBitField32) {
			processTypeBitField32(type.toTypeBitField32());
		} else if (type instanceof TypeMultiWord) {
			processTypeMultiWord(type.toTypeMultiWord());
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
	protected abstract void processTypeArrayReference(TypeArrayRef type);
	protected abstract void processTypeArraySubrange (TypeArraySub  type);
	
	protected abstract void processTypePointeShort   (TypePointerShort type);
	protected abstract void processTypePointeLong    (TypePointerLong  type);
	
	protected abstract void processTypeBitField16    (TypeBitField16 type);
	protected abstract void processTypeBitField32    (TypeBitField32 type);
	protected abstract void processTypeMultiWord     (TypeMultiWord  type);

	// misc type
	protected abstract void processTypeReference     (TypeReference type);
}

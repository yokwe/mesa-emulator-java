package yokwe.majuro.symbol.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

abstract class Field {
	private static final Logger logger = LoggerFactory.getLogger(Type.class);

	public enum TargetKind {
		TYPE, SELECT
	}
	public enum FieldKind {
		FEILD, BIT_FIELD
	}
	
	public final String    name;
	public final int       offset;
	
	public final TargetKind targetKind;
	public final FieldKind  fieldKind;
	
	public boolean needsFix;
	public int     size;
	
	protected Field(String name, int offset, TargetKind targetKind, FieldKind fieldKind) {
		this.name       = name;
		this.offset     = offset;
		this.targetKind = targetKind;
		this.fieldKind  = fieldKind;
		
		this.needsFix  = true;
		this.size      = Type.UNKNOWN_SIZE;
	}
	
	@Override
	public String toString() {
		logger.error("Unexpected");
		throw new UnexpectedException("Unexpected");
	}
	
	public abstract void fix();
}

class FieldType extends Field {
	public final TypeReference type;
	
	protected FieldType(String name, int offset, FieldKind fieldKind, String typeName) {
		super(name, offset, TargetKind.TYPE, fieldKind);
		
		this.type = new TypeReference(name + "#fieldType", typeName);
	}
	public FieldType(String name, int offset, String typeName) {
		this(name, offset, FieldKind.FEILD, typeName);
	}

	@Override
	public void fix() {
		if (type.needsFix) {
			type.fix();
			if (!type.needsFix) {
				size = type.size;
				needsFix = false;
			}
		}
	}
}
class BitFieldType extends FieldType {
	public final int startPos;
	public final int stopPos;
	
	public BitFieldType(String name, int offset, int startPos, int stopPos, String typeName) {
		super(name, offset, FieldKind.BIT_FIELD, typeName);
		
		this.startPos = startPos;
		this.stopPos  = stopPos;
	}
}



class FieldSelect extends Field {
	public final Select select;
	
	protected FieldSelect(String name, int offset, FieldKind fieldKind, Select select) {
		super(name, offset, TargetKind.SELECT, fieldKind);
		
		this.select = select;
	}
	public FieldSelect(String name, int offset, Select select) {
		this(name, offset, FieldKind.FEILD, select);
	}

	@Override
	public void fix() {
		if (select.needsFix) {
			select.fix();
			if (!select.needsFix) {
				size = select.size;
				needsFix = false;
			}
		}
	}
}
class BitFieldSelect extends FieldSelect {
	public final int startPos;
	public final int stopPos;
	
	public BitFieldSelect(String name, int offset, int startPos, int stopPos, Select select) {
		super(name, offset, FieldKind.BIT_FIELD, select);
		
		this.startPos = startPos;
		this.stopPos  = stopPos;
	}
}


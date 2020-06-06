package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;

public class FieldSelect extends Field {
	public final String fieldName;
	public final Select select;
	
	public FieldSelect(String prefix, String name, int offset, Select select) {
		super(name, offset, TargetKind.SELECT);
		
		this.fieldName = name;
		this.select    = select;
		
		fix();
	}
	public FieldSelect(String prefix, String name, int offset, int startPos, int stopPos, Select select) {
		super(name, offset, startPos, stopPos, TargetKind.SELECT);
		
		this.fieldName = name;
		this.select    = select;
		
		fix();
	}

	@Override
	public String toString() {
		if (hasValue()) {
			switch (fieldKind) {
			case FIELD:
				return String.format("{%s(%d) %s %s %s %s}", name, offset, targetKind, fieldKind, getSize(), select);
			case BIT_FIELD:
				return String.format("{%s(%d:%d..%d) %s %s %s %s}", name, offset, startPos, stopPos, targetKind, fieldKind, getSize(), select);
			}
		} else {
			switch (fieldKind) {
			case FIELD:
				return String.format("{%s(%d) %s %s %s %s}", fieldName, offset, targetKind, fieldKind, "*UNKNOWN*", select);
			case BIT_FIELD:
				return String.format("{%s(%d:%d..%d) %s %s %s %s}", fieldName, offset, startPos, stopPos, targetKind, fieldKind, "*UNKNOWN*", select);
			}
		}
		logger.error("Unexpected");
		throw new UnexpectedException("Unexpected");
	}

	@Override
	public String toMesaType() {
		switch (fieldKind) {
		case FIELD:
			return String.format("%s(%d) %s", fieldName, offset, select.toMesaType());
		case BIT_FIELD:
			return String.format("%s(%d:%d..%d) %s", fieldName, offset, startPos, stopPos, select.toMesaType());
		default:
			throw new UnexpectedException();
		}
	}

	@Override
	public void fix() {
		if (select.needsFix()) {
			select.fix();
			if (select.hasValue()) {
				setSize(select.getSize());
			}
		}
	}
}
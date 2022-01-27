package yokwe.majuro.mesa;

public final class MapFlag {
	private static final int MASK_REFERENCED = 0x8000;
	private static final int MASK_DIRTY      = 0x4000;
	private static final int MASK_PROTECT    = 0x2000;
	
	private static final int MASK_VACANT = MASK_REFERENCED | MASK_DIRTY | MASK_PROTECT;
	private static final int FLAG_VACANT = MASK_DIRTY | MASK_PROTECT; // not referenced and dirty and protect
				
	
	private int value;
	public MapFlag() {
		value = 0;
	}
	
	public void clear() {
		value = 0;
	}
	public char get() {
		return (char)value;
	}
	public void set(char newValue) {
		value = newValue;
	}
	public void set(int newValue) {
		value = (char)newValue;
	}
	
	public boolean isVacant() {
		return (value & MASK_VACANT) == FLAG_VACANT;
	}
	public boolean isProtect() {
		return (value & MASK_PROTECT) != 0;
	}
	public boolean isReferenced() {
		return (value & MASK_REFERENCED) != 0;
	}
	public boolean isDirty() {
		return (value & MASK_DIRTY) != 0;
	}
	
	public boolean isNotReferenced() {
		return (value & MASK_REFERENCED) == 0;
	}
	public boolean isNotReferencedDirty() {
		return (value & (MASK_REFERENCED | MASK_DIRTY)) != (MASK_REFERENCED | MASK_DIRTY);
	}

	public void setReferenced() {
		value |= MASK_REFERENCED;
	}
	public void setReferencedDirty() {
		value |= MASK_REFERENCED;
		value |= MASK_DIRTY;
	}
	public void setProtect() {
		value |= MASK_PROTECT;
	}
	public void setVacant() {
		value = FLAG_VACANT;
	}
}
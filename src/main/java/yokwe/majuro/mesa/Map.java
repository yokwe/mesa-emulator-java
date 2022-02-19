package yokwe.majuro.mesa;

import static yokwe.majuro.mesa.Constants.PAGE_BITS;

import java.util.ArrayList;
import java.util.List;

public final class Map {
	private static final int MASK_FLAGS      = 0x0000_FFFF;
	private static final int MASK_REALPAGE   = 0xFFFF_0000;
	private static final int SHIFT_FLAGS     =  0;
	private static final int SHIFT_REALPAGE  = 16;
			
	public static final int MASK_PROTECT    = 0x0000_0004;
	public static final int MASK_DIRTY      = 0x0000_0002;
	public static final int MASK_REFERENCED = 0x0000_0001;
	
	private static final int MASK_VACANT = MASK_REFERENCED | MASK_DIRTY | MASK_PROTECT;
	public static final int FLAG_VACANT = MASK_DIRTY | MASK_PROTECT; // not referenced and dirty and protect
	
	private int value = 0;
	
	@Override
	public String toString() {
		List<String> flags = new ArrayList<>();
		if (isProtect()) flags.add("PROTECT");
		if (isDirty()) flags.add("DIRTY");
		if (isReferenced()) flags.add("REFERENCED");
		
		return String.format("{%X {%s}}", getRealPage(), String.join(" ", flags));
	}

	public int value() {
		return value;
	}
	
	public void clear() {
		value = 0;
	}
	
	public int getRealPage() {
		return (value & MASK_REALPAGE) >>> SHIFT_REALPAGE;
	}
	public int getRealAddress() {
		return (value & MASK_REALPAGE) >>> (SHIFT_REALPAGE - PAGE_BITS);
	}
	public void setRealPage(int newValue) {
		value = (value & ~MASK_REALPAGE) | ((newValue << SHIFT_REALPAGE) & MASK_REALPAGE);
	}
	public int getFlags() {
		return (value & MASK_FLAGS) >>> SHIFT_FLAGS;
	}
	public void setFlags(int newValue) {
		value = (value & ~MASK_FLAGS) | ((newValue << SHIFT_FLAGS) & MASK_FLAGS);
	}
	
	public boolean isVacant() {
		return (value & MASK_VACANT) == FLAG_VACANT;
	}
	public boolean isProtect() {
		return (value & MASK_PROTECT) != 0;
	}
	public boolean isDirty() {
		return (value & MASK_DIRTY) != 0;
	}
	public boolean isReferenced() {
		return (value & MASK_REFERENCED) != 0;
	}
	
	public boolean isNotReferenced() {
		return (value & MASK_REFERENCED) == 0;
	}
	public boolean isNotReferencedDirty() {
		return (value & (MASK_REFERENCED | MASK_DIRTY)) != (MASK_REFERENCED | MASK_DIRTY);
	}
	
	public void setProtect() {
		value |= MASK_PROTECT;
	}
	public void setReferenced() {
		value |= MASK_REFERENCED;
	}
	public void setReferencedDirty() {
		value |= MASK_REFERENCED;
		value |= MASK_DIRTY;
	}
	public void setVacant() {
		value |= MASK_PROTECT;
		value |= MASK_DIRTY;
		value &= ~MASK_REFERENCED;
	}
	
}
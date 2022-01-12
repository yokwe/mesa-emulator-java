package yokwe.majuro.mesa;

public final class MapFlag {
	private static final int MASK_REFERENCED = 0x8000;
	private static final int MASK_DIRTY      = 0x4000;
	private static final int MASK_PROTECT    = 0x2000;
	
	private static final int MASK_REFERENCED_DIRTY = MASK_REFERENCED | MASK_DIRTY;
	
	private static final int MASK_VACANT = MASK_REFERENCED | MASK_DIRTY | MASK_PROTECT;
	
	private static final int FLAG_VACANT = MASK_DIRTY | MASK_PROTECT; // not referenced and dirty and protect
			
	public static boolean isVacant(char mapFlag) {
		return (mapFlag & MASK_VACANT) == FLAG_VACANT;
	}
	public static boolean isProtect(char mapFlag) {
		return (mapFlag & MASK_PROTECT) != 0;
	}
	public static boolean isDirty(char mapFlag) {
		return (mapFlag & MASK_DIRTY) != 0;
	}
	
	public static boolean isNotReferenced(char mapFlag) {
		return (mapFlag & MASK_REFERENCED) == 0;
	}
	public static boolean isNotReferencedDirty(char mapFlag) {
		return (mapFlag & (MASK_REFERENCED | MASK_DIRTY)) != (MASK_REFERENCED | MASK_DIRTY);
	}
	
	public static char getClear() {
		return 0;
	}
	public static char getVacant() {
		return (char)FLAG_VACANT;
	}
	
	public static char setReferenced(char mapFlag) {
		return (char)(mapFlag | MASK_REFERENCED);
	}
	public static char setReferencedDirty(char mapFlag) {
		return (char)(mapFlag | MASK_REFERENCED_DIRTY);
	}
	public static char setProtect(char mapFlag) {
		return (char)(mapFlag | MASK_PROTECT);
	}
}
package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

public final class Environment {
    // IGNORE PageNumber: TYPE = LONG_CARDINAL;
    
    //
    // Environment.FIRST_PAGE_NUMBER: PageNumber = 0;
    //
    public static final @Mesa.CARD32 int FIRST_PAGE_NUMBER = 0;
    
    //
    // Environment.LAST_PAGE_NUMBER: PageNumber = 16777214;
    //
    public static final @Mesa.CARD32 int LAST_PAGE_NUMBER = 0xFF_FFFE;
    // IGNORE PageCount: TYPE = LONG_CARDINAL;
    
    //
    // Environment.FIRST_PAGE_COUNT: PageCount = 0;
    //
    public static final @Mesa.CARD32 int FIRST_PAGE_COUNT = 0;
    
    //
    // Environment.LAST_PAGE_COUNT: PageCount = 16777215;
    //
    public static final @Mesa.CARD32 int LAST_PAGE_COUNT = 0xFF_FFFF;
    // IGNORE PageOffset: TYPE = PageNumber;
    
    //
    // Environment.FIRST_PAGE_OFFSET: PageOffset = 0;
    //
    public static final @Mesa.CARD32 int FIRST_PAGE_OFFSET = 0;
    
    //
    // Environment.LAST_PAGE_OFFSET: PageOffset = 16777214;
    //
    public static final @Mesa.CARD32 int LAST_PAGE_OFFSET = 0xFF_FFFE;
}

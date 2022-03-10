package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

public final class Environment {
    // IGNORE PageNumber: TYPE = LONG CARDINAL;
    
    //
    // firstPageNumber: PageNumber = 0;
    //
    public static final @Mesa.CARD32 int FIRST_PAGE_NUMBER = 0;
    
    //
    // lastPageNumber: PageNumber = 16777214;
    //
    public static final @Mesa.CARD32 int LAST_PAGE_NUMBER = 16777214;
    // IGNORE PageCount: TYPE = LONG CARDINAL;
    
    //
    // firstPageCount: PageCount = 0;
    //
    public static final @Mesa.CARD32 int FIRST_PAGE_COUNT = 0;
    
    //
    // lastPageCount: PageCount = 16777215;
    //
    public static final @Mesa.CARD32 int LAST_PAGE_COUNT = 16777215;
}

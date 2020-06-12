package yokwe.majuro.mesa.type;

//
// AllocationVector: TYPE = ARRAY FSIndex OF AVItem;
//   FSIndex: TYPE = CARDINAL [0..256)
//   AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType]
//

public final class AllocationVector {
    public static final int SIZE = 256;

    private static final int ELEMENT_SIZE = 1;
    public static int getAddress(int base, int index) {
        return base + (checkIndex(index) * ELEMENT_SIZE);
    }

    private static int checkIndex(int index) {
        return FSIndex.checkValue(index);
    }

    // Expand AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType];
    //   data (0:0..13): UNSPECIFIED
    public static final class data {
        public static int get(int base, int index) {
            return AVItem.data.get(getAddress(base, index));
        }
        public static void set(int base, int index, int newValue) {
            AVItem.data.set(getAddress(base, index), newValue);
        }
    }
    //   tag (0:14..15): AVItemType
    public static final class tag {
        public static int get(int base, int index) {
            return AVItem.tag.get(getAddress(base, index));
        }
        public static void set(int base, int index, int newValue) {
            AVItem.tag.set(getAddress(base, index), newValue);
        }
    }
}

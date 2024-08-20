package utils.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayUtils {
    public ArrayUtils() {
    }

    public static List<String> toStringList(String[] array) {
        if (org.apache.commons.lang3.ArrayUtils.isNotEmpty(array)) {
            List<String> list = new ArrayList(array.length);
            Collections.addAll(list, array);
            return list;
        } else {
            return new ArrayList();
        }
    }
}

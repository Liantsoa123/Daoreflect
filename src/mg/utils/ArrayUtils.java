package mg.utils;

import java.util.Vector;

public class ArrayUtils {
    public static String implodeStr(String[] arrayStrings, String separator) {
        if (arrayStrings != null || arrayStrings.length != 0) {
            String value = "";
            for (int i = 0; i < arrayStrings.length; i++) {
                value += arrayStrings[i];
                if (i != arrayStrings.length - 1) {
                    value += separator;
                }
            }
            return value;
        }
        return null;
    }

    public static String implodeValues(Vector vector, String separator) {
        if (vector != null || vector.size() == 0) {
            String value = "";
            for (int i = 0; i < vector.size(); i++) {
                if (vector.get(i).getClass().equals(Integer.TYPE) || vector.get(i).getClass().equals(Double.TYPE)
                        || vector.get(i).getClass().equals(Float.TYPE)) {
                    value += vector.get(i);
                } else {
                    value += "'" + vector.get(i) + "'";
                }
                if (i != vector.size() - 1) {
                    value += separator;
                }
            }
            return value;
        }
        return null;
    }

    

}

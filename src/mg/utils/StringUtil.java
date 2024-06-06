package mg.utils;

public class StringUtil {
    public static String toUppurcaseFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

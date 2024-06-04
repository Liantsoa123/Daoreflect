package utils;

public class ObjectUtil {
    public static boolean isDefault(Object obj) {
        if (obj == null) {
            return true;
        } else {
            if (obj.getClass().getSimpleName().equals("Integer")) {
                if ((int) obj == 0) {
                    return true;
                }
            } else if (obj.getClass().getSimpleName().equals("Double")) {
                if ((double) obj == 0.0) {
                    return true;
                }
            }
        }

        return false;
    }



}

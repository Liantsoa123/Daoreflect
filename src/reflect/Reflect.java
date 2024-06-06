package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

import annotation.Column;

public class Reflect {
    public static String getClassName(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public static String[] getNameAttribut(Object obj) {
        Field[] atts = obj.getClass().getDeclaredFields();
        String[] nameAtts = new String[atts.length];
        for (int i = 0; i < atts.length; i++) {
            nameAtts[i] = atts[i].getName();
        }
        return nameAtts;
    }

    @SuppressWarnings("rawtypes")
    private static Class[] getClass(Object... objects) {
        Class[] classes = new Class[objects.length];
        for (int i = 0; i < objects.length; i++) {
            Class cl = objects[i].getClass();
            if (cl.getSimpleName().equalsIgnoreCase("Integer"))
                classes[i] = Integer.TYPE;
            else if (cl.getSimpleName().equalsIgnoreCase("Double"))
                classes[i] = Double.TYPE;
            else
                classes[i] = cl;
            // System.out.println( classes[i].getSimpleName() );
        }

        return classes;
    }

    @SuppressWarnings("rawtypes")
    public static Object executMethod(Object obj, String methodName, Object... parameters) throws Exception {
        Class[] classes = getClass(parameters);
        Method method = obj.getClass().getMethod(methodName, classes);
        return method.invoke(obj, parameters);
    }

    public static String[] getAnnotationAttrName(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String[] result = new String[fields.length];
        Column annotationAttribut = null;
        for (int i = 0; i < fields.length; i++) {
            annotationAttribut = fields[i].getAnnotation(Column.class);
            if (annotationAttribut != null) {
                result[i] = annotationAttribut.value();
            }
        }
        return result;
    }

    // get fields name how have an annotation
    public static String[] getFieldName(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String[] result = new String[fields.length];
        Column annotationAttribut = null;
        for (int i = 0; i < fields.length; i++) {
            annotationAttribut = fields[i].getAnnotation(Column.class);
            if (annotationAttribut != null) {
                result[i] = fields[i].getName();
            }

        }
        return result;
    }

    public static Vector getValueField(Object obj) throws Exception {
        String[] nameFIeld = getFieldName(obj);
        if (nameFIeld != null || nameFIeld.length == 0) {
            Vector values = new Vector();
            for (int i = 0; i < nameFIeld.length; i++) {
                String methode = "get" + nameFIeld[i].substring(0, 1).toUpperCase() + nameFIeld[i].substring(1);
                values.add(executMethod(obj, methode));
            }
            return values;
        }
        return null;

    }

    public static String fieldName(Object obj, String annotation) throws Exception {
        String[] fieldsName = getFieldName(obj);
        String[] annotationName = getAnnotationAttrName(obj);
        String value = null;
        for (int i = 0; i < fieldsName.length; i++) {
            if (annotationName[i].equals(annotation)) {
                value = fieldsName[i];
            }
        }
        return value;
    }

}

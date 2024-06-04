package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import annotation.AnnotationClass;
import reflect.Reflect;

public class UtilDao {
    public static String findAllQuery(Object obj, boolean withCriteria) throws Exception {
        AnnotationClass annotationClass = obj.getClass().getAnnotation(AnnotationClass.class);

        if (annotationClass == null) {
            throw new Exception("annotation class not found ");
        } else {
            String tabName = annotationClass.value();
            String[] annotationName = Reflect.getAnnotationAttrName(obj);
            String[] fieldName = Reflect.getFieldName(obj);
            String query = "select * from " + tabName + " where 1=1 ";
            if (withCriteria) {
                for (int i = 0; i < fieldName.length; i++) {
                    Object values = Reflect.executMethod(obj, "get" + StringUtil.toUppurcaseFirstLetter(fieldName[i]));
                    if (!ObjectUtil.isDefault(values)) {
                        if (values.getClass().getSimpleName().equals("String"))
                            query += " and " + annotationName[i] + " like '%" + values + "%'";
                        else
                            query += " and " + annotationName[i] + " = '" + values + "'";
                    }
                }
            }
            return query;
        }
    }

    public static String insertQuery(Object obj) throws Exception {
        AnnotationClass annotationClass = obj.getClass().getAnnotation(AnnotationClass.class);

        if (annotationClass == null) {
            throw new Exception("annotation class not found ");
        } else {
            String tabName = annotationClass.value();
            String[] annotationAttr = Reflect.getAnnotationAttrName(obj);
            Vector valueFileds = Reflect.getValueField(obj);

            String query = "insert into " + tabName + "(" + ArrayUtils.implodeStr(annotationAttr, ", ") + ") values ("
                    + ArrayUtils.implodeValues(valueFileds, ", ") + ")";
            return query;
        }
    }

    public static String betweenQuery(String column, Object min, Object max) throws Exception {
        String fieldName = Reflect.fieldName(min, column);
        Object minValue = Reflect.executMethod(min, "get" + StringUtil.toUppurcaseFirstLetter(fieldName));
        Object maxValue = Reflect.executMethod(max, "get" + StringUtil.toUppurcaseFirstLetter(fieldName));
        String query = "";
        if (minValue != null) {
            query += "and " + column + " >= '" + minValue + "' ";
        }
        if (maxValue != null) {
            query += "and " + column + " <= '" + maxValue + "' ";
        }
        return query;
    }

    // }

    /// maka donne avy amin'ny rset
    public static ArrayList<Object> getData(Object obj, ResultSet rSet)
            throws Exception {

        ArrayList<Object> values = new ArrayList<Object>();
        String[] fieldName = Reflect.getFieldName(obj);
        String[] columnName = Reflect.getAnnotationAttrName(obj);
        while (rSet.next()) {
            Object newObj = obj.getClass().getConstructor().newInstance();
            for (int i = 0; i < columnName.length; i++) {
                String methode = "set" + fieldName[i].substring(0, 1).toUpperCase() + fieldName[i].substring(1);
                Object value = rSet.getObject(columnName[i]);
                Reflect.executMethod(newObj, methode, value);
            }
            values.add(newObj);
        }
        return values;
    }

    // find execute
    public static ArrayList<Object> executeFind(Object obj, String query, UtilDb utilDb, Connection c)
            throws Exception {
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            c = utilDb.getCon();
            pStatement = c.prepareStatement(query);
            rSet = pStatement.executeQuery();
            return UtilDao.getData(obj, rSet);
        } catch (Exception e) {
            throw e;
        } finally {
            if (pStatement != null)
                pStatement.close();

            if (rSet != null)
                rSet.close();
        }
    }

    // maka donne misy pagin
    public static String pagingQuery(UtilDb utilDb, int offset, int length) throws Exception {
        String query = "";
        if (utilDb.getBdd().equalsIgnoreCase("postgres") || utilDb.getBdd().equalsIgnoreCase("mysql")) {
            query += " offset " + offset + " limit " + length;
        } else if (utilDb.getBdd().equalsIgnoreCase("orcale")) {
            query += " FETCH FIRST " + length + " ONLY OFFSET " + offset + " ROWS";
        } else if (utilDb.getBdd().equalsIgnoreCase("sqlserver")) {
            query += " OFFSET " + offset + " FETCH NEXT " + length + " ROWS ONLY";
        }
        return query;
    }

}

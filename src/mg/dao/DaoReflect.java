package mg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import mg.utils.UtilDao;
import mg.utils.UtilDb;
import mg.utils.XMLHandler;
import java.util.HashMap;

public class DaoReflect {
    UtilDb utilDb;

    public UtilDb getUtilDb() {
        return utilDb;
    }

    public void setUtilDb(UtilDb utilDb) {
        this.utilDb = utilDb;
    }

    public DaoReflect(String xmlPath) throws Exception {
        HashMap<String, String> xml = XMLHandler.getRootValues(xmlPath);
        UtilDb utilDb = new UtilDb();
        utilDb.setDriver(xml.get("driver"));
        utilDb.setUrl(xml.get("url"));
        utilDb.setDatabase(xml.get("database"));
        utilDb.setUser(xml.get("user"));
        utilDb.setMdp(xml.get("password"));
        utilDb.setBdd(xml.get("bdd"));
        this.setUtilDb(utilDb);
    }

    public void save(Object obj, Connection c) throws Exception {
        String query = UtilDao.insertQuery(obj);
        System.out.println("query=" + query);
        PreparedStatement pStatement = null;
        try {
            pStatement = c.prepareStatement(query);
            // pStatement.executeQuery();
            pStatement.executeUpdate();
        } catch (Exception e) {
            // c.rollback();
            throw e;
        } finally {
            if (pStatement != null)
                pStatement.close();
        }
    }

    public void save(Object obj) throws Exception {
        Connection c = null;
        try {
            c = utilDb.getCon();
            save(obj, c);
        } catch (Exception e) {
            // c.rollback();
            throw e;
        } finally {
            if (c != null)
                c.close();
        }
    }

    public ArrayList<Object> findAll(Object obj, boolean withCriteria, Connection c) throws Exception {
        String query = UtilDao.findAllQuery(obj, withCriteria);
        System.out.println(query);
        return UtilDao.executeFind(obj, query, utilDb, c);
    }

    public ArrayList<Object> findAll(Object obj, boolean withCriteria) throws Exception {
        Connection c = null;
        try {
            c = utilDb.getCon();
            return findAll(obj, withCriteria, c);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null)
                c.close();
        }

    }

    public ArrayList<Object> findBetween(String column, Object min, Object max, Connection c) throws Exception {
        String query = UtilDao.findAllQuery(min, false);
        query += UtilDao.betweenQuery(column, min, max);
        System.out.println(query);
        return UtilDao.executeFind(min, query, utilDb, c);
    }

    public ArrayList<Object> findBetween(String column, Object min, Object max)
            throws Exception {
        Connection c = null;
        try {
            c = utilDb.getCon();
            return findBetween(column, min, max, c);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null)
                c.close();
        }
    }

    public ArrayList<Object> paging(Object obj, int offset, int length, Connection c) throws Exception {
        String query = UtilDao.findAllQuery(obj, false);
        query += UtilDao.pagingQuery(getUtilDb(), offset, length);
        System.out.println("query = " + query);
        return UtilDao.executeFind(obj, query, utilDb, c);
    }

    public ArrayList<Object> paging(Object obj, int offset, int length)
            throws Exception {
        Connection c = null;
        try {
            c = utilDb.getCon();
            return paging(obj, offset, length, c);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null)
                c.close();
        }
    }

}

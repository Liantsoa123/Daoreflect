package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class UtilDb {
    String driver, url, database, user, mdp, bdd;

    public UtilDb() {
    }

    public UtilDb(String driver, String url, String database, String user, String mdp, String bdd) {
        this.setDriver(driver);
        this.setUrl(url);
        this.setDatabase(database);
        this.setUser(user);
        this.setMdp(mdp);
        this.setBdd(bdd);
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getBdd() {
        return bdd;
    }

    public void setBdd(String bdd) {
        this.bdd = bdd;
    }

    public Connection getCon() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + ":" + database, user, mdp);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    

}

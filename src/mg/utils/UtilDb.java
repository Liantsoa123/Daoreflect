package mg.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class UtilDb {
    String url, database, user, mdp, bdd;

    public UtilDb() {
    }

    public UtilDb(String url, String database, String user, String mdp, String bdd) {

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

    public String getBdd() {
        return bdd;
    }

    public void setBdd(String bdd) {
        this.bdd = bdd;
    }

    public Connection getCon() {
        Connection con = null;
        try {
            System.out.println("Database:" + this.getBdd());
            if ("Oracle".equalsIgnoreCase(this.getBdd())) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection(url, user, mdp);
            } else if ("PostgreSql".equalsIgnoreCase(this.getBdd())) {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(url + "/" + database, user, mdp);
            } else if ("Mysql".equalsIgnoreCase(this.getBdd())) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url + "/" + database, user, mdp);
            }
            System.out.println("Connection to " + this.getBdd() + " database established successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}

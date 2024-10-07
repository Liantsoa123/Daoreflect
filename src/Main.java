import java.sql.Connection;

import mg.dao.DaoReflect;

public class Main {
    public static void main(String[] args) {
        try {
            DaoReflect daoReflect = new DaoReflect(
                    "C:\\Users\\rakot\\OneDrive\\Documents\\S5\\Zavatra ilaina\\Reflect\\Daoreflect\\Dao.xml");
            Connection c = daoReflect.getUtilDb().getCon();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 * Dao.xml exemple for oracle
 * <dao>
 * <url>jdbc:oracle:thin:@localhost:1521:orcl</url>
 * <database>GestionAnalytique</database>
 * <user>StationNew</user>
 * <password>liantsoa</password>
 * <bdd>oracle</bdd>
 * </dao>
 * 
 * Dao.xml exemple for postgresql
 * <dao>
 * <url>jdbc:postgresql://localhost:5432</url>
 * <database>GestionAnalytique</database>
 * <user>postgres</user>
 * <password>liantsoa</password>
 * <bdd>postgresql</bdd>
 * </dao>
 * 
 * N.B : types BDD : oracle , mysql , postgresql
 * 
 */
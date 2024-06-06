import mg.dao.DaoReflect;

public class Main {
    public static void main(String[] args) {
        try {
            DaoReflect daoReflect = new DaoReflect(
                    "D:\\IT_University\\IT University\\S4\\Web Dyn (Mr Naina)\\DaoReflect\\Daoreflect\\conf.xml");
            System.out.println(daoReflect.getUtilDb().getDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

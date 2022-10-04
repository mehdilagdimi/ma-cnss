package Database;

public class Config {


    private final static String URL = "jdbc:postgresql://localhost:5432/macnss";
    private final static String USER = "postgres";
    //setup env variable for later use
    private final static String PASSWORD = "admin";


    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}

package Database;

public class Config {

    private final static String url = "jdbc:postgresql://localhost:5432/macnss";
    private final static String user = "postgres";
    //setup env variable for later use
    private final static String password = "admin";

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}

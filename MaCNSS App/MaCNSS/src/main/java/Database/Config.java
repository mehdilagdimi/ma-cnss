package Database;

public class Config {

<<<<<<< HEAD
    private final static String url = "jdbc:postgresql://localhost:5432/macnss";
    private final static String user = "postgres";
    //setup env variable for later use
    private final static String password = "admin";
=======
    private final static String URL = "jdbc:postgresql://localhost:5432/macnss";
    private final static String USER = "mehdilagdimi";
    //setup env variable for later use
    private final static String PASSWORD = "1234";
>>>>>>> c09c2a9a77323e65f638ba1bb082afc1f004d5a2

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

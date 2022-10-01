package Database;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection conn = null;
    public DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
    }

    public Connection establishConnection () {

        try {
            conn = DriverManager.getConnection(Config.getUrl(), Config.getUser(), Config.getPassword());
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public Boolean closeConnection () {
        try{
            conn.close();
            System.out.println("Closing DB connection..");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error closing DB connection");
            return false;
        }
    }

}

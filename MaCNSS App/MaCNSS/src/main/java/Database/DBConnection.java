package Database;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

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

    public <T>PreparedStatement setParam(int index, T data) {
            try{
                switch (((Object)data).getClass().getSimpleName()){
                    case "Boolean" :
                        this.preparedStatement.setBoolean(index, (Boolean)data);
                        break;
                    case "Integer" :
                        this.preparedStatement.setInt(index, (int)data);
                        break;
                    case "Long" :
                        this.preparedStatement.setLong(index, (long)data);
                        break;
                    case "String":
                        this.preparedStatement.setString(index, (String)data);
                        break;
                    case "Float":
                        this.preparedStatement.setDouble(index, (Float)data);
                        break;
                    case "Double":
                        this.preparedStatement.setDouble(index, (Double)data);
                        break;
                }

            } catch (SQLException e){
                e.printStackTrace();
                System.out.println("Error with  statement parameter placeholder");
                this.preparedStatement = null;
            }
        return this.preparedStatement;

    }

    public boolean prepare (String query) {
        try{
            this.preparedStatement = conn.prepareStatement(query);
//            System.out.println("Prepared Statement");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed preparing statement");
            return false;
        }
    }

    public ResultSet execute (String query) {
        try{
            if(this.preparedStatement != null){
                resultSet = this.preparedStatement.executeQuery();
                return resultSet;
            } else {
                System.out.println("Prepared query is null!");
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean isEmpty() {
        Boolean isEmpty = true;
        if (this.resultSet != null) {
            try {
                if (!this.resultSet.isBeforeFirst()) {
                    isEmpty = true;
                } else {
                    isEmpty = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isEmpty;
    }

    public void closeConnection () {
            if (this.conn != null) try {
                this.conn.close();
                System.out.println("Closing DB connection..");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error closing DB connection");
            }
    }

    public void closeQueryOperations () {
        if (this.resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
        if (this.preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
    }

}
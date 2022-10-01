import Database.DBConnection;

public class Main {

    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        db.establishConnection();
        db.closeConnection();
    }


}

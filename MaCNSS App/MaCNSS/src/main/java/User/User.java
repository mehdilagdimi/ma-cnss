package User;

import Database.DBConnection;

public abstract class User {
    public String table;
    public String fname;
    public String lname;

    protected DBConnection db = new DBConnection();
}

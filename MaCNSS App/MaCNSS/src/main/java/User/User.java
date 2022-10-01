package User;

import Database.DBConnection;

public abstract class User {
    public String table;

    protected DBConnection db = new DBConnection();
}

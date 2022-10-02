package User.Admin;

import User.User;
public class AdminModel extends User {

    private int id;
    private String email;
    private String passw;

    public AdminModel () {
        db.establishConnection();
        table = "agent";
//        db.closeConnection();
    }
    public void getAdminByEmail (String email) {
        String query = "SELECT * FROM " + table + " WHERE role = 1 AND email = ? LIMIT 1";
        db.prepareStatement(query, email);
        db.execute(query);
    }
}

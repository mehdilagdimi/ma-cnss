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
    public Boolean getAdminByEmail (String email) {
        String query = "SELECT * FROM " + table + " WHERE role = 1 AND email = ? LIMIT 1";
        if(db.prepare(query)){
            db.setParam(1, email);
            db.execute();
        }
        return db.isEmpty();
    }
}

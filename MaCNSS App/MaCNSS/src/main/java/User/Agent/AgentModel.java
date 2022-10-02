package User.Agent;

import User.User;
public class AgentModel extends User{

    private int id;
    private String email;
    private String passw;

    public AgentModel () {
        db.establishConnection();
        table = "agent";
//        db.closeConnection();
    }
    public void getAgentByEmail (String email) {
        String query = "SELECT * FROM " + table + " WHERE role = 0 AND email = ? LIMIT 1";
        db.prepareStatement(query, email);
        db.execute(query);
    }
}

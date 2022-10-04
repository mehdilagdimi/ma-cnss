package User.Agent;

import User.User;
public class AgentModel extends User{

    public AgentModel () {
        db.establishConnection();
        table = "agent";
    }

    public void closeDBConnection () {
        db.closeConnection();
        System.out.println("Closed DB conn");
    }

    public boolean isAgentExist (String email, String passw) {
        String query = "SELECT * FROM " + table + " WHERE email = ? AND password = ? LIMIT 1";
        if(db.prepare(query)){
            db.setParam(1, email);
            db.setParam(2, passw);
            db.execute();
        }
        return !db.isEmpty();
    }


    public Boolean getAgentByEmail (String email) {
        String query = "SELECT * FROM " + table + " WHERE role = 0 AND email = ? LIMIT 1";
        if(db.prepare(query)){
            db.setParam(1, email);
            db.execute();
        }
        return db.isEmpty();
    }

    public void closeQuery () {
        db.closeQueryOperations();
    }

}

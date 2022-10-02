package User.Conjoint;

import User.User;
public class ConjointModel extends User {

    private int id;
    private int id_matricule_patient;
    private int age;

    public ConjointModel () {
        db.establishConnection();
        table = "conjoint";
//        db.closeConnection();
    }
    public void getConjoints (int id_matricule_patient) {
        String query = "SELECT * FROM " + table + " WHERE id_matricule_patient = ?";
        db.prepareStatement(query, Integer.toString(id_matricule_patient));
        db.execute(query);
    }
}

package User.Patient.Conjoint;

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
        if(db.prepare(query)){
            db.setParam(1, id_matricule_patient);
            db.execute(query);
        }
//        return db.isEmpty();
    }
}

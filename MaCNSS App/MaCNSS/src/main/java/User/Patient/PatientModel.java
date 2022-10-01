package User.Patient;

import User.User;


public class PatientModel extends User {

    public String fname;
    public String lname;
    private int id_matricule;
    private String email;
    private String passw;

    public PatientModel () {
        db.establishConnection();
//        db.closeConnection();
    }
    public void getPatientByMatricule () {
        String query = "SELECT 1 FROM " + table + " WHERE id_matricule = ? LIMIT 1";
        db.prepareStatement(query, Integer.toString(id_matricule));
        db.set
    }


}

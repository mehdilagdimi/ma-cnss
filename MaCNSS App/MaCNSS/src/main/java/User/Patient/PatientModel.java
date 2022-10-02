package User.Patient;

import User.User;

import java.sql.ResultSet;


public class PatientModel extends User {

    private int id_matricule;
    private String email;
    private String passw;

    public PatientModel () {
        db.establishConnection();
//        db.closeConnection();
        table = "patient";
    }

    public void closeDBConnection () {
        db.closeConnection();
        System.out.println("Closed DB conn");
    }

    public Boolean getPatient (String email, String passw) {
        String query = "SELECT * FROM " + table + " WHERE email = ? AND passw = ? LIMIT 1";
        if(db.prepare(query)){
            db.setParam(1, email);
            db.setParam(2, passw);
            db.execute(query);
        }
        return db.isEmpty();
    }

    public ResultSet getPatientByMatricule (int id_matricule) {
        String query = "SELECT * FROM " + table + " WHERE id_matricule = ? LIMIT 1";
        if(db.prepare(query)){
            db.setParam(1, Integer.toString(id_matricule));
            return db.execute(query);
        } else {
            return null;
        }
    }

    public void closeQuery () {
        db.closeQueryOperations();
    }

}

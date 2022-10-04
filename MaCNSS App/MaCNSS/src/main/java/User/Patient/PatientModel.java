package User.Patient;

import User.User;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;


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
            db.execute();
        }
        return db.isEmpty();
    }

    public ResultSet getPatientByMatricule (int id_matricule) {
        ResultSet result = null;
        String query = "SELECT * FROM " + table + " WHERE id_matricule = ? LIMIT 1";
        try {
            if (db.prepare(query)) {
                db.setParam(1, id_matricule);
                result = db.execute();
                if(result.next()){
                    return result;
                } else {
                    System.out.printf("No result found");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public void closeQuery () {
        db.closeQueryOperations();
    }

}

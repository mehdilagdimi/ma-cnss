package User.Patient;

import User.User;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.SystemeHelper.println;


public class PatientModel extends User {


    public PatientModel () {
        db.establishConnection();
        table = "patient";
    }

    public void closeDBConnection () {
        db.closeConnection();
        System.out.println("Closed DB conn");
    }

    public boolean isPatientExist (String email, String passw) {
        String query = "SELECT * FROM " + table + " WHERE email = ? AND password = ? LIMIT 1";
        if(db.prepare(query)){
            db.setParam(1, email);
            db.setParam(2, passw);
            db.execute();
        }
        return !db.isEmpty();
    }

    public boolean isPatientExistByMatrecule (long matrecule) {
        String query = "SELECT * FROM patient WHERE id_matricule = ?";
        if(db.prepare(query)){
            db.setParam(1, matrecule);
            db.execute();
        }
        return !db.isEmpty();
    }
    public ResultSet getPatientByMatricule (long id_matricule) {
        ResultSet result = null;
        String query = "SELECT * FROM " + table + " WHERE id_matricule = ? LIMIT 1";
        try {
            if (db.prepare(query)) {
                db.setParam(1, id_matricule);
                result = db.execute();
                if(result.next()){
                    return result;
                } else {
                    System.out.print("No result found");
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

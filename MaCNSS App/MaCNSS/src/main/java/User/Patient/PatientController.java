package User.Patient;


import java.sql.ResultSet;

public class PatientController {

    private int id_matricule;
    public String fname;
    public String lname;
    private String email;
    private String passw;

    PatientModel patientModel;

    public PatientController () {
        patientModel = new PatientModel();
    }

    public Boolean authenticate (String email, String passw) {
        Boolean isValid = patientModel.getPatient(email, passw);
        patientModel.closeQuery();
        return isValid;
    }

    public void closeDBConnection () {
        patientModel.closeDBConnection();
    }

}

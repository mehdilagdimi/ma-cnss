<<<<<<< HEAD
public class Main {
=======
//import Database.DBConnection;

import User.Patient.Patient;

public class Main {

    public static void main(String[] args) {
        Patient patient = new Patient();
        patient.displayPatient();

        
        //close DB connection
        patient.disconnect();
    }

>>>>>>> c09c2a9a77323e65f638ba1bb082afc1f004d5a2
}

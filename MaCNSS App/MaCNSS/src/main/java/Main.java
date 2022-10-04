//import Database.DBConnection;

import User.Patient.Patient;

public class Main {

    public static void main(String[] args) {
        Patient patient = new Patient();
        patient.displayPatient();

//        Dossier dossier = new  Dossier();
//        dossier.getAllFolder(patient.id_matricule);
//
//        dossier.getFolderByCode(code);
//

        
        //close DB connection
        patient.disconnect();
    }

}

package User.Patient;

import java.util.Map;
import java.util.Scanner;

public class Patient {
    private int id_matricule;
    public String fname;
    public String lname;
    private String email;
    private String passw;

    Scanner scanner = new Scanner(System.in);

    PatientController patientController;

    public Patient () {
        patientController = new PatientController();
    }

    public void displayPatient(){
        System.out.println("Enter patient matricule");
        id_matricule = scanner.nextInt();
        Map<String, String> patientMap = patientController.getPatientData(id_matricule);
        System.out.printf("ID Matricule : %s, Nom : %s,  Prénom : %s, Email : %s", patientMap.get("matricule"), patientMap.get("lname"), patientMap.get("fname"), patientMap.get("email"));
    }

    public void disconnect(){
        patientController.closeDBConnection();
    }
}

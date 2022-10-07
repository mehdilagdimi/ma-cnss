package User.Patient;

import helper.SystemeHelper;

import java.util.Map;
import java.util.Scanner;

public class Patient extends SystemeHelper {
    private long id_matricule;
    public String fname;
    public String lname;


    private String email;
    private String passw;

    Scanner scanner = new Scanner(System.in);

    public PatientController patientController;

    public Patient () {
        patientController = new PatientController();
    }


    /**
     * Display a msg to ask agent to enter ID_Matricule of patient and display his info
     * @return void
     */
    public void displayPatient(){
        System.out.println("\nEnter patient matricule : ");
        id_matricule = scanner.nextInt();
        Map<String, String> patientMap = patientController.getPatientData(id_matricule);
        System.out.printf("ID Matricule : %s, Nom : %s,  Prénom : %s, Email : %s", patientMap.get("matricule"), patientMap.get("lname"), patientMap.get("fname"), patientMap.get("email"));
    }

   public void setId_matricule (String email) {
       long matricule = patientController.getPatientMatricule(email);
       this.id_matricule = matricule;
   }

    public void authenticate(){
        println("**** \t\t Login Page \t\t ***");
        println("Enter your email :");
        scan().nextLine();
        this.email = scan().nextLine();
        println("Enter your password :");
        this.passw = scan().nextLine();
        if(patientController.authenticate(email, passw)){
            setId_matricule(email);
            println("Successfully authenticated!");
        } else {
            println("Failed authentication. Either invalid credentials or patient not existant!");
        };
    }

    public void disconnect(){
        patientController.closeDBConnection();
    }

    public long getId_matricule() {
        return id_matricule;
    }
    @Override
    public String toString() {
        return "Patient{" +
                "id_matricule=" + id_matricule +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", passw='" + passw + '\'' +
                ", scanner=" + scanner +
                ", patientController=" + patientController +
                '}';
    }
}

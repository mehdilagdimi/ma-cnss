import User.Patient.Patient;

public class Main {

    public static void main(String[] args) {
        Patient patient = new Patient();
        patient.displayPatient();

        
        //close DB connection
        patient.disconnect();
    }


}

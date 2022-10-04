import User.Agent.Agent;
import helper.Emailer.SimpleEmail;

public class Main {

    public static void main(String[] args) {


        //Agent workflow
        Agent agent = new Agent();
        agent.authenticate();

//        Patient patient = new Patient();
//        patient.displayPatient();




        //patient workflow

//        Patient patient = new Patient();
//        patient.authenticate();
//        patient.displayPatient();


//        Dossier dossier = new  Dossier();
//        dossier.getAllFolder(patient.id_matricule);
//
//        dossier.getFolderByCode(code);
//

        
        //close DB connection for each package called (patient, agent, admin, document ....
//        patient.disconnect();
    }


}

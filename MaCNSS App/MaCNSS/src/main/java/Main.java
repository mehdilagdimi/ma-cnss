import Medical.Folder.Consultation.Consultation;
import Medical.Folder.Dossier.Dossier;
import User.Agent.Agent;
import User.Patient.Patient;

import static helper.SystemeHelper.*;

public class Main {
    public static boolean repeat = true;
    public static void main(String[] args) {


    while(repeat){
        println("\t ---- \t Welcome to MaCNSS Dashboard \t ---- ");
        println("\t 1 - \t Continuer en tant qu'Admin ");
        println("\t 2 - \t Continuer en tant qu'Agent MaCNSS ");
        println("\t 3 - \t Continuer en tant que Bénéficiaire du MaCNSS ");
        println("\t 4 - \t Exit ");

        switch(scan().nextInt()){
            case 1 :
                adminWorkflow();
                break;
            case 2 :
                agentWorkflow();
                break;
            case 3 :
                patientWorkflow();
                break;
            case 4 :
                repeat = false;
                break;
        }
    }

    }

    //Agent workflow
    private static void agentWorkflow () {
        Agent agent = new Agent();
        Dossier dossier = new Dossier();
        if(!agent.authenticate()){
            return;
        };

        println("\t --- Agent Menu ---");
        println("1 - \t Ajouter nouveau dossier :");
        println("2 - \t Vérifier dossiers existants :");
        println("3 - \t Exit :");
        switch (scan().nextInt()){
            case 1 :
                dossier.addNewDossier();
                break;
            case 2 :
                dossier.displayPatientAllPendingFolders();
                break;
            case 3 :
                return;
        }

    }

    //Agent workflow

    private static void patientWorkflow () {
        Patient patient = new Patient();
        patient.authenticate();
        patient.displayPatient();;

        Dossier dossier = new  Dossier();
        dossier.displayPatientAllFoldersSortedByPending(patient.getId_matricule());

        patient.disconnect();
    }

    //Admin workflow
    private static void adminWorkflow () {
//        Agent agent = new Agent();
//        agent.authenticate();
    }

}

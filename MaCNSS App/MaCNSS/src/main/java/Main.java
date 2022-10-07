import Medical.Folder.Consultation.Consultation;
import Medical.Folder.Document.Document;
import Medical.Folder.Dossier.Dossier;
import User.Agent.Agent;
import User.Patient.Patient;
import scala.collection.immutable.Stream;

import java.util.ArrayList;
import java.util.List;

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
        List<Consultation> listConsultations = new ArrayList<Consultation>();

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
                listConsultations = populateDossier(dossier.getNbrConsultation(), dossier.getCode());

                //Process medical folder and display result
                dossier.getProcessResult(listConsultations);
                println("TOTAL Refund : " + dossier.totalRefund);
                break;
            case 2 :
                dossier.displayPatientAllPendingFolders();
                break;
            case 3 :
                return;
        }
    }
    

    public static List<Consultation> populateDossier (int nbrConsultation, long codeDossier) {
        List<Consultation> listConsultations = new ArrayList<Consultation>();
        List<Document> listDocuments;
        while(nbrConsultation > 0){
            Consultation consultation = new Consultation();
            consultation.addConsultation(codeDossier);

            //populate documents for each consultation and then add the returned list to the consultation object
            listDocuments = enterDocumentsData(consultation.getNumDocuments(), consultation.getId());
            consultation.setListDocuments(listDocuments);
            //Add the consultation object to the list of consultations
            listConsultations.add(consultation);
            nbrConsultation--;
        }
        return listConsultations;
    }

    public static List<Document> enterDocumentsData (int nbrDocuments, long id_consultation) {
        List<Document> listDocuments = new ArrayList<Document>();
        while(nbrDocuments > 0){
            Document document = new Document();
            document.addDocument(id_consultation);

            listDocuments.add(document);
            nbrDocuments--;
        }
        return listDocuments;
    }

    //Agent workflow
    private static void patientWorkflow () {
        Patient patient = new Patient();
        patient.authenticate();
        Dossier dossier = new Dossier();
        dossier.displayPatientAllFoldersSortedByPending(patient.getId_matricule());
        patient.disconnect();
    }

    //Admin workflow
    private static void adminWorkflow () {
//        Agent agent = new Agent();
//        agent.authenticate();
    }

}

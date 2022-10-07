import Medical.Folder.Consultation.Consultation;
import Medical.Folder.Document.Document;
import Medical.Folder.Dossier.Dossier;
import User.Agent.Agent;
import User.Patient.Patient;
import helper.Emailer.EmailHelper;
import helper.Emailer.SimpleEmail;
import scala.collection.immutable.Stream;
import scala.sys.process.ProcessBuilderImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static helper.SystemeHelper.*;

public class Main {
    public static boolean repeat = true;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(repeat){
            println("\t ---- \t Welcome to MaCNSS Dashboard \t ---- ");
            println("\t 1 - \t Continuer en tant qu'Admin ");
            println("\t 2 - \t Continuer en tant qu'Agent MaCNSS ");
            println("\t 3 - \t Continuer en tant que Bénéficiaire du MaCNSS ");
            println("\t 4 - \t Exit ");

            switch(scanner.nextInt()){
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
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;
        Agent agent = new Agent();
        Dossier dossier = new Dossier();
        List<Consultation> listConsultations;
        Patient patient = new Patient();
        if(!agent.authenticate()){
            return;
        };

        while (repeat) {
            println("\t --- Agent Menu ---");
            println("1 - \t Ajouter nouveau dossier :");
            println("2 - \t Vérifier dossiers existants :");
            println("3 - \t Exit :");

            switch (scanner.nextInt()) {
                case 1:
                    dossier.addNewDossier();
                    listConsultations = populateDossier(dossier.getNbrConsultation(), dossier.getCode());

                    //Process medical folder and display result
                    dossier.getProcessResult(listConsultations);
                    println("TOTAL Refund : " + dossier.totalRefund);
                    Map<String, String> patientMap = patient.patientController.getPatientData(dossier.getMatrecule());

                    println("\t 1 - Valider le dossier :");
                    println("\t 2 - Garder le dossier en attente :");
                    switch (scanner.nextInt()) {
                        case 1:
                            //update dossier status to VALIDE
                            dossier.controller.updateDossierStatus("VALIDE", dossier.getCode());
                            println("\tLe dossier est validé! Un email à été envoyé au patient! :");
                            //Send email
                            SimpleEmail.sendSimpleEmail(patientMap.get("email"), "Total du remboursement", "<h2> Total du remboursement : <h2>" + dossier.totalRefund + "DH");
                            break;
                        case 2:
                            SimpleEmail.sendSimpleEmail(patientMap.get("email"), "Etat de dossier MaCNSS", "<h3> Votre dossier est en cours d'attente merci pour votre compéhension! </h3>");
                            break;
                    }
                    break;
                case 2:
//                    dossier.displayPatientAllPendingFolders();
                    dossier.updateDossierStatus();
                    break;
                case 3:
                    repeat = false;
                    break;
            }
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

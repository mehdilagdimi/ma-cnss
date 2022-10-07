package Medical.Folder.Dossier;

import Medical.Folder.Consultation.Consultation;
import User.Agent.Agent;
import User.Patient.PatientController;
import helper.Emailer.SimpleEmail;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static helper.SystemeHelper.*;
import static helper.SystemeHelper.print;

public class Dossier {

    public enum StatusDoc{
        EN_ATTENTE,
        REFUSE,
        VALIDE;
    }

    public DossierController controller;
    private long code;
    private String date;
    private long matrecule;
    private int nbrConsultation;
    private String status;
    private double sumRefunds;
    public float totalRefund = 0;


    // Setters
    public void setCode(long code){
        this.code = code;
    }
    public void setDate(String date){
        this.date = date;
    }
    public  void setMatrecule(long matrecule){
        this.matrecule = matrecule;
    }
    public void setNbrConsultation(int nbrConsultation){
        this.nbrConsultation = nbrConsultation;
    }
    public  void  setStatus(String status){
        this.status = status;
    }
    public void setSumRefunds(double sumRefunds) {
        this.sumRefunds = sumRefunds;
    }
    // Getters
    public int getNbrConsultation() {
        return nbrConsultation;
    }

    public long getMatrecule() {
        return matrecule;
    }

    public String getDate() {
        return date;
    }

    public long getCode() {
        return code;
    }

    public String getStatus(){
        return status;
    }

    public double getSumRefunds() {
        return sumRefunds;
    }
    //Constructor
    public Dossier(){
        controller = new DossierController();
    }
    @Override
    public String toString(){
        return  "-------------------------------------" +
                "\n Code  =  " + this.code +
                "\n Date de creation  =  " + this.date +
                "\n Nombre de Consultation  =  " + this.nbrConsultation +
                "\n Matricule =  "+ this.matrecule +
                "\n Etat du dossier  =  " + this.status +
                "\n------------------------------------";

    }

    public void addNewDossier(){
        long codeDossier;
        PatientController patientController = new PatientController();
        println("Entrer le matricule du patient :");
        long idMatricule = scan().nextLong();
        if (patientController.checkPatientIsAvailable(idMatricule)){
            setMatrecule(idMatricule);
            println("Entrer le nombre des consultations joinier");
            this.nbrConsultation = scan().nextInt();
            codeDossier = controller.addNewDossier( idMatricule, this.nbrConsultation);
            if(codeDossier != -1){
                setCodeDossier(codeDossier);
            } else {
                println("Erreur, le dossier n'était pas ajouté");
            }
        }else{
            println("Erreur Matrecule introuvable");
        }

    }

    public void setCodeDossier (long codeDossier) {
        this.code = codeDossier;
    }
    public void  displayDossiers(){
        println("\n -------------   Dossiers  -------------");
        print("Enter matricule du patient :");
        long matrecule = scan().nextLong();
        ArrayList<Dossier> dossiers =  controller.setDossierList(matrecule);
        print(dossiers.toString());
    }


    public void displayPatientAllPendingFolders(){
        PatientController patientController = new PatientController();
        println("Entrer le matricule du patient :");
        long id_matricule = scan().nextLong();
        if (patientController.checkPatientIsAvailable(id_matricule)){
            ArrayList<Dossier> dossiers = controller.setDossierList(id_matricule);
            List<Dossier> filteredDossier = dossiers.stream().filter(dossier -> dossier.getStatus().equals("EN_ATTENTE")).toList();
            println(filteredDossier.toString());
        }else{
            println("Erreur Matrecule introuvable");
        }
    }
    public void displayPatientAllFoldersSortedByPending(long id_matricule){
       ArrayList<Dossier> dossiers = controller.setDossierList(id_matricule);
       List<Dossier> filteredDossier = Stream.concat(dossiers.stream().filter(dossier -> dossier.status.equals("EN_ATTENTE")), dossiers.stream().filter((dossier) -> dossier.status.equals("REFUSE") || dossier.status.equals("VALIDE"))).toList();
       filteredDossier.forEach(System.out::print);
    }

    public void updateDossierStatus(){
        int choice = 0;

        println("------------------ Validation ----------------");
        //input matricule of patient
        println("Entrer le matricule du patient :");
        long idMatricule = scan().nextLong();

        ArrayList<Dossier> dossiers = controller.setDossierList(idMatricule);
        List<Dossier> pendingDossier = dossiers.stream().filter(dossier -> dossier.status.equals("EN_ATTENTE")).toList();
        pendingDossier.forEach(System.out::println);
        // get patient email
        PatientController patient = new PatientController();
        String emailPatient = patient.getPatientData(idMatricule).get("email");


        // input dossier code
        println("\n-Entrer le code du patient :");
        long codeDossier = scan().nextLong();
        //display the specific dossier
        Dossier dossier = controller.getDossierByCode(idMatricule,codeDossier);
        println(dossier.toString());
        //Change dossier status
        println("Changer l'etat du dossier  :");
        println("1.Validé");
        println("2.Refusé");
        choice  = scan().nextInt();
        String valide = "Bonjour chèr(e) client,<br><br> "
                +"Nous vous informent que le dossier : <B> "
                +codeDossier+" </B> a bien était traité et que le dossier est <B>Validé</B>.";
        String Refuse = "Bonjour chèr(e) client,<br><br> "
                +"Nous vous informent que le dossier : <B> "
                +codeDossier+" </B> a bien était traité et que le dossier est malheureusement <B>Refusé</B>.";
        switch (choice){
            case 1 : if (controller.updateDossierStatus("VALIDE",codeDossier)){
                println("Etat De Dossier Modifier !!");
                // send email to the referred patient
                SimpleEmail.sendSimpleEmail(emailPatient,"Modification Agent", valide);
            }else println("Modification erreur");
                break;
            case 2: if (controller.updateDossierStatus("REFUSE",codeDossier)) {
                println("Etat De Dossier Modifier !!");
                SimpleEmail.sendSimpleEmail(emailPatient,"Modification Agent MACNSS", Refuse);
            }else println("Modification erreur");
                break;
        }
    }

    public void getProcessResult(List<Consultation> listConsultations){
        controller.processDossier(this, listConsultations);

    }

}

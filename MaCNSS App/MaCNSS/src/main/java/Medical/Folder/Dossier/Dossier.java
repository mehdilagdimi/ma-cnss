package Medical.Folder.Dossier;

import User.Agent.Agent;
import User.Patient.PatientController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static helper.SystemeHelper.*;
import static helper.SystemeHelper.print;

public class Dossier {
    public enum StatusDoc{
        EN_ATTENTE("En attente"),
        REFUSE("Refusé"),
        VALIDE("Validé");
        private String name;
        StatusDoc(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    DossierController controller;
    private long code;
    private String date;
    private long matrecule;
    private int nbrConsultation;
    private String status;

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

    //Constructor
    public Dossier(){
        controller = new DossierController();
    }
    @Override
    public String toString(){
        return  "\n Code  =  " + this.code +
                "\n Date de creation  =  " + this.date +
                "\n Nombre de Cinsultation  =  " + this.nbrConsultation +
                "\n Matricule =  "+ this.matrecule +
                "\n Etat du dossier  =" + this.status +
                "\n";

    }

    public void addNewDossier(){
        PatientController patientController = new PatientController();
        println("Entrer le matricule du patient :");
        long idMatricule = scan().nextLong();
        if (patientController.checkPatientIsAvailable(idMatricule)){
            println("Entrer le nombre des consultations joinier");
            int nbrConsultation = scan().nextInt();
            controller.addNewDossier( idMatricule, nbrConsultation);
        }else{
            println("Erreur Matrecule introuvable");
        }

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
       ArrayList<Dossier> dossiers = controller.getAllDossiers();
       List<Dossier> filteredDossier = Stream.concat(dossiers.stream().filter(dossier -> dossier.status.equals("EN_ATTENTE")), dossiers.stream().filter((dossier) -> dossier.status.equals("REFUSE") || dossier.status.equals("VALIDE"))).toList();
       println(filteredDossier.toString());
    }

    public void updateDossierStatus(){
        println("------------------ Validation ----------------");
        println("Entrer le matricule du patient :");
        long idMatricule = scan().nextLong();
        println(controller.setDossierList(idMatricule).toString());
        println("\n-Entrer le code du patient :");
        long codeDossier = scan().nextLong();
        Dossier dossier = controller.getDossierByCode(idMatricule,codeDossier);

        println(dossier.toString());
    }
    public static void main(String[] args) {
        Dossier newDossier = new Dossier();
        newDossier.updateDossierStatus();
    }
}

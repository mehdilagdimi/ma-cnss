package Medical.Folder.Consultation;

import Medical.Folder.Document.Document;
import helper.Specialite;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static helper.SystemeHelper.*;

public class Consultation {
    public ConsultationController getController() {
        return controller;
    }

    private final ConsultationController controller;

    private long id;
    private long codeDossier;
    private LocalDate date;
    private int idSpecialite;
    private int montantPaye;
    private boolean isConjoint;
    private int numDocuments;
    private double sumConsultationRefund;



    private List<Document> listDocuments;


    // Getters

    public long getId(){
        return this.id;
    }

    public long codeDossier() {
        return this.codeDossier;
    }

    public int getMontantPaye() {
        return this.montantPaye;
    }

    public int getIdSpecialite() {
        return this.idSpecialite;
    }

    public int getNumDocuments() {
        return this.numDocuments;
    }

    public double getSumConsultationRefund() {
        return sumConsultationRefund;
    }

    public LocalDate getDate() {
        return date;
    }
    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setCodeDossier(long codeDossier) {
        this.codeDossier = codeDossier;
    }

    public void setIdSpecialite(int id) {
        this.idSpecialite = id;
    }

    public void setConjoint(boolean isConjoint) {
        this.isConjoint = isConjoint;
    }

    public void setMontantPaye(int montant) {
        this.montantPaye = montant;
    }

    public void setNumDocuments(int numDocuments) {
        this.numDocuments = numDocuments;
    }


    public Consultation() {
        this.controller = new ConsultationController();
    }

    public void setSumConsultationRefund(double sumConsultationRefund) {
        this.sumConsultationRefund += sumConsultationRefund;
    }

    public void displayConsultation() {
        println("\n -------------   Consultation -------------");
        print("Enter consultation Code");
        int code = scan().nextInt();
        ArrayList<Consultation> consultations = controller.setConsultationByCode(code);
        print(consultations.toString());
    }
    public void setListDocuments(List<Document> listDocuments) {
        this.listDocuments = listDocuments;
    }



    public void addConsultation(long codeDossier) {
        println("-------------   Adding consultation data  -------------");
        println("Entrér la date de consultation :  (ex : 26/07/2022)");
        scan().nextLine();
        String dateStr = scan().nextLine();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" )
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = LocalDate.parse(dateStr, formatter);

        println("Le bénéficiare est-t-il un conjoint? : Y/N");
        if (scan().next().toUpperCase() == "Y") {
            this.isConjoint = true;
        } else {
            this.isConjoint = false;
        }


        println("Entrer le montant payé (DHs):");
        this.montantPaye = scan().nextInt();

        println("Entrer le nombre de documents :");
        this.numDocuments = scan().nextInt();

        //get specialities from DB
        println("Spécialité ? ");
        List<Specialite> specialites = this.controller.getAllSpecialites();
        for (Specialite specialite : specialites) {
            println("\t" + String.valueOf(specialite.id) + "- " + specialite.nom);
        }
        scan().nextLine();
        this.idSpecialite = scan().nextInt();
//        setSumConsultationRefund(controller.setRefundsPrice(this.idSpecialite));
//        if (this.controller.createConsultation(codeDossier, this.idSpecialite, this.date, this.isConjoint, this.montantPaye, this.numDocuments)) {

            //add consu and set the id for the object
            setId(this.controller.createConsultation(codeDossier, this.idSpecialite, this.date, this.isConjoint, this.montantPaye, this.numDocuments));

            if (this.id != -1) {
                println("Consultation added successfully");
            } else {
                println("Failed adding consultation");
            }
        }




    @Override
    public String toString() {
        return "------------------------------" +
                "\n" + this.codeDossier +
                "\n" + this.idSpecialite +
                "\n" + this.montantPaye +
                "\n" + this.isConjoint +
                "\n" + this.numDocuments +
                "\n------------------------------";

    }

    public List<Document> getListDocuments() {
        return listDocuments;
    }
}


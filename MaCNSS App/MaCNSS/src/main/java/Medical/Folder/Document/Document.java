package Medical.Folder.Document;

import Medical.Folder.Dossier.Dossier;
import Medical.Folder.Dossier.DossierController;
import helper.Specialite;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static helper.SystemeHelper.*;
import static helper.SystemeHelper.print;

public class Document {

    private enum TypeDoc{
        Médicament,
        Analyse,
        Imagerie
    }

    private final DocumentController controller;
    private int id;
    private LocalDate date;
    private int idConsultation;
    private float montantPaye;
    private String type;
    // Getters
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public float getMontantPaye() {
        return montantPaye;
    }
    public  String getType(){
        return this.type;
    }
    // Setters
    public void setMontantPaye(float montantPaye) {
        this.montantPaye = montantPaye;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setType(String  type) {
        this.type = type;
    }
    // Type Document Enum


    public Document(){
        controller = new DocumentController();
    }


    public static void main(String[] args) {
        Document test = new Document();
//        test.displayDocuments();
        test.addDocument(1);
    }


    public void addDocument(long idConsultation) {

        println("-------------   Adding document data  -------------");
        println("Entrer la date du document :  (ex : 26/07/2022)");
        String dateStr = scan().nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = LocalDate.parse(dateStr, formatter);

        println("Entrer le montant payé (DHs):");
        this.montantPaye = scan().nextFloat();

        //get specialities from DB
        println("Entrer le numéro du type de consultation :");
        int i = 0;
        for(TypeDoc type : TypeDoc.values()){
            println("\t" + i + "- " + type);
            i++;
        }
        this.type = TypeDoc.values()[scan().nextInt()].toString();

        //create document
        if(this.controller.createDocument(idConsultation, this.date, this.montantPaye, this.type)){
            println("Consultation added successfully");
        } else {
            println("Failed adding consultation");
        };
    }


    public void  displayDocuments(){
        println("\n -------------   Consultation -------------");
        print("Enter Consultation code");
        int idConsultation = scan().nextInt();
        ArrayList<Document> dossiers =  controller.setDocuments(idConsultation);
        print(dossiers.toString());
    }

    @Override
    public String toString(){
        return  "\n" + this.id +
                "\n" + this.date +
                "\n" + this.idConsultation +
                "\n" + this.montantPaye +
                "\n" + this.type +
                "\n";

    }
}

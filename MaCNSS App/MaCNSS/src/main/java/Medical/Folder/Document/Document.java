package Medical.Folder.Document;

import Medical.Folder.Dossier.Dossier;
import Medical.Folder.Dossier.DossierController;
import helper.Specialite;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static helper.SystemeHelper.*;
import static helper.SystemeHelper.print;

public class Document {

    private enum TypeDoc{
        MEDICAMENT,
        ANALYSE,
        IMAGERIE
    }

    private final DocumentController controller;
    private long id;
    private LocalDate date;
    private int idConsultation;
    private float montantPaye;
    private String type;

    public float percentage;


    private String nom;
    // Getters
    public String getNom() {
        return nom;
    }
    public long getId() {
        return id;
    }

    public DocumentController getController() {
        return controller;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getIdConsultation() {
        return idConsultation;
    }
    public float getPercentage() {
        return percentage;
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
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setType(String  type) {
        this.type = type;
    }
    // Type Document Enum
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }


    public Document(){
        controller = new DocumentController();
    }





    public void addDocument(long idConsultation) {
        Scanner scanner = new Scanner(System.in);
        println("-------------   Adding document data  -------------");
        println("Entrer la date du document :  (ex : 26/07/2022)");

        String dateStr = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = LocalDate.parse(dateStr, formatter);

        println("Entrer le montant payé (DHs):");
        this.montantPaye = scanner.nextFloat();

        //get specialities from DB
        println("Entrer le numéro du type de consultation :");
        int i = 0;
        for(TypeDoc type : TypeDoc.values()){
            println("\t" + i + "- " + type);
            i++;
        }
        setType(TypeDoc.values()[scanner.nextInt()].toString());

        println("Entrer le nom du médicament/examen/analyse':");
        scanner.nextLine();
        String nomDocument = scanner.nextLine();
        setNom(nomDocument);
        //create document
        setId(this.controller.createDocument(idConsultation, this.date, this.montantPaye, this.type));
        println(getType()+" "+getNom()+" here!!");
        setPercentage(this.controller.getRefundPercentage(getType(), getNom()));
        if(this.getId() != -1){
            println("Document added successfully");
        } else {
            println("Failed adding document");
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

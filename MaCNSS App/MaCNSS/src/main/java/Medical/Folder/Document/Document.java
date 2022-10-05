package Medical.Folder.Document;

import Medical.Folder.Dossier.Dossier;
import Medical.Folder.Dossier.DossierController;

import java.util.ArrayList;

import static helper.SystemeHelper.*;
import static helper.SystemeHelper.print;

public class Document {
    private final DocumentController controller;
    private int id;
    private String date;
    private int idConsultation;
    private float montantPaye;
    private String type;
    // Getters
    public int getId() {
        return id;
    }

    public String getDate() {
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setType(String  type) {
        this.type = type;
    }
    // Type Document Enum
    private enum TypeDoc{
        Médicament,
        Analyse,
        Imagerie
    }

    public Document(){
        controller = new DocumentController();
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

    public void  displayDocuments(){
        println("\n -------------   Consultation -------------");
        print("Enter Consultation code");
        int idConsultation = scan().nextInt();
        ArrayList<Document> dossiers =  controller.setDocuments(idConsultation);
        print(dossiers.toString());
    }
    public static void main(String[] args) {
        Document test = new Document();
        test.displayDocuments();
    }

}

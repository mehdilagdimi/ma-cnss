package Medical.Folder.Consultation;

import java.util.ArrayList;

import static helper.SystemeHelper.*;

public class Consultation {
    private final ConsultationController controller;

    private int id;
    private long codeDossier;
    private int idSpecialite;
    private int montantPaye;
    private boolean isConjoint;
    private int numDocuments;

    // Getters
    public int getId(){
        return this.id;
    }
    public long codeDossier(){
        return this.codeDossier;
    }
    public int getMontantPaye(){
        return this.montantPaye;
    }
    public int getIdSpecialite(){
        return this.idSpecialite;
    }
    public int getNumDocuments(){
        return this.numDocuments;
    }
    // Setters
    public void setId(int id){
        this.id = id;
    }
    public void setCodeDossier(long codeDossier){
        this.codeDossier = codeDossier;
    }
    public void setIdSpecialite(int id){
        this.idSpecialite = id;
    }
    public void setConjoint(boolean isConjoint){
        this.isConjoint = isConjoint;
    }
    public void setMontantPaye(int montant){
        this.montantPaye = montant;
    }
    public void setNumDocuments(int numDocuments){
        this.numDocuments = numDocuments;
    }
    public Consultation(){
        this.controller = new ConsultationController();
    }
    public void  displayConsultation(){
       println("\n -------------   Consultation -------------");
       print("Enter consultation Code");
       int code = scan().nextInt();
       ArrayList<Consultation> consultations =  controller.setConsultationByCode(code);
       print(consultations.toString());
    }
    @Override
    public String toString(){
        return  "\n" + this.codeDossier +
                "\n" + this.idSpecialite +
                "\n" + this.montantPaye +
                "\n" + this.isConjoint +
                "\n" + this.numDocuments +
                "\n";

    }


}

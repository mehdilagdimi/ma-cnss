package Medical.Folder.Dossier;

import java.util.ArrayList;

import static helper.SystemeHelper.*;
import static helper.SystemeHelper.print;

public class Dossier {
    DossierController controller;
    private long code;
    private String date;
    private long matrecule;
    private int nbrConsultation;
    private String status;

    // Setters
    public void setCode(int code){
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
        return  "\n" + this.code +
                "\n" + this.date +
                "\n" + this.nbrConsultation +
                "\n" + this.matrecule +
                "\n" + this.status +
                "\n";

    }

    public void  displayConsultation(){
        println("\n -------------   Consultation -------------");
        print("Enter consultation Code");
        long matrecule = scan().nextLong();
        ArrayList<Dossier> dossiers =  controller.setDossierList(matrecule);
        print(dossiers.toString());
    }

}

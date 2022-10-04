package Medical.Folder.Consultation;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static helper.SystemeHelper.print;

public class ConsultationController {
    private int id;
    private long codeDossier;
    private int idSpecialite;
    private int montantPaye;
    private boolean isConjoint;
    private int numDocuments;
    ConsultationModel consultationModel;
    public ConsultationController() {
        this.consultationModel = new ConsultationModel();
    }
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

    /**
     * @param codeDossier
     * @return Array list of object Consultation
     */
    public ArrayList<Consultation> setConsultationByCode(int codeDossier) {
        ResultSet result = consultationModel.getConsultation(codeDossier);
        ArrayList<Consultation> consultations = new ArrayList<>();
        try {
            while (result.next()){
                Consultation newConsultation = new Consultation();

                newConsultation.setId(result.getInt("id"));
                newConsultation.setCodeDossier(result.getInt("code_dossier"));
                newConsultation.setIdSpecialite(result.getInt("id_specialite"));
                newConsultation.setMontantPaye(result.getInt("montant_paye"));
                newConsultation.setNumDocuments(result.getInt("nbr_documents"));
                newConsultation.setConjoint(result.getBoolean("is_conjoint"));
                consultations.add(newConsultation);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            consultationModel.closeQuery();
        }
        return consultations;
    }
}

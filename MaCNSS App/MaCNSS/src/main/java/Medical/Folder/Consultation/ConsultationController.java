package Medical.Folder.Consultation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultationController {
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

    /**
     *
     * @param codeDossier
     * @throws SQLException
     */
    public void setConsultationByCode(int codeDossier) {

        ConsultationModel newConsultation = new ConsultationModel();
        ResultSet result = newConsultation.getConsultation(codeDossier);
        try {
            setId(result.getInt("id")); ;
            setCodeDossier(result.getInt("code_dossier"));
            setIdSpecialite(result.getInt("id_specialite"));
            setMontantPaye(result.getInt("montant_paye"));
            setNumDocuments(result.getInt("nbr_documents"));
            setConjoint(result.getBoolean("is_conjoint"));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

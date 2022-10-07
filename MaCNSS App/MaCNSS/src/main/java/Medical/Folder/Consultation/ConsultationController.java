package Medical.Folder.Consultation;

import helper.Specialite;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public long createConsultation (long codeDossier, int idSpecialité, LocalDate date, boolean isConjoint, int montantPaye,  int numDocuments) {
        long id_consultation = -1;
        try{
            ResultSet resultSet = this.consultationModel.addConsultation(codeDossier, idSpecialité, date, isConjoint, montantPaye, numDocuments);
            if(resultSet != null){
                id_consultation = resultSet.getLong("id");
            }
                //close resultset and statement
            this.consultationModel.closeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return id_consultation;
    }

    public List<Specialite> getAllSpecialites () {
        List<Specialite> listSpecialite = new ArrayList<>();
        ResultSet result = consultationModel.getAllSpecialites();
        try{
            while(result.next()){
                Specialite specialite = new Specialite();
                specialite.id = result. getInt("id");
                specialite.nom = result.getString("nom");
                listSpecialite.add(specialite);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            //close resultset and statement
            consultationModel.closeQuery();
            return listSpecialite;
        }
    }

    public boolean checkDateValidity(LocalDate date) {
        int daysOfValidity = 60;
        if(date.until(LocalDate.now(), ChronoUnit.DAYS) > daysOfValidity){
            return true;
        }
        return false;
    }

    public void closeDBConnection () {
        this.consultationModel.closeDBConnection();
    }


}

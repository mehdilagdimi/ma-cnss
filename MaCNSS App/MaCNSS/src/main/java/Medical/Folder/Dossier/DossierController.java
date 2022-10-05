package Medical.Folder.Dossier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DossierController {

    private final DossierModel dossierModel;



    //Constructor
    public DossierController(){
        this.dossierModel = new DossierModel();
    }
    public ArrayList<Dossier> setDossierList(long matrecule) {
        ResultSet result = dossierModel.getDossierByMatrecule(matrecule);
        ArrayList<Dossier> dossiers = new ArrayList<>();
        try {
            while (result.next()){
                Dossier  newDossier = new Dossier();

                newDossier.setCode(result.getInt("code"));
                newDossier.setDate(result.getString("date"));
                newDossier.setMatrecule(result.getInt("id_matricule_patient"));
                newDossier.setNbrConsultation(result.getInt("nbr_consultation"));
                newDossier.setStatus(result.getString("etat"));
                dossiers.add(newDossier);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dossierModel.closeQuery();
        }
        return dossiers;
    }

    public ArrayList<Dossier> getAllDossiers() {
        ResultSet result = dossierModel.getAllDossiers();
        ArrayList<Dossier> dossiers = new ArrayList<>();
        try {
            while (result.next()){
                Dossier  newDossier = new Dossier();

                newDossier.setCode(result.getInt("code"));
                newDossier.setDate(result.getString("date"));
                newDossier.setMatrecule(result.getInt("id_matricule_patient"));
                newDossier.setNbrConsultation(result.getInt("nbr_consultation"));
                newDossier.setStatus(result.getString("etat"));
                dossiers.add(newDossier);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dossierModel.closeQuery();
        }
        return dossiers;
    }

    public void addNewDossier(long idMatricule, int nbrConsultation) {
        dossierModel.addNewDossier(idMatricule,nbrConsultation);
    }
}

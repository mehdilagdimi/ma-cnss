package Medical.Folder.Dossier;

import Medical.Folder.Consultation.Consultation;
import Medical.Folder.Document.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static helper.SystemeHelper.println;

public class DossierController {

    private final DossierModel dossierModel;



    //Constructor
    public DossierController(){
        this.dossierModel = new DossierModel();
    }
    public ArrayList<Dossier> setDossierList(long matrecule) {
        ResultSet result = dossierModel.getDossiersByMatrecule(matrecule);
        ArrayList<Dossier> dossiers = new ArrayList<>();
        try {
            while (result.next()){
                Dossier  newDossier = new Dossier();

                newDossier.setCode(result.getLong("code"));
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

    public Dossier getDossierByCode(long matrecule, long code) {
        ResultSet result = dossierModel.fetchDossierByCode(matrecule,code);
        Dossier dossiers = new Dossier();
        try {
            while (result.next()) {
                dossiers.setCode(result.getInt("code"));
                dossiers.setDate(result.getString("date"));
                dossiers.setMatrecule(result.getInt("id_matricule_patient"));
                dossiers.setNbrConsultation(result.getInt("nbr_consultation"));
                dossiers.setStatus(result.getString("etat"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dossierModel.closeQuery();
        }
        return dossiers;
    }

    public boolean updateDossierStatus(String status, long code){
        return dossierModel.updateDossierStatus(status,code);
    }

    public long addNewDossier(long idMatricule, int nbrConsultation) {
        return dossierModel.addNewDossier(idMatricule,nbrConsultation);

    }

    public void processDossier(Dossier dossier, List<Consultation> listConsultations){        //Map containing valid consultations (key is TRUE in Map)  and non valid consultations (key is FALSE in Map)
        Map<Boolean, List<Consultation>> mapOfValidAndNonValidConsultations =  listConsultations.stream().collect(Collectors.groupingBy((consultation) -> consultation.getController().checkDateValidity(consultation.getDate())));

        println("printing map of consultations : " + mapOfValidAndNonValidConsultations.toString());

        Map<Boolean, List<Document>> mapOfRefundableAndNotDocuments;

        for(Consultation validConsultation : mapOfValidAndNonValidConsultations.get(true)){
            mapOfRefundableAndNotDocuments =  validConsultation.getListDocuments().stream().collect(Collectors.groupingBy((document -> document.getController().checkIfRefundable(document.getType(), document.getNom()))));
            dossier.totalRefund += validConsultation.getController().setRefundsPrice(validConsultation.getIdSpecialite());
            println("printing map of documents :" + mapOfRefundableAndNotDocuments.toString());
            if(mapOfRefundableAndNotDocuments.get(true) != null){
                for(Document document : mapOfRefundableAndNotDocuments.get(true)){
                    dossier.totalRefund += document.getMontantPaye() * document.getPercentage() / 100;
                }
            }
        }
        dossier.controller.saveTotalRefunds(dossier.getCode(), dossier.totalRefund);
    }

    public void saveTotalRefunds(long code, float totalRefunds){
        dossierModel.saveTotalRefunds(code, totalRefunds);
    }
}

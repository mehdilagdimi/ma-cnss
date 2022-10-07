package Medical.Folder.Dossier;

import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.SystemeHelper.println;

public class DossierModel {

    protected DBConnection db = new DBConnection();
    ResultSet result = null;

    public DossierModel(){
        db.establishConnection();
    }
    /**
     * Check if Dossier exist using the parameter giving
     * @param matrecule
     * @return Boolean
     */
    public Boolean checkDossierExist (long matrecule) {
        String query = "SELECT * FROM consultation WHERE code_dossier = ?";
        if(db.prepare(query)){
            db.setParam(1, matrecule);
            db.execute();
        }
        return db.isEmpty();
    }

    /**
     * fetch dossier by executing a query statement that takes parameter long "matrecule"
     * @param matrecule long
     * @return result ResultSet
     */
    public ResultSet getDossiersByMatrecule(long matrecule) {
        ResultSet result = null;
        String query = "SELECT * FROM dossier WHERE id_matricule_patient = ?";
        if (db.prepare(query)) {
            db.setParam(1, matrecule);
            result = db.execute();
            this.result = result;

        } else {
            System.out.print("No result found");
        }

        return result;
    }
    public ResultSet fetchDossierByCode(long matrecule,long code) {
        ResultSet result = null;
        String query = "SELECT * FROM dossier WHERE id_matricule_patient = ? AND code = ?";
        if (db.prepare(query)) {
            db.setParam(1, matrecule);
            db.setParam(2, code);
            result = db.execute();
            this.result = result;
            return result;
        } else {
            System.out.print("No result found");
        }

        return result;
    }

    public ResultSet getAllDossiers() {
        ResultSet result = null;
        String query = "SELECT * FROM dossier";
        if (db.prepare(query)) {
            result = db.execute();
            this.result = result;
            return result;
        } else {
            System.out.print("No result found");
        }

        return result;
    }

    public void closeQuery () {
        db.closeQueryOperations();
    }

    public long addNewDossier(long idMatricule, int nbrConsultation) {
        long codeDossier = -1;
        String query = "INSERT INTO dossier (id_matricule_patient , nbr_consultation) values (?,?) RETURNING code";
        try {
            if (db.prepare(query)) {
                db.setParam(1, idMatricule);
                db.setParam(2, nbrConsultation);
                ResultSet result = db.execute();
                if (result.next()) {
                    println("Dossier ajouter avec succes");
                    codeDossier = result.getLong("code");
//            if (db.executeUpdate() != 0){
//
                } else {
                    println("Ajout échouer");
                }
            } else {
                println("Execution echoué !!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return codeDossier;
    }
     public int saveTotalRefunds(long codeDossier, float totalRefunds) {
        int count = 0;
        println("CODE DOSSIER A MIS A JOUR :" + String.valueOf(codeDossier));
        String query = "UPDATE dossier SET totalrefunds = ? WHERE code = ?";
            if (db.prepare(query)) {
                db.setParam(1, totalRefunds);
                db.setParam(2, codeDossier);
            if (db.executeUpdate() != 0){
                println("Dossier Update Succes");
            } else {
                println("Dossier Update failed");
            }
            } else {
                println("Execution echoué !!");
            }
        return count;
    }

    public boolean updateDossierStatus(String status, long code){
        String query = "UPDATE dossier SET etat = ?::folderstatus  WHERE code = ?";
        if (db.prepare(query)){
            db.setParam(1,status);
            db.setParam(2,code);
            if (db.executeUpdate() != 0){
                println("Dossier modifier avec succes");
                return true;
            }else {
                println("Modification échouer");
                return false;
            }
        }else {
            println("Execution echoué !!");
        }
        return false;
    }



}

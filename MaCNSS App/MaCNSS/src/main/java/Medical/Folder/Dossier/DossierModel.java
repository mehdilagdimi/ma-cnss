package Medical.Folder.Dossier;

import Database.DBConnection;

import java.sql.ResultSet;

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
    public ResultSet getDossierByMatrecule(long matrecule) {
        ResultSet result = null;
        String query = "SELECT * FROM dossier WHERE id_matricule_patient = ?";
        if (db.prepare(query)) {
            db.setParam(1, matrecule);
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
}

package Medical.Folder.Document;

import Database.DBConnection;

import java.sql.ResultSet;

public class DocumentModel {
    protected DBConnection db = new DBConnection();
    ResultSet result = null;

    public DocumentModel(){
        db.establishConnection();
    }
    /**
     * Check if consultation exist using the parameter giving
     * @param idConsultation int
     * @return Boolean
     */
    public Boolean checkConsultationExist (int idConsultation) {
        String query = "SELECT * FROM consultation WHERE code_dossier = ?";
        if(db.prepare(query)){
            db.setParam(1, idConsultation);
            db.execute();
        }
        return db.isEmpty();
    }

    /**
     * fetch consultation executing a query statement
     * @param idConsultation int
     * @return ResultSet
     */
    public ResultSet getDocuments(int idConsultation) {

        String query = "SELECT * FROM document WHERE id_consultation = ?";
        if (db.prepare(query)) {
            db.setParam(1, idConsultation);
            this.result = db.execute();
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

package Medical.Folder.Document;

import Database.DBConnection;

import java.sql.ResultSet;
import java.time.LocalDate;

public class DocumentModel {
    private String table;
    protected DBConnection db = new DBConnection();
    ResultSet result = null;

    public DocumentModel(){
        db.establishConnection();
        table = "document";
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

    public boolean addDocument (long idConsultation, LocalDate date, float montantPaye, String type) {
        boolean success = false;
        String query = "INSERT INTO " + table + " (id_consultation, date, type, montant_paye) VALUES (?, ?, ?::typedocument, ?)";
        if(db.prepare(query)){
            db.setParam(1, idConsultation);
            db.setParam(2, date);
            db.setParam(3, type);
            db.setParam(4, montantPaye);

            if(db.executeUpdate() > 0){
                success = true;
            };
        }
        return success;
    }

    public void closeQuery () {
        db.closeQueryOperations();
    }
}

package Medical.Folder.Document;

import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
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
     * Check if document exist using the parameter giving
     * @param idConsultation int
     * @return Boolean
     */
    public Boolean checkDocumentExist (int idConsultation) {
        String query = "SELECT * FROM "+ table +" WHERE id_consultation = ?";
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

    public ResultSet addDocument (long idConsultation, LocalDate date, float montantPaye, String type) throws SQLException {
        ResultSet resultSet = null;
        String query = "INSERT INTO " + table + " (id_consultation, date, type, montant_paye) VALUES (?, ?, ?::typedocument, ?) RETURNING id";
        if(db.prepare(query)){
            db.setParam(1, idConsultation);
            db.setParam(2, date);
            db.setParam(3, type);
            db.setParam(4, montantPaye);
            resultSet = db.execute();
            if(resultSet.next()){
                return resultSet;
            };
        }
        return resultSet;
    }
    public ResultSet getDocumentWithName (String table, String nom) throws  SQLException{
        ResultSet result = null;
        String query = "SELECT * FROM " + table + " WHERE nom = ? LIMIT 1";
        if (db.prepare(query)) {
            db.setParam(1, nom);
            result = db.execute();
            if(result.next()){
                return result;
            };
        } else {
            System.out.print("No result found");
        }
        return result;
    }
    public void closeQuery () {
        db.closeQueryOperations();
    }
}

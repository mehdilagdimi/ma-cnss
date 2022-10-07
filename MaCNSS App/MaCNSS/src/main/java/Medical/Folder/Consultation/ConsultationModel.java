package Medical.Folder.Consultation;

import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static helper.SystemeHelper.print;

public class ConsultationModel {

    protected  DBConnection db = new DBConnection();
    ResultSet  result = null;
    public String table;
    public ConsultationModel(){
        db.establishConnection();
        table = "consultation";
    }
    /**
     * Check if consultation exist using the parameter giving
     * @param codeDossier
     * @return Boolean
     */
    public Boolean checkConsultationExist (int codeDossier) {
        String query = "SELECT * FROM consultation WHERE code_dossier = ?";
        if(db.prepare(query)){
            db.setParam(1, codeDossier);
            db.execute();
        }
        return db.isEmpty();
    }

    /**
     * fetch consultation executing a query statement
     * @param codeDossier
     * @return ResultSet
     */
    public ResultSet getConsultation(int codeDossier) {
        ResultSet result = null;
        String query = "SELECT * FROM consultation WHERE code_dossier = ?";
        if (db.prepare(query)) {
            db.setParam(1, codeDossier);
            result = db.execute();
                this.result = result;
                return result;
            } else {
                System.out.print("No result found");
            }

        return result;
    }

    public ResultSet addConsultation (long codeDossier, int id_specialite, LocalDate date, boolean isConjoint, int montantPaye, int numDocuments) throws SQLException{
        ResultSet result = null;
        String query = "INSERT INTO " + table + " (code_dossier,  id_specialite, date, is_conjoint, nbr_documents, montant_paye) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        if(db.prepare(query)){
            db.setParam(1, codeDossier);
            db.setParam(2, id_specialite);
            db.setParam(3, date);
            db.setParam(4, isConjoint);
            db.setParam(5, numDocuments);
            db.setParam(6, montantPaye);
            result = db.execute();
            if(result.next()){
                return result;
            };
        }
        return result;
    }

    public ResultSet getAllSpecialites() {
        ResultSet result = null;
        String query = "SELECT * FROM specialite ORDER BY id";
        if (db.prepare(query)) {
            result = db.execute();
            return result;
        } else {
            System.out.print("No result found");
        }

        return result;
    }

    public void closeDBConnection () {
        db.closeConnection();
        System.out.println("Closed DB conn");
    }

    public void closeQuery () {
        db.closeQueryOperations();
    }
//    @Override
//    public String toString(){
//        return print(this.id + "\n" + code_dossier +"\n" +  id_specialite +"\n" +  montant_paye +"\n" +  date +"\n" + nbr_documents);
//    }
}

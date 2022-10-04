package Medical.Folder.Consultation;

import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import static helper.SystemeHelper.print;

public class ConsultationModel {

    protected  DBConnection db = new DBConnection();
    ResultSet  result = null;

    public ConsultationModel(){
        db.establishConnection();
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


    public void closeQuery () {
        db.closeQueryOperations();
    }
//    @Override
//    public String toString(){
//        return print(this.id + "\n" + code_dossier +"\n" +  id_specialite +"\n" +  montant_paye +"\n" +  date +"\n" + nbr_documents);
//    }
}

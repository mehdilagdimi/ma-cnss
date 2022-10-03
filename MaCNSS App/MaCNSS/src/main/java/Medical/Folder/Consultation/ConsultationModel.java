package Medical.Folder.Consultation;

import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        try {
            if (db.prepare(query)) {
                db.setParam(1, codeDossier);
                result = db.execute();
                if(result.next()){
                    this.result = result;
                    return result;
                } else {
                    System.out.print("No result found");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) throws SQLException {
        ConsultationModel newConsultation = new ConsultationModel();
        ResultSet result = newConsultation.getConsultation(1);

            int id = result.getInt("id");
            int code_dossier = result.getInt("code_dossier");
            int id_specialite = result.getInt("id_specialite");
            int montant_paye = result.getInt("montant_paye");
            int nbr_documents = result.getInt("nbr_documents");
            String date = result.getString("date");


        System.out.println(id + "\n" + code_dossier +"\n" +  id_specialite +"\n" +  montant_paye +"\n" +  date +"\n" + nbr_documents);
    }
//    @Override
//    public String toString(){
//        return "Consultation" + result. ;
//    }
}

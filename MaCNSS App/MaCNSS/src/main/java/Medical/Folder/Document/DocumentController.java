package Medical.Folder.Document;

import Medical.Folder.Document.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static helper.SystemeHelper.println;

public class DocumentController {

    private final DocumentModel documentModel;
    public DocumentController(){
         this.documentModel = new DocumentModel();
    }

    public ArrayList<Document> setDocuments(int idConsultation) {
        ResultSet result = documentModel.getDocuments(idConsultation);
        ArrayList<Document> documents = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try {
            while (result.next()){
                Document  newDoc = new Document();

                newDoc.setId(result.getInt("id"));
                newDoc.setDate(LocalDate.parse(result.getString("date"), formatter));
                newDoc.setMontantPaye(result.getFloat("id_consultation"));
                newDoc.setIdConsultation(result.getInt("montant_paye"));
                newDoc.setType(result.getString("type"));
                documents.add(newDoc);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            documentModel.closeQuery();
        }
        return documents;
    }

    public long createDocument(long idConsultation, LocalDate date, float montantPaye, String type) {
        long id = -1;
        try {
            ResultSet result = this.documentModel.addDocument(idConsultation, date, montantPaye, type);
            if(result != null){
                id = result.getLong("id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            //close resultset and statement
            this.documentModel.closeQuery();
            return id;
        }
    }

    public boolean checkIfRefundable (String type, String nom) {
        ResultSet result;
        String table;
        boolean isRefundable = false;
        try {
            switch (type) {
                case "MEDICAMENT":
                    table = "medicament";
                    result = documentModel.getDocumentWithName(table, nom);
                    if (result != null) {
                        isRefundable = result.getBoolean("is_remboursable");
                    }
                    break;
                case "ANALYSE":
                    table = "analysemedicale";
                    result = documentModel.getDocumentWithName(table, nom);
                    if (result != null) {
                        isRefundable = result.getBoolean("is_remboursable");
                    }
                    break;
                case "IMAGERIE":
                    table = "imagerie";
                    result = documentModel.getDocumentWithName(table, nom);
                    if (result != null) {
                        isRefundable = result.getBoolean("is_remboursable");
                    }
                    break;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            documentModel.closeQuery();
            return isRefundable;
        }
    }

    public float getRefundPercentage (String type, String nom) {
        ResultSet result;
        String table;
        float percentage = 0;
        try {
            switch (type) {
                case "MEDICAMENT":
                    table = "medicament";
                    result = documentModel.getDocumentWithName(table, nom);
                    if (result != null) {
                        percentage = result.getFloat("taux");
                    }
                    break;
                case "ANALYSE":
                    table = "analysemedicale";
                    result = documentModel.getDocumentWithName(table, nom);
                    if (result != null) {
                        percentage = result.getFloat("taux");
                    }
                    break;
                case "IMAGERIE":
                    table = "imagerie";
                    result = documentModel.getDocumentWithName(table, nom);
                    if (result != null) {
                        percentage = result.getFloat("taux");
                    }
                    break;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            documentModel.closeQuery();
            return percentage;
        }
    }

}

package Medical.Folder.Document;

import Medical.Folder.Document.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentController {

    private final DocumentModel documentModel;
    public DocumentController(){
         this.documentModel = new DocumentModel();
    }

    public ArrayList<Document> setDocuments(int idConsultation) {
        ResultSet result = documentModel.getDocuments(idConsultation);
        ArrayList<Document> documents = new ArrayList<>();
        try {
            while (result.next()){
                Document  newDoc = new Document();

                newDoc.setId(result.getInt("id"));
                newDoc.setDate(result.getString("date"));
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

}

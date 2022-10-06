package Medical.Folder.Document;

import Medical.Folder.Document.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
                String date = "16/08/2016";
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

    public boolean createDocument(long idConsultation, LocalDate date, float montantPaye, String type) {
        boolean isSuccessful = false;
        isSuccessful = this.documentModel.addDocument(idConsultation, date,montantPaye, type);
        //close resultset and statement
        this.documentModel.closeQuery();
        return isSuccessful;
    }

}

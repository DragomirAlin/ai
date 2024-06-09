package ro.dragomiralin.chatwithdocuments.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentImportService {
    private final VectorStore vectorStore;

    /**
     * This method is used to import a document into the vector store.
     * It takes a MultipartFile as input, which is the document to be imported.
     * Convert the MultipartFile into a Resource.
     * It then reads the document from the Resource and adds it to the vector store.
     *
     * @param file This is the document to be imported, represented as a MultipartFile.
     * @throws RuntimeException if there is an error during the import process.
     */
    public void importDocument(MultipartFile file) {
        log.info("Importing document {}", file.getOriginalFilename());
        try {
            Resource resource = new ByteArrayResource(file.getBytes());
            List<Document> documents = readDocuments(resource);
            vectorStore.add(documents);

            log.info("Document imported successfully");
        } catch (Exception e) {
            log.error("Error importing document", e);
            throw new RuntimeException("Error importing document");
        }
    }

    public List<Document> readDocuments(Resource resource) {
        PagePdfDocumentReader reader = new PagePdfDocumentReader(resource);
        return new ArrayList<>(reader.get());
    }

}

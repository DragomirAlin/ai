package ro.dragomiralin.chatwithdocuments.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import ro.dragomiralin.chatwithdocuments.model.ChatDocRequest;
import ro.dragomiralin.chatwithdocuments.model.PromptTemplate;

import java.util.stream.Collectors;

@Service
public class DocumentChatService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public DocumentChatService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    /**
     * This method is used to chat with a document. It takes a ChatDocRequest as input and returns a String as output.
     * The method performs a similarity search on the vector store using the prompt from the ChatDocRequest.
     * The top 5 similar documents are retrieved, and their contents are joined together with a line separator.
     * This combined document content is then used as a parameter in a chat client prompt.
     * The chat client prompt also includes the original prompt from the ChatDocRequest.
     * The chat client then makes a call and returns the content of the response.
     *
     * @param chatDocRequest This is a request object which contains the prompt for the chat.
     * @return String This returns the content of the response from the chat client.
     */
    public String chatWithDocument(ChatDocRequest chatDocRequest) {
        String documentContents = vectorStore.similaritySearch(SearchRequest.query(chatDocRequest.prompt())
                .withTopK(5))
                .stream()
                .map(Document::getContent)
                .collect(Collectors.joining(System.lineSeparator()));

        return chatClient.prompt()
                .system(systemSpec -> systemSpec
                        .text(PromptTemplate.CHAT_DOCUMENTS)
                        .param("documentsContent", documentContents))
                .user(chatDocRequest.prompt())
                .call()
                .content();
    }
}

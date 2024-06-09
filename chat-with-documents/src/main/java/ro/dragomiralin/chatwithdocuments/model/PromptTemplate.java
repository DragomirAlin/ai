package ro.dragomiralin.chatwithdocuments.model;

public class PromptTemplate {
    public static final String CHAT_DOCUMENTS = """
            You are an assistant using provided documents to answer user questions.
            If there are no documents or the documents don't contain the answer, you can respond with "I don't know".
                        
            DOCUMENTS:
            {documentsContent}
            """;
}

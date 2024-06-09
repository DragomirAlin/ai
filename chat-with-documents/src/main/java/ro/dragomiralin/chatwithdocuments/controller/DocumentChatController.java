package ro.dragomiralin.chatwithdocuments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dragomiralin.chatwithdocuments.model.ChatDocRequest;
import ro.dragomiralin.chatwithdocuments.service.DocumentChatService;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentChatController {
    private final DocumentChatService documentChatService;

    @PostMapping("/chat")
    public String chatWithDocument(@RequestBody ChatDocRequest chatDocRequest) {
        return documentChatService.chatWithDocument(chatDocRequest);
    }
}

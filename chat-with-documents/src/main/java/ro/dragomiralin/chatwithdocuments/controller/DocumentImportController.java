package ro.dragomiralin.chatwithdocuments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.dragomiralin.chatwithdocuments.service.DocumentImportService;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentImportController {
    private final DocumentImportService documentImportService;

    @PostMapping("/import")
    public void importDocument(@RequestParam("file") MultipartFile file) {
        documentImportService.importDocument(file);
    }
}

# Play


1. Start the Llama:
```bash
ollama run llama3
```

2. Start the application

3. Import file
```shell
curl --location 'http://localhost:8080/documents/import' \
--form 'file=@"/chat-with-documents/src/main/resources/Factură CFR Călători - Dragomir Alin.pdf"'
```

4. Chat with the documents
```shell
curl --location 'http://localhost:8080/documents/chat' \
--data '{
    "prompt": "Tell me about invoice with number 9923215?"
}'
```



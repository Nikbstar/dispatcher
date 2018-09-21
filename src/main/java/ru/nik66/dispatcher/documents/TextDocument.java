package ru.nik66.dispatcher.documents;

public class TextDocument extends AbstractDocument {

    public TextDocument(String name, PageSize pageSize, long printDuration) {
        super(name, pageSize, printDuration);
    }

    public DocumentType getType() {
        return DocumentType.TXT;
    }

}

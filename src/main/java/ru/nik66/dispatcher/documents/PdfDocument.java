package ru.nik66.dispatcher.documents;

public class PdfDocument extends AbstractDocument {

    public PdfDocument(String name, PageSize pageSize, long printDuration) {
        super(name, pageSize, printDuration);

    }

    public DocumentType getType() {
        return DocumentType.PDF;
    }

}

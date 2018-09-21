package ru.nik66.dispatcher.documents;

public class Picture extends AbstractDocument {

    public Picture(String name, PageSize pageSize, long printDuration) {
        super(name, pageSize, printDuration);
    }

    public DocumentType getType() {
        return DocumentType.PIC;
    }

}

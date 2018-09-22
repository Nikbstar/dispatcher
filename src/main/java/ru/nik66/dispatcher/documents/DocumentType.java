package ru.nik66.dispatcher.documents;

public enum DocumentType {

    PDF("PDF document"),
    PIC("Picture"),
    TXT("Text document");

    private String name;

    DocumentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

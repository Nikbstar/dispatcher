package ru.nik66.dispatcher.documents;

public enum DocumentType {

    TXT("Text document"),
    PDF("PDF document"),
    PIC("Picture");

    private String name;

    DocumentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

package ru.nik66.dispatcher.documents;

/**
 * Список типов документов.
 */
public enum DocumentType {

    PDF("PDF document"),
    PIC("Picture"),
    TXT("Text document");

    /**
     * Описание типа документа.
     */
    private String description;

    /**
     * Конструктор, принимающий описание типа документа.
     * @param description описание типа документа.
     */
    DocumentType(String description) {
        this.description = description;
    }

    /**
     * Геттер для описания типа документа.
     * @return описание типа документа.
     */
    public String getDescription() {
        return this.description;
    }

}

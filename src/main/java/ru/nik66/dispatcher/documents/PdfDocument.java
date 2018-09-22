package ru.nik66.dispatcher.documents;

/**
 * Печать pdf документов.
 */
public class PdfDocument extends AbstractDocument {

    /**
     * Передает в абстрактный класс данные
     * @param name имя документа.
     * @param pageSize размер листа.
     * @param printDuration продолжительность печати документа.
     */
    public PdfDocument(String name, PageSize pageSize, long printDuration) {
        super(name, pageSize, printDuration);

    }

    /**
     * Геттер для типа документа.
     * @return тип документа.
     */
    public DocumentType getType() {
        return DocumentType.PDF;
    }

}

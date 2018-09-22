package ru.nik66.dispatcher.documents;

/**
 * Печать документов типа текст.
 */
public class TextDocument extends AbstractDocument {

    /**
     * Передает в абстрактный класс данные
     * @param name имя документа.
     * @param pageSize размер листа.
     * @param printDuration продолжительность печати документа.
     */
    public TextDocument(String name, PageSize pageSize, long printDuration) {
        super(name, pageSize, printDuration);
    }

    /**
     * Геттер для типа документа.
     * @return тип документа.
     */
    public DocumentType getType() {
        return DocumentType.TXT;
    }

}

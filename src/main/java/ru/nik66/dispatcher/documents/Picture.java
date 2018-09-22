package ru.nik66.dispatcher.documents;

/**
 * Печать документов типа изображение.
 */
public class Picture extends AbstractDocument {

    /**
     * Передает в абстрактный класс данные
     * @param name имя документа.
     * @param pageSize размер листа.
     * @param printDuration продолжительность печати документа.
     */
    public Picture(String name, PageSize pageSize, long printDuration) {
        super(name, pageSize, printDuration);
    }

    /**
     * Геттер для типа документа.
     * @return тип документа.
     */
    public DocumentType getType() {
        return DocumentType.PIC;
    }

}

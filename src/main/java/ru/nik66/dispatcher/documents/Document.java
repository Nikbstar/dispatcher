package ru.nik66.dispatcher.documents;

/**
 * Интерфейс описывающий документы.
 */
public interface Document {

    /**
     * Геттер для родолдительность печати.
     * @return продолжительность печати.
     */
    long getPrintDuration();

    /**
     * Геттер для типа докуметна.
     * @return тип документа.
     */
    DocumentType getType();

    /**
     * Геттер для размера страницы.
     * @return размер страницы.
     */
    PageSize getPageSize();

    /**
     * Геттер для имени документа.
     * @return имя документа.
     */
    String getName();

}

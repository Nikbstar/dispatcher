package ru.nik66.dispatcher.documents;

/**
 * Абстрактный класс, реализующий общие методы документов.
 */
public abstract class AbstractDocument implements Document {

    /**
     * Продолжительность печати.
     */
    private long printDuration;
    /**
     * Размер страницы.
     */
    private PageSize pageSize;
    /**
     * Имя документа.
     */
    private String name;

    /**
     * Конструктор для всех полей.
     * @param name Имя документа.
     * @param pageSize Размер страницы.
     * @param printDuration Продолжительность печати.
     */
    public AbstractDocument(String name, PageSize pageSize, long printDuration) {
        this.printDuration = printDuration;
        this.pageSize = pageSize;
        this.name = name;
    }

    /**
     * Геттер для продолжительности документа.
     * @return продолжительность документа.
     */
    public long getPrintDuration() {
        return this.printDuration;
    }

    /**
     * Геттер для размера страницы.
     * @return размер страницы.
     */
    public PageSize getPageSize() {
        return this.pageSize;
    }

    /**
     * Геттер для имени документа.
     * @return имя документа.
     */
    public String getName() {
        return this.name;
    }


}

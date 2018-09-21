package ru.nik66.dispatcher.documents;

public abstract class AbstractDocument implements Document {

    private long printDuration;
    private PageSize pageSize;
    private String name;

    public AbstractDocument(String name, PageSize pageSize, long printDuration) {
        this.printDuration = printDuration;
        this.pageSize = pageSize;
        this.name = name;
    }

    public long getPrintDuration() {
        return this.printDuration;
    }

    public PageSize getPageSize() {
        return this.pageSize;
    }

    public String getName() {
        return this.name;
    }


}

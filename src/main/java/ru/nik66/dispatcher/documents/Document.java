package ru.nik66.dispatcher.documents;

public interface Document {

    long getPrintDuration();
    DocumentType getType();
    PageSize getPageSize();
    String getName();

}

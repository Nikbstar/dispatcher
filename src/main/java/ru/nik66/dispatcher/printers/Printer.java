package ru.nik66.dispatcher.printers;

import ru.nik66.dispatcher.documents.Document;

public interface Printer {

    void print(Document document) throws InterruptedException;

}

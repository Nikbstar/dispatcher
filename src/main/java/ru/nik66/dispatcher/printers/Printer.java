package ru.nik66.dispatcher.printers;

import ru.nik66.dispatcher.documents.Document;

/**
 * Интерфейс для создания принтеров.
 */
public interface Printer {

    /**
     * Печать документа.
     * @param document Документ для печати.
     * @throws InterruptedException от PrintDuration.
     */
    void print(Document document) throws InterruptedException;

}

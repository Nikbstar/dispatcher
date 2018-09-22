package ru.nik66.dispatcher.printers;

import ru.nik66.dispatcher.documents.Document;

public class ConsolePrinter implements Printer {

    public void print(Document document) throws InterruptedException {
        String eol = System.getProperty("line.separator");
        System.out.printf("Printing document: %s%sDocument type: %s%sPage size: %s%sPrint duration: %s%s",
                document.getName(), eol,
                document.getType().getName(), eol,
                document.getPageSize(), eol,
                document.getPrintDuration(), eol
        );
        Thread.sleep(document.getPrintDuration());

    }

}

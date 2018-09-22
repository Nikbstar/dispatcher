package ru.nik66.dispatcher;

import ru.nik66.dispatcher.documents.*;
import ru.nik66.dispatcher.printers.ConsolePrinter;
import ru.nik66.dispatcher.printers.Printer;

public class StartUI {

    public static void main(String[] args) {
        Document pdf = new PdfDocument("Book", PageSize.A4, 1000);
        Document bigPdf = new PdfDocument("BigPage", PageSize.A3, 2000);
        Document jpeg = new Picture("SmallPhoto", PageSize.A5, 500);
        Document txt = new TextDocument("AnyText", PageSize.A4, 100);

        Printer printer = new ConsolePrinter();

        Dispatcher dispatcher = new Dispatcher(printer);

        dispatcher.putDocument(pdf);
        dispatcher.putDocument(bigPdf);
        dispatcher.putDocument(jpeg);
        dispatcher.putDocument(txt);

        dispatcher.print();
    }

}

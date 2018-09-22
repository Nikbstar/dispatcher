package ru.nik66.dispatcher.printers;

import ru.nik66.dispatcher.documents.Document;

import java.util.ArrayList;
import java.util.List;

public class TestPrinter implements Printer {

    private static List<String> prints = new ArrayList<>();

    @Override
    public void print(Document document) throws InterruptedException {
        prints.add(document.getName());
        Thread.sleep(document.getPrintDuration());
    }

    public List<String> getPrints() {
        return prints;
    }

}

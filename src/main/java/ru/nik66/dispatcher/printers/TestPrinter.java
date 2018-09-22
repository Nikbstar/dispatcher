package ru.nik66.dispatcher.printers;

import ru.nik66.dispatcher.documents.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Принтер для тестирования.
 */
public class TestPrinter implements Printer {

    /**
     * Лист напечатанного на данном принтере.
     */
    private static List<String> prints = new ArrayList<>();

    /**
     * Печать документа.
     * @param document Документ для печати.
     * @throws InterruptedException от PrintDuration.
     */
    @Override
    public void print(Document document) throws InterruptedException {
        prints.add(document.getName());
        Thread.sleep(document.getPrintDuration());
    }

    /**
     * Геттер для листа печати.
     * @return Лист напечатанных документов.
     */
    public List<String> getPrints() {
        return prints;
    }

}

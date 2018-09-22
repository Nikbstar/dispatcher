package ru.nik66.dispatcher;

import ru.nik66.dispatcher.documents.Document;
import ru.nik66.dispatcher.printers.Printer;

import java.util.Comparator;
import java.util.List;

public class Dispatcher {

    private PrintQueue printQueue = new PrintQueue();
    private Printer printer;

    public List<Document> stopPrinting() {
        return printQueue.stop();
    }

    public void putDocument(Document document) {
        this.printQueue.add(document);
    }

    public void print() {
        try {
            this.printQueue.print(this.printer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean cancelPrintDocument(Document document) {
        return printQueue.remove(document);
    }

    public List<Document> getPrinted(SortType sortBy) {
        List<Document> result;
        switch (sortBy) {
            case TYPE:
                result = printQueue.getPrinted(new Comparator<Document>() {
                    public int compare(Document o1, Document o2) {
                        return o1.getType().compareTo(o2.getType());
                    }
                });
                break;
            case DURATION:
                result = printQueue.getPrinted(new Comparator<Document>() {
                    public int compare(Document o1, Document o2) {
                        return Long.compare(o1.getPrintDuration(), o2.getPrintDuration());
                    }
                });
                break;
            case SIZE:
                result = printQueue.getPrinted(new Comparator<Document>() {
                    @Override
                    public int compare(Document o1, Document o2) {
                        return o1.getPageSize().compareTo(o2.getPageSize());
                    }
                });
                break;
            case NAME:
                result = printQueue.getPrinted(new Comparator<Document>() {
                    @Override
                    public int compare(Document o1, Document o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                break;
                default:
                    result = printQueue.getPrinted();
        }
        return result;
    }

    public long getAvgPrintDuration() {
        return printQueue.getAvgPrintDuration();
    }

    public Dispatcher(Printer printer) {
        this.printer = printer;
    }

}

package ru.nik66.dispatcher;

import ru.nik66.dispatcher.documents.Document;
import ru.nik66.dispatcher.printers.Printer;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintQueue {

    private Queue<Document> documents = new LinkedList<>();
    private List<Document> printedDocuments = new ArrayList<>();

    private ExecutorService executor = Executors.newCachedThreadPool();
    private final Object lock = new Object();
    private boolean printing = false;

    public void add(Document document) {
        this.documents.add(document);
    }

    private void waitPrinting() throws InterruptedException {
        synchronized (this.lock) {
            while (this.printing) {
                lock.wait();
            }
        }
    }

    public void print(final Printer printer) throws InterruptedException {
        waitPrinting();
        if (!documents.isEmpty()) {
            this.printing = true;
            this.executor.execute(new Runnable() {
                public void run() {
                    synchronized (lock) {
                        Document printingDocument = documents.poll();
                        try {
                            printer.print(printingDocument);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            printedDocuments.add(printingDocument);
                            printing = false;
                            lock.notifyAll();
                        }
                    }
                }
            });
        } else {
            this.printing = false;
        }
    }

    public List<Document> stop() {
        synchronized (this.lock) {
            List<Document> notPrinted = new ArrayList<>(this.documents);
            this.documents = new LinkedList<>();
            return notPrinted;
        }
    }

    public boolean remove(Document document) {
        synchronized (this.lock) {
            boolean result = false;
            if (this.documents.contains(document)) {
                this.documents.remove(document);
                result = true;
            }
            return result;
        }
    }

    public List<Document> getPrinted() {
        return new ArrayList<>(this.printedDocuments);
    }

    public List<Document> getPrinted(Comparator<Document> comparator) {
        List<Document> result = new ArrayList<>(this.printedDocuments);
        Collections.sort(result, comparator);
        return result;
    }

    public long getAvgPrintDuration() {
        long result = 0L;
        for (Document printedDocument : printedDocuments) {
            result += printedDocument.getPrintDuration();
        }
        return result;
    }

}

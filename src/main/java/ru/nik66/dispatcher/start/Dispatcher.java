package ru.nik66.dispatcher.start;

import ru.nik66.dispatcher.documents.Document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Dispatcher {

    private Queue<Document> documents = new LinkedList<Document>();
    private List<Document> printedDocuments = new ArrayList<Document>();

    public void print(Document newDocument) {
        this.documents.offer(newDocument);
        this.printing();
    }

    private void printing() {
        new Thread(new Runnable() {
            public void run() {
                if (!documents.isEmpty()) {
                    Document document = documents.poll();
                    System.out.printf("Printing the document: %s%s", document.getName());
                    try {
                        Thread.sleep(document.getPrintDuration());
                    } catch (InterruptedException e) {
                        System.out.println("Printing was aborted");
                    }
                    printedDocuments.add(document);
                    System.out.println("Doc");

                }
            }
        }).start();
    }

}

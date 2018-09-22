package ru.nik66.dispatcher;

import ru.nik66.dispatcher.documents.Document;
import ru.nik66.dispatcher.printers.Printer;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс отвечает за очередь печати.
 */
public class PrintQueue {

    /**
     * Очередь нераспечатанных документов.
     */
    private Queue<Document> documents = new LinkedList<>();
    /**
     * Очередь распечатанных документов.
     */
    private List<Document> printedDocuments = new ArrayList<>();

    /**
     * Экзекутор, запускает потоки для печати документов.
     */
    private ExecutorService executor = Executors.newCachedThreadPool();
    /**
     * Лок для синхронизации.
     */
    private final Object lock = new Object();
    /**
     * Показывает, идёт ли в данный момент печать документа.
     */
    private volatile boolean printing = false;
    /**
     * Зоворит потокам, что диспетчер печати остановлен.
     */
    private volatile boolean stop = false;

    /**
     * Добавить документ в очередь на печать.
     * @param document Документ для печати.
     */
    public void add(Document document) {
        this.documents.add(document);
    }

    /**
     * Печатать документ в отдельном потоке.
     * @param printer Принтер, на который необходимо напечатать документ.
     */
    private void print(final Printer printer) {
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
                    }
                }
            }
        });
    }

    /**
     * Останавливает диспетчер, обнуляет список печати и возвращает список нераспечатанных документов.
     * @return Лист нераспечатанных документов.
     */
    public List<Document> stop() {
        synchronized (this.lock) {
            this.stop = true;
            this.executor.shutdown();
            List<Document> notPrinted = new ArrayList<>(this.documents);
            this.documents = new LinkedList<>();
            return notPrinted;
        }
    }

    /**
     * Удалить документ из списка документов для печати.
     * @param document Документ, который необходимо удалить.
     * @return true если документ успешно удален, и false, если документ уже напечатан или не был добавлен на печать вообще.
     */
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

    /**
     * Получить список напечатанных документов, отсортированный по порядку печати.
     * @return Лист с напечатанными документами.
     */
    public List<Document> getPrinted() {
        return new ArrayList<>(this.printedDocuments);
    }

    /**
     * Получить список напечатанных документов, отсортированный по компоратору.
     * @param comparator Компоратор, по которому необходимо отсортировать список.
     * @return Отсортированный лист.
     */
    public List<Document> getPrinted(Comparator<Document> comparator) {
        List<Document> result = new ArrayList<>(this.printedDocuments);
        Collections.sort(result, comparator);
        return result;
    }

    /**
     * Получить среднюю продолжительность печати документов.
     * @return long Средняя продолжительность печати.
     */
    public long getAvgPrintDuration() {
        long result = 0L;
        int count = printedDocuments.size();
        for (Document printedDocument : printedDocuments) {
            result += printedDocument.getPrintDuration();
        }
        return result / count;
    }

    /**
     * Проверяет очередь на наличие документов и при наличии печатает их.
     * @param printer Принтер, на который необходимо печатать документ.
     */
    public void listener(final Printer printer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    synchronized (this) {
                        if (!printing && !documents.isEmpty()) {
                            print(printer);
                            try {
                                wait(documents.element().getPrintDuration());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}

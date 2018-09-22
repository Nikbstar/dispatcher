package ru.nik66.dispatcher;

import ru.nik66.dispatcher.documents.Document;
import ru.nik66.dispatcher.printers.Printer;

import java.util.Comparator;
import java.util.List;

/**
 * Диспетчер печати документов.
 */
public class Dispatcher {

    /**
     * Очередь печати.
     */
    private PrintQueue printQueue = new PrintQueue();

    /**
     * Остановить диспетчер печати.
     * @return Возвращает список нераспечатанных документов.
     */
    public List<Document> stopPrinting() {
        return printQueue.stop();
    }

    /**
     * Отправиль документ в очередь печати.
     * @param document Документ для печати.
     */
    public void putDocument(Document document) {
        this.printQueue.add(document);
    }

    /**
     * Отменить печать определенного документа.
     * @param document Документ, печать которого необходимо отменить.
     * @return true, если документ был успешно отменён и не напечатан.
     */
    public boolean cancelPrintDocument(Document document) {
        return printQueue.remove(document);
    }

    /**
     * Получить отсортированный список напечатанных документов.
     * @param sortBy Тип сортировки.
     * @return Отсортированный список с напечатанными документами.
     */
    public List<Document> getPrinted(SortType sortBy) {
        List<Document> result;
        switch (sortBy) {
            case TYPE:
                result = printQueue.getPrinted(new Comparator<Document>() {
                    public int compare(Document o1, Document o2) {
                        return o1.getType().name().compareTo(o2.getType().name());
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
                        return o1.getPageSize().name().compareTo(o2.getPageSize().name());
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

    /**
     * Получить среднюю продолжительность печати документов.
     * @return Средняя продолжительность печати.
     */
    public long getAvgPrintDuration() {
        return printQueue.getAvgPrintDuration();
    }

    /**
     * Конструктор принимает принтер, на который необходимо произвести печать.
     * @param printer Принтер.
     */
    public Dispatcher(Printer printer) {
        this.printQueue.listener(printer);
    }

}

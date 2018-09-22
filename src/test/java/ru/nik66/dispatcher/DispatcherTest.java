package ru.nik66.dispatcher;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.dispatcher.documents.*;
import ru.nik66.dispatcher.printers.TestPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class DispatcherTest {

    private TestPrinter printer;
    private Dispatcher dispatcher;

    @Before
    public void init() {
        this.printer = new TestPrinter();
        this.dispatcher = new Dispatcher(this.printer);
    }

    @Test
    public void whenAddToPrintDocumentThenThatOneBePrinted() throws Exception {
        Document pdf = new PdfDocument("Book", PageSize.A4, 1000);
        this.dispatcher.putDocument(pdf);
        Thread.sleep(500);
        this.dispatcher.stopPrinting();
        List<String> actual = this.printer.getPrints();
        List<String> expected = new ArrayList<>(Collections.singletonList("Book"));
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDispatcherWasStopThenPrintingIsStop() throws Exception {
        Document pdf = new PdfDocument("Book", PageSize.A4, 1000);
        Document bigPdf = new PdfDocument("BigPage", PageSize.A3, 2000);
        Document jpeg = new Picture("SmallPhoto", PageSize.A5, 500);
        this.dispatcher.putDocument(pdf);
        this.dispatcher.putDocument(bigPdf);
        this.dispatcher.putDocument(jpeg);
        Thread.sleep(1500);
        this.dispatcher.stopPrinting();
        List<String> actual = this.printer.getPrints();
        List<String> expected = new ArrayList<>(Arrays.asList("Book", "BigPage"));
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDispatcherWasStopThenReturnNotPrintedDocuments() throws Exception {
        Document pdf = new PdfDocument("Book", PageSize.A4, 1000);
        Document bigPdf = new PdfDocument("BigPage", PageSize.A3, 2000);
        this.dispatcher.putDocument(pdf);
        this.dispatcher.putDocument(bigPdf);
        Thread.sleep(500);
        List<Document> actual = this.dispatcher.stopPrinting();
        List<Document> expected = new ArrayList<>();
        expected.add(bigPdf);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCancelPrintingDocumentThenThatOneDidNotBePrinted() throws Exception {
        Document pdf = new PdfDocument("Book", PageSize.A4, 1000);
        Document bigPdf = new PdfDocument("BigPage", PageSize.A3, 2000);
        Document jpeg = new Picture("SmallPhoto", PageSize.A5, 500);
        this.dispatcher.putDocument(pdf);
        this.dispatcher.putDocument(bigPdf);
        this.dispatcher.putDocument(jpeg);
        Thread.sleep(500);
        this.dispatcher.cancelPrintDocument(bigPdf);
        Thread.sleep(1700);
        this.dispatcher.stopPrinting();
        List<String> actual = this.printer.getPrints();
        List<String> expected = new ArrayList<>(Arrays.asList("Book", "SmallPhoto"));
        assertThat(actual, is(expected));
    }

    @Test
    public void whenGetSortedPrintedDocumentsList() throws Exception {
        Document pdf = new PdfDocument("Book", PageSize.A4, 1000);
        Document bigPdf = new PdfDocument("BigPage", PageSize.A3, 2000);
        Document jpeg = new Picture("SmallPhoto", PageSize.A5, 500);
        Document txt = new TextDocument("AnyText", PageSize.A4, 100);
        this.dispatcher.putDocument(pdf);
        this.dispatcher.putDocument(bigPdf);
        this.dispatcher.putDocument(jpeg);
        this.dispatcher.putDocument(txt);
        Thread.sleep(4000);
        this.dispatcher.stopPrinting();
        List<Document> actual = this.dispatcher.getPrinted(SortType.STANDART);
        List<Document> expected = new ArrayList<>(Arrays.asList(pdf, bigPdf, jpeg, txt));
        assertThat(actual, is(expected));
        actual = this.dispatcher.getPrinted(SortType.NAME);
        expected = new ArrayList<>(Arrays.asList(txt, bigPdf, pdf, jpeg));
        assertThat(actual, is(expected));
        actual = this.dispatcher.getPrinted(SortType.DURATION);
        expected = new ArrayList<>(Arrays.asList(txt, jpeg, pdf, bigPdf));
        assertThat(actual, is(expected));
        actual = this.dispatcher.getPrinted(SortType.SIZE);
        expected = new ArrayList<>(Arrays.asList(bigPdf, pdf, txt, jpeg));
        assertThat(actual, is(expected));
        actual = this.dispatcher.getPrinted(SortType.TYPE);
        expected = new ArrayList<>(Arrays.asList(pdf, bigPdf, jpeg, txt));
        assertThat(actual, is(expected));
    }

    @Test
    public void whenGetAvgPrintTime() throws Exception {
        Document pdf = new PdfDocument("Book", PageSize.A4, 1000);
        Document bigPdf = new PdfDocument("BigPage", PageSize.A3, 2000);
        this.dispatcher.putDocument(pdf);
        this.dispatcher.putDocument(bigPdf);
        Thread.sleep(2000);
        this.dispatcher.stopPrinting();
        long actual = this.dispatcher.getAvgPrintDuration();
        long expected = 1500L;
        assertThat(actual, is(expected));
    }

}

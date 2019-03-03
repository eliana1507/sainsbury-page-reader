package eliana.cappello.unit.utilities;

import eliana.cappello.utilities.HtmlParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;

import java.io.File;

public class HtmlParserTest {
    final static String DUMMY_BASE_PATH = "src/test/java/eliana/cappello/unit/dummy/";

    static Document document;
    static Document productDocument;

    @BeforeAll
    public static void setUp() throws Exception {
        document = Jsoup.parse(new File(DUMMY_BASE_PATH + "dummy_product_parse_test.html"), "UTF-8");
        productDocument = Jsoup.parse(new File(DUMMY_BASE_PATH + "dummy_product_web_page.html"), "UTF-8");
    }

    @Test
    public void readTitleFromDocument() {
        Element element = document.getElementsByClass("product ").first();
        HtmlParser parser = new HtmlParser();

        Assertions.assertEquals("Dummy product Title", parser.getTitleFromElement(element), "Title did't match with value expected");
    }

    @Test
    public void titleValueIsEmpty() {
        Element element = document.getElementsByClass("product ").last();
        HtmlParser parser = new HtmlParser();

        Assertions.assertEquals("", parser.getTitleFromElement(element), "Title is not empty");
    }

    @Test
    public void readUnitPrice() {
        Element element = document.getElementsByClass("product ").first();
        HtmlParser parser = new HtmlParser();

        Assertions.assertEquals(8.99, parser.getUnitPriceFromElement(element), "Unit Price did't match with value expected");
    }

    @Test
    public void unitPriceDoNotContainsCurrencyAndPence() {
        Element element = document.getElementsByClass("product ").last();
        HtmlParser parser = new HtmlParser();

        Assertions.assertEquals(899.00, parser.getUnitPriceFromElement(element), "Unit Price did't match with value expected");
    }

    @Test
    public void readDescription() {
        Element element = document.getElementById("information");
        HtmlParser parser = new HtmlParser();

        Assertions.assertEquals("Dummy Description", parser.getDescriptionFromElement(element), "Description did't match with value expected");
    }

    @Test
    public void descriptionIsEmpty() {
        Element element = document.getElementById("empty_information");
        HtmlParser parser = new HtmlParser();

        Assertions.assertEquals("", parser.getDescriptionFromElement(element), "Description is not empty");
    }

    @Test
    public void readKcalValue() {
        Element element = document.getElementById("information");
        HtmlParser parser = new HtmlParser();

        Assertions.assertEquals(33, parser.getkcalFromElement(element), "Kcal did't match with value expected");
    }

    @Test
    public void kcalValueNotSpecified() {
        Element element = document.getElementById("empty_information");
        HtmlParser parser = new HtmlParser();

        Assertions.assertEquals(-1, parser.getkcalFromElement(element), "Kcal is specified");
    }

    @Test
    public void nutritionTableNotSpecified() {
        Element element = document.getElementById("empty_nutritionTable");
        HtmlParser parser = new HtmlParser();

        Assertions.assertEquals(-1, parser.getkcalFromElement(element), "nutritionTable is specified");
    }
}

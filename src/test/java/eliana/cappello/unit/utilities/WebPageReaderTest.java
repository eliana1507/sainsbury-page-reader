package eliana.cappello.unit.utilities;

import eliana.cappello.utilities.WebPageReader;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

@PrepareForTest({Jsoup.class, Connection.class})
public class WebPageReaderTest {
    private static String PRODUCT_LIST_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    final static String DUMMY_BASE_PATH = "src/test/java/eliana/cappello/unit/dummy/";

    private static WebPageReader reader;
    private static Document documentProductsList;
    private static Document documentSingleProduct;

    @Mock
    private static Connection connectionProductsList;

    @Mock
    private static Connection connectionSingleProduct;

    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static PrintStream originalOut = System.out;
    private static PrintStream originalErr = System.err;

    @BeforeAll
    public static void setup() throws Exception {
        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.mockStatic(Connection.class);
        documentProductsList = Jsoup.parse(new File(DUMMY_BASE_PATH + "dummy_web_page.html"), "UTF-8");
        documentSingleProduct = Jsoup.parse(new File(DUMMY_BASE_PATH + "dummy_product_web_page.html"), "UTF-8");

        connectionProductsList = Mockito.mock(Connection.class);
        connectionSingleProduct = Mockito.mock(Connection.class);

        Mockito.doReturn(connectionProductsList).when(Jsoup.connect(PRODUCT_LIST_URL));
        Mockito.doReturn(documentProductsList).when(connectionProductsList.get());

        Mockito.doReturn(connectionSingleProduct).when(Jsoup.connect(Mockito.anyString()));
        Mockito.doReturn(documentSingleProduct).when(connectionSingleProduct.get());

        reader = new WebPageReader();

        // Set the stream to check the results
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void runTest() {
        String[] args = {"-webpage" + PRODUCT_LIST_URL};
        reader.run(args);
        Assertions.assertTrue(outContent.toString().indexOf("result") > 0 , "The stream contains the result");
        Assertions.assertTrue(outContent.toString().indexOf("total") > 0 , "The stream contains the total");
    }

    @Test
    public void runWithSrongParameter() {
        String[] args = {"-page" + PRODUCT_LIST_URL};
        reader.run(args);

    }
}

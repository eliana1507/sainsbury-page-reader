package eliana.cappello.integration.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import eliana.cappello.utilities.WebPageReader;

public class WebPageReaderTest {

    private String webPage = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    @Test
    public void mainTest() {
        WebPageReader reader = new WebPageReader();
        String[] args = {"-webpage" + this.webPage};
        reader.run(args);
        Assertions.assertTrue(true);
    }
}

package eliana.cappello.utilities;

import java.io.IOException;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

import com.google.gson.Gson;
import eliana.cappello.models.Product;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLineParser;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebPageReader {

    final String BASE_PATH = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/";
    /**
     * Runs the application
     *
     * @param args an array of String arguments to be parsed
     */
    public void run(String[] args) {

        CommandLine line = parseArguments(args);

        if (line.hasOption("webpage")) {
            System.out.println(line.getOptionValue("webpage"));
            String webpage = line.getOptionValue("webpage");
            try {
                Gson gson = new Gson();

                final List<Product> products = readData(webpage);
                Map<String,Double> total = getTotal(products);

                Map result = new HashMap<>();
                result.put("result", products);
                result.put("total", total);

                String jsonInString = gson.toJson(result);

                System.out.println(jsonInString);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            printAppHelp();
        }
    }

    /**
     * Parses application arguments
     *
     * @param args
     * @return <code>CommandLine</code> which represents a list of parameters
     * arguments.
     */
    private CommandLine parseArguments(String[] args) {

        Options options = getOptions();
        CommandLine line = null;

        CommandLineParser parser = new DefaultParser();

        try {
            line = parser.parse(options, args);

        } catch (ParseException ex) {

            System.err.println(ex);
            printAppHelp();

            System.exit(1);
        }

        return line;
    }

    /**
     * Reads data from a url
     *
     * @param webPage
     * @return list of products from the web page
     * @throws IOException
     */
    private List<Product> readData(String webPage) throws IOException {
        List<Product> result = new LinkedList<>();
        HtmlParser parser = new HtmlParser();

        Connection connection = Jsoup.connect(webPage);
        connection.timeout(10000);
        Document doc = connection.get();
        Elements elementsProduct = doc.getElementsByClass("product ");

        for (Element element: elementsProduct) {
            Product product = new Product();

            // Here we sets the title and the unit price
            product.setTitle(parser.getTitleFromElement(element));
            product.setUnit_price(parser.getUnitPriceFromElement(element));

            // In order to get Description an kcal value, we get the page of the product
            Element h3 = element.getElementsByTag("h3").first();
            String productLink = h3.getElementsByTag("a").attr("href");

            productLink = productLink.replace("../", "");
            connection = Jsoup.connect(BASE_PATH + productLink);
            connection.timeout(10000);
            Document productDocument = connection.get();

            // get information section
            Element informationSection = productDocument.getElementById("information");
            product.setDescription(parser.getDescriptionFromElement(informationSection));

            // here we set the kcal value only if is specify in page
            if (parser.getkcalFromElement(informationSection) > 0) {
                product.setKcal_per_100g(parser.getkcalFromElement(informationSection));
            }

            result.add(product);
        }

        return result;
    }

    /**
     * Get the gross amount and the vat value of the list of products
     *
     * @param products products in the page
     * @return total gross price and vat value
     */
    private Map<String,Double> getTotal(List<Product> products) {
        Map<String,Double> total = new HashMap<String, Double>();
        double gross = 0.0;
        double vatPercentage = 0.2;

        for (Product product : products) {
            gross += product.getUnit_price();
        }

        total.put("gross", gross);
        total.put("vat", gross * vatPercentage);
        return total;
    }

    /**
     * Generates application command line options
     *
     * @return application <code>Options</code>
     */
    private Options getOptions() {

        Options options = new Options();

        options.addOption("w", "webpage", true,
                "webpage to load data from");
        return options;
    }

    /**
     * Prints application help
     */
    private void printAppHelp() {

        Options options = getOptions();

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("JavaStatsEx", options, true);
    }
}

package eliana.cappello.utilities;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

    public String getTitleFromElement(Element element) {
        Element h3 = element.getElementsByTag("h3").first();
        return h3.select("a").first().ownText();
    }

    public Double getUnitPriceFromElement(Element element) {
        Element unitPrice = element.getElementsByClass("pricePerUnit").first();
        String unitPriceText = unitPrice.ownText();
        unitPriceText = unitPriceText.trim().replace("£", "");
        return Double.valueOf(unitPriceText);
    }

    public String getDescriptionFromElement(Element element) {
        Element description = element.getElementsByClass("productText").first();
        return description.getElementsByTag("p").first() != null ?
                description.getElementsByTag("p").first().ownText() : "";
    }

    public int getkcalFromElement(Element element) {
        Element nutritionalTable = element.getElementsByClass("nutritionTable").first();
        if (nutritionalTable == null) {
            return -1;
        }

        Elements rows = nutritionalTable.getElementsByTag("td");

        // get each row of the nutrition table and check if the kcal value is specified
        for (Element row: rows) {
            String value = row.ownText();
            if (value.indexOf("kcal") > 0) {
                return Integer.parseInt(value.trim().replace("kcal", ""));
            }
        }

        return -1;
    }
}

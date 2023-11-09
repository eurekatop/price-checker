package mutiitu.blog.services;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;

public class HtmlExtractorEroski {
    public static List<JsonObject> extractImageUrls(String responseString) {
        List<JsonObject> data = new ArrayList<>();

        Document doc = Jsoup.parse(responseString);

        Elements imgElements = doc.select("div.product-item-lineal");


        for (Element imgElement : imgElements) {
            var name = imgElement.select("h2.product-title.product-title-resp");
            var price = imgElement.select("span.price-offer-now");

            System.out.println(name.text());

            var extracted = new JsonObject();
            extracted.addProperty("name", name.text());
            extracted.addProperty("price", price.text());
         

            data.add(extracted);
        }

        return data;
    }
}
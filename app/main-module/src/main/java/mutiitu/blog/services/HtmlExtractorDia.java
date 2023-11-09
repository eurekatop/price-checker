package mutiitu.blog.services;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;

public class HtmlExtractorDia {
    public static List<JsonObject> extractImageUrls(String responseString) {
        List<JsonObject> data = new ArrayList<>();

        // Parsea el HTML
        Document doc = Jsoup.parse(responseString);

        // Selecciona todos los elementos <img>
        //Elements imgElements = doc.select("p");
        Elements imgElements = doc.select("[data-test-id=search-product-card-list-item]");


        // Itera sobre los elementos <img> y agrega las URLs a la lista
        for (Element imgElement : imgElements) {
            var name = imgElement.select("p.search-product-card__product-name");
            var price = imgElement.select("p.search-product-card__active-price");

            System.out.println(name.text());

            var extracted = new JsonObject();
            extracted.addProperty("name", name.text());
            extracted.addProperty("price", price.text());
         

            data.add(extracted);
        }

        return data;
    }
}
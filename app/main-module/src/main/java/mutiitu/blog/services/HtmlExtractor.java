package mutiitu.blog.services;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlExtractor {
    public static List<String> extractImageUrls(String responseString) {
        List<String> imageUrls = new ArrayList<>();

        // Parsea el HTML
        Document doc = Jsoup.parse(responseString);

        // Selecciona todos los elementos <img>
        //Elements imgElements = doc.select("p");
        Elements imgElements = doc.select("div.position-imagen");


        // Itera sobre los elementos <img> y agrega las URLs a la lista
        for (Element imgElement : imgElements) {
            String imgUrl = imgElement.html();
            imageUrls.add(imgUrl);
        }

        return imageUrls;
    }
}
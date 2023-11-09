package mutiitu.blog.services;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class JsonExtractorConsum {
    public static List<JsonObject> extract(String json) {
        List<JsonObject> result = new ArrayList<>();

        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        ArrayList<Object> products = JsonPath
        .read(document, "$.catalog.products[*]");
        for (Object object : products) {
            var name = JsonPath.read(object, "$.productData.name");
            var price = JsonPath.read(object, "$.priceData.prices[*].value.centUnitAmount");
         
            var extracted = new JsonObject();
            extracted.addProperty("name", name.toString());
            extracted.addProperty("price", price.toString());
         
            result.add(extracted);
        }






        return result;
    }
}
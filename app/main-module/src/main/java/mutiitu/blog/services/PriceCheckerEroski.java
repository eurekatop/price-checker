package mutiitu.blog.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


// li search-product-card-list__item"
public class PriceCheckerEroski {
    public static String test(String arg) {
        String _responseBody = "";

        OkHttpClient client = new OkHttpClient();

        String url = String.format("https://supermercado.eroski.es/ca/search/results/?q=%s&suggestionsFilter=false", arg);

        // Create a request
        Request request = new Request.Builder()
                .url(url)
                .addHeader ("authority", "supermercado.eroski.es")
                .addHeader ("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7'")
                .addHeader ("accept-language",         "ca-ES,ca;q=0.9,es-ES;q=0.8,es;q=0.7")
                .addHeader ("cache-control",      "max-age=0")
                .addHeader ("User-Agent",         "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                .build();

        try {
            // Execute the request
            Response response = client.newCall(request).execute();

            // Get the response code
            int statusCode = response.code();
            System.out.println("Response Code: " + statusCode);

            // Read the response body
            String responseBody = response.body().string();
            System.out.println("Response Body:\n" + responseBody);
            _responseBody = responseBody;

            System.out.println(url);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return _responseBody;
    }
}
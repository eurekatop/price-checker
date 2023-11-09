package mutiitu.blog.services;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class PriceCheckerMercadona {
    public static String test(String arg) {
        String _responseBody = "";

        OkHttpClient client = new OkHttpClient();
        String url = String.format("https://7uzjkl1dj0-dsn.algolia.net/1/indexes/products_prod_bcn1_es/query");



        String requestBody = String.format("{\"params\":\"query=%s&getRankingInfo=false\"}", arg);

        // Create a request
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Algolia-API-Key", "9d8f2e39e90df472b4f2e559a116fe17")
                .addHeader("X-Algolia-Application-Id", "7UZJKL1DJ0")
                .post(RequestBody.create(MediaType.parse("application/json"), requestBody))
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
package mutiitu.blog.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


// li search-product-card-list__item"
public class PriceCheckerDia {
    public static String test(String arg) {
        String _responseBody = "";

        OkHttpClient client = new OkHttpClient();

        String url = String.format("https://www.dia.es/search?q=%s", arg);

        // Create a request
        Request request = new Request.Builder()
                .url(url)
                .addHeader("sec-ch-ua", "Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24\"")
                .addHeader ("sec-ch-ua-mobile", "?0")
                .addHeader ("sec-ch-ua-platform",         "Linux")
                .addHeader ("sec-fetch-dest",      "document")
                .addHeader ("sec-fetch-mode",   "navigate")
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
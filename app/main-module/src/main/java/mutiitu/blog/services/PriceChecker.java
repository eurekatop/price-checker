package mutiitu.blog.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PriceChecker {
    public static String test(String arg) {
        String _responseBody = "";

        OkHttpClient client = new OkHttpClient();

        // Replace this URL with your desired endpoint
        //String url = "https://www.bing.com/search?form=&form=QBLH&q=" + arg +"&sp=-1&lq=1&pq=&sc=0-0&qs=n&sk=&cvid=8401079B863B4B899147502C2F22CB52&ghsh=0&ghacc=0&ghpl=";
        
        //String url = "https://101blogdecocina.com/recetas/busqueda/" + arg;
        String url = "https://www.recetasgratis.net/busqueda?q=" + arg; 

        

        // Create a request
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0")
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
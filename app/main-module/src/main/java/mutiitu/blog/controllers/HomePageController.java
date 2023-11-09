package mutiitu.blog.controllers;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.mutiitu.dao.ContactMeDao;
import com.mutiitu.dao.MigrateDatabase;
import com.mutiitu.framework.core.ApplicationStarter;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import io.javalin.http.Context;
import mutiitu.blog.layouts.home.HomeLayout;
import mutiitu.blog.services.HtmlExtractor;
import mutiitu.blog.services.HtmlExtractorDia;
import mutiitu.blog.services.HtmlExtractorEroski;
import mutiitu.blog.services.JsonExtractorConsum;
import mutiitu.blog.services.JsonExtractorMercadona;
import mutiitu.blog.services.PriceChecker;
import mutiitu.blog.services.PriceCheckerConsum;
import mutiitu.blog.services.PriceCheckerDia;
import mutiitu.blog.services.PriceCheckerEroski;
import mutiitu.blog.services.PriceCheckerMercadona;

@Controller
public class HomePageController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Inject
    HomeLayout homeLayout;

    @Inject
    MigrateDatabase migrateDatabase;

    @Inject
    ContactMeDao contactMeDao;




    @Path(Value = "/")
    @Method(Value = "GET")
    public HttpResponse Home() {

        try {
            return homeLayout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }


    private static CompletableFuture<String>  asyncTask(Context ctx, String productName) {
        CompletableFuture<String> future = new CompletableFuture<>();

        // Start an asynchronous task in a separate thread
        new Thread(() -> {
            try {
            var stream = ctx.res().getOutputStream();

                // for (int i = 0; i < 1000; i++) {
                //     // Simulate some processing time
                //     Thread.sleep(1);

                //     // Send data to the client
                //     String data = "" + i;
                //     //ctx.result(data);

                //     var stream = ctx.res().getOutputStream();
                //     stream.write(data.getBytes());
                //     stream.flush();

                //     // Flush the buffer to ensure data is sent immediately
                //     //ctx.res().getOutputStream().flush();
                // }
                // future.complete("hhola"); // Complete the CompletableFuture when the processing is done


            //String productName = ctx.formParam("product");
            //var response = PriceCheckerConsum.test(productName);

            var responseMercadona = PriceCheckerMercadona.test(productName);
            
            //var list = HtmlExtractor.extractImageUrls(response);

            var listMercadona = JsonExtractorMercadona.extract(responseMercadona);
            String template = "";
            for (JsonObject jsonObject : listMercadona) {
                var name = jsonObject.get("name");
                var price = jsonObject.get("price");

                template += "Mercadona: " + name + ", "+price+"<br />";
            }
            stream.write(template.getBytes());
            stream.flush();
            template = "";


            var responseConsum = PriceCheckerConsum.test(productName);
            var listConsum = JsonExtractorConsum.extract(responseConsum);
            for (JsonObject jsonObject : listConsum) {
                var name = jsonObject.get("name");
                var price = jsonObject.get("price");

                template += "Consum: " + name + ", "+price+"<br />";
            }
            stream.write(template.getBytes());
            stream.flush();
            template = "";


            var responseDia = PriceCheckerDia.test(productName);
            var listDia = HtmlExtractorDia.extractImageUrls(responseDia);
            for (JsonObject jsonObject : listDia) {

                var name = jsonObject.get("name");
                var price = jsonObject.get("price");

                template += "Dia: " + name + ", "+price+"<br />";
            }
            stream.write(template.getBytes());
            stream.flush();
            template = "";

            var responseEroski = PriceCheckerEroski.test(productName);
            var listEroski = HtmlExtractorEroski.extractImageUrls(responseEroski);
            for (JsonObject jsonObject : listEroski) {

                var name = jsonObject.get("name");
                var price = jsonObject.get("price");

                template += "Eroski: " + name + ", "+price+"<br />";
            }
            stream.write(template.getBytes());
            stream.flush();
            template = "";


            future.complete("");



            } catch (Exception e) {
                e.printStackTrace();
                future.completeExceptionally(e); // Complete exceptionally in case of an error
            }
        }).start();

        return future;
    }
    @Path(Value = "/search-async")
    @Method(Value = "POST")
    public void test() {
            String productName = ctx.formParam("product");
            ctx.async(
                () -> ctx.result(HomePageController.asyncTask(ctx, productName).join())          // some long running task
            );
    }


    @Path(Value = "/search")
    @Method(Value = "POST")
    public HttpResponse searchProduct() {

        try {
            String productName = ctx.formParam("product");
            //var response = PriceCheckerConsum.test(productName);

            var responseMercadona = PriceCheckerMercadona.test(productName);
            var responseConsum = PriceCheckerConsum.test(productName);
            var responseDia = PriceCheckerDia.test(productName);
            var responseEroski = PriceCheckerEroski.test(productName);
            
            //var list = HtmlExtractor.extractImageUrls(response);

            var listMercadona = JsonExtractorMercadona.extract(responseMercadona);
            var listConsum = JsonExtractorConsum.extract(responseConsum);
            var listDia = HtmlExtractorDia.extractImageUrls(responseDia);
            var listEroski = HtmlExtractorEroski.extractImageUrls(responseEroski);

            String template = "";
            for (JsonObject jsonObject : listMercadona) {
                var name = jsonObject.get("name");
                var price = jsonObject.get("price");

                template += "Mercadona: " + name + ", "+price+"<br />";
            }

            for (JsonObject jsonObject : listConsum) {
                var name = jsonObject.get("name");
                var price = jsonObject.get("price");

                template += "Consum: " + name + ", "+price+"<br />";
            }

            for (JsonObject jsonObject : listDia) {

                var name = jsonObject.get("name");
                var price = jsonObject.get("price");

                template += "Dia: " + name + ", "+price+"<br />";
            }

            for (JsonObject jsonObject : listEroski) {

                var name = jsonObject.get("name");
                var price = jsonObject.get("price");

                template += "Eroski: " + name + ", "+price+"<br />";
            }

            


            return new HtmlResponse(template);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }
}

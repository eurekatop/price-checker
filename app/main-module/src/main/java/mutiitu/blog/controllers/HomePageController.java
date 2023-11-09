package mutiitu.blog.controllers;

import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.dao.ContactMeDao;
import com.mutiitu.dao.MigrateDatabase;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.JsonResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import mutiitu.blog.components.home.HomePage;
import mutiitu.blog.layouts.home.HomeLayout;
import mutiitu.blog.services.BlogEntryService;

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

    @Path(Value = "/search")
    @Method(Value = "GET")
    public HttpResponse searchProduct(String productName) {

        try {
            var random = Math.random()*100;
            return new StringResponse((random + "").toString());
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }
}

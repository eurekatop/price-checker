package mutiitu.blog;

import com.google.inject.AbstractModule;
import mutiitu.blog.components.Header;
import mutiitu.blog.components.Header2;
import mutiitu.blog.components.Header3;
import mutiitu.blog.components.HeaderService;
import mutiitu.blog.components.HeaderServiceImpl;
import mutiitu.blog.components.admin.AdminHeaderComponent;
import mutiitu.blog.components.admin.core.ButtonActionComponent;
import mutiitu.blog.components.admin.pages.AuthorFormPageComponent;
import mutiitu.blog.components.admin.pages.ConfigPageComponent;
import mutiitu.blog.components.admin.pages.NewEntryPageComponent;
import mutiitu.blog.components.card.CardUIComponent;
import mutiitu.blog.layouts.home.HomeLayout;
import mutiitu.blog.services.AuthorService;
import mutiitu.blog.services.BlogEntryService;
import mutiitu.blog.services.CMSEntryService;
import mutiitu.blog.services.TestService;

import com.mutiitu.di.PersistenceModule;
import com.mutiitu.framework.core.di.CoreModule;
import com.mutiitu.persistence.SQLiteDB;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();

        // TODO: refactor
        var dataSource = new SQLiteDB().getPoolDataSource();

        install(new CoreModule(dataSource));
        install(new PersistenceModule());

        // components
        bind(Header.class).asEagerSingleton();
        bind(Header2.class).asEagerSingleton();
        bind(Header3.class).asEagerSingleton();
        bind(CardUIComponent.class);

        bind(HeaderService.class).to(HeaderServiceImpl.class);

        // layouts
        bind(HomeLayout.class); // .asEagerSingleton();

        bind(AdminHeaderComponent.class);
        bind(ButtonActionComponent.class);
        bind(ConfigPageComponent.class);
        bind(NewEntryPageComponent.class);
        bind(AuthorFormPageComponent.class);

        // bind(TestService.class).in(Scopes.SINGLETON);

        // TODO: discover with anotation
        // bind(Router.class).to(BlogController.class);
        // bind(HelloWorldRouter.class).toInstance(new HelloWorldRouter(null));

        // add template components...... maybe discover with annotations.....
        // UIComponentFactory.AddComponent("Header3", new Header3() );
        // UIComponentFactory.AddComponent("HomePage", new HomePage() );

        // TODO: automatic binding application services
        bind(TestService.class).in(PersistenceModule.SQLiteDBScope);
        bind(BlogEntryService.class).in(PersistenceModule.SQLiteDBScope);
        bind(CMSEntryService.class).in(PersistenceModule.SQLiteDBScope);
        bind(AuthorService.class).in(PersistenceModule.SQLiteDBScope);

    }
}

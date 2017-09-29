package com.hazardousdev.gym_dairy.wicket.application;

import com.hazardousdev.gym_dairy.guice.GymDairyModule;
import com.hazardousdev.gym_dairy.wicket.page.LoginPage;
import com.hazardousdev.gym_dairy.wicket.page.base.BasePage;
import com.hazardousdev.gym_dairy.wicket.page.dairy.GymDairyPage;
import com.hazardousdev.gym_dairy.wicket.page.home.HomePage;
import com.hazardousdev.gym_dairy.wicket.page.move_declarations.MoveDeclarationsPage;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 */
public class GymDairyApplication extends AuthenticatedWebApplication {
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends BasePage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, new GymDairyModule()));
        getRequestCycleListeners().add(new GymDairyRequestCycleListener());
        setRootRequestMapper(new HttpsMapper(getRootRequestMapper(), new HttpsConfig(8080, 443)));


        mountPage("login", LoginPage.class);
        mountPage("home", HomePage.class);
        mountPage("dairy", GymDairyPage.class);
        mountPage("moves", MoveDeclarationsPage.class);
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return GymDairyAuthenticatedWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }
}

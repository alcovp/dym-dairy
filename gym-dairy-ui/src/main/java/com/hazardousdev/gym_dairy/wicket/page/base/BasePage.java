package com.hazardousdev.gym_dairy.wicket.page.base;

import com.hazardousdev.gym_dairy.wicket.page.IRequireHttps;
import com.hazardousdev.gym_dairy.wicket.page.base.panel.BaseHeaderPanel;
import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author alcovp
 */
public abstract class BasePage extends WebPage implements IRequireHttps {

    public BasePage(final PageParameters parameters) {
        super(parameters);

        add(new BaseHeaderPanel("header"));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            app.restartResponseAtSignInPage();
        }
    }
}
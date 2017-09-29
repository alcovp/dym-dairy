package com.hazardousdev.gym_dairy.wicket.application;

import com.google.inject.Inject;
import com.hazardousdev.gym_dairy.model.User;
import com.hazardousdev.gym_dairy.service.IGymDairyService;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 * @author alcovp
 */
public class GymDairyAuthenticatedWebSession extends AuthenticatedWebSession {

    @Inject
    private IGymDairyService gymDairyService;

    private long userId;
    private String userName;

    public static GymDairyAuthenticatedWebSession get() {
        return (GymDairyAuthenticatedWebSession) Session.get();
    }

    public GymDairyAuthenticatedWebSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = gymDairyService.authenticate(username, password);
        if (user != null) {
            userId = user.getId();
            userName = username;
            return true;
        }
        return false;
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles(Roles.USER);
        if (userName.equals("admin")) {
            roles.add(Roles.ADMIN);
        }
        return roles;
    }

    public long getCurrentUserId() {
        return userId;
    }
}

package com.hazardousdev.gym_dairy.wicket.application;

import org.apache.wicket.Session;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * @author alcovp
 * <p>
 * Class for injecting service to instance of GymDairyAuthenticatedWebSession
 */
public class GymDairyRequestCycleListener extends AbstractRequestCycleListener {
    @Override
    public void onBeginRequest(RequestCycle cycle) {
        if (Session.exists()) {
            Injector.get().inject(Session.get());
        }
    }
}

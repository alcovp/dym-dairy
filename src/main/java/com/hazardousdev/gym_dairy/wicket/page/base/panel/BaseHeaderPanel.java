package com.hazardousdev.gym_dairy.wicket.page.base.panel;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

import java.util.List;

/**
 * @author alcovp
 */
public class BaseHeaderPanel extends Panel {
    public BaseHeaderPanel(String id) {
        super(id);

        add(new NavBarPanel("nav-bar", new AbstractReadOnlyModel<List<NavBarItem>>() {
            @Override
            public List<NavBarItem> getObject() {
                return NavBarManager.getItems();
            }
        }));
    }
}

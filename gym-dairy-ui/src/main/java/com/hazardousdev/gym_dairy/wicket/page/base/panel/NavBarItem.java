package com.hazardousdev.gym_dairy.wicket.page.base.panel;

import com.hazardousdev.gym_dairy.wicket.page.base.BasePage;

/**
 * @author alcovp
 */
public class NavBarItem {

    private final Class<? extends BasePage> pageClass;
    private final String labelResourceId;

    public NavBarItem(Class<? extends BasePage> pageClass, String labelResourceId) {
        this.pageClass = pageClass;
        this.labelResourceId = labelResourceId;
    }

    public Class<? extends BasePage> getPageClass() {
        return pageClass;
    }

    public String getLabelResourceId() {
        return labelResourceId;
    }
}

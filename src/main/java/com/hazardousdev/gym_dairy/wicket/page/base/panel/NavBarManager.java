package com.hazardousdev.gym_dairy.wicket.page.base.panel;

import com.hazardousdev.gym_dairy.wicket.page.dairy.GymDairyPage;
import com.hazardousdev.gym_dairy.wicket.page.home.HomePage;
import com.hazardousdev.gym_dairy.wicket.page.move_declarations.MoveDeclarationsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alcovp
 */
public class NavBarManager {

    public static List<NavBarItem> getItems() {
        return new ArrayList<>(Arrays.asList(
                new NavBarItem(HomePage.class, "home"),
                new NavBarItem(GymDairyPage.class, "dairy"),
                new NavBarItem(MoveDeclarationsPage.class, "moves")
        ));
    }
}

package com.hazardousdev.gym_dairy;

import com.hazardousdev.gym_dairy.wicket.application.GymDairyApplication;
import com.hazardousdev.gym_dairy.wicket.page.LoginPage;
import com.hazardousdev.gym_dairy.wicket.page.home.HomePage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {
    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new GymDairyApplication());
    }

    @Test
    public void homepageRendersSuccessfully() {
        //start and render the test page
        tester.startPage(HomePage.class);

        //assert rendered page class
        tester.assertRenderedPage(LoginPage.class);
    }
}

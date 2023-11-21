package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
    void canLoginWebBrowser() {
        app.session().login("administrator", "root");
        Assertions.assertTrue(app.session().isLoggedIn());
    }

    @Test
    void canLogin() {
        app.http().login("administrator", "root");
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}

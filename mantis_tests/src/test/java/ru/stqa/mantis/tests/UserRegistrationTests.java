package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() throws InterruptedException {
        var username = String.format(CommonFunctions.randomString(8));
        var email = String.format("%s@localhost", username);
        var password = "password";

        app.jamesApi().addUser(email, password);
        app.rest().createUser(new UserData(username, password, email));

        var sms = app.mail().receive(email, password, Duration.ofSeconds(60));
        var text = sms.get(0).content();
        var url = CommonFunctions.extractUrl(text);

        app.session().finishedRegistration(url, username, password);

        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
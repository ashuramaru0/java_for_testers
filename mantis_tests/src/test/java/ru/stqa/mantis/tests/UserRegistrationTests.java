package ru.stqa.mantis.tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase{
    @Test
    void canRegisterUser() {
        var username = String.format(CommonFunctions.randomString(8));
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(email, "password");
        app.session().signup(username, email);
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(),matcher.end());
            System.out.println(url);
        }
        app.session().finishedRegistration(url, username, "password");
        Assertions.assertTrue(app.session().isLoggedIn());
        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
package ru.stqa.mantis.tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import java.time.Duration;
import java.util.regex.Pattern;
public class UserRegistrationTests extends TestBase{
    DeveloperMailUser user;
    @Test
    void canRegisterUser(){
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.session().signup(user.name(), email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(message);

        app.session().finishedRegistration(url, user.name(), password);

        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
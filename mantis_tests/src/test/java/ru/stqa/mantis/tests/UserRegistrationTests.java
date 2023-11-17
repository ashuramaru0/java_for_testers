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
        user =  app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());
    }

    @AfterEach
    void deleteMailUser () {
        app.developerMail().deleteUser(user);
    }
}
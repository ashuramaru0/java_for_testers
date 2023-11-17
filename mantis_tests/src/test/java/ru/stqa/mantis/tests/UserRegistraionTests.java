package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistraionTests extends TestBase{

    @Test
    void canRegisterUser(String username){
        var email = String.format("%s@localhost", username);
    }
}

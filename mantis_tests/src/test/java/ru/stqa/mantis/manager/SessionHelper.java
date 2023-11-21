package ru.stqa.mantis.manager;


import org.openqa.selenium.*;

public class SessionHelper extends HelperBase{
    public SessionHelper (ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void signup(String username, String email) {
        click(By.xpath("//*[@id='login-box']/div/div[2]/a"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
        click(By.xpath("//*[@id='login-box']/div/div/div[4]/a"));
    }

    public void finishedRegistration(String pattern, String username, String password) {
        manager.driver().get(pattern);
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));

        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));

        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }
}
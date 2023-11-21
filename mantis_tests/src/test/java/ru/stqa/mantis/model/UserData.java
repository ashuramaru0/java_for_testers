package ru.stqa.mantis.model;


public record UserData (String username, String password, String email){

    public UserData ()  {
        this("username","password","");
    }

    public ru.stqa.mantis.model.UserData withUsername(String username) {
        return new ru.stqa.mantis.model.UserData(username, this.password, this.email);
    }

    public ru.stqa.mantis.model.UserData withPassword(String password) {
        return new ru.stqa.mantis.model.UserData(this.username, password, this.email);
    }

    public ru.stqa.mantis.model.UserData withEmail(String email) {
        return new ru.stqa.mantis.model.UserData(this.username, this.password,email);
    }
}
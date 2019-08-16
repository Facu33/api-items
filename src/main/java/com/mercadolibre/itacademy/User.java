package com.mercadolibre.itacademy;

public class User {
    public String username;
    public String password;
    public String token;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                "token='" + token + '\'' +
                '}';
    }
}

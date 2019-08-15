package com.mercadolibre.itacademy;

public class User {
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                "password='" + password + '\'' +
                '}';
    }
}

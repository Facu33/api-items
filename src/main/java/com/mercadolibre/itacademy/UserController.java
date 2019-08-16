package com.mercadolibre.itacademy;

import com.google.gson.Gson;
import org.jooby.Request;
import org.jooby.Response;

import java.io.BufferedReader;

public class UserController {

    public void doLogin(Request req, Response res) throws Exception {
        try {
            User user = req.body(User.class);
            Gson gson = new Gson();
            UserService userService = new UserService();
            String response = userService.postLogin("http://localhost:8084/login", user);
            User userJson = gson.fromJson(response,User.class);
            res.status(200).send(userJson);
        } catch (Throwable throwable) {

        }
    }

    public void getSites(Request req, Response res) {
        try {
            UserService userService = new UserService();
            Gson gson = new Gson();
            String username = req.param("username").value();
            String token = req.param("token").value();
            String response = userService.get("http://localhost:8084/sites",username,token);
            Site[] sitesJson = gson.fromJson(response,Site[].class);
            res.status(200).send(sitesJson);
        } catch (Throwable throwable) {
        }
    }

    public String getSitesCategories(Request req, Response res) {
        System.out.println(req.param("username").value());
        return "ok";
    }
}

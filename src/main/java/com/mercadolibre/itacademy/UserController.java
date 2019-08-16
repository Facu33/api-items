package com.mercadolibre.itacademy;

import com.google.gson.Gson;
import org.jooby.Request;
import org.jooby.Response;

import java.util.Collection;

public class UserController {

    private final Gson gson = new Gson();

    public void doLogin(Request req, Response res) throws Exception {
        try {
            User user = req.body(User.class);
            String response = UserService.postLogin("http://localhost:8084/login", user);
            User userJson = gson.fromJson(response, User.class);
            res.status(200).send(userJson);
        } catch (Throwable throwable) {

        }
    }

    public void getSites(Request req, Response res) {
        try {
            String username = req.param("username").value();
            String token = req.param("token").value();
            String response = UserService.getSites("http://localhost:8084/sites", username, token);
            Site[] sitesJson = gson.fromJson(response, Site[].class);
            res.status(200).send(sitesJson);
        } catch (Throwable throwable) {
        }
    }

    public void getSitesCategories(Request req, Response res) {
        try {
            UserService userService = new UserService();
            String username = req.param("username").value();
            String id = req.param("id").value();
            String token = req.param("token").value();
            String response = userService.getSites("http://localhost:8084/sites/" + id + "/categories", username, token);
            Site[] sitesJson = gson.fromJson(response, Site[].class);
            res.status(200).send(sitesJson);
        } catch (Throwable throwable) {
        }
    }

    public String postItem(Request req, Response res) {

        try {
            Item item = req.body().to(Item.class);
            Item response = UserService.postItems(item);
            res.header("Content-Type", "application/json");
            res.status(200).send(response);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "ok";
    }

    public String getItems(Request req, Response res) {

        Collection<Item> items = UserService.getItems();
        try {
            res.status(200).send(items);
        } catch (Throwable throwable) {
            System.out.println(throwable.toString());
        }

        return "ok";
    }

    public String getItemsxUser(Request req, Response res) {

        Collection<Item> itemsxuser = UserService.getItemsxUser(req.param("username").value());
        try {
            res.status(200).send(itemsxuser);
        } catch (Throwable throwable) {
            System.out.println(throwable.toString());
        }

        return "ok";
    }

}

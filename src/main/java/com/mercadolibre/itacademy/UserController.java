package com.mercadolibre.itacademy;

import com.google.gson.Gson;
import org.jooby.Request;
import org.jooby.Response;

import java.io.IOException;
import java.net.SocketException;
import java.util.Collection;

public class UserController {

    private final Gson gson = new Gson();

    public void doLogin(Request req, Response res) throws Exception {
        try {
            User user = req.body(User.class);
            String response = UserService.postLogin("http://localhost:8084/users/login", user);
            User userJson = gson.fromJson(response, User.class);
            res.status(200).send(userJson);
        } catch (Throwable throwable) {

        }
    }

    public void getSites(Request req, Response res) {

        try {
            String username = req.param("username").value();
            String token = req.param("token").value();
            String response = UserService.getSites("http://localhost:8084/users/sites", username, token);
            if (response == "404") {
                res.status(404).send("Bad Request");
            }
            Site[] sitesJson = gson.fromJson(response, Site[].class);
            res.status(200).send(sitesJson);
        } catch (SocketException se) {
            System.out.println(se.toString());
            try {
                String response = "Server is down service unavailable";
                res.status(503).send(response);
            } catch (Throwable throwable) {
                System.out.print(throwable.toString());
            }

        } catch (IOException ie) {
            try {
                String response = "Could not confirm user incorrect parameters";
                res.status(400).send(response);
            } catch (Throwable throwable) {
                System.out.print(throwable.toString());
            }
        } catch (Throwable throwable) {
            try {
                res.status(401).send("Bad Credentials");
            } catch (Throwable e) {
                System.out.print(e.toString());
            }
        }
    }

    public void getSitesCategories(Request req, Response res) {
        try {
            UserService userService = new UserService();
            String username = req.param("username").value();
            String id = req.param("id").value();
            String token = req.param("token").value();
            String response = userService.getSites("http://localhost:8084/users/sites/" + id + "/categories", username, token);
            if (response == "404") {
                res.status(404).send("Bad Request");
            }
            Site[] sitesJson = gson.fromJson(response, Site[].class);
            res.status(200).send(sitesJson);
        } catch (SocketException se) {

            System.out.println(se.toString());

            try {
                res.status(401).send("Bad Credentials");
            } catch (Throwable throwable) {

                System.out.print(throwable.toString());

            }

        } catch (IOException ie) {

            System.out.println(ie.toString());

        } catch (Throwable throwable) {

            System.out.print(throwable.toString());

            try {

                res.status(400).send("{" +
                        "\"message\": \"Malformed body\"" +
                        "}");

            } catch (Throwable throwable1) {

                System.out.print(throwable1.toString());

            }

        }
    }

    public String postItem(Request req, Response res) {

        try {
            Item item = req.body().to(Item.class);
            Item response = UserService.postItems(item);
            res.header("Content-Type", "application/json");
            res.status(200).send(response);

        } catch (SocketException se) {

            System.out.println(se.toString());

            try {

                res.status(503).send("{" +
                        "\"message\": \"Server is Down\"" +
                        "}");

            } catch (Throwable throwable) {

                System.out.print(throwable.toString());

            }

        } catch (IOException ie) {

            System.out.println(ie.toString());

        } catch (Throwable throwable) {

            System.out.print(throwable.toString());

            try {

                res.status(400).send("{" +
                        "\"message\": \"Malformed body\"" +
                        "}");

            } catch (Throwable throwable1) {

                System.out.print(throwable1.toString());

            }

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

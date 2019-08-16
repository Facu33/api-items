package com.mercadolibre.itacademy;

import com.google.gson.Gson;
import org.jooby.Mutant;
import org.jooby.Request;
import org.jooby.Response;

import java.util.Collection;

public class UserController {

    public void doLogin(Request req, Response res) throws Exception {
        try {
            User user = req.body(User.class);
            System.out.println(user.username);
            UserService userService = new UserService();
            String response = userService.post("http://localhost:8083/login", user);
            res.status(200).send(response);
        } catch (Throwable throwable) {

        }
    }

    public String getSites(Request req, Response res) {
        String id = req.param("token").value();
        return "ok";
    }

    public String getSitesCategories(Request req, Response res) {
        String idCategory = req.param("id").value();
        String token = req.param("token").value();
        return "ok";
    }

    public String postItem(Request req, Response res){

        try {

            Item item = req.body().to(Item.class);
            Item response = UserService.postItems(item);
            res.header("Content-Type","application/json");
            res.status(200).send(response);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "ok";
    }

    public String getItems(Request req, Response res){

        Collection<Item> items = UserService.getItems();
        try {
            res.status(200).send(items);
        } catch (Throwable throwable) {
            System.out.println(throwable.toString());
        }

        return "ok";
    }

    public String getItemsxUser(Request req, Response res){



        Collection<Item> itemsxuser = UserService.getItemsxUser(req.param("username").value());
        try {
            res.status(200).send(itemsxuser);
        } catch (Throwable throwable) {
            System.out.println(throwable.toString());
        }

        return "ok";
    }

}

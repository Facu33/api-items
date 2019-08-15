package com.mercadolibre.itacademy;

import com.google.gson.Gson;
import org.jooby.Request;
import org.jooby.Response;

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
}

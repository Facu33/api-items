package com.mercadolibre.itacademy;

import org.jooby.Request;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

import java.util.Optional;

public class UserController {

    public String doLogin(Request req) throws Exception {
        User user = req.body(User.class);
        System.out.println(user);
        return "ok";
          /*String body = req.body().value();
          UserService userService = new UserService();
          String userLogin = userService.sendPost("https://localhost:8081/login");

            //UserService userService = new UserService();
            //String userLogin = userService.sendPost("https://localhost:8081/login");
            */
    }

    public String getSites(Request req) {
        String id = req.param("token").value();
        return "ok";
    }

    public String getSitesCategories(Request req) {
        String idCategory = req.param("id").value();
        String token = req.param("token").value();
        return "ok";
    }
}

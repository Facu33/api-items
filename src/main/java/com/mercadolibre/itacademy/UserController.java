package com.mercadolibre.itacademy;

import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

import java.util.Optional;

public class UserController {

    @Path("/mvc")

    public class User {

        @Path("/login")
        @POST
        public String login(String name,String password) throws Exception {

            UserService userService = new UserService();
            String userLogin = userService.sendPost("https://localhost:8081/login");
            return userLogin;
        }
    }
}

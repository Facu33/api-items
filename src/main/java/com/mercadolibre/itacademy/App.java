package com.mercadolibre.itacademy;

import org.jooby.Jooby;
import org.jooby.json.Jackson;

/**
 * @author jooby generator
 */
public class App extends Jooby {

    {
        use(new Jackson());
        UserController userController = new UserController();

        post("/login", req -> userController.doLogin(req));

        get("/sites/:token", req -> userController.getSites(req));

        get("/sites/:id/categories/:token", req -> userController.getSitesCategories(req));

    }


    public static void main(final String[] args) throws Exception {
        run(App::new, args);
    }

}

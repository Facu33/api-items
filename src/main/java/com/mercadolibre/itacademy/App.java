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

        post("/login", (req,res) -> userController.doLogin(req,res));

        get("/sites/:token", (req,res) -> userController.getSites(req,res));

        get("/sites/:id/categories/:token", (req,res) -> userController.getSitesCategories(req,res));

    }


    public static void main(final String[] args) throws Exception {
        run(App::new, args);
    }

}

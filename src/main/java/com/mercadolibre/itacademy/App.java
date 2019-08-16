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

        post("/items/login", (req, res) -> UserController.doLogin(req, res));

        get("/items/sites", (req, res) -> UserController.getSites(req, res));

        get("/items/sites/:id/categories", (req, res) -> UserController.getSitesCategories(req, res));

        post("/items/item", (req, res) -> UserController.postItem(req, res));

        get("/items/item", (req, res) -> UserController.getItems(req, res));

        get("/items/item/:username", (req, res) -> UserController.getItemsxUser(req, res));

    }


    public static void main(final String[] args) throws Exception {
        run(App::new, args);
    }

}

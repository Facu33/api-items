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

        post("items/login", (req, res) -> userController.doLogin(req, res));

        get("items/sites", (req, res) -> userController.getSites(req, res));

        get("items/sites/:id/categories", (req, res) -> userController.getSitesCategories(req, res));

        post("items/item", (req, res) -> userController.postItem(req, res));

        get("items/item", (req, res) -> userController.getItems(req, res));

        get("items/item/:username", (req, res) -> userController.getItemsxUser(req, res));

    }


    public static void main(final String[] args) throws Exception {
        run(App::new, args);
    }

}

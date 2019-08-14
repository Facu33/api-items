package com.mercadolibre.itacademy;

import org.jooby.Jooby;
import org.jooby.json.Jackson;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  {
    use(new Jackson());
    use(UserController.class);
    get("/", () -> "Hello World!");
  }

  public static void main(final String[] args) throws Exception {

    run(App::new, args);

  }

}

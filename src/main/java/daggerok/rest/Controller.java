package daggerok.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;

@org.springframework.stereotype.Controller
public class Controller {

  @GetMapping({ "", "/" })
  public Rendering index() {
    return Rendering.view("index")
                    .modelAttribute("message", "Hololo trololo!")
                    .build();
  }
}

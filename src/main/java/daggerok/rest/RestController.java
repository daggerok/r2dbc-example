package daggerok.rest;

import daggerok.coffee.Coffee;
import daggerok.coffee.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api")
@org.springframework.web.bind.annotation.RestController
public class RestController {

  final CoffeeRepository coffeeRepository;

  @GetMapping("/**")
  public Publisher<Coffee> getAll() {
    return coffeeRepository.findAll();
  }
}

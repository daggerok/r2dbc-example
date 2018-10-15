package daggerok.coffee;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class CoffeeTestData {

  final CoffeeRepository coffeeRepository;

  @PostConstruct
  public void init() {
    coffeeRepository.deleteCoffee().thenMany(
        Flux.just("a", "b", "c")
            .map(Coffee::new)
            .flatMap(coffeeRepository::save))
                    .thenMany(coffeeRepository.findAll())
                    .subscribe(coffee -> System.out.println("coffee = " + coffee));
  }
}

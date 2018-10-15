package daggerok.coffee;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CoffeeRepository extends ReactiveCrudRepository<Coffee, Long> {

  @Query(" delete from Coffee ")
  Flux<Coffee> deleteCoffee();
}

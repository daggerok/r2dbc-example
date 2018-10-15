package daggerok.r2dbc;

import daggerok.coffee.CoffeeRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

import javax.annotation.PostConstruct;

/**
 * Sorry, but at this point of time we need this ceremony boilerplate code...
 */
@Configuration
@RequiredArgsConstructor
public class R2dbcConfig {

  final DbProps db;

  @Bean
  public ConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(
        PostgresqlConnectionConfiguration
            .builder()
            .database(db.name)
            .username(db.user)
            .password(db.password)
            .host(db.server.host)
            .port(db.server.port)
            .build());
  }

  @Bean
  public DatabaseClient databaseClient() {
    return DatabaseClient.builder()
                         .connectionFactory(connectionFactory())
                         .build();
  }

  @Bean
  public MappingContext mappingContext() {
    final RelationalMappingContext relationalMappingContext = new RelationalMappingContext();
    relationalMappingContext.afterPropertiesSet();
    return relationalMappingContext;
  }

  @Bean
  public R2dbcRepositoryFactory repositoryFactory() {
    return new R2dbcRepositoryFactory(databaseClient(), mappingContext());
  }

  @Bean
  public CoffeeRepository reactiveCoffeeRepository() {
    return repositoryFactory().getRepository(CoffeeRepository.class);
  }

//  @Bean
//  public io.r2dbc.client.R2dbc r2dbc() {
//    return new io.r2dbc.client.R2dbc(connectionFactory());
//  }
}

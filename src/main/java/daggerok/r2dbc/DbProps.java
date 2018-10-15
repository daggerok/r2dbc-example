package daggerok.r2dbc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "db")
public class DbProps {

  String name, user, password, url;
  Server server;

  @Data
  public static class Server {
    String host;
    Integer port;
  }
}

package io.github.bocharoviliyav.gis.config;


import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Jackson configuration class.
 */
@Configuration
public class JacksonConfig {

  /**
   * Jts module bean.
   *
   * @return the jts module
   */
  @Bean
  public JtsModule jtsModule() {
    return new JtsModule();
  }
}

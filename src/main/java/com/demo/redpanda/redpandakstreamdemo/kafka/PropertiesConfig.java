package com.demo.redpanda.redpandakstreamdemo.kafka;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PropertiesConfig {
  @Value("${spring.kafka.properties.schema.registry.url}")
  private String schemaRegistryUrl;

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootStrapServers;

  @Value("${kafka.topics}")
  private String[] topics;

}

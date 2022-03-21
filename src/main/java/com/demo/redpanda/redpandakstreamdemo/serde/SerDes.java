package com.demo.redpanda.redpandakstreamdemo.serde;

import com.demo.redpanda.redpandakstreamdemo.generated.Input;
import com.demo.redpanda.redpandakstreamdemo.generated.Output;
import com.demo.redpanda.redpandakstreamdemo.kafka.PropertiesConfig;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroDeserializer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SerDes {
  @Autowired
  PropertiesConfig propertiesConfig;

  @Bean
  public Serde<Input> inputSerde() {
    Serde<Input>accountSerde = Serdes.serdeFrom(new SpecificAvroSerializer<>(), new SpecificAvroDeserializer<>());
    accountSerde.configure(Collections.singletonMap(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, propertiesConfig.getSchemaRegistryUrl()), false);
    return accountSerde;
  }

  @Bean
  public Serde<Output> outputSerde() {
    Serde<Output>accountFilterSerde = Serdes.serdeFrom(new SpecificAvroSerializer<>(), new SpecificAvroDeserializer<>());
    accountFilterSerde.configure(Collections.singletonMap(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, propertiesConfig.getSchemaRegistryUrl()), false);
    return accountFilterSerde;
  }
}

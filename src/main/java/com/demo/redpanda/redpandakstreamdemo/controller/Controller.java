package com.demo.redpanda.redpandakstreamdemo.controller;

import com.demo.redpanda.redpandakstreamdemo.generated.Input;
import com.demo.redpanda.redpandakstreamdemo.kafka.PropertiesConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Properties;
import java.util.logging.Logger;

@RestController
public class Controller {
  @Autowired
  private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

  @Autowired
  PropertiesConfig propertiesConfig;

  @Autowired
  StreamsBuilderFactoryBean streamsBuilderFactoryBean;

  @Autowired
  Environment environment;

  private Producer<String, Input> producer;

  @GetMapping(value = "/generate")
  public String startStream() {
    //init the producer
    final Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, propertiesConfig.getBootStrapServers());
    props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, propertiesConfig.getSchemaRegistryUrl());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SpecificAvroSerializer.class);
    this.producer = new KafkaProducer<>(props);

    //produce message
    Input genInput = Input.newBuilder().setName("SampleName").setPhoneNumber("000-000-0000").setAge(99).build();
    String topic = this.propertiesConfig.getTopics()[0];
    String key = "test_key_" + Instant.now().toEpochMilli();
    ProducerRecord<String, Input> transaction = new ProducerRecord<>(topic, key, genInput);
    this.producer.send(transaction);
    return "Produced Message";
  }

  @GetMapping("/")
  public String hello() {
    return "Go to /generate to produce a message";
  }

}

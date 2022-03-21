package com.demo.redpanda.redpandakstreamdemo.stream;

import com.demo.redpanda.redpandakstreamdemo.generated.Input;
import com.demo.redpanda.redpandakstreamdemo.generated.Output;
import com.demo.redpanda.redpandakstreamdemo.kafka.PropertiesConfig;
import com.demo.redpanda.redpandakstreamdemo.mapper.Mapper;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class Stream {
  @Autowired
  PropertiesConfig propertiesConfig;

  @Autowired
  Serde<Input> inputSerde;

  @Autowired
  Serde<Output> outputSerde;


  @Bean
  public StreamsBuilder redpandaStream(StreamsBuilder streamsBuilder) {
    KStream<String,Input> inputStream = streamsBuilder.stream(propertiesConfig.getTopics()[0], Consumed.with(Serdes.String(), inputSerde));
    inputStream
        	 .mapValues(new Mapper())
        	 .to(propertiesConfig.getTopics()[1],
        		 Produced.with(Serdes.String(), outputSerde));
    return streamsBuilder;
  }
}

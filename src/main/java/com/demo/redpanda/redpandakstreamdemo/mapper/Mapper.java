package com.demo.redpanda.redpandakstreamdemo.mapper;

import com.demo.redpanda.redpandakstreamdemo.generated.Input;
import com.demo.redpanda.redpandakstreamdemo.generated.Output;
import org.apache.kafka.streams.kstream.ValueMapper;

public class Mapper implements ValueMapper<Input, Output> {
  @Override
  public Output apply(Input in) {
    Output output = Output.newBuilder().
            setContactInfo(in.getName() + "-" + in.getAge() + "-" + in.getPhoneNumber())
            .build();
    return output;
  }
}

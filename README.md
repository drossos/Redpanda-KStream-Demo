# Redpanda KStream Demo 
This demo is in reference to the following [article](medium.com)

This application is to be used to test the compatibility of your Redpanda cluster. It will use two topics `input` and `output` where messages published to `input` will get mapped to `output`. There are only two environment that must be set for this application to function properly. 

* `BOOTSTRAP_SERVERS` - set to this to the address(es) of your Redpanda brokers on port `9092`. The formatting should be: `xxx.xx.xx.xx:9092,xxx.xx.xx.xx:9092,xxx.xx.xx.xx:9092`
* `SCHEMA_REGISTRY` - set to this to the address(es) of your Redpanda brokers on port `8081`. The formatting should be: `http://xxx.xx.xx.xx:8081,http://xxx.xx.xx.xx:8081,http://xxx.xx.xx.xx:8081`

Once you have the application running locally, you can generate sample messages to the `input` topic by going to [http://localhost:8080/generate](http://localhost:8080/generate)



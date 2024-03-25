# Redpanda KStream Demo 
This demo is in reference to the following [article](https://medium.com/@shahirdaya/first-impressions-of-redpanda-6dd0d860ecfd)

## Running the App
This application is to be used to test the compatibility of your Redpanda cluster. It will use two topics `input` and `output` where messages published to `input` will get mapped to `output`. There are only two environment variables that must be set for this application to function properly. 

* `BOOTSTRAP_SERVERS` - set to this to the address(es) of your Redpanda brokers on port `9092` (exception being if you are running Redpanda on local container). The formatting should be: `xxx.xx.xx.xx:9092,xxx.xx.xx.xx:9092,xxx.xx.xx.xx:9092`
* `SCHEMA_REGISTRY` - set to this to the address(es) of your Redpanda brokers on port `8081`. The formatting should be: `http://xxx.xx.xx.xx:8081,http://xxx.xx.xx.xx:8081,http://xxx.xx.xx.xx:8081`

Once you have the application running locally, you can generate sample messages to the `input` topic by going to [http://localhost:8080/generate](http://localhost:8080/generate)

## Example
Following the guide found [here](https://docs.redpanda.com/docs/deployment/guide-rpk-container/). We will spin up a local Redpanda cluster to be used for testing the KStreams application.

Assuming you have Redpanda installed locally (if not refer to the [Redpanda installation page](https://redpanda.com/platform/)).
```shell
rpk container start
```
Using the broker addresses and ports provided when running the previous command, now create the topics `input` and `output` using RPK. (Note the ports used by the brokers may be different for each user)
```shell
rpk topic create input output --brokers 127.0.0.1:53875,127.0.0.1:53881,127.0.0.1:53880
```

Now can set the `BOOTSTRAP_SERVERS` and `SCHEMA_REGISTRY` using your container's broker addresses:
```shell
export BOOTSTRAP_SERVERS=127.0.0.1:53875,127.0.0.1:53881,127.0.0.1:53880
export SCHEMA_REGISTRY=http://127.0.0.1:8081
```
> Note that port `8081` might not be available for your Schema Registry and therefore your cluster will use another port. To know what port that is run `docker container port rp-node-0` and look for `8081/tcp -> 0.0.0.0:<schmea-registry-port>`. Use that provided port in your `SCHEMA_REGISTRY` environment variable.

Then run this application and generate sample messages by going to [http://localhost:8080/generate](http://localhost:8080/generate).

When done with this test run to spin down the container
```shell
rpk container stop
```

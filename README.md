
# Kafka demo App

### Environment

* jdk 1.8
* maven 3.5.2
* kafka_2.12-2.2.0

### Kafka commands

start zookeeper

```
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```

start 3 kafka brokers

```
bin\windows\kafka-server-start.bat config\server.properties
bin\windows\kafka-server-start.bat config\server-1.properties
bin\windows\kafka-server-start.bat config\server-2.properties
```

create a new topic my-test-topic

```
bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic my-test-topic
```

create a producer on my-test-topic

```
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic my-test-topic
```

create a consumer on my-test-topic

```
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic my-test-topic
```

create a new topic todo-activity-topic

```
bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 2 --topic todo-activity-topic
```


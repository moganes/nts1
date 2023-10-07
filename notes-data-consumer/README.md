Start Consumer
cd kafkaHome

Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

Start Kafka server
bin/kafka-server-start.sh config/server.properties

Create a topic
bin/kafka-topics.sh --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1 --topic scope.cmts
bin/kafka-topics.sh --zookeeper 127.0.0.1:2181 --describe --topic scope.cmts

Send some message
bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic scope.cmts

Consumer message by console consumer
bin/kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic scope.cmts --from-beginning



root@lenovo-y40-80:/home/alam/Alam/soft/kafka/kafka_2.11-2.1.0# bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic scope.cmts
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
Created topic "scope.cmts".



https://memorynotfound.com/spring-kafka-json-serializer-deserializer-example/

# Kafka + Spark Streaming + PySpark

## 1. Introduction
This guide outlines the steps to set up an environment for working with Kafka, Spark Streaming, and PySpark on a Linux environment. The setup involves starting Kafka, creating a PySpark application to consume and process events, and writing back the processed data to Kafka.

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/9998b3d7-c32c-478b-8054-d72924a6facc)


## 2. Design
1. Discuss the steps to perform to setup Apache Spark in a GCP environment.
2. Creating a PySpark app for consume and process the events and write back to Kafka.
4. Steps to produce and consume events using Kafka-Python.

## 3. Environment install

1. Create, Configure and Launch a Google Cloud Dataproc cluster.

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/74aa13a9-4fc5-482d-a1cf-494f18244a4d)

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/96804813-0a2f-4751-a4cf-f3b70ff4f386)

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/6fcb6f8a-97f5-4811-b8a0-5ce95a027ace)


2. Connecting to the Master Node using Secure Shell (ssh) 

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/22e81b96-b7f3-474c-9264-667086486e3c)

### Setup Kafka

Step 1. Downlaod kafka 

which is available at https://kafka.apache.org/downloads

```
$ wget https://downloads.apache.org/kafka/3.5.1/kafka_2.12-3.5.1.tgz
$ tar -xvf kafka_2.12-3.5.1.tgz
```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/8f30c9e6-b230-44c9-b1f4-34454202acb2)
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/74317103-fb37-4f37-86c4-794129fefb1b)

### Setup Spark
```
$ pip3 install msgpack
$ pip3 install kafka-python
```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/5e79cb26-6ec0-471f-a3bd-fda85f2dd383)

if pip3 command not found:(if it works, you don't need to do it again.)
```
$ sudo apt install python3-pip
$ wget https://repo1.maven.org/maven2/org/apache/spark/spark-streaming-kafka-0-8-assembly_2.11/2.3.2/spark-streaming-kafka-0-8-assembly_2.11-2.3.2.jar
```

## 4. Check kafka is working, use input_event topic as example

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/3e4f50a2-8469-402e-a882-533f19eaa5ae)

Step 1. Start Kafka Zookeeper (Keep this terminal open: terminal 1)
```
$ cd kafka_2.12-3.5.1/
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/2fa33588-c41e-4185-ac86-89ba0a4da306)

Step 2. Start Kafka broker(Keep this terminal open: terminal 2)
```
$ cd kafka_2.12-3.3.1/
$ bin/kafka-server-start.sh config/server.properties
```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/0e90e4bc-93a1-4481-ada6-023690f44919)

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/fc7583e3-1c3e-445d-bb54-a51555ba7958)
Step 3: Create one Kafka Topics (input_event)
```
$ cd kafka_2.12-3.3.1/
$ bin/kafka-topics.sh --create --topic input_event --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/bedb95d3-13e9-4a31-b964-e4b4d0615099)

Step 4: Create consumer.py, then run it (terminal 3)
```
$ vi consumer.py
$ python3 consumer.py
```

```consumber.py code
from kafka import KafkaConsumer

consumer = KafkaConsumer('input_event', bootstrap_servers=['localhost:9092'])
for msg in consumer:
    print(msg)
```

Step 5: Create producer.py, then run it(terminal 4)
```
$ vi producer.py
$ python3 producer.py
```

```producer.py code
from kafka import KafkaProducer
producer = KafkaProducer(bootstrap_servers='localhost:9092')
producer.send('input_event', b'(1, Main Menu), (2, Phone), (3, Smart Phone), (4, iPhone)')
producer.close()
```

Step 6: Result

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/7dd747a2-2f07-4587-8da4-b0cc99dd9a52)

## 5. Implement Kafka + Spark Streaming + PySpark

Step 1: In terminal 1(Start Kafka Zookeeper):
```
$ cd kafka_2.12-3.3.1/
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```

Step 2: In terminal 2(Start Kafka broker):
```
$ cd kafka_2.12-3.3.1/
$ bin/kafka-server-start.sh config/server.properties
```

Step 3: Create two Kafka Topics (input_event and output_event)
```
$ cd kafka_2.12-3.3.1/
$ bin/kafka-topics.sh --create --topic input_event --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
$ bin/kafka-topics.sh --create --topic output_event --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

Step 4: In terminal 3, create consumer.py, then run it:
```
$ vi consumer.py
$ python3 consumer.py
```
```consumber.py code
from kafka import KafkaConsumer

consumer = KafkaConsumer('output_event', bootstrap_servers=['localhost:9092'])
for msg in consumer:
    print(msg.value)
```

Step 5: In terminal 4, create producer.py, then run it:
```
$ vi producer.py
$ python3 producer.py
```
```producer.py code
from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers='localhost:9092', value_serializer=str.encode, key_serializer=str.encode)
event_stream_key = 'product_list'
event_stream_value = 'product1 product2 product3 product1'
producer.send('input_event', key = event_stream_key, value = event_stream_value)
```
![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/7dd747a2-2f07-4587-8da4-b0cc99dd9a52)

In terminal 5, running spark_processor.py

spark_processor.py will process events and write back to Kafka:
1.consume the message from input_event topic <br>
2.process the data <br>
3.push the message to output_event topic.


```
$ spark-submit --packages org.apache.spark:spark-streaming-kafka-0-10_2.12:3.1.3,org.apache.spark:spark-sql-kafka-0-10_2.12:3.1.3 --deploy-mode client spark_processor.py
```

Step 6: Result

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/eef130e6-100c-448f-b643-76ef3ab9b146)

![image](https://github.com/TejasriVaitla/Cloud-Computing/assets/128747986/9f8f25f0-53c0-4d2f-b497-5c126a0da38f)

## 6. Detailed Design Presentation
[Kafka + Spark streaming + pyspark](https://docs.google.com/presentation/d/1k0t4MrgixU1m9lCSff5dcmCzLQ28q2UG4ECxKoMgPGE/edit?usp=sharing)

package org.bamboo.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class BytesKafkaProducer {
    private static Properties props = new Properties();

    static {
        props.put("zookeeper.connect", "192.168.176.104:2191,192.168.176.201:2191,192.168.176.202:2191");
        props.put("serializer.class", "kafka.serializer.DefaultEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "192.168.176.104:9092,192.168.176.201:9092,192.168.176.202:9092");
        props.put("request.required.acks", "1");
    }

    private static Producer<String, byte[]> producer = new Producer<String, byte[]>(new ProducerConfig(props));

    public static void produce(String topic, String tmnlAddr, byte[] message) {
        producer.send(new KeyedMessage<String, byte[]>(topic, tmnlAddr, message));
    }
}

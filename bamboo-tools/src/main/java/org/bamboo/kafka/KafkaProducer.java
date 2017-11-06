package org.bamboo.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

public class KafkaProducer extends Thread {
	private static Properties props = new Properties();
	
	private static final String zookeeper = "192.168.176.104:2181,192.168.176.201:2181,192.168.176.202:2181";
	private static final String brokerList = "192.168.176.104:9092,192.168.176.201:9092,192.168.176.202:9092";
	static {
		props.put("zookeeper.connect", zookeeper);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("key.serializer.class", "kafka.serializer.StringEncoder");
		props.put("metadata.broker.list", brokerList);
		props.put("request.required.acks", "1");

	}

	// public static Producer<String, byte[]> producer = new Producer<String,
	// byte[]>(new ProducerConfig(props));
	// public static void produce(String topic, byte[] message){
	// producer.send(new KeyedMessage<String, byte[]>(topic, message));
	// }

	public static Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));

}
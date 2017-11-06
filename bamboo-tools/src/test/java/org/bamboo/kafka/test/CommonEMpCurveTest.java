package org.bamboo.kafka.test;

import java.io.InputStream;

import org.bamboo.kafka.KafkaProducer;
import org.bamboo.stream.InputStreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.producer.KeyedMessage;

/**
 * 电压、电流、有功功率、无功功率、功率因数(E_MP_CURVE)入库测试
 * 
 * @author wxz
 * 
 */
public class CommonEMpCurveTest {
	private static Logger log = LoggerFactory.getLogger(CommonEMpCurveTest.class);
	public static void main(String[] args) {
		InputStream in = CommonEMpCurveTest.class.getResourceAsStream("E_MP_CURVE.json");
		String encode = "utf-8";
		String data = InputStreamUtil.inToStr_ByteArray(in, encode);
		log.debug(data);
		
		KafkaProducer.producer.send(new KeyedMessage<String, String>("DataObject", data));
		
		log.debug("20171026测试通过！！！");
	}
}

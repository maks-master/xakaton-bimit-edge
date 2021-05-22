package ru.xakaton.bimit.mqtt.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class MqttToDBService {

	private final Logger logger = LoggerFactory.getLogger(MqttToDBService.class);

	public void mqttToDB(Message<?> message) {
		
		System.out.println(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC) + " : " + message.getPayload());
	}

}

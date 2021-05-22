package ru.xakaton.bimit.mqtt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.xakaton.bimit.service.MessageService;

@Service
public class MqttToDBService {
	public Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	MessageService messageService;
	
	ObjectMapper objectMapper = new ObjectMapper();

	public void mqttToDB(Message<?> message) {
		try {
			messageService.queueMesage(objectMapper.readValue(message.getPayload().toString(), ru.xakaton.bimit.model.Message.class));
		} catch (JsonMappingException e) {
			log.error("", e);
		} catch (JsonProcessingException e) {
			log.error("", e);
		}
		//System.out.println(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC) + " : " + message.getPayload());
	}

}

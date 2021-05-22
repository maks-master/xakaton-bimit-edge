package ru.xakaton.bimit.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ru.xakaton.bimit.model.Message;

@Service
public class MessageService {

	public static Map<String, UUID> devicesMap = new HashMap<String, UUID>();
	
	public void packMesage(Message message) {
		
		String sensorId = message.getSensorID();
		
		
		
		
		
		return;
	}

	
}

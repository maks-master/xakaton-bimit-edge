package ru.xakaton.bimit.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import ru.xakaton.bimit.model.Message;
import ru.xakaton.bimit.model.SimpleMessage;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageService {

	protected static Map<String, String> mapNames = new ConcurrentHashMap<String, String>();
	
	protected static Map<String, Queue<SimpleMessage>> queues = new ConcurrentHashMap<String, Queue<SimpleMessage>>();
	
	public static boolean packProcessStart = false;
	public static LongActionThread packThread;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private TaskExecutor taskExecutor;
	
	public void queueMesage(Message message) {
		String sensorId = message.getSensorID();
		String sensorName = message.getSensorName();
		mapNames.put(sensorId, sensorName);
		
		Queue<SimpleMessage> queue = queues.get(sensorId);
		if (queue==null) queue = new ConcurrentLinkedQueue<SimpleMessage>();
		
		queue.add(new SimpleMessage(message.getTs().getData().getValue(), message.getTs().getTs().getTime()));
		
		return;
	}
	

	@PostConstruct
	public void startService() {
		if (!packProcessStart) {
			packThread = applicationContext.getBean(PackThread.class);
			taskExecutor.execute(packThread);
			packProcessStart = true;
		}
	}
	

	
}

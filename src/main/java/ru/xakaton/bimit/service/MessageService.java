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

	
	private Map<String, Queue<SimpleMessage>> queues = new ConcurrentHashMap<String, Queue<SimpleMessage>>();
	

	public static boolean readerProcessStart = false;
	public static LongActionThread readerThread;

	public static boolean packProcessStart = false;
	public static LongActionThread packThread;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private TaskExecutor taskExecutor;
	
	public void queueMesage(Message message) {
		String sensorId = message.getSensorID();

		Queue<SimpleMessage> queue = queues.get(sensorId);
		if (queue==null) queue = new ConcurrentLinkedQueue<SimpleMessage>();
		
		queue.add(new SimpleMessage(message.getTd().getData().getValue(), message.getTd().getTs().getTime()));
		
		return;
	}
	
	

	@PostConstruct
	public void startService() {
		if (!readerProcessStart) {
			readerThread = applicationContext.getBean(ReaderThread.class);
			taskExecutor.execute(readerThread);
			readerProcessStart = true;
		}
		
		if (!packProcessStart) {
			packThread = applicationContext.getBean(PackThread.class);
			taskExecutor.execute(packThread);
			packProcessStart = true;
		}
	}
	

	
}

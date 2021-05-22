package ru.xakaton.bimit.service;


import java.sql.Timestamp;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.xakaton.bimit.device.model.Device;
import ru.xakaton.bimit.device.model.DeviceData;
import ru.xakaton.bimit.device.model.DeviceState;
import ru.xakaton.bimit.device.repository.DeviceDataRepository;
import ru.xakaton.bimit.device.repository.DeviceRepository;
import ru.xakaton.bimit.device.repository.DeviceStateRepository;
import ru.xakaton.bimit.model.SimpleMessage;


@Service
@Scope("prototype")
public class PackThread extends LongActionThread {
	public Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	protected static Map<UUID, Queue<Double>> alr = new ConcurrentHashMap<UUID, Queue<Double>>();
	protected static Map<UUID, Queue<Double>> sts = new ConcurrentHashMap<UUID, Queue<Double>>();
	
	public static Map<UUID, DeviceState> states = new ConcurrentHashMap<UUID, DeviceState>();
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	DeviceDataRepository deviceDataRepository;
	
	@Autowired
	DeviceStateRepository deviceStateRepository;
	
	@PostConstruct
	private void  initPackData() {
		deviceRepository.findAll().forEach(device -> {
			UUID deviceUuid = device.getUuid();
			
			DeviceState state = deviceStateRepository.findFirstByDeviceUuid(deviceUuid).orElseGet(()->{
				return deviceStateRepository.save(new DeviceState(deviceUuid));
			});
			
			states.put(deviceUuid, state);
		});
	}

	@Transactional
	public void initProcess() throws Exception {
		MessageService.queues.entrySet().parallelStream().forEach(entry -> {
			String sensorId = entry.getKey();
			Device device = deviceRepository.findBySensorId(sensorId).orElseGet(() -> {
					Device newDevice = deviceRepository.save(new Device(MessageService.mapNames.get(sensorId), sensorId));
					UUID deviceUuid = newDevice.getUuid();
					DeviceState state = new DeviceState(deviceUuid);
					states.put(deviceUuid, state);
					return newDevice;
				}
			);
			UUID deviceUuid = device.getUuid();
			
			Queue<SimpleMessage> queue = entry.getValue();
			SimpleMessage massage;
			DeviceData deviceData = null;
			int counter = 0;
			double doubleValue = 0.0;
        	double preDoubleValue = Double.MAX_VALUE;
			while ( queue.size() > 0) {
	            if ((massage = queue.poll()) != null) {
	            	try {
	            		String doubleVal = massage.getValue();
	            		if (doubleVal!=null && !doubleVal.equals("")) {
	            			doubleVal = doubleVal.replace(',', '.');
	            			
		            		doubleValue = Double.parseDouble(doubleVal);
		            		
		            		alertChech(doubleValue, device);
		            		
		            		if (preDoubleValue == doubleValue) {
		            			counter++;
		            		} else {
		            			if (counter != 0) {
		            				deviceData.setDeviceState(device.getDeviceState());
		            				deviceData.setCount(counter);
		            				deviceDataRepository.save(deviceData);
		            			}
		            			counter = 0;
		            		}
		            		if (counter == 0) {
		            			deviceData = new DeviceData(deviceUuid);
			            		deviceData.setTime(new Timestamp(massage.getTs()));
			            		deviceData.setData(doubleValue);
			            		counter++;
			            		preDoubleValue = doubleValue;
			            	}
		            		
		            		stateChech(doubleValue, device);
	            		}
	            	} catch (Exception e) {
	            		log.error("parse error", e);
	            	}
	            }
	        }
			
			if (deviceData != null) {
				deviceData.setDeviceState(device.getDeviceState());
				deviceData.setCount(counter);
				deviceDataRepository.save(deviceData);
			}
		});
	}
	
	public void stateChech(double doubleValue, Device device) {
		UUID deviceUuid = device.getUuid();
		//DeviceState state = states.get(deviceUuid);
	}

	
	
	public void alertChech(double doubleValue, Device device) {
		/*if (device.getMaxValue() != null || device.getMinValue()) {
			UUID deviceUuid = device.getUuid();
			if (doubleValue < device.getMaxValue() != null ) {
				
			}
				
		}*/
		
		
		
	}

	public void run() {
		long sleeptime = 0;
		try {
			boolean notEndProcess = true;
			while (notEndProcess) {
				Thread.sleep(sleeptime * 100L);
				try {

					initProcess();

					sleeptime = 1;

				} catch (InterruptedException inex) {
					// Restore the interrupted status
					Thread.currentThread().interrupt();
				} catch (Exception sst) {
					log.error("PackThread cicle thread error", sst);
				}
			}
		} catch (Exception e) {
			log.error("PackThread main thread error", e);
		}
	}

	public void interrupt() {
		Thread.currentThread().isInterrupted();
	}
}

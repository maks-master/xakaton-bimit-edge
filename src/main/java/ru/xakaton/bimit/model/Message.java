package ru.xakaton.bimit.model;

/**
 { 	"sensorName": "Датчик освещенности на 2-ром этаже, верхний угол правой стены",
 	"sensorID": "50c999f4-dfc5-4017-a87a-6567cb414ffe",
 	{"ts":1451649600512, "":{"lumen":"42.3", "battery-status":"70%"}}
}
 * */
public class Message {

	private String sensorName;
	private String sensorID;
	private MessageTimestamp td;
	
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public String getSensorID() {
		return sensorID;
	}
	public void setSensorID(String sensorID) {
		this.sensorID = sensorID;
	}
	public MessageTimestamp getTd() {
		return td;
	}
	public void setTd(MessageTimestamp td) {
		this.td = td;
	}
}

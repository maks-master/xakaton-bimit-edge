package ru.xakaton.bimit.model;

public class SimpleMessage {

	public SimpleMessage(String value, long ts) {
		super();
		this.value = value;
		this.ts = ts;
	}
	
	private String value;
	private long ts;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
	
}

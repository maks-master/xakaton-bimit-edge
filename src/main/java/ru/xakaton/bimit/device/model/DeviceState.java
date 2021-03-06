package ru.xakaton.bimit.device.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class DeviceState {

	@Id
	@GeneratedValue
	private UUID uuid;
	
	private UUID deviceUuid;
	private UUID alarmUuid;
	
	@DateTimeFormat()
	private java.sql.Timestamp time;
	
	private Double min;
	private Double max;
	private Double average;
	private Double mediana;
	
	@Transient
	public Set<DeviceData> values = new HashSet<DeviceData>();
	
	public DeviceState() {
		super();
	}

	public DeviceState(UUID deviceUuid) {
		super();
		this.deviceUuid = deviceUuid;
		this.time = new Timestamp(new Date().getTime());
	}
	
	public UUID getAlarmUuid() {
		return alarmUuid;
	}

	public void setAlarmUuid(UUID alarmUuid) {
		this.alarmUuid = alarmUuid;
	}

	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public UUID getDeviceUuid() {
		return deviceUuid;
	}
	public void setDeviceUuid(UUID deviceUuid) {
		this.deviceUuid = deviceUuid;
	}
	public java.sql.Timestamp getTime() {
		return time;
	}
	public void setTime(java.sql.Timestamp time) {
		this.time = time;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	public Double getMediana() {
		return mediana;
	}
	public void setMediana(Double mediana) {
		this.mediana = mediana;
	}
	
	
	
}

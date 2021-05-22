package ru.xakaton.bimit.device.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ru.xakaton.bimit.device.model.Device;

public interface DeviceRepository extends CrudRepository<Device, UUID>{

	Optional<Device> findBySensorId(String sensorId);
}

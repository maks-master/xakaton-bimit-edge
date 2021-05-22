package ru.xakaton.bimit.device.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ru.xakaton.bimit.device.model.Alarm;

public interface AlarmRepository extends CrudRepository<Alarm, UUID>{

}

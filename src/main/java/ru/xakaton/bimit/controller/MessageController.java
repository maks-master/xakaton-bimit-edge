package ru.xakaton.bimit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.xakaton.bimit.model.Message;
import ru.xakaton.bimit.service.MessageService;


@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@PostMapping("")
	@Transactional(readOnly = true)
	public void getMyProcess(@RequestBody Message message) {
		messageService.queueMesage(message);
	}
	
}

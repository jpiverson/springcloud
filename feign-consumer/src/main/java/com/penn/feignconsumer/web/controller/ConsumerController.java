package com.penn.feignconsumer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.penn.feignconsumer.pojo.User;
import com.penn.feignconsumer.service.HelloService;

@RestController
public class ConsumerController {

	@Autowired
	HelloService helloService;

	@RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
		return helloService.hello();
	}

	@RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
	public String helloConsumer2() {
		StringBuilder sb = new StringBuilder();
		sb.append(helloService.hello()).append("\n");
		sb.append(helloService.hello("Penn")).append("\n");
		sb.append(helloService.hello("Penn", 30)).append("\n");
		sb.append(helloService.hello(new User("Penn", 30))).append("\n");
		return sb.toString();
	}
}

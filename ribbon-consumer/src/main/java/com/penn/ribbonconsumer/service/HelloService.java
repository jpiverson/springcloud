package com.penn.ribbonconsumer.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "helloFallback")
	public String helloService() {
		long start = System.currentTimeMillis();
		String result = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
		long end = System.currentTimeMillis();
		logger.info("spend time:::" + (end - start));
		return result;
	}

	public String helloFallback() {

		// 1.在服务降级逻辑中，我们需要实现一个通用的的响应结果，并且该结果的处理逻辑应当是从缓存或是根据一些静态逻辑来获取，而不是依赖网络请求获取。
		// 2.如果一定要在降级服务中包含网络请求，那么该请求必须被包含在HystrixCommand或是HystrixObservableCommand中，从而形成联级的降级策略。
		// 3.最终的降级逻辑一定不是一个依赖网络请求的处理，而是一个能够稳定地返回结果的处理逻辑

		return "error";
	}
}

package com.fh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;

@RestController
public class OrderService {

	@Autowired
	private MemberFeign memberFeign;

	@RequestMapping("getOrder")
	@HystrixCommand(
			fallbackMethod = "fallback", 
			threadPoolProperties = { @HystrixProperty(name = HystrixPropertiesManager.CORE_SIZE, value = "2"),@HystrixProperty(name = HystrixPropertiesManager.MAX_QUEUE_SIZE, value = "2") },
			commandProperties = { @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "1000")} )
	public String getOrder() throws InterruptedException {

		// 超时
//		Thread.sleep(2000);
		// 报错
//		System.out.println(1/0);
		// 超出线程数量

		return "我是一个订单服务---------------" + memberFeign.getMember();
	}

	@RequestMapping("get")
	public String get() {

		return "get---------------";
	}

	public String fallback() {
		return "降级了";
	}
}

@FeignClient("fh-member")
interface MemberFeign {
	@RequestMapping("getMember")
	public String getMember();
}
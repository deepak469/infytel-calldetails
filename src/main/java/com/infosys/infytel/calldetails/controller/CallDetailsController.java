package com.infosys.infytel.calldetails.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@RequestMapping("/")
@RefreshScope
public class CallDetailsController {
	
	@Value("${calldetail.count}")
	private String calldetailCount;
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@RequestMapping("/calldetail")
	//@ResponseBody
	public String getCallsDetails() {
		//List<Long> friends = new RestTemplate().getForObject("http://localhost:8301/friendNumbers", List.class);
		
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("infytelplanMS");
		ServiceInstance service = serviceInstances.get(0);	
		URI planUri = service.getUri();
		List<Long> friends = new RestTemplate().getForObject(planUri+"/planNumbers", List.class);
	
		return "call details "+calldetailCount;
	}
	
//	@RequestMapping("/calldetail")
//	//@ResponseBody
//	public ResponseEntity<String> getCallsDetails() {
//		return new ResponseEntity<>("call details", HttpStatus.OK);
//	}

}

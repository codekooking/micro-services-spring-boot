package com.codekooking.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codekooking.microservices.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
    
    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfigurations() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
    
    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod="fallBackConfigurations")
    public LimitConfiguration retrieveConfigurations() {
        throw new RuntimeException("Not avaiable");
    }
    
    public LimitConfiguration fallBackConfigurations() {
    	return new LimitConfiguration(999, 111);
    }

}

package com.codekooking.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codekooking.microservices.currencyconversionservice.bean.CurrencyConversionBean;

@FeignClient(name="currency-exchange-service", url="localhost:8000")
public interface CurrencyConversionProxy {
    
    @GetMapping("/currency-exchange-from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(
            @PathVariable("from") String from, 
            @PathVariable("to") String to);

}

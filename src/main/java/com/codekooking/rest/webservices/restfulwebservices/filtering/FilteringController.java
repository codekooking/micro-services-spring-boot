package com.codekooking.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("Value 1", "Value 2", "Value 3");
	}
	
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue retrieveSomeDynamicBean() {
		SomeDynamicBean someBean = new SomeDynamicBean("Value 1", "Value 2", "Value 3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/filtering-list-dynamic")
	public MappingJacksonValue retrieveSomeDynamicBeanList() {
		
		List<SomeDynamicBean> list = Arrays.asList(new SomeDynamicBean("Value 1", "Value 2", "Value 3"), new SomeDynamicBean("Value 21", "Value 22", "Value 23"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		return mapping;
	}

}

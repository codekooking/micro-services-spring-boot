package com.codekooking.rest.webservices.restfulwebservices.versoning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersoningController {
	/*
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Code Cooking");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Code", "Cooking"));
	}
	
	@GetMapping(value="person/param", params="version=1")
	public PersonV1 personParamV1() {
		return new PersonV1("Code Cooking");
	}
	
	@GetMapping(value="person/param", params="version=2")
	public PersonV2 personParamV2() {
		return new PersonV2(new Name("Code", "Cooking"));
	}
	
	@GetMapping(value="person/header", headers="X-API-VERSION=1")
	public PersonV1 personHeaderV1() {
		return new PersonV1("Code Cooking");
	}
	
	@GetMapping(value="person/header", headers="X-API-VERSION=2")
	public PersonV2 personHeaderV2() {
		return new PersonV2(new Name("Code", "Cooking"));
	}
	
	@GetMapping(value="person/produce", produces="application/vnd.codecooking.api-v1+json")
	public PersonV1 personProduceV1() {
		return new PersonV1("Code Cooking");
	}
	
	@GetMapping(value="person/produce", produces="application/vnd.codecooking.api-v2+json")
	public PersonV2 personProduceV2() {
		return new PersonV2(new Name("Code", "Cooking"));
	}*/
}

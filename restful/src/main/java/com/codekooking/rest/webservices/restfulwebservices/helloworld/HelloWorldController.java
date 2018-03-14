package com.codekooking.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.codekooking.rest.webservices.restfulwebservices.helloworld.Greeting;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public Greeting helloWorldBean() {
        return new Greeting("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public Greeting helloWorldPathVaiable(@PathVariable String name) {
        return new Greeting(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }

}

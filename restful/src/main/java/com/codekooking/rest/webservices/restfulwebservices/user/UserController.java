package com.codekooking.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieUser(@PathVariable int id) {
        User user = service.findOne(id);
        
        if(user == null) 
            throw new UserNotFoundException("id-" + id);
        
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkToAllUsers = linkTo(methodOn(this.getClass()).retrieAllUsers());
        resource.add(linkToAllUsers.withRel("all-users"));
        
        ControllerLinkBuilder linkToDeleteUser = linkTo(methodOn(this.getClass()).deleteUser(id));
        resource.add(linkToDeleteUser.withRel("delete-user"));
        
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId()).toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/users/{id}")
    public Resource<User> deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        
        if(user == null) 
            throw new UserNotFoundException("id-" + id);
        
        return new Resource<User>(user);
    }
}

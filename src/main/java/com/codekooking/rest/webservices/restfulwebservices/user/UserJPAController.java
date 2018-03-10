package com.codekooking.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAController {

    @Autowired
    private UserDaoService service;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        
        if(!user.isPresent()) 
            throw new UserNotFoundException("id-" + id);
        
        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkToAllUsers = linkTo(methodOn(this.getClass()).retrieAllUsers());
        resource.add(linkToAllUsers.withRel("all-users"));
        
        ControllerLinkBuilder linkToDeleteUser = linkTo(methodOn(this.getClass()).deleteUser(id));
        resource.add(linkToDeleteUser.withRel("delete-user"));
        
        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId()).toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/jpa/users/{id}")
    public Resource<User> deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        
        if(user == null) 
            throw new UserNotFoundException("id-" + id);
        
        return new Resource<User>(user);
    }
}

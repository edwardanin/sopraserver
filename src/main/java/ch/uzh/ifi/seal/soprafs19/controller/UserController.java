package ch.uzh.ifi.seal.soprafs19.controller;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.service.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    Iterable<User> all() {
        return service.getUsers();
    }

    /*@PostMapping("/users/{userId}")
    User editUser(@RequestBody User user) {
        return this.service.editUser(user);
    }*/

    //When user logs out
    @PostMapping("/users/offline")
    User outUser(@RequestBody User user) {
        return this.service.outUser(user);
    }

    //For Logging in
    @PostMapping("/users/{username}")
    User logUser(@RequestBody User userCredentials) {
        return this.service.logUser(userCredentials);
    }

    //For Registration
    @PostMapping("/users")
    User createUser(@RequestBody User newUser) {
        return this.service.createUser(newUser);
    }
}

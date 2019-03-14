package ch.uzh.ifi.seal.soprafs19.controller;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    //For viewing all registered users
    @GetMapping()
    Iterable<User> all() {
        return service.getUsers();
    }

    //For updating username and birthdate
    @CrossOrigin
    @PutMapping(path = "/{userId}")
    User editUser(@PathVariable String userId, @RequestBody User user) {
        System.out.println("Start edit user..." + userId);
        System.out.println("Username: " + user.getUsername());
        return this.service.editUser(userId, user);
    }

    //When user logs out
    @CrossOrigin
    @PutMapping(path = "/offline/{username}")
    User outUser(@PathVariable String username) {
        System.out.println("Logging out user: " + username);
        return this.service.outUser(username);
    }

    //For Logging in
    @CrossOrigin
    @PutMapping(path = "/login/{username}")
    User logUser(@PathVariable String username, @RequestBody User userCredentials) {
        return this.service.logUser(username, userCredentials);
    }

    //For Registration
    @PostMapping()
    User createUser(@RequestBody User newUser) {
        return this.service.createUser(newUser);
    }
}

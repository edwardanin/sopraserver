package ch.uzh.ifi.seal.soprafs19.controller;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.repository.UserRepository;
import ch.uzh.ifi.seal.soprafs19.service.UserService;
import org.junit.Before;
import org.junit.Test;

//import static org.junit.Assert.*;

public class UserControllerTest {

    @Test
    public void all() {
    }

    @Test
    public void editUser() {
    }

    @Test
    public void outUser() {
    }

    @Test
    public void logUser() {
    }

    @Test
    public void createUser() {
        String username = "user1";
        String password = "user1";
        String createdDate = "2000-03-15";
        String birthdate = "2019-03-15";
        User testUser = new User();
        testUser.setUsername(username);
        testUser.setPassword(password);
        testUser.setCreationDate(createdDate);
        testUser.setBirthdate(birthdate);
    }
}
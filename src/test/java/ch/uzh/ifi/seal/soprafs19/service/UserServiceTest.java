package ch.uzh.ifi.seal.soprafs19.service;

import ch.uzh.ifi.seal.soprafs19.Application;
import ch.uzh.ifi.seal.soprafs19.constant.UserStatus;
import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test class for the UserResource REST resource.
 *
 * @see UserService
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class UserServiceTest {


    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void createUser() {
        Assert.assertNull(userRepository.findByUsername("testUsername"));

        User testUser = new User();
        testUser.setPassword("testName");
        testUser.setUsername("testUsername");

        User createdUser = userService.createUser(testUser);

        Assert.assertNotNull(createdUser.getToken());
        Assert.assertEquals(createdUser.getStatus(),UserStatus.OFFLINE);
        Assert.assertEquals(createdUser, userRepository.findByToken(createdUser.getToken()));
    }

    @Test
    public void getUsers() {
    }

    @Test
    public void outUser() {
        /*String username = "testUsername";

        User loggedUser = userService.outUser(username);
        Assert.assertEquals(loggedUser.getStatus(), UserStatus.OFFLINE);*/
    }

    @Test
    public void editUser() {
        /*String token = "sampletoken";
        String username = "testUsername";
        String birthdate = "2012-12-12";
        User testUser = new User();
        testUser.setUsername(username);
        testUser.setBirthdate(birthdate);

        User updatedUser = userService.editUser(token, testUser);*/
    }

    @Test
    public void logUser() {
        /*String username = "testUsername";
        String password = "password";
        User testUser = new User();
        testUser.setPassword(password);

        User loggedUser = userService.logUser(username, testUser);
        Assert.assertEquals(loggedUser.getUsername(), username);
        Assert.assertEquals(loggedUser.getPassword(), password);*/
    }
}

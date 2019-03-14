package ch.uzh.ifi.seal.soprafs19.service;

import ch.uzh.ifi.seal.soprafs19.constant.UserStatus;
import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.errorHandler.LoginPError;
import ch.uzh.ifi.seal.soprafs19.errorHandler.LoginUError;
import ch.uzh.ifi.seal.soprafs19.errorHandler.RegisterError;
import ch.uzh.ifi.seal.soprafs19.errorHandler.UpdateUsernameError;
import ch.uzh.ifi.seal.soprafs19.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Retrieve all registered users
    public Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    //Set status of user to OFFLINE when logging out
    public User outUser(String username) {
        User getUser = this.userRepository.findByUsername(username);
        System.out.println("Logout: " + getUser.getUsername());
        getUser.setStatus(UserStatus.OFFLINE);
        return getUser;
    }

    //Update username and birthday of logged in user
    public User editUser(String userId, User eUser) {
        System.out.println("Username from client: " + eUser.getUsername());
        System.out.println("Username from client: " + eUser.getUsername());
        User getUser = this.userRepository.findByToken(userId);
        User checkUser = this.userRepository.findByUsername(eUser.getUsername());
        //Check if username is used by a different user
        if (checkUser != null && !checkUser.getToken().equals(getUser.getToken())) {
            System.out.println("Username in server: " + checkUser.getUsername());
            System.out.println("Token/Id in server: " + checkUser.getToken() + " / " + checkUser.getId());
            System.out.println("Username '" + getUser.getUsername() + "' is already taken!");
            throw new UpdateUsernameError();
        } else if (checkUser == null || checkUser.getToken().equals(getUser.getToken())) {
            getUser.setUsername(eUser.getUsername());
            getUser.setBirthdate(eUser.getBirthdate());
            System.out.println("Id: " + eUser.getId());
            System.out.println("Username: " + eUser.getUsername());
            System.out.println("Birthdate: " + eUser.getBirthdate());
            System.out.println("Token: " + eUser.getToken());
        }
        return getUser;
    }

    //When user log in, check credentials then update status if successful
    public User logUser(String username, User userCredentials) {
        User checkUser = this.userRepository.findByUsername(username);
        //Check username if it exists in database
        if (checkUser == null) {
            throw new LoginUError();
        } else if (checkUser != null) {
            if (checkUser.getPassword().equals(userCredentials.getPassword())) {
                System.out.println("Successful login: " + checkUser.getPassword() + userCredentials.getPassword());
                System.out.println("Successful login: " + checkUser.getCreationDate());
                checkUser.setStatus(UserStatus.ONLINE);
            } else if (!checkUser.getPassword().equals(userCredentials.getPassword())) {
                System.out.println("Unsuccessful login: " + checkUser.getPassword() + userCredentials.getPassword());
                throw new LoginPError();
            }
        }
        return checkUser;
    }

    //Creates new user
    public User createUser(User newUser) {
        String creationDate;
        User checkUser = this.userRepository.findByUsername(newUser.getUsername());
        //Check username if it is in the database
        if (checkUser != null) {
            System.out.println("Not null: " + checkUser);
            throw new RegisterError();
        } else if (checkUser == null) {
            System.out.println("Null: " + checkUser);
            Calendar date = Calendar.getInstance();
            creationDate = date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DAY_OF_MONTH);
            newUser.setToken(UUID.randomUUID().toString());
            newUser.setStatus(UserStatus.OFFLINE);
            newUser.setCreationDate(creationDate);
            userRepository.save(newUser);
            log.debug("Created Information for User: {}", newUser);
        }
        return newUser;
    }
}
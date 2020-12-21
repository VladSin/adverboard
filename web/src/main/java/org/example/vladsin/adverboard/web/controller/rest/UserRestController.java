package org.example.vladsin.adverboard.web.controller.rest;

import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.service.repository.UserRepositoryService;
import org.example.vladsin.adverboard.web.controller.view.AdminController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    private final UserRepositoryService userRepositoryService;

    @Autowired
    public UserRestController(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addUser(@RequestBody String jsonString){
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();

        User user = new User();
        user.setUsername(jsonObject.getString("name"));
        user.setEmail(jsonObject.getString("email"));

        User newUser = userRepositoryService.saveUser(user);
        log.info("user created {} logged at {}", newUser.getUsername(), LocalDateTime.now());
        return newUser.getId();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userRepositoryService.getUser(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepositoryService.getUsers();

        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Long id,
            @RequestBody User newUser){
        User user = userRepositoryService.getUser(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        userRepositoryService.updateUser(user);
        log.info("user updated {} logged at {}", user.getUsername(), LocalDateTime.now());
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PatchMapping(value = "/name/{id}")
    public ResponseEntity<User> updateUserName(
            @PathVariable("id") Long id,
            @RequestBody String name) {
        User user = userRepositoryService.getUser(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        user.setUsername(name);
        userRepositoryService.updateUser(user);
        user = userRepositoryService.getUser(id);
        log.info("user updated {} logged at {}", user.getUsername(), LocalDateTime.now());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping(value = "/email/{id}")
    public ResponseEntity<User> updateUserEmail(
            @PathVariable("id") Long id,
            @RequestBody String email) {
        User user = userRepositoryService.getUser(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        user.setEmail(email);
        userRepositoryService.updateUser(user);
        log.info("user updated {} logged at {}", user.getUsername(), LocalDateTime.now());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable("id") Long id){
        userRepositoryService.deleteUser(id);
        log.info("user deleted {} logged at {}", id, LocalDateTime.now());
        return "OK";
    }
}

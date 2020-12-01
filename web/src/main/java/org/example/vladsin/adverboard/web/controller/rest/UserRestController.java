package org.example.vladsin.adverboard.web.controller.rest;

import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.service.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addUser(@RequestBody User user){
        User newUser = userService.saveUser(user);
        return newUser.getId();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();

        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Long id,
            @RequestBody User newUser){
        User user = userService.getUser(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        userService.updateUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> updateUserName(
            @PathVariable("id") Long id,
            @RequestBody String name) {
        User user = userService.getUser(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        user.setName(name);
        userService.updateUser(user);
        user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> updateUserEmail(
            @PathVariable("id") Long id,
            @RequestBody String email) {
        User user = userService.getUser(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        user.setEmail(email);
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "OK";
    }
}

package org.example.vladsin.adverboard.web.controller;

import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.controller.RegistrationUser;
import org.example.vladsin.adverboard.model.Role;
import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.service.repository.AuthUserService;
import org.example.vladsin.adverboard.service.repository.SecurityService;
import org.example.vladsin.adverboard.service.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register/")
public class RegistrationController {

    private final UserService userService;
    private final AuthUserService authUserService;
    private final SecurityService securityService;

    @Autowired
    public RegistrationController(UserService userService, AuthUserService authUserService, SecurityService securityService) {
        this.userService = userService;
        this.authUserService = authUserService;
        this.securityService = securityService;
    }

    @PostMapping
    public ResponseEntity<User> registrationUser(@RequestBody RegistrationUser user){
        if (securityService.checkUniqLogin(user.getUsername())){
            User newUser = new User(null, user.getUsername(), user.getEmail());
            newUser = userService.saveUser(newUser);
            AuthUser newAuth = new AuthUser(null, user.getUsername(), user.getPassword(), Role.USER, newUser.getId());
            authUserService.saveAuthUser(newAuth);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

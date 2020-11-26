package org.example.vladsin.adverboard.web.controller;

import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.RegistrationUser;
import org.example.vladsin.adverboard.model.Role;
import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.service.repository.AuthUserService;
import org.example.vladsin.adverboard.service.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;
    private final AuthUserService authUserService;
    @Autowired
    public RegistrationController(UserService userService, AuthUserService authUserService) {
        this.userService = userService;
        this.authUserService = authUserService;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Long registrationUser(@RequestBody RegistrationUser user){
        User newUser = new User(null, user.getName(), user.getEmail());
        newUser = userService.saveUser(newUser);
        AuthUser newAuth = new AuthUser(null, user.getName(), user.getPassword(), Role.User, newUser.getId());
        authUserService.saveAuthUser(newAuth);
        return newUser.getId();
    }
}

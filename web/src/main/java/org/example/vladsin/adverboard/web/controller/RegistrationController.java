package org.example.vladsin.adverboard.web.controller;

import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.Role;
import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.model.controller.RegistrationUser;
import org.example.vladsin.adverboard.service.repository.AuthUserRepositoryService;
import org.example.vladsin.adverboard.service.repository.SecurityRepositoryService;
import org.example.vladsin.adverboard.service.repository.UserRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private final UserRepositoryService userRepositoryService;
    private final AuthUserRepositoryService authUserRepositoryService;
    private final SecurityRepositoryService securityRepositoryService;

    @Autowired
    public RegistrationController(UserRepositoryService userRepositoryService, AuthUserRepositoryService authUserRepositoryService, SecurityRepositoryService securityRepositoryService) {
        this.userRepositoryService = userRepositoryService;
        this.authUserRepositoryService = authUserRepositoryService;
        this.securityRepositoryService = securityRepositoryService;
    }

    @PostMapping("/")
    public ResponseEntity<User> registrationUser(@RequestBody String jsonString, HttpServletRequest request) {
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();

        RegistrationUser user = new RegistrationUser();
        user.setUsername(jsonObject.getString("username"));
        user.setPassword(jsonObject.getString("password"));
        user.setEmail(jsonObject.getString("email"));

        if (securityRepositoryService.checkUniqLogin(user.getUsername())) {
            User newUser = new User(null, user.getUsername(), user.getEmail());
            newUser = userRepositoryService.saveUser(newUser);
            AuthUser newAuth = new AuthUser(null, user.getUsername(), user.getPassword(), Role.USER, newUser.getId());
            authUserRepositoryService.saveAuthUser(newAuth);

            log.info("user created {} logged at {}", newAuth.getLogin(), LocalDateTime.now());
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } else {
            request.setAttribute("error", "Username or email is invalid or already exists");
            log.info("BAD REQUEST logged at {}", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

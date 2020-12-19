package org.example.vladsin.adverboard.web.controller;

import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.model.controller.LoginUser;
import org.example.vladsin.adverboard.model.controller.UpdatePass;
import org.example.vladsin.adverboard.service.repository.SecurityRepositoryService;
import org.example.vladsin.adverboard.service.repository.UserRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final SecurityRepositoryService securityRepositoryService;
    private final UserRepositoryService userRepositoryService;


    @Autowired
    public LoginController(SecurityRepositoryService securityRepositoryService, UserRepositoryService userRepositoryService) {
        this.securityRepositoryService = securityRepositoryService;
        this.userRepositoryService = userRepositoryService;
    }

    @GetMapping("/")
    public  String doGet(){
        return "index";
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> login(@RequestBody String jsonString) {
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();

        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(jsonObject.getString("username"));
        loginUser.setPassword(jsonObject.getString("password"));

        AuthUser authUser = securityRepositoryService.login(loginUser.getUsername(), loginUser.getPassword());
        if (authUser == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(userRepositoryService.getUser(authUser.getUserId()), HttpStatus.OK);
    }

    // TODO прокастить userId (long) через стринг
    @PostMapping(value = "/update")
    public ResponseEntity<Long> updatePassword(@RequestBody UpdatePass updatePass) {

        securityRepositoryService.updatePassword(updatePass.getUserId(), updatePass.getPassword());
        return new ResponseEntity<>(updatePass.getUserId(), HttpStatus.OK);
    }
}

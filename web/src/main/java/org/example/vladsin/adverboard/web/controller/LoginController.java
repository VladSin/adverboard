package org.example.vladsin.adverboard.web.controller;

import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.LoginUser;
import org.example.vladsin.adverboard.model.UpdatePass;
import org.example.vladsin.adverboard.service.repository.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final SecurityService securityService;
    @Autowired
    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> login(@RequestBody LoginUser loginUser){
        AuthUser authUser = securityService.login(loginUser.getLogin(), loginUser.getPassword());

        if (authUser == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(authUser.getUserId(), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> updatePassword(@RequestBody UpdatePass updatePass){
       securityService.updatePassword(updatePass.getUserId(), updatePass.getPassword());

        return new ResponseEntity<>(updatePass.getUserId(), HttpStatus.OK);
    }
}

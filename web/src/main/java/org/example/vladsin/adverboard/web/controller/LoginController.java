package org.example.vladsin.adverboard.web.controller;

import org.example.vladsin.adverboard.service.SecurityService;
import org.example.vladsin.adverboard.service.repository.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final AuthUserService authUserService;

    public LoginController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }
}

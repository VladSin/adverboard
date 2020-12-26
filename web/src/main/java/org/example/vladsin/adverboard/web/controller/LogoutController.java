package org.example.vladsin.adverboard.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    private static final Logger log = LoggerFactory.getLogger(LogoutController.class);

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void doGet(HttpServletRequest request){
        SecurityContextHolder.clearContext();
        try{
            request.logout();
        } catch (ServletException e) {
            log.info("invalidate data at {}", LocalDateTime.now());
            throw new RuntimeException();
        }
    }

    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public void logoutWeb(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SecurityContextHolder.clearContext();
        try{
            request.logout();
        } catch (ServletException e) {
            log.info("invalidate data at {}", LocalDateTime.now());
            throw new RuntimeException();
        }
        response.sendRedirect("redirect:/adminAuth/login");
    }
}

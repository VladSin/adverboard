package org.example.vladsin.adverboard.web.controller.rest;

import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.service.repository.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserRestController {

    private final AuthUserService authUserService;

    @Autowired
    public AuthUserRestController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAuthUser(@RequestBody AuthUser authUser) {
        AuthUser newUser = authUserService.saveAuthUser(authUser);
        return newUser.getLogin();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthUser> getAuthUser(@PathVariable("id") Long id) {
        AuthUser authUser = authUserService.getAuthUser(id);

        if (authUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(authUser, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AuthUser> updateAuthUser(
            @PathVariable("id") Long id,
            @RequestBody AuthUser newUser) {
        AuthUser authUser = authUserService.getAuthUser(id);

        if (authUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        authUser.setLogin(newUser.getLogin());
        authUser.setPassword(newUser.getPassword());

        authUserService.updateAuthUser(authUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PatchMapping(value = "/login/{id}")
    public ResponseEntity<AuthUser> updateLogin(
            @PathVariable("id") Long id,
            @RequestBody String login) {
        AuthUser authUser = authUserService.getAuthUser(id);
        if (authUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        authUser.setLogin(login);
        authUserService.updateAuthUser(authUser);
        return new ResponseEntity<>(authUser, HttpStatus.OK);
    }

    @PatchMapping(value = "/pass/{id}")
    public ResponseEntity<AuthUser> updatePassword(
            @PathVariable("id") Long id,
            @RequestBody String password) {
        AuthUser authUser = authUserService.getAuthUser(id);
        if (authUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        authUser.setPassword(password);
        authUserService.updateAuthUser(authUser);
        return new ResponseEntity<>(authUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAuthUser(@PathVariable("id") Long id) {
        authUserService.deleteAuthUser(id);
        return "OK";
    }
}

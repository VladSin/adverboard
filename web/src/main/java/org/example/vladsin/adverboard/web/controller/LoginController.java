package org.example.vladsin.adverboard.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.Role;
import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.model.controller.LoginUser;
import org.example.vladsin.adverboard.model.controller.UpdatePass;
import org.example.vladsin.adverboard.service.repository.AuthUserRepositoryService;
import org.example.vladsin.adverboard.service.repository.SecurityRepositoryService;
import org.example.vladsin.adverboard.service.repository.UserRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final SecurityRepositoryService securityRepositoryService;
    private final UserRepositoryService userRepositoryService;
    private final AuthUserRepositoryService authUserRepositoryService;

    @Autowired
    public LoginController(SecurityRepositoryService securityRepositoryService, UserRepositoryService userRepositoryService, AuthUserRepositoryService authUserRepositoryService) {
        this.securityRepositoryService = securityRepositoryService;
        this.userRepositoryService = userRepositoryService;
        this.authUserRepositoryService = authUserRepositoryService;
    }

    @GetMapping("/")
    public  String doGet(HttpServletRequest request, HttpServletResponse response){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || "anonymousUser".equals(authentication.getPrincipal())){
            try {
                request.getRequestDispatcher("/error.html").forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "index";
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> login(@RequestBody String jsonString, HttpServletRequest request) {
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();

        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(jsonObject.getString("username"));
        loginUser.setPassword(jsonObject.getString("password"));

        AuthUser authUser = securityRepositoryService.login(loginUser.getUsername(), loginUser.getPassword());
        if (authUser == null){
            request.setAttribute("error", "Login or password invalid");
            log.info("authUser UNAUTHORIZED logged at {}", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities(authUser.getId()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("authUser {} logged at {}", authUser.getLogin(), LocalDateTime.now());
        return new ResponseEntity<>(userRepositoryService.getUser(authUser.getUserId()), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Long> updatePassword(@RequestBody String jsonString) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        UpdatePass updatePass = objectMapper.readValue(jsonString, new TypeReference<UpdatePass>(){});

        securityRepositoryService.updatePassword(updatePass.getUserId(), updatePass.getPassword());
        log.info("password updated for {} logged at {}", updatePass.getUserId(), LocalDateTime.now());
        return new ResponseEntity<>(updatePass.getUserId(), HttpStatus.OK);
    }


    private List<GrantedAuthority> getAuthorities(long id) {
        if(authUserRepositoryService.getAuthUser(id).getRole().equals(Role.USER)){
            return Arrays.asList(
                    (GrantedAuthority) () -> "ROLE_USER");
        } else {
            return Arrays.asList(
                    (GrantedAuthority) () -> "ROLE_ADMIN");
        }

    }
}

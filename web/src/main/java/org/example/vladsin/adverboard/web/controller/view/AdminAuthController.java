package org.example.vladsin.adverboard.web.controller.view;

import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.Role;
import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.model.controller.LoginUser;
import org.example.vladsin.adverboard.model.controller.RegistrationUser;
import org.example.vladsin.adverboard.service.repository.AuthUserRepositoryService;
import org.example.vladsin.adverboard.service.repository.SecurityRepositoryService;
import org.example.vladsin.adverboard.service.repository.UserRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/adminAuth")
public class AdminAuthController {

    private static final Logger log = LoggerFactory.getLogger(AdminAuthController.class);

    private final UserRepositoryService userRepositoryService;
    private final SecurityRepositoryService securityRepositoryService;
    private final AuthUserRepositoryService authUserRepositoryService;

    @Autowired
    public AdminAuthController(UserRepositoryService userRepositoryService, SecurityRepositoryService securityRepositoryService, AuthUserRepositoryService authUserRepositoryService) {
        this.userRepositoryService = userRepositoryService;
        this.securityRepositoryService = securityRepositoryService;
        this.authUserRepositoryService = authUserRepositoryService;
    }

    @GetMapping("/login")
    public  String doGetAdminLogin(){
        return "admin/login";
    }

    @GetMapping("/register")
    @Secured("ROLE_ADMIN")
    public  String doGetAdminRegister(){
        return "admin/register";
    }


    @PostMapping(value = "/login")
    public String loginAdmin(HttpServletRequest request) {

        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(request.getParameter("login"));
        loginUser.setPassword(request.getParameter("password"));

        AuthUser authUser = securityRepositoryService.login(loginUser.getUsername(), loginUser.getPassword());
        if (authUser == null){
            request.setAttribute("error", "login or password invalid");
            log.info("authUser UNAUTHORIZED logged at {}", LocalDateTime.now());
            return "admin/login";
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities(authUser.getId()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("authUser {} logged at {}", authUser.getLogin(), LocalDateTime.now());
        return "redirect:/admin/users";
    }

    @PostMapping("/register")
    @Secured("ROLE_ADMIN")
    public String registrationAdmin(HttpServletRequest request) {

        RegistrationUser user = new RegistrationUser();
        user.setUsername(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));

        if (securityRepositoryService.checkUniqLogin(user.getUsername())) {
            User newUser = new User(null, user.getUsername(), user.getEmail());
            newUser = userRepositoryService.saveUser(newUser);
            AuthUser newAuth = new AuthUser(null, user.getUsername(), user.getPassword(), Role.ADMIN, newUser.getId());
            authUserRepositoryService.saveAuthUser(newAuth);
            log.info("user created {} logged at {}", newAuth.getLogin(), LocalDateTime.now());
            return "admin/login";
        } else {
            log.info("BAD REQUEST logged at {}", LocalDateTime.now());
            return "admin/register";
        }
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

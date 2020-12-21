package org.example.vladsin.adverboard.web.controller.view;

import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.model.Role;
import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.model.controller.LoginUser;
import org.example.vladsin.adverboard.model.controller.RegistrationUser;
import org.example.vladsin.adverboard.service.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final UserRepositoryService userRepositoryService;
    private final SecurityRepositoryService securityRepositoryService;
    private final AuthUserRepositoryService authUserRepositoryService;
    private final BillboardRepositoryService billboardRepositoryService;
    private final LocationRepositoryService locationRepositoryService;
    private final GroupBillboardRepositoryService groupBillboardRepositoryService;

    public AdminController(UserRepositoryService userRepositoryService,
                           SecurityRepositoryService securityRepositoryService,
                           AuthUserRepositoryService authUserRepositoryService,
                           BillboardRepositoryService billboardRepositoryService,
                           LocationRepositoryService locationRepositoryService,
                           GroupBillboardRepositoryService groupBillboardRepositoryService) {
        this.userRepositoryService = userRepositoryService;
        this.securityRepositoryService = securityRepositoryService;
        this.authUserRepositoryService = authUserRepositoryService;
        this.billboardRepositoryService = billboardRepositoryService;
        this.locationRepositoryService = locationRepositoryService;
        this.groupBillboardRepositoryService = groupBillboardRepositoryService;
    }

    @GetMapping("/control")
    public  String adminControl(){
        return "admin/control";
    }

    @GetMapping("/login")
    public  String doGetAdminLogin(){
        return "admin/login";
    }

    @GetMapping("/register")
    public  String doGetAdminRegister(){
        return "admin/register";
    }

    @PostMapping("/register")
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
            return "admin/control";
        } else {
            return "admin/register";
        }
    }

    @PostMapping(value = "/login")
    public String loginAdmin(HttpServletRequest request) {

        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(request.getParameter("login"));
        loginUser.setPassword(request.getParameter("password"));

        AuthUser authUser = securityRepositoryService.login(loginUser.getUsername(), loginUser.getPassword());
        if (authUser == null){
            request.setAttribute("error", "login or password invalid");
            return "admin/login";
        }
        return "admin/control";
    }
}

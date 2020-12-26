package org.example.vladsin.adverboard.web.controller.view;

import org.example.vladsin.adverboard.model.*;
import org.example.vladsin.adverboard.model.controller.LoginUser;
import org.example.vladsin.adverboard.model.controller.RegistrationUser;
import org.example.vladsin.adverboard.service.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final UserRepositoryService userRepositoryService;
    private final SecurityRepositoryService securityRepositoryService;
    private final AuthUserRepositoryService authUserRepositoryService;
    private final BillboardRepositoryService billboardRepositoryService;
    private final AdRepositoryService adRepositoryService;

    public AdminController(UserRepositoryService userRepositoryService,
                           SecurityRepositoryService securityRepositoryService,
                           AuthUserRepositoryService authUserRepositoryService,
                           BillboardRepositoryService billboardRepositoryService,
                           AdRepositoryService adRepositoryService) {
        this.userRepositoryService = userRepositoryService;
        this.securityRepositoryService = securityRepositoryService;
        this.authUserRepositoryService = authUserRepositoryService;
        this.billboardRepositoryService = billboardRepositoryService;
        this.adRepositoryService = adRepositoryService;
    }

    @GetMapping("/control")
    @Secured("ROLE_ADMIN")
    public  String adminControl(){
        return "admin/control";
    }


    @GetMapping(value = "/users")
    @Secured("ROLE_ADMIN")
    public String getUsers(HttpServletRequest request){
        List<User> all = userRepositoryService.getUsers();
        List<User> users = new ArrayList<>();
        for (User u: all) {
            AuthUser authUser = authUserRepositoryService.getAuthUser(u.getId());
            if(!authUser.getRole().equals(Role.ADMIN)){
                users.add(u);
            }
        }
        request.setAttribute("users", users);
        return "admin/control";
    }

    @GetMapping(value = "/user/{id}")
    @Secured("ROLE_ADMIN")
    public String getMoreDetailByUser(@PathVariable("id") Long id, HttpServletRequest request){
        List<Billboard> billboards = billboardRepositoryService.getBillboardsByUserId(id);
        for (Billboard b: billboards) {
            List<Ad> ads = adRepositoryService.getAdByBillboardId(b.getId());
            b.setAds(ads);
        }
        request.setAttribute("billboards", billboards);
        return "admin/userDetail";
    }

    @GetMapping(value = "/delete/user/{id}")
    @Secured("ROLE_ADMIN")
    public String deleteUser(@PathVariable("id") Long id){
        User user = userRepositoryService.getUser(id);
        userRepositoryService.deleteUser(id);
        authUserRepositoryService.deleteAuthUser(user.getId());
        log.info("user deleted {} logged at {}", id, LocalDateTime.now());
        return "redirect:/admin/users";
    }



    @GetMapping(value = "/billboard/{id}")
    @Secured("ROLE_ADMIN")
    public String getAdsByBillboardId(@PathVariable("id") Long id, HttpServletRequest request){
        List<Ad> ads = adRepositoryService.getAdByBillboardId(id);
        request.setAttribute("ads", ads);
        request.setAttribute("id", id);
        return "admin/adDetail";
    }

    @GetMapping(value = "/verify/ad/{id}")
    @Secured("ROLE_ADMIN")
    public void verifyAd(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Ad ad = adRepositoryService.getAd(id);
        ad.setVerification("verified");
        adRepositoryService.updateAd(ad);
        log.info("ad verify {} logged at {}", id, LocalDateTime.now());
        response.sendRedirect("redirect:/admin/billboard/" + ad.getBillboardId());
    }

    @GetMapping(value = "/delete/ad/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteAd(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        Ad ad = adRepositoryService.getAd(id);
        adRepositoryService.deleteAd(id);
        log.info("ad deleted {} logged at {}", id, LocalDateTime.now());
        response.sendRedirect("redirect:/admin/billboard/" + ad.getBillboardId());
    }
}
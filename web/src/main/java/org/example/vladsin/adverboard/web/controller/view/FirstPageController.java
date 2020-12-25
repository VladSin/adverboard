package org.example.vladsin.adverboard.web.controller.view;

import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.service.repository.AdRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class FirstPageController {

    private final AdRepositoryService adRepositoryService;

    @Autowired
    public FirstPageController(AdRepositoryService adRepositoryService) {
        this.adRepositoryService = adRepositoryService;
    }

    @GetMapping("/")
    public  String doGet(HttpServletRequest request){
        List<Ad> ads = adRepositoryService.getAd();
        List<String> text = new ArrayList<>();
        text.add("Only the best!");
        text.add("Make your day more beautiful!");
        text.add("With love!");
        request.setAttribute("ads", ads);
        request.setAttribute("text", text);
        return "index";
    }
}
